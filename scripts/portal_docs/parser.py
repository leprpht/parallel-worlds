import re
from pathlib import Path

from .config import (
    BLOCK_EXPRESSION_MAP,
    FRAME_POSITION_TO_PROPERTY,
    PORTAL_INTERIOR_POSITIONS,
)


def load_portal_designs_source(source_file: Path) -> str:
    return source_file.read_text(encoding="utf-8")


def block_id_from_kotlin_expression(expression: str) -> str:
    expression = expression.strip()

    # PortalDesigns.kt uses the explicit resource id as the source of truth:
    # block("poppy", Blocks.AIR).  Keep the fallback argument opaque; it is
    # only needed by Minecraft at runtime, not by the documentation renderer.
    match = re.fullmatch(
        r'block\(\s*"([a-z0-9_.:/-]+)"(?:\s*,.*)?\)',
        expression,
        flags=re.DOTALL,
    )

    if match:
        return match.group(1)

    if expression in BLOCK_EXPRESSION_MAP:
        return BLOCK_EXPRESSION_MAP[expression]

    match = re.fullmatch(r"Blocks\.([A-Z0-9_]+)", expression)

    if match:
        return match.group(1).lower()

    raise ValueError(f"Unsupported block expression: {expression}")


def display_name(portal_id: str) -> str:
    return portal_id.replace("_", " ").title()


def parse_positions(body: str) -> dict[int, str]:
    positions = {position: "nether_portal" for position in PORTAL_INTERIOR_POSITIONS}

    for position, property_name in FRAME_POSITION_TO_PROPERTY.items():
        assignment = re.search(rf"\b{re.escape(property_name)}\s*=\s*", body)

        if not assignment:
            raise ValueError(f"Missing portal position '{property_name}'")

        start = assignment.end()
        depth = 0
        quote = None
        escaped = False
        end = start

        # Stop at the next top-level argument separator. This deliberately
        # permits commas inside block("id", fallback) expressions.
        while end < len(body):
            character = body[end]

            if quote:
                if escaped:
                    escaped = False
                elif character == "\\":
                    escaped = True
                elif character == quote:
                    quote = None
            elif character in ('"', "'"):
                quote = character
            elif character == "(":
                depth += 1
            elif character == ")":
                if depth == 0:
                    break
                depth -= 1
            elif depth == 0 and character in ",\n":
                break

            end += 1

        expression = body[start:end].strip()
        positions[position] = block_id_from_kotlin_expression(expression)

    return positions


def parse_portal_designs(source: str | Path) -> list[dict]:
    if isinstance(source, Path):
        source = load_portal_designs_source(source)

    pattern = re.compile(
        r"(?ms)"
        r"^\s*val\s+([A-Z0-9_]+)\s*=\s*"
        r"PortalDesign\(\s*"
        r"(.*?)"
        r"^\s*\)",
    )

    portals = []

    for match in pattern.finditer(source):
        portal_id = match.group(1)
        body = match.group(2)
        positions = parse_positions(body)

        blocks = []

        for position in range(1, 21):
            block_id = positions[position]

            if block_id not in blocks:
                blocks.append(block_id)

        portals.append(
            {
                "id": portal_id.lower(),
                "name": display_name(portal_id),
                "positions": positions,
                "blocks": blocks,
            }
        )

    if not portals:
        raise RuntimeError("No portal designs found in PortalDesigns.kt")

    return portals
