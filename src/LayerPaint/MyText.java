/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerPaint;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Pieter
 */
public class MyText implements Drawable, Fillable {
    
    private double x, y;
    private int width, height;
    private boolean selected;
    private String string = "Bla";
    private Color color;
    private SelectBox selectBox;
    private Line2D selectLine;
    private boolean isFocusable;
    
    public MyText(){
        
    }
    
    public MyText(double x, double y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
        selectBox = new SelectBox(x,y, 10, 10);
    }
    
    @Override
    public boolean equals(Object other){
        return other instanceof MyText;
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
        
        selectBox = new SelectBox(x, y - height, width, height);
        
        g.setColor(color);
        g.drawString(string, (int) x, (int) y);
        if(selected){
            selectBox.draw(g);
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
    
    public void setString(String string){
        this.string = string;
    }
    
    public String getString(){
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
