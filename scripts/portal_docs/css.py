PORTAL_CSS = """
:root {
    --background: #181818;
    --text: #eeeeee;
    --muted: #aaaaaa;
    --border: #333333;
    --cube-size: 120px;
    --horizontal-step: 53.5px;
    --vertical-step: 64px;
    --column-offset: 26.75px;
    --portal-offset-x: -120px;
}

* {
    box-sizing: border-box;
}

html {
    scroll-behavior: smooth;
}

body {
    margin: 0;
    background: var(--background);
    color: var(--text);
    font-family: Arial, Helvetica, sans-serif;
}

main {
    width: min(1400px, calc(100% - 40px));
    margin: 0 auto;
    padding: 40px 0 80px;
}

.page-header {
    margin-bottom: 32px;
}

.page-header h1 {
    margin: 0 0 10px;
    font-size: 36px;
}

.page-header p {
    margin: 0;
    color: var(--muted);
}

.portal-navigation {
    display: flex;
    flex-wrap: wrap;
    gap: 8px;
    margin-bottom: 48px;
}

.portal-navigation a {
    display: inline-block;
    padding: 8px 12px;
    border: 1px solid var(--border);
    border-radius: 4px;
    color: var(--text);
    text-decoration: none;
}

.portal-navigation a:hover {
    border-color: var(--text);
}

.portal-section {
    margin-bottom: 80px;
    padding-top: 20px;
    border-top: 1px solid var(--border);
}

.portal-section h2 {
    margin: 0 0 32px;
    font-size: 28px;
}

.portal-content {
    display: grid;
    grid-template-columns: minmax(520px, 1fr) minmax(240px, 320px);
    gap: 48px;
    align-items: start;
}

.portal-preview {
    position: relative;
    min-height: 570px;
    overflow: hidden;
}

.portal-frame {
    position: relative;
    width: 100%;
    max-width: 500px;
    height: 540px;
    margin: 0 auto;
}

.portal-block {
    position: absolute;
    width: var(--cube-size);
    height: var(--cube-size);
    object-fit: contain;
    image-rendering: pixelated;
}

.position-1 {
    left: 0;
    top: 0;
}

.position-2 {
    left: var(--horizontal-step);
    top: var(--column-offset);
}

.position-3 {
    left: calc(2 * var(--horizontal-step));
    top: calc(2 * var(--column-offset));
}

.position-4 {
    left: calc(3 * var(--horizontal-step));
    top: calc(3 * var(--column-offset));
}

.position-5 {
    left: 0;
    top: var(--vertical-step);
}

.position-6 {
    left: calc(3 * var(--horizontal-step));
    top: calc(var(--vertical-step) + 3 * var(--column-offset));
}

.position-7 {
    left: 0;
    top: calc(2 * var(--vertical-step));
}

.position-8 {
    left: calc(3 * var(--horizontal-step));
    top: calc(2 * var(--vertical-step) + 3 * var(--column-offset));
}

.position-9 {
    left: 0;
    top: calc(3 * var(--vertical-step));
}

.position-10 {
    left: calc(3 * var(--horizontal-step));
    top: calc(3 * var(--vertical-step) + 3 * var(--column-offset));
}

.position-11 {
    left: 0;
    top: calc(4 * var(--vertical-step));
}

.position-12 {
    left: var(--horizontal-step);
    top: calc(4 * var(--vertical-step) + var(--column-offset));
}

.position-13 {
    left: calc(2 * var(--horizontal-step));
    top: calc(4 * var(--vertical-step) + 2 * var(--column-offset));
}

.position-14 {
    left: calc(3 * var(--horizontal-step));
    top: calc(4 * var(--vertical-step) + 3 * var(--column-offset));
}

.position-15 {
    left: var(--horizontal-step);
    top: calc(250px - 3 * var(--vertical-step) + var(--column-offset));
}

.position-16 {
    left: calc(2 * var(--horizontal-step));
    top: calc(250px - 3 * var(--vertical-step) + 2 * var(--column-offset));
}

.position-17 {
    left: var(--horizontal-step);
    top: calc(250px - 2 * var(--vertical-step) + var(--column-offset));
}

.position-18 {
    left: calc(2 * var(--horizontal-step));
    top: calc(250px - 2 * var(--vertical-step) + 2 * var(--column-offset));
}

.position-19 {
    left: var(--horizontal-step);
    top: calc(250px - var(--vertical-step) + var(--column-offset));
}

.position-20 {
    left: calc(2 * var(--horizontal-step));
    top: calc(250px - var(--vertical-step) + 2 * var(--column-offset));
}

.blocks-used {
    display: grid;
    grid-template-columns: repeat(2, minmax(100px, 1fr));
    gap: 12px;
}

.block-card {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 8px;
    padding: 12px;
    border: 1px solid var(--border);
    border-radius: 4px;
    text-align: center;
}

.block-card img {
    width: 72px;
    height: 72px;
    object-fit: contain;
    image-rendering: pixelated;
}

.block-card span {
    color: var(--muted);
    font-size: 13px;
}

@media (max-width: 900px) {
    .portal-content {
        grid-template-columns: 1fr;
    }

    .portal-preview {
        overflow-x: auto;
    }

    .blocks-used {
        max-width: 500px;
    }
}

@media (max-width: 600px) {
    main {
        width: min(100% - 24px, 1400px);
        padding-top: 24px;
    }

    .page-header h1 {
        font-size: 28px;
    }

    .portal-frame {
        transform-origin: top left;
        transform: scale(0.8);
        width: 400px;
    }

    .portal-preview {
        min-height: 460px;
    }
}
""".strip()
