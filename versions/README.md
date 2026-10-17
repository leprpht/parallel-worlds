# Version adapters

Each maintained behavior line has its own adapter directory:

| Adapter | Boundary                                |
| ------- | --------------------------------------- |
| `26.2`  | Sulfur Caves                            |
| `26.3`  | Dappled Forest and the 26.3 API changes |

The shared catalog and dimension JSONs live in `common/`; adapters should reuse
them rather than copy portal definitions. `common` contains every portal design
and definition, while `BiomeCatalog` exposes the biomes available at each
version boundary so older adapters never select newer dimensions.
The Gradle version matrix in
`version-lines.properties` supplies each line's Minecraft, Loader, Fabric API,
Java, and Loom requirements. The 26.2 and 26.3 adapters are fully wired;
older adapters are isolated here so their mappings and API changes do not leak
into common code.
