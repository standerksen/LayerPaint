package LayerPaint;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author pieter
 */
public class MyImage implements Drawable {

    private static final String IMG_PATH = "src/LayerPaint/resources/eiffel.png";

    public boolean selected;
    public SelectBox s;
    private double x1, y1, x2, y2;
    private BufferedImage image;

    public MyImage(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        loadImage();
    }

    public MyImage(Tuple4d location) {
        x1 = location.x;
        y1 = location.y;
        x2 = location.z;
        y2 = location.w;
        loadImage();
    }

    private void loadImage() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            image = ImageIO.read(new File(IMG_PATH));
        } catch (IOException ex) {
            Logger.getLogger(MyImage.class.getName() + ": Could not Load Image").log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void draw(Graphics2D g) {
        int width = (int) getWidth();
        int height = (int) getHeight();
        width = x1 > x2 ? -width : width;
        height = y1 > y2 ? -height : height;
        g.drawImage(image, (int) x1, (int) y1, width, height, null);
        if (selected) {
            s.draw(g);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.x1) ^ (Double.doubleToLongBits(this.x1) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.y1) ^ (Double.doubleToLongBits(this.y1) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.x2) ^ (Double.doubleToLongBits(this.x2) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.y2) ^ (Double.doubleToLongBits(this.y2) >>> 32));
        return hash;
    }

    private double getWidth() {
        return Math.abs(x1 - x2);
    }

    private double getHeight() {
        return Math.abs(y1 - y2);
    }

    private double getStartX() {
        return Math.min(x1, x2);
    }

    private double getStartY() {
        return Math.min(y1, y2);
    }

    public Tuple4d getCoords() {
        return new Tuple4d(x1, y1, x2, y2);
    }

    @Override
    public void setCoords(Tuple4d tuple) {
        x1 = tuple.x;
        y1 = tuple.y;
        x2 = tuple.z;
        y2 = tuple.w;
        s = new SelectBox(x1, y1, getWidth(), getHeight());
    }

    @Override
    public SelectBox getBox() {
        return s;
    }

    @Override
    public boolean contains(double x, double y) {
        double width = getWidth();
        double height = getHeight();
        Rectangle2D r = new Rectangle2D.Double(getStartX(), getStartY(), width, height);
        return r.contains(x, y);
    }

    @Override
    public void select(boolean select) {
        selected = select;
    }

}
