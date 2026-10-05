from pathlib import Path

SCRIPT_DIR = Path(__file__).resolve().parent
SCRIPTS_DIR = SCRIPT_DIR.parent
PROJECT_ROOT = SCRIPTS_DIR.parent

SOURCE_FILE = (
    PROJECT_ROOT
    / "src"
    / "main"
    / "kotlin"
    / "com"
    / "leprpht"
    / "parallelworlds"
    / "portal"
    / "PortalDesigns.kt"
)

IMAGE_CONFIG_FILE = SCRIPTS_DIR / "portal_images.json"

OUTPUT_DIR = PROJECT_ROOT / "docs" / "portals"
OUTPUT_FILE = OUTPUT_DIR / "index.html"
OUTPUT_CSS_FILE = OUTPUT_DIR / "portals.css"

FRAME_POSITION_TO_PROPERTY = {
    1: "topLeft",
    2: "topInnerLeft",
    3: "topInnerRight",
    4: "topRight",
    5: "upperLeft",
    6: "upperRight",
    7: "middleLeft",
    8: "middleRight",
    9: "lowerLeft",
    10: "lowerRight",
    11: "bottomLeft",
    12: "bottomInnerLeft",
    13: "bottomInnerRight",
    14: "bottomRight",
}

PORTAL_INTERIOR_POSITIONS = {
    15,
    16,
    17,
    18,
    19,
    20,
}

RENDER_COLUMNS = [
    [11, 9, 7, 5, 1],
    [12, 19, 17, 15, 2],
    [13, 20, 18, 16, 3],
    [14, 10, 8, 6, 4],
]

RENDER_ORDER = [position for column in RENDER_COLUMNS for position in column]

PORTAL_POSITIONS = set(FRAME_POSITION_TO_PROPERTY) | PORTAL_INTERIOR_POSITIONS

BLOCK_EXPRESSION_MAP = {
    "Blocks.DYED_TERRACOTTA.brown()": "brown_terracotta",
    "Blocks.DYED_TERRACOTTA.green()": "green_terracotta",
    "Blocks.DYED_TERRACOTTA.orange()": "orange_terracotta",
    "Blocks.DYED_TERRACOTTA.pink()": "pink_terracotta",
    "Blocks.DYED_TERRACOTTA.red()": "red_terracotta",
    "Blocks.DYED_TERRACOTTA.white()": "white_terracotta",
    "Blocks.DYED_TERRACOTTA.yellow()": "yellow_terracotta",
    "Blocks.GLAZED_TERRACOTTA.blue()": "blue_glazed_terracotta",
}
