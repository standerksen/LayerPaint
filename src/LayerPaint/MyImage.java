/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerPaint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author pieter
 */
public class MyImage implements Drawable {
    
    private static final String IMG_PATH = "src/resources/black.png";
    
    public boolean selected;
    private double x1, y1, x2, y2;
    private BufferedImage image;
    
    public MyImage(double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        loadImage();
    }
    
    public MyImage(Tuple4d location){
        x1 = location.x;
        y1 = location.y;
        x2 = location.z;
        y2 = location.w;
        loadImage();
    }
    
    private void loadImage()
    {
        try {
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
        if(selected){
            Rectangle2D s = new Rectangle2D.Double(x1, y1, width, height);
            g.setStroke(dashed);
            g.setColor(Color.BLACK);
            g.draw(s);
        }
    }

    @Override
    public Stroke stroke() {
        return null;
    }
    
    public boolean equals(Object other){
        return other instanceof MyImage;
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
    public void setCoords(Tuple4d tuple){
        x1 = tuple.x;
        y1 = tuple.y;
        x2 = tuple.z;
        y2 = tuple.w;
    }

    @Override
    public void setColor(Color color) {
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
