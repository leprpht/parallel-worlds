# Minecraft 1.21.1 adapter

This adapter reuses the shared portal engine, definitions, designs, and
dimension resources. It owns the 1.21.1 Fabric entrypoint, command integration,
portal mixin, and tests. The shared catalog includes all biomes available
through 1.21.1, while excluding the later Pale Garden, Sulfur Caves, and
dappled forest.

Build it with:

```bash
./gradlew build -PversionLine=1.21.1
```
