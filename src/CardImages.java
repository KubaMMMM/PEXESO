import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

public class CardImages {

    private static final int SIZE = 80;

    private enum Symbol { HEART, STAR, MOON, SUN, DIAMOND, CLUB, FLOWER, LIGHTNING,
        CIRCLE, TRIANGLE, SQUARE, CROSS, ARROW, CROWN, LEAF,
        FISH, BELL, KEY }

    private static final Color[] COLORS = {
            new Color(220, 50,  50),
            new Color(50,  150, 220),
            new Color(50,  180, 80),
            new Color(230, 160, 30),
            new Color(160, 60,  200),
            new Color(220, 100, 30),
            new Color(30,  180, 180),
            new Color(200, 50,  150),
            new Color(100, 100, 200),
            new Color(80,  160, 80),
            new Color(200, 80,  80),
            new Color(60,  120, 200),
            new Color(180, 140, 30),
            new Color(140, 60,  160),
            new Color(30,  160, 140),
            new Color(200, 120, 60),
            new Color(160, 160, 50),
            new Color(100, 60,  160),
    };

    public static Image getImage(int id) {
        Symbol symbol = Symbol.values()[id % Symbol.values().length];
        Color color   = COLORS[id % COLORS.length];

        BufferedImage img = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g.setColor(Color.WHITE);
        g.fillRoundRect(0, 0, SIZE, SIZE, 12, 12);
        g.setColor(color);
        g.setStroke(new BasicStroke(3));
        g.drawRoundRect(1, 1, SIZE-3, SIZE-3, 12, 12);

        g.setColor(color);
        drawSymbol(g, symbol, SIZE/2, SIZE/2, 26);

        g.dispose();
        return img;
    }

    private static void drawSymbol(Graphics2D g, Symbol s, int cx, int cy, int r) {
        switch (s) {
            case HEART     -> drawHeart(g, cx, cy, r);
            case STAR      -> drawStar(g, cx, cy, r, 5);
            case MOON      -> drawMoon(g, cx, cy, r);
            case SUN       -> drawSun(g, cx, cy, r);
            case DIAMOND   -> drawDiamond(g, cx, cy, r);
            case CLUB      -> drawClub(g, cx, cy, r);
            case FLOWER    -> drawFlower(g, cx, cy, r);
            case LIGHTNING -> drawLightning(g, cx, cy, r);
            case CIRCLE    -> g.fillOval(cx-r, cy-r, 2*r, 2*r);
            case TRIANGLE  -> drawPolygon(g, cx, cy, r, 3, -Math.PI/2);
            case SQUARE    -> g.fillRoundRect(cx-r, cy-r, 2*r, 2*r, 6, 6);
            case CROSS     -> drawCross(g, cx, cy, r);
            case ARROW     -> drawArrow(g, cx, cy, r);
            case CROWN     -> drawCrown(g, cx, cy, r);
            case LEAF      -> drawLeaf(g, cx, cy, r);
            case FISH      -> drawFish(g, cx, cy, r);
            case BELL      -> drawBell(g, cx, cy, r);
            case KEY       -> drawKey(g, cx, cy, r);
        }
    }

    // ── Srdce ──────────────────────────────────────────────────────────────
    private static void drawHeart(Graphics2D g, int cx, int cy, int r) {
        int cr = r / 2;                        // poloměr každého kruhu

        Area heart = new Area(new Ellipse2D.Double(cx - r, cy - r, r, r));  // levý kruh
        heart.add (new Area(new Ellipse2D.Double(cx,       cy - r, r, r)));  // pravý kruh

        // trojúhelník zakryje mezeru mezi kruhy a vytvoří špičku přesně uprostřed
        GeneralPath tri = new GeneralPath();
        tri.moveTo(cx - r, cy - cr);           // levý bod
        tri.lineTo(cx + r, cy - cr);           // pravý bod
        tri.lineTo(cx,     cy + r);            // špička dole – přesně cx = střed
        tri.closePath();
        heart.add(new Area(tri));

        g.fill(heart);
    }

    // ── Hvězda ─────────────────────────────────────────────────────────────
    private static void drawStar(Graphics2D g, int cx, int cy, int r, int points) {
        int[] xs = new int[points * 2], ys = new int[points * 2];
        for (int i = 0; i < points * 2; i++) {
            double angle = Math.PI / points * i - Math.PI / 2;
            int rad = (i % 2 == 0) ? r : r / 2;
            xs[i] = (int)(cx + rad * Math.cos(angle));
            ys[i] = (int)(cy + rad * Math.sin(angle));
        }
        g.fillPolygon(xs, ys, points * 2);
    }

    // ── Měsíc ──────────────────────────────────────────────────────────────
    private static void drawMoon(Graphics2D g, int cx, int cy, int r) {
        Area moon = new Area(new Ellipse2D.Double(cx - r, cy - r, 2*r, 2*r));
        moon.subtract(new Area(new Ellipse2D.Double(cx - r*0.3, cy - r, r*1.8, r*2)));
        g.fill(moon);
    }

    // ── Slunce ─────────────────────────────────────────────────────────────
    private static void drawSun(Graphics2D g, int cx, int cy, int r) {
        int core = r / 2;
        g.fillOval(cx - core, cy - core, core*2, core*2);
        g.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        for (int i = 0; i < 8; i++) {
            double a = Math.PI / 4 * i;
            int x1 = (int)(cx + (core + 4) * Math.cos(a));
            int y1 = (int)(cy + (core + 4) * Math.sin(a));
            int x2 = (int)(cx + r * Math.cos(a));
            int y2 = (int)(cy + r * Math.sin(a));
            g.drawLine(x1, y1, x2, y2);
        }
    }

    // ── Diamant ────────────────────────────────────────────────────────────
    private static void drawDiamond(Graphics2D g, int cx, int cy, int r) {
        // horní část užší, spodní ostřejší = klasický diamant
        int[] xs = {cx, cx + r, cx + r*2/3, cx - r*2/3, cx - r};
        int[] ys = {cy - r, cy - r/4, cy + r, cy + r, cy - r/4};
        g.fillPolygon(xs, ys, 5);
    }

    // ── Jetel (club) ───────────────────────────────────────────────────────
    private static void drawClub(Graphics2D g, int cx, int cy, int r) {
        int s = r * 5 / 8;
        // tři symetrické kuličky
        g.fillOval(cx - s - s/2, cy - s/2, s*2, s*2); // levá
        g.fillOval(cx - s,       cy - r,   s*2, s*2); // horní
        g.fillOval(cx - s/2,     cy - s/2, s*2, s*2); // pravá
        // stonek
        int sw = r / 4;
        g.fillRoundRect(cx - sw, cy + s/2, sw*2, r - s/2, 4, 4);
        // základna stonku
        g.fillRoundRect(cx - r/2, cy + r - r/4, r, r/4, 4, 4);
    }

    // ── Květ ───────────────────────────────────────────────────────────────
    private static void drawFlower(Graphics2D g, int cx, int cy, int r) {
        int petalR = r / 2;
        for (int i = 0; i < 6; i++) {
            double a = Math.PI / 3 * i;
            int ox = (int)(cx + petalR * Math.cos(a));
            int oy = (int)(cy + petalR * Math.sin(a));
            g.fillOval(ox - petalR, oy - petalR, petalR*2, petalR*2);
        }
        // střed přes lístky
        g.setColor(Color.WHITE);
        g.fillOval(cx - petalR/2, cy - petalR/2, petalR, petalR);
        g.setColor(g.getColor()); // reset – volající nastaví barvu před drawSymbol
    }

    // ── Blesk ──────────────────────────────────────────────────────────────
    // klasický tvar blesku: šikmý Z článek, horní část vpravo, dolní vlevo
    private static void drawLightning(Graphics2D g, int cx, int cy, int r) {
        int[] xs = {cx + r/3, cx,      cx + r/2, cx - r/3, cx,      cx - r/2};
        int[] ys = {cy - r,   cy - r/5, cy - r/5, cy + r,   cy + r/5, cy + r/5};
        g.fillPolygon(xs, ys, 6);
    }

    // ── Pravidelný polygon ─────────────────────────────────────────────────
    private static void drawPolygon(Graphics2D g, int cx, int cy, int r, int sides, double startAngle) {
        int[] xs = new int[sides], ys = new int[sides];
        for (int i = 0; i < sides; i++) {
            double a = startAngle + 2 * Math.PI / sides * i;
            xs[i] = (int)(cx + r * Math.cos(a));
            ys[i] = (int)(cy + r * Math.sin(a));
        }
        g.fillPolygon(xs, ys, sides);
    }

    // ── Kříž ───────────────────────────────────────────────────────────────
    private static void drawCross(Graphics2D g, int cx, int cy, int r) {
        int t = r / 3;
        g.fillRoundRect(cx - t, cy - r, 2*t, 2*r, 6, 6);
        g.fillRoundRect(cx - r, cy - t, 2*r, 2*t, 6, 6);
    }

    // ── Šipka ──────────────────────────────────────────────────────────────
    // jednoduchá šipka doprava: trojúhelníkový hrot + obdélníkový ocas
    private static void drawArrow(Graphics2D g, int cx, int cy, int r) {
        int t = r / 3;                         // polovina tloušťky ocasu
        int split = cx;                        // kde končí ocas a začíná hrot

        // ocas
        g.fillRoundRect(cx - r, cy - t, r + t, 2*t, 4, 4);

        // hrot = trojúhelník
        g.fillPolygon(
                new int[]{split,      cx + r, split},
                new int[]{cy - r/2,   cy,     cy + r/2},
                3
        );
    }

    // ── Koruna ─────────────────────────────────────────────────────────────
    // koruna se třemi špičkami a rovnou základnou
    private static void drawCrown(Graphics2D g, int cx, int cy, int r) {
        int base = cy + r/2;                   // y souřadnice základny
        int[] xs = {cx - r, cx - r,  cx - r/2, cx,      cx + r/2, cx + r,  cx + r};
        int[] ys = {base,   cy - r/3, cy + r/4, cy - r,  cy + r/4, cy - r/3, base};
        g.fillPolygon(xs, ys, 7);
        // základna jako plný pruh aby koruna vypadala pevně
        g.fillRoundRect(cx - r, base - 2, 2*r, r/3, 4, 4);
    }

    // ── List ───────────────────────────────────────────────────────────────
    private static void drawLeaf(Graphics2D g, int cx, int cy, int r) {
        GeneralPath p = new GeneralPath();
        p.moveTo(cx, cy + r);
        p.curveTo(cx - r, cy + r/2.0, cx - r, cy - r, cx, cy - r);
        p.curveTo(cx + r, cy - r, cx + r, cy + r/2.0, cx, cy + r);
        p.closePath();
        g.fill(p);
        // žilka listu
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.drawLine(cx, cy - r + 4, cx, cy + r - 4);
    }

    // ── Ryba ───────────────────────────────────────────────────────────────
    private static void drawFish(Graphics2D g, int cx, int cy, int r) {
        Color c = g.getColor();
        // tělo
        g.fillOval(cx - r/2, cy - r/3, r, 2*r/3);
        // ocas = dva trojúhelníky (rozevřená vidlička)
        g.fillPolygon(new int[]{cx+r/2, cx+r, cx+r}, new int[]{cy,     cy-r/2, cy-r/8}, 3);
        g.fillPolygon(new int[]{cx+r/2, cx+r, cx+r}, new int[]{cy,     cy+r/2, cy+r/8}, 3);
        // oko
        g.setColor(Color.WHITE);
        g.fillOval(cx - r/4, cy - r/8, r/4, r/4);
        g.setColor(c);
    }

    // ── Zvon ───────────────────────────────────────────────────────────────
    private static void drawBell(Graphics2D g, int cx, int cy, int r) {
        // tělo zvonu – symetrický tvar: nahoře úzký, dole rozšiřující se
        GeneralPath p = new GeneralPath();
        p.moveTo(cx - r/5, cy - r);            // levý horní okraj (úchyt)
        p.curveTo(cx - r,  cy - r,             // kontrolní bod: táhne vlevo nahoru
                cx - r,  cy + r/3,           // kontrolní bod: dole vlevo
                cx - r,  cy + r/3);          // spodní levý roh
        p.lineTo(cx + r,   cy + r/3);          // přímá spodní hrana
        p.curveTo(cx + r,  cy + r/3,
                cx + r,  cy - r,
                cx + r/5, cy - r);           // pravý horní okraj
        p.closePath();
        g.fill(p);
        // příčná lišta nahoře (úchyt)
        g.fillRoundRect(cx - r/3, cy - r - r/6, 2*r/3, r/5, 4, 4);
        // srdíčko zvonu dole
        g.setColor(Color.WHITE);
        g.fillOval(cx - r/6, cy + r/3 - r/6, r/3, r/3);
    }

    // ── Klíč ───────────────────────────────────────────────────────────────
    // vyplněný klíč: kroužek vlevo + dřík s zuby vpravo
    private static void drawKey(Graphics2D g, int cx, int cy, int r) {
        int kr = r / 2;                        // poloměr kroužku

        // kroužek (prstenec) = velký kruh mínus malý
        Area ring = new Area(new Ellipse2D.Double(cx - r, cy - kr, kr*2, kr*2));
        ring.subtract(new Area(new Ellipse2D.Double(cx - r + kr/2, cy - kr/2, kr, kr)));
        g.fill(ring);

        // dřík
        g.fillRoundRect(cx - r + kr*2, cy - r/6, r, r/3, 4, 4);

        // dva zuby dolů
        g.fillRoundRect(cx + r/4,  cy + r/6 - 2, r/5, r/3, 3, 3);
        g.fillRoundRect(cx + r*2/3, cy + r/6 - 2, r/5, r/4, 3, 3);
    }
}
