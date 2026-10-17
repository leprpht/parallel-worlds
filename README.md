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

- Minecraft `26.2` or `26.3`
- Fabric Loader `0.19.5` or newer
- Fabric Language Kotlin `1.14.1+kotlin.2.4.20` or newer
- Java 25

The released mod jar is built for Minecraft `26.3`. Install Fabric Loader, Fabric API, and Fabric Language Kotlin in the same Minecraft instance before installing Parallel Worlds.

Version-specific source lives under [`versions/`](versions/), with shared
compatibility metadata under [`common/`](common/). Run `./gradlew supportedVersionLines`
to print the maintained behavior/API boundaries from Minecraft `1.16.1` through
`26.3`. At runtime, the portal catalog can be smoke-tested for a line with
`-Dparallelworlds.version=1.18.2`; unavailable biome dimensions are then omitted
from portal lookup and return travel.

## Building from source

Use the included setup script to configure Java and the repository hooks, or run the Gradle wrapper directly:

```bash
./gradlew build
```

The release artifacts are written to `build/libs/`.

## Releases

Releases are created automatically when a SemVer tag such as `v1.0.0` is pushed. The tag must match `mod_version` in `gradle.properties`, and the matching version section in `CHANGELOG.md` becomes the GitHub release notes.

## Development hooks

The setup scripts configure `.githooks` as the repository hook directory. The `pre-push` hook checks release tags, the changelog format, and the Gradle version before allowing a tag push.
