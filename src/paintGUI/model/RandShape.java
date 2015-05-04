package paintGUI.model;

import java.awt.Color;
import java.awt.Shape;

public class RandShape {
	private int xPos;
	private int yPos;
	private int width;
	private int height;
	private Color color;
	private int type;

	public RandShape() {
		this.xPos = 0;
		this.yPos = 0;
		this.width = 0;
		this.height = 0;
		this.color = null;
		this.type = 0;
	}

	public RandShape(int xPos, int yPos, int width, Color color, int type) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = width;
		this.color = color;
		this.type = type;
	}

	public RandShape(int xPos, int yPos, int width, int height, Color color, int type) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.color = color;
		this.type = type;
	}
	public int numOfResizePoints()
	{
		return 0;
	}
	public Shape resizePoints(int n)
	{
		return null;
	}

	public boolean contain(double x, double y) {
		System.out.println("Default cotain");
		return false;
	}

	public Shape drawShape() {
		return null;
	}

	public Color getColor() {
		return color;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setXPos(int xPos) {
		this.xPos = xPos;
	}

	public void setYPos(int yPos) {
		this.yPos = yPos;
	}

	public RandShape clone(){
		return null;
	}

	public void edit() {
		System.out.println("I feel the fail");
		// TODO Auto-generated method stub
		
	}
	public String toString()
	{
		String s = "";
		s=(getXPos() + " " + getYPos() + " " +getWidth() + " " +getHeight() + " " +getColor().getRed() + " " +getColor().getGreen() + " " +getColor().getBlue() + " " + getType());
		return s;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
}
