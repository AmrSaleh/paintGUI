package paintGUI.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GFX2 {
	static int editMode = 0;
	static int startMoveX;
	static int startMoveY;
	static int shapeIndex = -1;
	static int prevShapeIndex = -1;
	static boolean selectMode = false;
	static boolean dragged = false;
	public static RandShape tempShape;
	public static TheCanvas myPanel;
	static boolean resize = false;
	static int resizeIndex = -1;
	static int xCoordinate = 0;
	static int yCoordinate = 0;
	public GFX2() {

		myPanel = new TheCanvas();
		// myPanel.setBounds(0,
		// 0,GFX.myFrame2.getWidth(),GFX.myFrame2.getHeight());
		/*
		select.addActionListener(new ActionListener() {
			  
			   @Override
		   public void actionPerformed(ActionEvent arg0) {
					GFX2.editMode = 0;
					GFX2.shapeIndex = -1;
					GFX2.prevShapeIndex = -1;
		myPanel.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (editMode == 0) {
					prevShapeIndex = shapeIndex;
					shapeIndex = SelectShape.shapeSelect(e.getX(), e.getY());
					if (shapeIndex != -1) {
						selectMode = true;
					} else if (shapeIndex == -1 && prevShapeIndex != -1) {
						//TheCanvas.myLib.getElement(prevShapeIndex).setColor(PaintView.drawingColor);
						selectMode = false;

						 ShapeLibrary temp = new ShapeLibrary();
						 for(int i=0;i<TheCanvas.myLib.getSize();i++){
						 temp.addShape(TheCanvas.myLib.getElement(i));
						 }
						
						RandShape coloredShape = temp.getElement(prevShapeIndex).clone();
						coloredShape.setColor(PaintView.drawingColor);
						
						 temp.set(prevShapeIndex, coloredShape);
						 try {
						 TheCanvas.undoStack.push(temp);
						 System.out.println("pushed with color");
						 System.out.println(temp.getElement(temp.getSize()-1).getColor());
						 TheCanvas.redoStack.clear();
						 
						 
						 ShapeLibrary temp2 = new ShapeLibrary();
							for (int i = 0; i < temp.getSize(); i++) {
								temp2.addShape(temp.getElement(i));
							}
							TheCanvas.myLib=temp2;
							
							myPanel.reset();
							myPanel.repaint();
						 } catch (Exception e2) {
						
							 e2.printStackTrace();
						 // TODO: handle exception
						 }
					} else {
						selectMode = false;
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				/*
				editMode = PaintView.geteditMode();
				// TODO Auto-generated method stub
				myPanel.x = e.getX();
				myPanel.y = e.getY();
				myPanel.width = 0;
				myPanel.height = 0;
				myPanel.running = true;

			}

			@Override
			public void mouseReleased(MouseEvent e) {

				// TODO Auto-generated method stub
				if (dragged) {
					dragged = false;
					myPanel.width = e.getX() - myPanel.x;
					myPanel.height = e.getY() - myPanel.y;
					myPanel.running = false;
					if (editMode != 0) {
						TheCanvas.myLib.addShape(tempShape);

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

						tempShape = null;
						
						//debug//////////////
						for(int i = 0; i < myPanel.undoStack.size(); i++)
					    {
					     for(int j=0 ; j<myPanel.undoStack.elementAt(i).getSize();j++){
					      System.out.println("UNDO: Library# " + i + " Shape# "+ j + " is " + myPanel.undoStack.elementAt(i).getElement(j).toString());
					     }
					    }
					    System.out.println("");
					    for(int i = 0; i < myPanel.redoStack.size(); i++)
					    {
					     for(int j=0 ; j<myPanel.redoStack.elementAt(i).getSize();j++){
					      System.out.println("REDO: Library# " + i + " Shape# "+ j + " is " + myPanel.undoStack.elementAt(i).getElement(j).toString());
					     }
					    }
					    System.out.println("");
						//debug///////////////
						
						
						myPanel.reset();
						myPanel.repaint();
					}
				}


			}
		});

		myPanel.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				if (editMode != 0)
					dragged = true;
				myPanel.width = e.getX() - myPanel.x;
				myPanel.height = e.getY() - myPanel.y;
				switch (editMode) {
				case 0:
					// koko = new LineShape(myPanel.x, myPanel.y, 0,0,
					// Color.WHITE);
					break;
				case 1:
					tempShape = new LineShape(myPanel.x, myPanel.y, myPanel.width, myPanel.height, PaintView.drawingColor);
					break;
				case 2:
					tempShape = new RectangleShape(myPanel.x, myPanel.y, myPanel.width, myPanel.height, PaintView.drawingColor);
					break;
				case 3:
					tempShape = new SquareShape(myPanel.x, myPanel.y, myPanel.width, myPanel.width, PaintView.drawingColor);
					break;
				case 4:
					tempShape = new TriangleShape90(myPanel.x, myPanel.y, myPanel.width, myPanel.height, PaintView.drawingColor);
					break;
				case 5:
					tempShape = new CircleShape(myPanel.x, myPanel.y, myPanel.width, myPanel.height, PaintView.drawingColor);
					break;
				case 6:
					tempShape = new EllipseShape(myPanel.x, myPanel.y, myPanel.width, myPanel.height, PaintView.drawingColor);
					break;
				}
				// System.out.println(PaintView.drawingColor);

				if (editMode == 2 || editMode == 6) {
					if (myPanel.width < 0 && myPanel.height < 0) {
						tempShape.setxPos(myPanel.x + myPanel.width);
						tempShape.setWidth(Math.abs(myPanel.width));
						tempShape.setyPos(myPanel.y + myPanel.height);
						tempShape.setHeight(Math.abs(myPanel.height));
					} else if (myPanel.width < 0) {
						tempShape.setxPos(myPanel.x + myPanel.width);
						tempShape.setWidth(Math.abs(myPanel.width));
					} else if (myPanel.height < 0) {
						tempShape.setyPos(myPanel.y + myPanel.height);
						tempShape.setHeight(Math.abs(myPanel.height));

					}
				} else if (editMode == 3 || editMode == 5) {
					if (myPanel.width < 0 && myPanel.height < 0) {
						tempShape.setxPos(myPanel.x + myPanel.height);
						tempShape.setWidth(Math.abs(myPanel.height));
						tempShape.setyPos(myPanel.y + myPanel.height);
						tempShape.setHeight(Math.abs(myPanel.height));
					} else if (myPanel.width < 0) {
						tempShape.setxPos(myPanel.x - myPanel.height);
						tempShape.setWidth(Math.abs(myPanel.height));
						tempShape.setyPos(myPanel.y);
						tempShape.setHeight(myPanel.height);
					} else if (myPanel.height < 0) {
						tempShape.setxPos(myPanel.x);
						tempShape.setWidth(Math.abs(myPanel.height));
						tempShape.setyPos(myPanel.y + myPanel.height);
						tempShape.setHeight(Math.abs(myPanel.height));
					} else {
						tempShape.setWidth(myPanel.height);
						tempShape.setHeight(myPanel.height);
					}
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
	}
		  });
		  */
	}

	public void start() {

		myPanel.run();

	}

}
