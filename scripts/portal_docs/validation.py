import json
from pathlib import Path


def load_image_config(image_config_file: Path) -> dict:
    with image_config_file.open("r", encoding="utf-8") as file:
        config = json.load(file)

    if not isinstance(config, dict):
        raise RuntimeError("portal_images.json must contain a JSON object")

    images = config.get("images")

    if not isinstance(images, dict):
        raise RuntimeError("portal_images.json must contain an 'images' object")

    return images


def require_image(block_id: str, images: dict) -> dict:
    image = images.get(block_id)

    if not isinstance(image, dict):
        raise RuntimeError(f"Missing image configuration for block '{block_id}'")

    image_url = image.get("image_url")

    if not isinstance(image_url, str) or not image_url:
        raise RuntimeError(f"Missing image_url for block '{block_id}'")

    return image


def validate_portal_images(
    portals: list[dict],
    images: dict,
) -> None:
    used_blocks = set()

    for portal in portals:
        used_blocks.update(portal["blocks"])

    for block_id in sorted(used_blocks):
        require_image(block_id, images)
