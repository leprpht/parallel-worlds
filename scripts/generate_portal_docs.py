from pathlib import Path

from portal_docs.config import (
    IMAGE_CONFIG_FILE,
    OUTPUT_CSS_FILE,
    OUTPUT_FILE,
    SOURCE_FILE,
)
from portal_docs.css import PORTAL_CSS
from portal_docs.html import generate_html
from portal_docs.parser import parse_portal_designs
from portal_docs.validation import load_image_config, validate_portal_images


def write_if_changed(path: Path, content: str) -> None:
    if path.exists() and path.read_text(encoding="utf-8") == content:
        return

    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(content, encoding="utf-8")


def main() -> None:
    images = load_image_config(IMAGE_CONFIG_FILE)
    portals = parse_portal_designs(SOURCE_FILE)

    validate_portal_images(portals, images)

    write_if_changed(
        OUTPUT_FILE,
        generate_html(portals, images),
    )

    write_if_changed(
        OUTPUT_CSS_FILE,
        PORTAL_CSS,
    )


if __name__ == "__main__":
    main()
