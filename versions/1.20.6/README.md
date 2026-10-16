# Minecraft 1.20.6 adapter

This adapter reuses the shared portal engine, definitions, designs, and
dimension resources. It owns the 1.20.6 Fabric entrypoint, command integration,
portal mixin, and tests. The shared catalog includes Cherry Grove and all
biomes available through 1.20.6, while excluding the later Pale Garden,
Sulfur Caves, and dappled forest.

Build it with:

```bash
./gradlew build -PversionLine=1.20.6
```
