# Minecraft 26.1.2 adapter

This adapter reuses the shared portal engine, definitions, designs, and
dimension resources. It owns the 26.1.2 Fabric entrypoint, command integration,
portal mixin, and tests. The shared catalog includes the biomes available
through 26.1.2 and excludes the later Sulfur Caves and dappled forest.

Build it with:

```bash
./gradlew build -PversionLine=26.1.2
```
