import html

from .config import PORTAL_INTERIOR_POSITIONS, RENDER_ORDER


def escape(value: object) -> str:
    return html.escape(str(value), quote=True)


def portal_block_html(
    position: int,
    block_id: str,
    images: dict,
) -> str:
    image = images[block_id]
    image_url = escape(image["image_url"])
    alt = escape(image.get("alt", block_id))

    classes = ["portal-block"]

    if position in PORTAL_INTERIOR_POSITIONS:
        classes.append("portal-interior")

    return (
        f'<img class="{" ".join(classes)} position-{position}" '
        f'src="{image_url}" alt="{alt}" loading="lazy">'
    )


def portal_html(portal: dict, images: dict) -> str:
    rendered_blocks = []

    for position in RENDER_ORDER:
        block_id = portal["positions"][position]

        rendered_blocks.append(
            portal_block_html(
                position,
                block_id,
                images,
            )
        )

    return (
        '<div class="portal-preview">'
        '<div class="portal-frame">'
        f'{"".join(rendered_blocks)}'
        "</div>"
        "</div>"
    )


def blocks_used_html(portal: dict, images: dict) -> str:
    blocks = []

    for block_id in portal["blocks"]:
        if block_id == "nether_portal":
            continue

        image = images[block_id]
        image_url = escape(image["image_url"])
        alt = escape(image.get("alt", block_id))
        name = escape(image.get("name", block_id))

        blocks.append(
            '<div class="block-card">'
            f'<img src="{image_url}" alt="{alt}" loading="lazy">'
            f"<span>{name}</span>"
            "</div>"
        )

    return f'<div class="blocks-used">{"".join(blocks)}</div>'


def portal_section_html(
    portal: dict,
    images: dict,
) -> str:
    portal_id = escape(portal["id"])
    name = escape(portal["name"])

    return (
        f'<section class="portal-section" id="{portal_id}">'
        f"<h2>{name}</h2>"
        '<div class="portal-content">'
        f"{portal_html(portal, images)}"
        f"{blocks_used_html(portal, images)}"
        "</div>"
        "</section>"
    )


def navigation_html(portals: list[dict]) -> str:
    links = []

    for portal in portals:
        portal_id = escape(portal["id"])
        name = escape(portal["name"])

        links.append(f'<a href="#{portal_id}">{name}</a>')

    return '<nav class="portal-navigation">' f'{"".join(links)}' "</nav>"
