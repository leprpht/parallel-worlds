#!/usr/bin/env bash

set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$ROOT_DIR"

echo "=== Parallel Worlds development setup ==="
echo

# Git

if ! command -v git >/dev/null 2>&1; then
    echo "Git is not installed."

    if command -v brew >/dev/null 2>&1; then
        echo "Installing Git with Homebrew..."
        brew install git
    else
        echo "Error: Git is required."
        echo "Install Homebrew or Git, then run setup.sh again."
        exit 1
    fi
fi

echo "Git: $(git --version)"

if [ ! -d ".git" ]; then
    echo
    echo "Initializing Git repository..."
    git init
fi

# Java 25

JAVA_REQUIRED=25

java_is_25() {
    if ! command -v java >/dev/null 2>&1; then
        return 1
    fi

    local version
    version="$(
        java -version 2>&1 |
            awk -F '"' '/version/ {print $2}' |
            cut -d. -f1
    )"

    [ "$version" = "$JAVA_REQUIRED" ]
}

if ! java_is_25; then
    echo
    echo "Java 25 is required."

    if command -v brew >/dev/null 2>&1; then
        echo "Installing Java 25 with Homebrew..."

        if brew list --cask temurin@25 >/dev/null 2>&1; then
            echo "Temurin 25 is already installed."
        else
            brew install --cask temurin@25
        fi

        JAVA_HOME="$(/usr/libexec/java_home -v 25)"
        export JAVA_HOME
        export PATH="$JAVA_HOME/bin:$PATH"

    else
        echo "Error: Java 25 is required and Homebrew is not installed."
        echo "Install Homebrew, then run setup.sh again."
        exit 1
    fi
fi

if ! java_is_25; then
    echo
    echo "Error: Java 25 is still not available."
    echo "Make sure the Java 25 installation is visible to your shell."
    exit 1
fi

echo "Java:"
java -version

# Gradle wrapper

if [ ! -x "./gradlew" ]; then
    echo
    echo "Making Gradle wrapper executable..."
    chmod +x ./gradlew
fi

echo
echo "Gradle:"
./gradlew --version

# Git hooks

echo
echo "Configuring Git hooks..."

mkdir -p .githooks

git config core.hooksPath .githooks

if [ -f ".githooks/pre-commit" ]; then
    chmod +x .githooks/pre-commit
fi

if [ -f ".githooks/pre-push" ]; then
    chmod +x .githooks/pre-push
fi

if [ -d "scripts" ]; then
    chmod +x scripts/*.sh
fi

echo "Git hooks path: $(git config core.hooksPath)"

# Development dependencies

echo
echo "Downloading and configuring development dependencies..."

./gradlew spotlessApply

./gradlew classes

# Python

if ! command -v python3 >/dev/null 2>&1; then
    echo
    echo "Python 3 is required."

    if command -v brew >/dev/null 2>&1; then
        echo "Installing Python with Homebrew..."
        brew install python
    else
        echo "Error: Python 3 is required and Homebrew is not installed."
        echo "Install Homebrew or Python 3, then run setup.sh again."
        exit 1
    fi
fi

echo "Python: $(python3 --version)"

if [ ! -d ".venv" ]; then
    echo
    echo "Creating Python virtual environment..."
    python3 -m venv .venv
fi

echo
echo "Installing Python development tools..."

.venv/bin/python -m pip install --upgrade pip
.venv/bin/python -m pip install black

echo "Black:"
.venv/bin/python -m black --version

echo
echo "Running formatter check..."

./gradlew spotlessCheck

echo
echo "=== Development environment ready ==="
echo
echo "Java 25:       OK"
echo "Gradle:        OK"
echo "Git:           OK"
echo "Git hooks:     OK"
echo "Formatters:    OK"
echo "Fabric/Loom:   OK"
echo "Python:        OK"
echo "Black:         OK"
