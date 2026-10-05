#!/usr/bin/env bash

set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
CHANGELOG="$ROOT_DIR/CHANGELOG.md"
GRADLE_PROPERTIES="$ROOT_DIR/gradle.properties"

fail() {
    echo "Release validation failed: $*" >&2
    exit 1
}

[[ -f "$CHANGELOG" ]] || fail "CHANGELOG.md is missing."
[[ -f "$GRADLE_PROPERTIES" ]] || fail "gradle.properties is missing."

version="${1:-}"
if [[ -z "$version" ]]; then
    version="$(sed -nE 's/^mod_version=([0-9]+\.[0-9]+\.[0-9]+)$/\1/p' "$GRADLE_PROPERTIES")"
fi

[[ "$version" =~ ^(0|[1-9][0-9]*)\.(0|[1-9][0-9]*)\.(0|[1-9][0-9]*)$ ]] || fail "version '$version' is not valid SemVer (expected MAJOR.MINOR.PATCH)."

gradle_version="$(sed -nE 's/^mod_version=([0-9]+\.[0-9]+\.[0-9]+)$/\1/p' "$GRADLE_PROPERTIES")"
[[ "$gradle_version" == "$version" ]] || fail "tag version $version does not match gradle.properties version $gradle_version."

first_line="$(sed -n '1p' "$CHANGELOG")"
second_line="$(sed -n '2p' "$CHANGELOG")"
[[ "$first_line" == "# Changelog" ]] || fail "the first line must be '# Changelog'."
[[ -z "$second_line" ]] || fail "the second line must be blank."

grep -Fxq "## [Unreleased]" "$CHANGELOG" || fail "CHANGELOG.md must contain an '## [Unreleased]' section."

grep -Eq "^## \[$version\] - [0-9]{4}-[0-9]{2}-[0-9]{2}$" "$CHANGELOG" || fail "CHANGELOG.md must contain '## [$version] - YYYY-MM-DD'."

awk -v version="$version" '
    $0 ~ "^## \\[" version "\\] - " { found = 1; next }
    found && /^## \[/ { exit }
    found { print }
    END { if (!found) exit 1 }
' "$CHANGELOG" | grep -Eq '^### (Added|Changed|Deprecated|Removed|Fixed|Security)$' || fail "the $version section must contain at least one standard category."

awk '
    /^## \[/ { section++ }
    section > 0 && /^### / && $0 !~ /^### (Added|Changed|Deprecated|Removed|Fixed|Security)$/ { exit 1 }
' "$CHANGELOG" || fail "CHANGELOG.md contains a non-standard category."

echo "Release validation passed for version $version."
