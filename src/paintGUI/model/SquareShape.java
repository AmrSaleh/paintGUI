package paintGUI.model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;

public class SquareShape extends RandShape {

	static {
		JButton square = new JButton("Square");
		square.setBounds(851, 220, 88, 25);
		PaintView.myButtons.add(square);
		// PaintView.contentPane.repaint();
		square.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < GFX2.myPanel.getMouseListeners().length; i++) {
					GFX2.myPanel.removeMouseListener(GFX2.myPanel.getMouseListeners()[i]);
				}
				for (int i = 0; i < GFX2.myPanel.getMouseMotionListeners().length; i++) {
					GFX2.myPanel.removeMouseMotionListener(GFX2.myPanel.getMouseMotionListeners()[i]);
				}
				GFX2.editMode = -1;
				// TODO Auto-generated method stub
				GFX2.myPanel.addMouseListener(new MouseListener() {
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						if (GFX2.dragged) {
							GFX2.dragged = false;
							GFX2.myPanel.width = e.getX() - GFX2.myPanel.x;
							GFX2.myPanel.height = e.getY() - GFX2.myPanel.y;
							GFX2.myPanel.running = false;
							TheCanvas.myLib.addShape(GFX2.tempShape);

							ShapeLibrary temp3 = new ShapeLibrary();
							for (int i = 0; i < TheCanvas.myLib.getSize(); i++) {
								temp3.addShape(TheCanvas.myLib.getElement(i));
							}

							try {
								TheCanvas.undoStack.push(temp3);
								System.out.println("pushed no color");
								TheCanvas.redoStack.clear();
							} catch (Exception e2) {

								// TODO: handle exception
							}

							GFX2.tempShape = null;

							// debug//////////////
							for (int i = 0; i < GFX2.myPanel.undoStack.size(); i++) {
								for (int j = 0; j < GFX2.myPanel.undoStack.elementAt(i).getSize(); j++) {
									System.out.println("UNDO: Library# " + i + " Shape# " + j + " is " + GFX2.myPanel.undoStack.elementAt(i).getElement(j).toString());
								}
							}
							System.out.println("");
							for (int i = 0; i < GFX2.myPanel.redoStack.size(); i++) {
								for (int j = 0; j < GFX2.myPanel.redoStack.elementAt(i).getSize(); j++) {
									System.out.println("REDO: Library# " + i + " Shape# " + j + " is " + GFX2.myPanel.undoStack.elementAt(i).getElement(j).toString());
								}
							}
							System.out.println("");
							// debug///////////////

							GFX2.myPanel.reset();
							GFX2.myPanel.repaint();
						}

					}

					@Override
					public void mouseClicked(MouseEvent arg0) {
						// TODO Auto-generated method stub

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
						GFX2.myPanel.x = arg0.getX();
						GFX2.myPanel.y = arg0.getY();
						GFX2.myPanel.width = 0;
						GFX2.myPanel.height = 0;
						GFX2.myPanel.running = true;

					}

				});
				GFX2.myPanel.addMouseMotionListener(new MouseMotionListener() {

					@Override
					public void mouseDragged(MouseEvent e) {
						// TODO Auto-generated method stub
						GFX2.dragged = true;
						GFX2.myPanel.width = e.getX() - GFX2.myPanel.x;
						GFX2.myPanel.height = e.getY() - GFX2.myPanel.y;
						GFX2.tempShape = new SquareShape(GFX2.myPanel.x, GFX2.myPanel.y, GFX2.myPanel.width, GFX2.myPanel.height, PaintView.drawingColor, 5);
						// System.out.println(PaintView.drawingColor);

						if (GFX2.myPanel.width < 0 && GFX2.myPanel.height < 0) {
							GFX2.tempShape.setXPos(GFX2.myPanel.x + GFX2.myPanel.height);
							GFX2.tempShape.setWidth(Math.abs(GFX2.myPanel.height));
							GFX2.tempShape.setYPos(GFX2.myPanel.y + GFX2.myPanel.height);
							GFX2.tempShape.setHeight(Math.abs(GFX2.myPanel.height));
						} else if (GFX2.myPanel.width < 0) {
							GFX2.tempShape.setXPos(GFX2.myPanel.x - GFX2.myPanel.height);
							GFX2.tempShape.setWidth(Math.abs(GFX2.myPanel.height));
							GFX2.tempShape.setYPos(GFX2.myPanel.y);
							GFX2.tempShape.setHeight(GFX2.myPanel.height);
						} else if (GFX2.myPanel.height < 0) {
							GFX2.tempShape.setXPos(GFX2.myPanel.x);
							GFX2.tempShape.setWidth(Math.abs(GFX2.myPanel.height));
							GFX2.tempShape.setYPos(GFX2.myPanel.y + GFX2.myPanel.height);
							GFX2.tempShape.setHeight(Math.abs(GFX2.myPanel.height));
						} else {
							GFX2.tempShape.setWidth(GFX2.myPanel.height);
							GFX2.tempShape.setHeight(GFX2.myPanel.height);
						}
					}

					@Override
					public void mouseMoved(MouseEvent e) {
						// TODO Auto-generated method stub

					}

				});
			}
		});

	}
	static int firstx, firsty, lastx, lasty;
	public SquareShape() {
		// TODO Auto-generated constructor stub
	}

	public SquareShape(int xPos, int yPos, int width, int height, Color color, int type) {
		super(xPos, yPos, (width), (width), color, type);
	}

	public boolean contain(double x, double y) {
		Shape square = new Rectangle2D.Double(getXPos(), getYPos(), getWidth(), getWidth());
		if (square.contains(x, y)) {
			return true;
		} else {
			return false;
		}
	}

	public Shape drawShape() {
		Shape square = null;
		square = new Rectangle2D.Double(getXPos(), getYPos(), getWidth(), getWidth());
		return square;
	}

	public RandShape clone() {
		RandShape newSquare = new SquareShape(getXPos(), getYPos(), getWidth(), getHeight(), getColor(), 5);
		return newSquare;
	}

	public int numOfResizePoints() {
		return 9;
	}

	public Shape resizePoints(int i) {
		RandShape newResize = null;
		if(i==0){
			newResize = new ResizeShape(getXPos()-4, getYPos()-4,
					8, 8, Color.BLACK, 4);
		}
		else if(i==1){
			newResize = new ResizeShape(getXPos()+getWidth()/2-4, getYPos()-4,
					8, 8, Color.BLACK, 4);
		}
		else if(i==2){
			newResize = new ResizeShape((getXPos()+getWidth())-4, getYPos()-4,
					8, 8, Color.BLACK, 4);
		}
		else if(i==3){
			newResize = new ResizeShape(getXPos()-4, getYPos()+getHeight()/2-4,
					8, 8, Color.BLACK, 4);
		}
		else if(i==4){
			newResize = new ResizeShape((getXPos()+getWidth())-4, getYPos()+getHeight()/2-4,
					8, 8, Color.BLACK, 4);
		}
		else if(i==5){
			newResize = new ResizeShape(getXPos()-4, (getYPos()+getHeight())-4,
					8, 8, Color.BLACK, 4);
		}
		else if(i==6){
			newResize = new ResizeShape(getXPos()+getWidth()/2-4, (getYPos()+getHeight())-4,
					8, 8, Color.BLACK, 4);
		}
		else if(i==7){
			newResize = new ResizeShape(getXPos()+getWidth()-4, (getYPos()+getHeight())-4,
					8, 8, Color.BLACK, 4);
		}
		else if(i==8){
			newResize = new ResizeShape(getXPos()+getWidth()/2-4, (getYPos()+getHeight()/2)-4,
					8, 8, Color.BLACK, 4);
		}
		GFX.resizeLib.addShape(newResize);
		return newResize.drawShape();
	}

	public void edit() {

		for (int i = 0; i < GFX2.myPanel.getMouseListeners().length; i++) {
			GFX2.myPanel.removeMouseListener(GFX2.myPanel.getMouseListeners()[i]);
		}
		for (int i = 0; i < GFX2.myPanel.getMouseMotionListeners().length; i++) {
			GFX2.myPanel.removeMouseMotionListener(GFX2.myPanel.getMouseMotionListeners()[i]);
		}
		// System.out.println(GFX2.resize);
		GFX2.myPanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (GFX2.editMode == 0) {
					GFX2.prevShapeIndex = GFX2.shapeIndex;
					GFX2.shapeIndex = SelectShape.shapeSelect(arg0.getX(), arg0.getY());

					// GFX.resizeLib.clear();
					// GFX2.myPanel.reset();
					GFX2.myPanel.repaint();
					if (GFX2.shapeIndex != -1) {
						// GFX2.myPanel.repaint();
						TheCanvas.myLib.getElement(GFX2.shapeIndex).edit();
						// GFX2.myPanel.reset();
						// GFX2.myPanel.repaint();
					} else if (GFX2.shapeIndex == -1 && GFX2.prevShapeIndex != -1) {
						// TheCanvas.myLib.getElement(prevShapeIndex).setColor(PaintView.drawingColor);
						GFX2.selectMode = false;

						ShapeLibrary temp = new ShapeLibrary();
						for (int i = 0; i < TheCanvas.myLib.getSize(); i++) {
							temp.addShape(TheCanvas.myLib.getElement(i));
						}

						RandShape coloredShape = temp.getElement(GFX2.prevShapeIndex).clone();
						coloredShape.setColor(PaintView.drawingColor);

						temp.set(GFX2.prevShapeIndex, coloredShape);
						try {
							if (TheCanvas.myLib.getElement(GFX2.prevShapeIndex).getColor() != temp.getElement(GFX2.prevShapeIndex).getColor()) {
								TheCanvas.undoStack.push(temp);
								System.out.println("pushed with color");
								System.out.println(temp.getElement(temp.getSize() - 1).getColor());
							}
							TheCanvas.redoStack.clear();

							ShapeLibrary temp2 = new ShapeLibrary();
							for (int i = 0; i < temp.getSize(); i++) {
								temp2.addShape(temp.getElement(i));
							}
							TheCanvas.myLib = temp2;

							GFX2.myPanel.reset();
							GFX2.myPanel.repaint();
						} catch (Exception e2) {

							e2.printStackTrace();
							// TODO: handle exception
						}
					} else {
						GFX2.selectMode = false;
					}
				}
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
				System.out.println(GFX.resizeLib.getSize());
				GFX2.myPanel.x = arg0.getX();
				GFX2.myPanel.y = arg0.getY();
				GFX2.myPanel.width = 0;
				GFX2.myPanel.height = 0;

				GFX2.resize = false;
				for (int i = 0; i < GFX.resizeLib.getSize(); i++) {
					System.out.println("Are you there yet?");
					if (GFX.resizeLib.getElement(i).contain(arg0.getX(), arg0.getY())) {
						System.out.println(GFX.resizeLib.getElement(i).getXPos() + " " + GFX.resizeLib.getElement(i).getYPos() + " " + GFX.resizeLib.getElement(i).getWidth() + " " + GFX.resizeLib.getElement(i).getHeight() + " " + arg0.getX() + " " + arg0.getY());
						GFX2.resize = true;
						GFX2.resizeIndex = i;
						GFX2.myPanel.running = true;
						GFX2.myPanel.width = TheCanvas.myLib.getElement(GFX2.shapeIndex).getWidth();
						GFX2.myPanel.height = TheCanvas.myLib.getElement(GFX2.shapeIndex).getHeight();
						GFX2.startMoveX = arg0.getX();
						GFX2.startMoveY = arg0.getY();
						GFX2.myPanel.running = true;
						firstx = TheCanvas.myLib.getElement(GFX2.shapeIndex).getXPos();
						firsty = TheCanvas.myLib.getElement(GFX2.shapeIndex).getYPos();
						lastx = TheCanvas.myLib.getElement(GFX2.shapeIndex).getWidth();
						lasty = TheCanvas.myLib.getElement(GFX2.shapeIndex).getHeight();
						break;
					}
				}
				GFX.resizeLib.clear();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				GFX2.myPanel.running = false;
				GFX.resizeLib.clear();
				// GFX2.myPanel.reset();
				GFX2.myPanel.repaint();
				GFX2.resize = false;
				GFX2.resizeIndex = -1;
				if (GFX2.dragged) {
					ShapeLibrary temp = new ShapeLibrary();
					for (int i = 0; i < TheCanvas.myLib.getSize(); i++) {
						temp.addShape(TheCanvas.myLib.getElement(i));
					}
					try {
						TheCanvas.undoStack.push(temp);
						TheCanvas.redoStack.clear();
					} catch (Exception e2) {

						// TODO: handle exception
					}
				}
				GFX2.dragged = false;
			}

		});
		GFX2.myPanel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// System.out.println("I feel the drag");
				// TODO Auto-generated method stub
				// System.out.println("resize is " + GFX2.resize);
				if (GFX2.resize == true) {
					GFX2.dragged = true;

					RandShape square = TheCanvas.myLib.getElement(GFX2.shapeIndex).clone();

					if (GFX2.resizeIndex == 0) {
//						if (e.getX() < firstx + lastx && e.getY() < firsty + lasty) {
//							square.setxPos(e.getX());
//							square.setyPos(e.getY());
//							square.setWidth(lastx - (e.getX() - firstx));
//							square.setHeight(lasty - (e.getY() - firsty));
//						}else if(e.getX() > firstx + lastx && e.getY() > firsty + lasty){
//							square.setxPos(firstx+lastx);
//							square.setyPos(firsty+lasty);
//							square.setWidth(e.getX() - (firstx+lastx));
//							square.setHeight(e.getY() - (firsty+lasty));
//						}else if (e.getY() < firsty + lasty) {
//							square.setxPos(firstx+lastx);
//							square.setyPos(e.getY());
//							square.setWidth(e.getX()-(firstx+lastx));
//							square.setHeight(lasty - (e.getY() - firsty));
//						} else if (e.getX() < firstx + lastx){
//							square.setxPos(e.getX());
//							square.setyPos(firsty+lasty);
//							square.setWidth(lastx - (e.getX() - firstx));
//							square.setHeight(e.getY()-(firsty+lasty));
//						}
					} else if (GFX2.resizeIndex == 1) {
						if (e.getY() < firsty + lasty){
							square.setYPos(e.getY());
							square.setXPos(firstx+(e.getY()-firsty));
							square.setHeight(lasty - (e.getY() - firsty));
							square.setWidth(lasty - (e.getY() - firsty));
						}else{
							square.setYPos(firsty+lasty);
							square.setXPos((firstx+lastx)-(e.getY()-(firsty+lasty)));
							square.setHeight(e.getY()-(firsty+lasty));
							square.setWidth(e.getY()-(firsty+lasty));
						}
					} else if (GFX2.resizeIndex == 2) {
//						if (e.getX() < firstx && e.getY() < firsty+lasty) {
//							square.setxPos(e.getX());
//							square.setyPos(e.getY());
//							square.setWidth(firstx-e.getX());
//							square.setHeight((firsty+lasty)-e.getY());
//						}else if(e.getX() > firstx && e.getY() > firsty + lasty){
//							square.setxPos(firstx);
//							square.setyPos(firsty+lasty);
//							square.setWidth(e.getX() - (firstx));
//							square.setHeight(e.getY() - (firsty+lasty));
//						}else if (e.getY() < (firsty + lasty)) {
//							square.setxPos(firstx);
//							square.setyPos(e.getY());
//							square.setWidth(e.getX()-(firstx));
//							square.setHeight(lasty - (e.getY() - firsty));
//						} else if (e.getX() < firstx ){
//							square.setxPos(e.getX());
//							square.setyPos(firsty+lasty);
//							square.setWidth(firstx - e.getX());
//							square.setHeight(e.getY()-(firsty+lasty));
//						}
					} else if (GFX2.resizeIndex == 3) {
						if (e.getX() < firstx+lastx){
							square.setXPos(e.getX());
							square.setYPos(firsty+(e.getX()-firstx));
							square.setWidth((lastx)+(firstx-e.getX()));
							square.setHeight(square.getWidth());
						
						}else{
							square.setXPos(firstx+lastx);
							square.setYPos((firsty+lasty)-(e.getX()-(firstx+lastx)));
							square.setWidth(e.getX()-(firstx+lastx));
							square.setHeight(square.getWidth());
						}
					} else if (GFX2.resizeIndex == 4) {
						if (e.getX() < firstx){
							square.setXPos(e.getX());
							square.setYPos((firsty+lasty)-(firstx-e.getX()));
							square.setWidth((firstx-e.getX()));
							square.setHeight((firstx-e.getX()));
							
						}else{
							square.setXPos(firstx);
							square.setYPos((firsty)-(e.getX()-(firstx+lastx)));
							
							square.setWidth(e.getX()-(firstx));
							square.setHeight(e.getX()-(firstx));
						}
					} else if (GFX2.resizeIndex == 5) {
//						if (e.getX() < firstx + lastx && e.getY() < firsty ) {
//							square.setxPos(e.getX());
//							square.setyPos(e.getY());
//							square.setWidth((firstx+lastx )- e.getX());
//							square.setHeight(firsty - e.getY());
//						}else if(e.getX() > firstx + lastx && e.getY() > firsty ){
//							square.setxPos(firstx+lastx);
//							square.setyPos(firsty);
//							square.setWidth(e.getX() - (firstx+lastx));
//							square.setHeight(e.getY() - (firsty));
//						}else if (e.getY() < firsty  && e.getX()>firstx) {
//							square.setxPos(firstx+lastx);
//							square.setyPos(e.getY());
//							square.setWidth(e.getX()-(firstx+lastx));
//							square.setHeight(firsty - (e.getY() ));
//					}else if (e.getX() < firstx + lastx && e.getY()>firsty){
//							square.setxPos(e.getX());
//							square.setyPos(firsty);
//							square.setWidth(firstx+lastx - (e.getX()));
//							square.setHeight(e.getY()-(firsty));
//						}
					} else if (GFX2.resizeIndex == 6) {
						if (e.getY() > firsty ){
							square.setYPos(firsty);
//							square.setxPos(firstx+lastx+(e.getY()-(firsty+lasty)));
							square.setHeight(e.getY()-firsty);
							square.setWidth(square.getHeight());
						}else{
							square.setYPos(e.getY());
							//square.setxPos(xPos);
							square.setHeight(firsty-e.getY());
							square.setWidth(square.getHeight());
							
						}
					} else if (GFX2.resizeIndex == 7) {
//						if (e.getX() < firstx && e.getY() < firsty ) {
//							square.setxPos(e.getX());
//							square.setyPos(e.getY());
//							square.setWidth((firstx )- e.getX());
//							square.setHeight(firsty - e.getY());
//						}else if(e.getX() > firstx  && e.getY() > firsty ){
//							square.setxPos(firstx);
//							square.setyPos(firsty);
//							square.setWidth(e.getX() - (firstx));
//							square.setHeight(e.getY() - (firsty));
//						}else if (e.getY() < firsty  && e.getX()>firstx) {
//							square.setxPos(firstx);
//							square.setyPos(e.getY());
//							square.setWidth(e.getX()-(firstx));
//							square.setHeight(firsty - (e.getY() ));
//					}else if (e.getX() < firstx && e.getY()>firsty){
//							square.setxPos(e.getX());
//							square.setyPos(firsty);
//							square.setWidth(firstx - (e.getX()));
//							square.setHeight(e.getY()-(firsty));
//						}
					} else if (GFX2.resizeIndex == 8) {
						square.setXPos((e.getX() - GFX2.startMoveX) + firstx);
						square.setYPos((e.getY() - GFX2.startMoveY) + firsty);

					}
					int xAxis = square.getXPos();
					int yAxis = square.getYPos();
					int width = square.getWidth();
					int height = square.getHeight();
					// GFX2.myPanel.width = rectangle.getWidth();
					// GFX2.myPanel.height = rectangle.getHeight();
					if (square.getWidth() < 0 && square.getHeight() < 0) {
						square.setXPos(e.getX());
						square.setWidth(Math.abs(width));
						square.setYPos(e.getY());
						square.setHeight(Math.abs(height));
					} else if (square.getWidth() < 0) {
						square.setXPos(e.getX());
						square.setWidth(Math.abs(width));
					} else if (square.getHeight() < 0) {
						square.setYPos(e.getY());
						square.setHeight(Math.abs(height));
					}

					// GFX2.myPanel.height = e.getY() - GFX2.myPanel.y;
					// GFX2.tempShape = new rectangleShape(GFX2.myPanel.x,
					// GFX2.myPanel.y, GFX2.myPanel.width, GFX2.myPanel.height,
					// PaintView.drawingColor);
					ShapeLibrary tempLib = new ShapeLibrary();
					for (int i = 0; i < TheCanvas.myLib.getSize(); i++) {
						if (i == GFX2.shapeIndex) {
							tempLib.addShape(square);
						} else {
							tempLib.addShape(TheCanvas.myLib.getElement(i));
						}
					}
					TheCanvas.myLib = new ShapeLibrary();
					TheCanvas.myLib = tempLib;
					// GFX2.myPanel.repaint();
				}

			}
		});
	}

}
