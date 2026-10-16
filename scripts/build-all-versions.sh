#!/usr/bin/env bash

set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
VERSION_MATRIX="$ROOT_DIR/versions/version-lines.properties"

[[ -f "$VERSION_MATRIX" ]] || {
    echo "Version matrix not found: $VERSION_MATRIX" >&2
    exit 1
}

mapfile -t version_lines < <(
    sed -nE 's/^([^#][^=]*)\.minecraft=.*/\1/p' "$VERSION_MATRIX" |
        awk '!seen[$0]++'
)

[[ "${#version_lines[@]}" -gt 0 ]] || {
    echo "No version lines found in $VERSION_MATRIX" >&2
    exit 1
}

cd "$ROOT_DIR"

for version_line in "${version_lines[@]}"; do
    echo "Building Minecraft $version_line..."
    ./gradlew build "-PversionLine=$version_line"
done

echo
echo "Built release artifacts:"
find build/libs -maxdepth 1 -type f -name 'parallelworlds-*.jar' -print | sort
