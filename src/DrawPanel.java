import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JPanel;

public class DrawPanel extends JPanel implements MouseListener,
		ComponentListener {

	private final int MAX_WIDTH = (int) Toolkit.getDefaultToolkit()
			.getScreenSize().getWidth();
	private final int MAX_HEIGHT = (int) Toolkit.getDefaultToolkit()
			.getScreenSize().getWidth();

	private int grid[][];
	private int cursorx;
	private int cursory;
	private boolean bounded;
	private Random r;

	private int pathLength = 1000000;

	public DrawPanel() {
		grid = new int[0][0];
		r = new Random();
		bounded = false;

		addMouseListener(this);
		addComponentListener(this);
	}

	public void walk(int steps) {
		grid = new int[MAX_WIDTH][MAX_HEIGHT];
		for (int i = 0; i < steps; i++) {
			// Walk somewhere
			switch (r.nextInt(4)) {
			case 0:
				if (!bounded || cursorx > 0) {
					cursorx--;
				}
				break;
			case 1:
				if (!bounded || cursorx < grid.length - 1) {
					cursorx++;
				}
				break;
			case 2:
				if (!bounded || cursory < grid[0].length - 1) {
					cursory++;
				}
				break;
			case 3:
				if (!bounded || cursory > 0) {
					cursory--;
				}
				break;
			}
			// Add to the pixel
			if (cursorx >= 0 && cursorx < grid.length && cursory >= 0
					&& cursory < grid[0].length) {
				grid[cursorx][cursory]++;
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		// Draw the random walk
		g.clearRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < grid.length; i++) {
			for (int e = 0; e < grid[i].length; e++) {
				if (grid[i][e] != 0) {
					g.setColor(new Color(0, 0, 0, grid[i][e]));
					g.drawRect(i, e, 1, 1);
				}
			}
		}

		// Display the instruction text
		g.setColor(Color.RED);
		final String DISPLAY_STRING = "Click to walk a random path!";
		g.setFont(g.getFont().deriveFont((float) getWidth() / 20));
		g.drawString(DISPLAY_STRING, getWidth() / 2
				- g.getFontMetrics().stringWidth(DISPLAY_STRING) / 2,
				getHeight() / 2);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		cursorx = getWidth() / 2;
		cursory = getHeight() / 2;
		walk(pathLength);
		System.out.println("Final coordinates: (" + cursorx + ", " + cursory
				+ ")");
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		repaint();
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub

	}
}
