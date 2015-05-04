package paintGUI.model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class ResizeShape extends RandShape {
	public ResizeShape(int xPos, int yPos, int width, int height, Color color, int type) {
		super(xPos, yPos, width, height, color, type);
	}

	public boolean contain(double x, double y) {
		Shape rect = new Rectangle2D.Double(getXPos(), getYPos(), getWidth(),
				getHeight());
		if (rect.contains(x, y)) {
			//System.out.println("I'm sure they're true.");
			return true;
		} else {
			return false;
		}
	}

	public Shape drawShape() {
		Shape Resize = null;
		Resize = new Rectangle2D.Double(getXPos(), getYPos(), getWidth(),
				getHeight());
		return Resize;
	}

	public RandShape clone() {
		RandShape newResize = new ResizeShape(getXPos(), getYPos(),
				getWidth(), getHeight(), getColor(), -1);
		return newResize;
	}

}
