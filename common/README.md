# Common compatibility model

This module contains the shared portal catalog, designs, dimension data, and
version metadata. Version adapters own the Minecraft/Fabric entrypoints,
registry/API calls, mixins, and any mapping-specific implementation.

`SupportedVersions` is grouped by behavior/API boundary. Patch releases that do
not change biome availability or the portal implementation use the same line.
