import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame("Simple Paint");
        SimplePaint.SimplePaintPanel content = new SimplePaint.SimplePaintPanel();
        window.setContentPane(content);
        window.setSize(600,480);
        window.setLocation(100,100);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setVisible(true);
    }
}
