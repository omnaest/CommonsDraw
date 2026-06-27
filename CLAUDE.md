# CommonsDraw

2D drawing abstraction layer (`org.omnaest.utils.draw`). 16 classes providing a renderer-agnostic canvas/drawer API — no dependency on AWT, SVG, or any output format directly. Other projects use this as a drawing primitive and render to their own target format.

## Build

```cmd
mvn clean install
mvn test -Dtest=MyTestClass#myMethod
```

## Architecture

Static facade `DrawerUtils` creates a `DrawerBuilder`. The `Drawer` interface exposes drawing operations; `DrawProcessor` implementations handle actual rendering. Domain types (`Point`, `Color`, `Vector`, `Distance`, `CanvasSize`) are plain value objects. No Lombok, no framework deps.

| Package | What lives here |
|---|---|
| `org.omnaest.utils.draw` | `DrawerUtils` facade, `DrawerBuilder` |
| `draw.domain` | `Drawer`, `DrawProcessor`, `Color`, `Point`, `Vector`, `CanvasSize`, `Distance` value types |
| `draw.internal` | `DrawProcessorManager`, `PointTranslator`, `VectorUtils` implementations |

## Key classes

- **`DrawerUtils`** — entry point; creates a `DrawerBuilder` configured with canvas size and stroke/fill defaults
- **`Drawer`** — fluent drawing API: polylines, shapes, colors, stroke width
- **`DrawProcessor`** — SPI interface implemented by renderers in dependent projects

## Dependencies (compile scope)

None beyond JDK — intentionally zero compile-scope library deps to stay embeddable.

Test scope: `CommonsTest`, `CommonsLog`.
