#!/usr/bin/env python3
"""Configure a Parallel Worlds development checkout."""

from pathlib import Path
import os
import shutil
import subprocess
import sys


ROOT = Path(__file__).resolve().parent


def run(*command: str) -> None:
    print(f"$ {' '.join(command)}")
    subprocess.run(command, cwd=ROOT, check=True)


def install_java() -> None:
    if sys.platform == "darwin" and shutil.which("brew"):
        installed = subprocess.run(
            ["brew", "list", "--cask", "temurin@25"],
            stdout=subprocess.DEVNULL,
            stderr=subprocess.DEVNULL,
            check=False,
        )
        if installed.returncode != 0:
            run("brew", "install", "--cask", "temurin@25")
        java_home = subprocess.run(
            ["/usr/libexec/java_home", "-v", "25"],
            capture_output=True,
            text=True,
            check=True,
        ).stdout.strip()
        os.environ["JAVA_HOME"] = java_home
        os.environ["PATH"] = f"{java_home}/bin{os.pathsep}{os.environ['PATH']}"
        return

    if sys.platform == "win32" and shutil.which("winget"):
        run("winget", "install", "--id", "EclipseAdoptium.Temurin.25.JDK", "-e", "--source", "winget")
        raise SystemExit("Java 25 was installed. Restart the terminal and run setup.py again.")

    raise SystemExit(
        "Java 25 is required. Install a Java 25 JDK, or install Homebrew (macOS) "
        "or winget (Windows), then run setup.py again."
    )


def main() -> None:
    print("=== Parallel Worlds development setup ===\n")

    if shutil.which("git") is None:
        raise SystemExit("Git is required. Install Git and run setup.py again.")

    print(f"Git: {subprocess.check_output(['git', '--version'], text=True).strip()}")

    if not (ROOT / ".git").exists():
        run("git", "init")

    if sys.version_info < (3, 9):
        raise SystemExit("Python 3.9 or newer is required.")
    print(f"Python: {sys.version.split()[0]}")

    java = shutil.which("java")
    java_output = ""
    if java is not None:
        java_version = subprocess.run(
            [java, "-version"], capture_output=True, text=True, check=False
        )
        java_output = java_version.stderr + java_version.stdout
    if 'version "25' not in java_output:
        install_java()
        java = shutil.which("java")
    print("Java 25: OK")

    gradle_wrapper = ROOT / ("gradlew.bat" if sys.platform == "win32" else "gradlew")
    if not gradle_wrapper.exists():
        raise SystemExit(f"Gradle wrapper not found: {gradle_wrapper.name}")
    if sys.platform != "win32":
        gradle_wrapper.chmod(gradle_wrapper.stat().st_mode | 0o111)
    run(str(gradle_wrapper), "--version")

    hooks = ROOT / ".githooks"
    hooks.mkdir(exist_ok=True)
    run("git", "config", "core.hooksPath", ".githooks")
    for hook in hooks.iterdir():
        if hook.is_file() and hook.name in {"pre-commit", "pre-push"} and sys.platform != "win32":
            hook.chmod(hook.stat().st_mode | 0o111)

    for script in (ROOT / "scripts").glob("*.sh"):
        if sys.platform != "win32":
            script.chmod(script.stat().st_mode | 0o111)

    run(str(gradle_wrapper), "spotlessApply")
    run(str(gradle_wrapper), "classes")
    run(str(gradle_wrapper), "spotlessCheck")

    venv_python = ROOT / ".venv" / ("Scripts/python.exe" if sys.platform == "win32" else "bin/python")
    if not venv_python.exists():
        run(sys.executable, "-m", "venv", ".venv")
    run(str(venv_python), "-m", "pip", "install", "--upgrade", "pip")
    run(str(venv_python), "-m", "pip", "install", "black")
    run(str(venv_python), "-m", "black", "--version")

    print("\n=== Development environment ready ===")
    print("Git:               OK")
    print("Python:            OK")
    print("Java 25:           OK")
    print("Gradle:            OK")
    print("Git hooks:         OK")
    print("Fabric/Loom:       OK")
    print("Python virtualenv: OK")
    print("Black:             OK")


if __name__ == "__main__":
    main()
