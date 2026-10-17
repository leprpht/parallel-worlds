# Common compatibility model

This module contains the shared portal catalog, designs, dimension data, and
version metadata. Version adapters own the Minecraft/Fabric entrypoints,
registry/API calls, mixins, and any mapping-specific implementation.

`BiomeCatalog` owns the canonical 1.16.1 baseline and explicit additions for
newer Minecraft releases. `SupportedVersions` selects snapshots from that list;
the shared portal definitions and designs are never copied into version
adapters. Patch releases that do not change biome availability or the portal
implementation use the same line.
