package LayerPaint;

import java.awt.BasicStroke;
import java.awt.Color;

interface Strokeable {

    public BasicStroke stroke();

    public void setStrokeColor(Color c);

    public void setStroke(BasicStroke s);

}
