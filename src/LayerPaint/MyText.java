package LayerPaint;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Objects;
import static LayerPaint.SelectBox.SOLID_STROKE;

/**
 *
 * @author Pieter
 */
public class MyText implements Drawable, Fillable {

    private double x, y;
    private int width, height;
    private boolean selected;
    private String string = "";
    private Color color;
    private SelectBox selectBox;
    private Line2D selectLine;

    public MyText() {

    }

    public MyText(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        selectBox = new SelectBox(x, y, 10, 10);
        selectLine = new Line2D.Double(x, y, x, y);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
        hash = 37 * hash + Objects.hashCode(this.string);
        hash = 37 * hash + Objects.hashCode(this.color);
        return hash;
    }

    @Override
    public SelectBox getBox() {
        return selectBox;
    }

    @Override
    public void select(boolean select) {
        selected = select;
    }

    @Override
    public void draw(Graphics2D g) {
        System.out.println(string);
        FontMetrics fm = g.getFontMetrics();
        height = fm.getHeight();
        width = fm.stringWidth(string);

        selectBox = new SelectBox(x - 5, y - height - 5, width + 10, height + fm.getMaxDescent() + 10);
        selectLine = new Line2D.Double(x, y, x + width, y);

        g.setColor(color);
        g.drawString(string, (int) x, (int) y);
        g.setColor(Color.BLACK);
        if (selected) {
            selectBox.draw(g);
            g.setStroke(SOLID_STROKE);
            g.draw(selectLine);
        }
    }

    @Override
    public Tuple4d getCoords() {
        return new Tuple4d(x, y, x + width, x + height);
    }

    @Override
    public boolean contains(double x, double y) {
        Rectangle2D hitBox = new Rectangle2D.Double(this.x, this.y - height, width, height);
        return hitBox.contains(x, y);
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    @Override
    public void setCoords(Tuple4d tuple) {
        x = tuple.x;
        y = tuple.y;
    }

    @Override
    public void setColor(Color c) {
        color = c;
    }

}
