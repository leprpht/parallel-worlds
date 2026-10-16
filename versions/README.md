# Version adapters

Each maintained behavior line has its own adapter directory:

| Adapter | Boundary |
| --- | --- |
| `1.16.1` | Classic Overworld biomes, pre-1.16.2 Fabric API |
| `1.16.2` | Classic Overworld biomes, post-refactor Fabric API through 1.16.5 |
| `1.17.1` | Cave biomes and 1.17 blocks |
| `1.18.2` | Renamed mountain biomes and highlands |
| `1.19.4` | Mangrove swamp and deep dark |
| `1.20.1` | 1.20 baseline |
| `1.20.6` | Component API |
| `1.21.1` | Portal/registry API baseline |
| `1.21.4` | Pale Garden |
| `26.1.2` | Calendar-versioned baseline and official mappings |
| `26.2` | Sulfur Caves |
| `26.3` | Dappled Forest and the 26.3 API changes |

The shared catalog and dimension JSONs live in `common/`; adapters should reuse
them rather than copy portal definitions. `common` contains every portal design
and definition, while `BiomeCatalog` exposes the biomes available at each
version boundary so older adapters never select newer dimensions.
The Gradle version matrix in
`version-lines.properties` supplies each line's Minecraft, Loader, Fabric API,
Java, and Loom requirements. The 26.2 and 26.3 adapters are fully wired;
older adapters are isolated here so their mappings and API changes do not leak
into common code.
