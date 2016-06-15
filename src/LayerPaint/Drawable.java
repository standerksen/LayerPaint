package LayerPaint;

import java.awt.Graphics2D;

public interface Drawable {

    public SelectBox getBox();

    public void select(boolean select);

    public void draw(Graphics2D g);

    public Tuple4d getCoords();

    public boolean contains(double x, double y);

    public void setCoords(Tuple4d tuple);

}
