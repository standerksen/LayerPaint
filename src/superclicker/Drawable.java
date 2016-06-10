package superclicker;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Stroke;

public interface Drawable {

	public void draw(Graphics2D g);
        
        public Stroke stroke();
        
        public Tuple4d getCoords();

        public void setColor(Color color);
        
        public boolean contains(double x, double y);
        
        public void setCoords( Tuple4d tuple);
}
