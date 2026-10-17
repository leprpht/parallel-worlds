# Minecraft 1.16.1 adapter

This adapter keeps the shared portal catalog and dimension resources in
`common/`, while its entrypoint, portal runtime, and mixin target the API
surface of Minecraft 1.16.1.

Build it with:

```bash
./gradlew build -PversionLine=1.16.1
```

