# Changelog

All notable changes to Parallel Worlds are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added

- Nothing yet.

## [2.0.1] - 2026-10-18

### Added

- Added the Python development setup script and documented Python as a prerequisite.
- Added optional in-game portal diagnostics with `-Dparallelworlds.debugChat=true`.

### Fixed

- Fixed documentation generation for resource-registered portal blocks.
- Fixed release artifact selection so stale jars are not included in releases.
- Updated the 1.20.1 Fabric Loader compatibility metadata.

## [2.0.0] - 2026-10-05

### Added

- Support of legacy versions of Minecraft.
- Separate build strategies for different Minecraft versions.
- Mod versions for Minecraft 1.16.1 - 26.3

## [1.1.0] - 2026-10-05

### Fixed

- Custom-dimension portals can now be recreated and used to return to the Overworld when the original return portal was destroyed.

## [1.0.1] - 2026-10-05

### Changed

- Changed Mushroom biome and Flowery Forest biome portal layout

## [1.0.0] - 2026-10-05

### Added

- Added alternate biome-based dimensions for exploring parallel versions of the Overworld.
- Added custom portal designs that select a destination based on the frame pattern.
- Added portal travel between the Overworld and each registered parallel dimension.
- Added `/parallelworlds`, `/parallelworlds info`, and `/parallelworlds dimensions` commands.
