# Parallel Worlds

Parallel Worlds is a Fabric mod for Minecraft that adds alternate world-generation variants based on familiar Overworld biomes. Build a supported portal design, light it with flint and steel, and travel to a parallel dimension generated from that biome.

## Features

- Parallel dimensions for many Overworld biomes, including forests, taigas, mountains, oceans, deserts, and more.
- Custom portal frame designs that select the destination biome.
- Return travel from a parallel dimension to the Overworld.
- Commands for checking the mod, inspecting the current location, and listing registered dimensions.

## Commands

```text
/parallelworlds
/parallelworlds info
/parallelworlds dimensions
```

## Requirements

Development setup requires Python 3.9 or newer. Python is not installed automatically by the setup script.

The repository builds separate jars for each maintained line: `1.16.1`,
`1.16.2`, `1.17.1`, `1.18.2`, `1.19.4`, `1.20.1`, `1.20.6`, `1.21.1`,
`1.21.4`, `26.1.2`, `26.2`, and `26.3`. Each jar declares its matching Fabric
Loader, Fabric API, and Fabric Language Kotlin versions in
[`versions/version-lines.properties`](versions/version-lines.properties).

Minecraft runtime requirements are Java 8 for 1.16.x, Java 16 for 1.17.1,
Java 17 for 1.18.2–1.20.1, Java 21 for 1.20.6–1.21.4, and Java 25 for 26.x.
The Gradle build uses a modern JDK with `--release` for older bytecode where
available; the runtime requirement does not change.

Version-specific source lives under [`versions/`](versions/), with shared
compatibility metadata under [`common/`](common/). Run `./gradlew supportedVersionLines`
to print the maintained behavior/API boundaries from Minecraft `1.16.1` through
`26.3`. At runtime, the portal catalog can be smoke-tested for a line with
`-Dparallelworlds.version=1.18.2`; unavailable biome dimensions are then omitted
from portal lookup and return travel.

## Building from source

Use the included Python setup script to configure Java and the repository hooks, or run the Gradle wrapper directly:

```bash
python3 setup.py
./gradlew build -PversionLine=1.18.2
```

Omitting `-PversionLine` builds the default `26.3` line. The artifact is
written to `build/libs/` with the version line in its filename.

The release artifacts are written to `build/libs/`.

## Releases

Releases are created automatically when a SemVer tag such as `v1.0.0` is pushed. The tag must match `mod_version` in `gradle.properties`, and the matching version section in `CHANGELOG.md` becomes the GitHub release notes.

## Development hooks

The setup script configures `.githooks` as the repository hook directory. The `pre-push` hook checks release tags, the changelog format, and the Gradle version before allowing a tag push.
