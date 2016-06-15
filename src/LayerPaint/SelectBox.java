package LayerPaint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author Pieter
 */
public class SelectBox extends JPanel {

    public static final double BOX_SIZE = 5;

    static final Stroke DASHED_STROKE = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 10.0f, new float[]{2}, 0);
    static final Stroke SOLID_STROKE = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);

    private Rectangle2D box, tl, tm, tr, ml, mr, bl, bm, br;

    public SelectBox() {

    }

    public SelectBox(double x, double y, double w, double h) {
        box = new Rectangle2D.Double(x, y, w, h);
        tl = new Rectangle2D.Double(x - BOX_SIZE / 2, y - BOX_SIZE / 2, BOX_SIZE, BOX_SIZE);
        tm = new Rectangle2D.Double((x + w / 2) - BOX_SIZE / 2, y - BOX_SIZE / 2, BOX_SIZE, BOX_SIZE);
        tr = new Rectangle2D.Double((x + w) - BOX_SIZE / 2 - 1, y - BOX_SIZE / 2, BOX_SIZE, BOX_SIZE);
        ml = new Rectangle2D.Double(x - BOX_SIZE / 2, (y + h / 2) - BOX_SIZE / 2, BOX_SIZE, BOX_SIZE);
        mr = new Rectangle2D.Double(x + w - BOX_SIZE / 2 - 1, (y + h / 2) - BOX_SIZE / 2, BOX_SIZE, BOX_SIZE);
        bl = new Rectangle2D.Double(x - BOX_SIZE / 2, (y + h) - BOX_SIZE / 2, BOX_SIZE, BOX_SIZE);
        bm = new Rectangle2D.Double((x + w / 2) - BOX_SIZE / 2, (y + h) - BOX_SIZE / 2, BOX_SIZE, BOX_SIZE);
        br = new Rectangle2D.Double((x + w) - BOX_SIZE / 2 - 1, (y + h) - BOX_SIZE / 2, BOX_SIZE, BOX_SIZE);
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setStroke(SOLID_STROKE);
        g.draw(tl);
        g.draw(tm);
        g.draw(tr);
        g.draw(ml);
        g.draw(mr);
        g.draw(bl);
        g.draw(bm);
        g.draw(br);
        g.setStroke(DASHED_STROKE);
        g.draw(box);
    }

    public int contains(double x, double y) {
        if (tl.contains(x, y)) {
            return 1;
        } else if (tm.contains(x, y)) {
            return 2;
        } else if (tr.contains(x, y)) {
            return 3;
        } else if (ml.contains(x, y)) {
            return 4;
        } else if (mr.contains(x, y)) {
            return 5;
        } else if (bl.contains(x, y)) {
            return 6;
        } else if (bm.contains(x, y)) {
            return 7;
        } else if (br.contains(x, y)) {
            return 8;
        } else if (box.contains(x, y)) {
            return 9;
        } else {
            return 0;
        }
    }

}
