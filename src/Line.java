import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;


public class Line extends JPanel{

    private final int line_width = 5;
    private final int length = 500;

    Line() {

        this.setPreferredSize(new Dimension(this.length, this.line_width)); // Set panel size
        this.setOpaque(false); // Non opaque panel background
    }

    protected void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g; // Upgrade to Graphics2D
        super.paintComponent(g); // Render empty graph
        g2d.setPaint(Color.WHITE); // Choose color
        g2d.setStroke(new BasicStroke(this.line_width)); // Choose line width
        g2d.drawLine(0, 0, this.length, 0); // Draw line from the top left corner of the panel to top right corner of the panel
    }

    public void printline(GUI game, int y) {   // (The game JFrame, the y-axis coordinate of the line)

        game.add(this); // Adds line to the frame
        this.setBounds(0, y, this.length, this.line_width);
    }
}