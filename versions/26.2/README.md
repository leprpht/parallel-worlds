# Minecraft 26.2 adapter

This adapter reuses the shared portal engine, definitions, designs, and
dimension resources. It owns the 26.2 Fabric entrypoint, command integration,
portal mixin, and tests. The shared catalog filters out the 26.3-only dappled
forest while retaining 26.2's Sulfur Caves dimension.

Build it with:

```bash
./gradlew build -PversionLine=26.2
```
