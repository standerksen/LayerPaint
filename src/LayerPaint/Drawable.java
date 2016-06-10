package LayerPaint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;

public interface Drawable {
        
        static final Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 10.0f, new float[]{2}, 0);
    
        public void select(boolean select);

	public void draw(Graphics2D g);
        
        public Stroke stroke();
        
        public Tuple4d getCoords();

        public void setColor(Color color);
        
        public boolean contains(double x, double y);
        
        public void setCoords( Tuple4d tuple);
}
