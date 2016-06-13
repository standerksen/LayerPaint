/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerPaint;

import java.awt.Color;
import java.awt.Stroke;

/**
 *
 * @author Pieter
 */
interface Strokeable {
    
    public Stroke stroke();
    
    public void setStrokeColor(Color c);
    
}
