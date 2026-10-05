from .renderer import navigation_html, portal_section_html


def generate_html(
    portals: list[dict],
    images: dict,
) -> str:
    navigation = navigation_html(portals)

    sections = "".join(portal_section_html(portal, images) for portal in portals)

    return f"""<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Parallel Worlds Portals</title>
<link rel="stylesheet" href="portals.css">
</head>
<body>
<main>
<header class="page-header">
<h1>Parallel Worlds Portals</h1>
<p>Portal designs used by Parallel Worlds.</p>
</header>
{navigation}
<div class="portals">
{sections}
</div>
</main>
</body>
</html>
"""
