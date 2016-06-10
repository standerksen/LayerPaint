/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LayerPaint;

/**
 *
 * @author pieter
 */
class Tuple4d {
    public double x, y, z, w;

    public Tuple4d()
    {
        x = y = z = w = 0;
    }
    
    public Tuple4d(double x, double y, double z, double w){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
}
