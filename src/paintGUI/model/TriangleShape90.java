package paintGUI.model;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;

public class TriangleShape90 extends RandShape {

	static{
		JButton tri = new JButton("Triangle");
		  tri.setBounds(851, 250, 88, 25);
		  PaintView.myButtons.add(tri);
		  //PaintView.contentPane.repaint();

		  tri.addActionListener(new ActionListener() {
			   
			   @Override
		   public void actionPerformed(ActionEvent arg0) {
				   for(int i = 0; i<GFX2.myPanel.getMouseListeners().length;
							  i++) {
							  GFX2.myPanel.removeMouseListener(GFX2.myPanel.getMouseListeners
							  ()[i]); } for(int i = 0;
							  i<GFX2.myPanel.getMouseMotionListeners().length; i++) {
							  GFX2.myPanel
							  .removeMouseMotionListener(GFX2.myPanel.getMouseMotionListeners
							  ()[i]); }
				   GFX2.editMode=-1;
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
							
							//debug//////////////
							for(int i = 0; i < GFX2.myPanel.undoStack.size(); i++)
						    {
						     for(int j=0 ; j<GFX2.myPanel.undoStack.elementAt(i).getSize();j++){
						      System.out.println("UNDO: Library# " + i + " Shape# "+ j + " is " + GFX2.myPanel.undoStack.elementAt(i).getElement(j).toString());
						     }
						    }
						    System.out.println("");
						    for(int i = 0; i < GFX2.myPanel.redoStack.size(); i++)
						    {
						     for(int j=0 ; j<GFX2.myPanel.redoStack.elementAt(i).getSize();j++){
						      System.out.println("REDO: Library# " + i + " Shape# "+ j + " is " + GFX2.myPanel.undoStack.elementAt(i).getElement(j).toString());
						     }
						    }
						    System.out.println("");
							//debug///////////////
							
							
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
					GFX2.tempShape = new TriangleShape90(GFX2.myPanel.x, GFX2.myPanel.y, GFX2.myPanel.width, GFX2.myPanel.height, PaintView.drawingColor, 6);
					// System.out.println(PaintView.drawingColor);

				}

				@Override
				public void mouseMoved(MouseEvent e) {
					// TODO Auto-generated method stub

				}

			});
		 }
		});
		  
	}
	static int firstx,firsty,lastx,lasty;
	 int[] xPositions;
	 int[] yPositions;
	 int[] xPositions2=new int[3];
	 int[] yPositions2= new int[3];
	 public TriangleShape90() {
		// TODO Auto-generated constructor stub
		 xPositions = new int[3];
		 yPositions = new int[3];
	}
	
	public TriangleShape90(int xPos, int yPos, int width, int height,
			Color color, int type) {
		super(xPos, yPos, width, height, color, type);
		
		 xPositions = new int[3];
		 yPositions = new int[3];
		 
		xPositions[0] = xPos;
		xPositions[1] = xPos;
		xPositions[2] = xPos + width;

		yPositions[0] = yPos;
		yPositions[1] =yPos + height;
		yPositions[2] = yPos +height;
	}

	public boolean contain(double x, double y) {
		Shape polygon = null;
		
		xPositions2[0] = getXPos();
		xPositions2[1] = getXPos();
		xPositions2[2] = getXPos() + getWidth();

		yPositions2[0] = getYPos();
		yPositions2[1] =getYPos() + getHeight();
		yPositions2[2] = getYPos() +getHeight();
		

		polygon = new Polygon(xPositions2, yPositions2, 3);
		if (polygon.contains(x, y)) {
			return true;
		} else {
			return false;
		}
	}

	public Shape drawShape() {
		Shape polygon = null;
		
		xPositions2[0] = getXPos();
		xPositions2[1] = getXPos();
		xPositions2[2] = getXPos() + getWidth();

		yPositions2[0] = getYPos();
		yPositions2[1] =getYPos() + getHeight();
		yPositions2[2] = getYPos() +getHeight();

		polygon = new Polygon(xPositions2, yPositions2, 3);
		return polygon;
	}
	public RandShape clone(){
		  RandShape newTri = new TriangleShape90(getXPos(), getYPos(), getWidth(), getHeight(), getColor(), 6);
		  return newTri;
		 }
	public int numOfResizePoints()
	{
		return 9;
	}
	public Shape resizePoints(int i){
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
	
	public void edit()
	{
		
		for(int i = 0; i<GFX2.myPanel.getMouseListeners().length;
				  i++) {
				  GFX2.myPanel.removeMouseListener(GFX2.myPanel.getMouseListeners
				  ()[i]); } for(int i = 0;
				  i<GFX2.myPanel.getMouseMotionListeners().length; i++) {
				  GFX2.myPanel
				  .removeMouseMotionListener(GFX2.myPanel.getMouseMotionListeners
				  ()[i]); }
		//System.out.println(GFX2.resize);
		GFX2.myPanel.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (GFX2.editMode == 0) {
					GFX2.prevShapeIndex = GFX2.shapeIndex;
					GFX2.shapeIndex = SelectShape.shapeSelect(arg0.getX(),
							arg0.getY());
					
					//GFX.resizeLib.clear();
					//GFX2.myPanel.reset();
					GFX2.myPanel.repaint();
					if (GFX2.shapeIndex != -1) {
						//GFX2.myPanel.repaint();
						TheCanvas.myLib.getElement(GFX2.shapeIndex).edit();
						//GFX2.myPanel.reset();
						//GFX2.myPanel.repaint();
					} else if (GFX2.shapeIndex == -1
							&& GFX2.prevShapeIndex != -1) {
						// TheCanvas.myLib.getElement(prevShapeIndex).setColor(PaintView.drawingColor);
						GFX2.selectMode = false;
						
						ShapeLibrary temp = new ShapeLibrary();
						for (int i = 0; i < TheCanvas.myLib.getSize(); i++) {
							temp.addShape(TheCanvas.myLib.getElement(i));
						}

						RandShape coloredShape = temp.getElement(
								GFX2.prevShapeIndex).clone();
						coloredShape.setColor(PaintView.drawingColor);

						temp.set(GFX2.prevShapeIndex, coloredShape);
						try {
							if(TheCanvas.myLib.getElement(GFX2.prevShapeIndex).getColor()!=temp.getElement(GFX2.prevShapeIndex).getColor()){
							TheCanvas.undoStack.push(temp);
							System.out.println("pushed with color");
							System.out.println(temp.getElement(
									temp.getSize() - 1).getColor());
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
				GFX2.myPanel.width =0;
				GFX2.myPanel.height =0;
				
				GFX2.resize=false;
				for(int i = 0; i < GFX.resizeLib.getSize(); i++)
				{
					System.out.println("Are you there yet?");
					if(GFX.resizeLib.getElement(i).contain(arg0.getX(), arg0.getY()))
					{
						System.out.println(GFX.resizeLib.getElement(i).getXPos() + " " + GFX.resizeLib.getElement(i).getYPos() + " "+GFX.resizeLib.getElement(i).getWidth() + " "+GFX.resizeLib.getElement(i).getHeight() + " " + arg0.getX() + " "+arg0.getY());
						GFX2.resize=true;
						GFX2.resizeIndex=i;
						GFX2.myPanel.running = true;
						GFX2.myPanel.width = TheCanvas.myLib.getElement(GFX2.shapeIndex).getWidth();
						GFX2.myPanel.height = TheCanvas.myLib.getElement(GFX2.shapeIndex).getHeight();
						GFX2.startMoveX=arg0.getX();
						GFX2.startMoveY=arg0.getY();
						GFX2.myPanel.running = true;
						firstx=TheCanvas.myLib.getElement(GFX2.shapeIndex).getXPos();
						firsty=TheCanvas.myLib.getElement(GFX2.shapeIndex).getYPos();
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
				//GFX2.myPanel.reset();
				GFX2.myPanel.repaint();
				GFX2.resize=false;
				GFX2.resizeIndex=-1;
				if(GFX2.dragged)
				{
					ShapeLibrary temp = new ShapeLibrary();
					for(int i = 0; i < TheCanvas.myLib.getSize(); i++)
					{
						temp.addShape(TheCanvas.myLib.getElement(i));
					}
					try {
						TheCanvas.undoStack.push(temp);
						TheCanvas.redoStack.clear();
					} catch (Exception e2) {
	
						// TODO: handle exception
					}
				}
				GFX2.dragged=false;
			}
			
		});
		GFX2.myPanel.addMouseMotionListener(new MouseMotionListener() {
			
			
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				//System.out.println("I feel the drag");
				// TODO Auto-generated method stub
				//System.out.println("resize is " + GFX2.resize);
				if(GFX2.resize==true)
				{
					GFX2.dragged = true;
					
					RandShape triangle = TheCanvas.myLib.getElement(GFX2.shapeIndex).clone();
					if (GFX2.resizeIndex == 0) {
						xPositions[0]=e.getX();
						yPositions[0]=e.getY();
						triangle.setXPos(xPositions[0]);
						triangle.setYPos(yPositions[0]);
						triangle.setWidth(xPositions[2]-xPositions[0]);
						triangle.setHeight(yPositions[2]-yPositions[0]);
					} else if (GFX2.resizeIndex == 1) {
						yPositions[0]=e.getY();
						triangle.setXPos(xPositions[0]);
						triangle.setYPos(yPositions[0]);
						triangle.setWidth(xPositions[2]-xPositions[0]);
						triangle.setHeight(yPositions[2]-yPositions[0]);
					} else if (GFX2.resizeIndex == 2) {
						
						
						yPositions[0]=e.getY();
						
						xPositions[2]=e.getX();
						
						
						triangle.setXPos(xPositions[0]);
						triangle.setYPos(yPositions[0]);
						triangle.setWidth(xPositions[2]-xPositions[0]);
						triangle.setHeight(yPositions[2]-yPositions[0]);
					} else if (GFX2.resizeIndex == 3) {
						
							
							xPositions[0]=e.getX();
							xPositions[1]=e.getX();
						triangle.setXPos(xPositions[0]);
						triangle.setYPos(yPositions[0]);
						triangle.setWidth(xPositions[2]-xPositions[0]);
						triangle.setHeight(yPositions[2]-yPositions[0]);
						
					} else if (GFX2.resizeIndex == 4) {
						xPositions[2]=e.getX();
						triangle.setXPos(xPositions[0]);
						triangle.setYPos(yPositions[0]);
						triangle.setWidth(xPositions[2]-xPositions[0]);
						triangle.setHeight(yPositions[2]-yPositions[0]);
					} else if (GFX2.resizeIndex == 5) {

						xPositions[0]=e.getX();
						yPositions[0]=firsty-(e.getY()-GFX2.startMoveY);
						xPositions[1]=e.getX();
						yPositions[1]=e.getY();
						xPositions[2]=(firstx+lastx)-(e.getX()-GFX2.startMoveX);
						yPositions[2]=e.getY();
						
						triangle.setXPos(xPositions[0]);
						triangle.setYPos(yPositions[0]);
						triangle.setWidth(xPositions[2]-xPositions[0]);
						triangle.setHeight(yPositions[2]-yPositions[0]);
					} else if (GFX2.resizeIndex == 6) {
						yPositions[1]=e.getY();
						yPositions[2]=e.getY();
						triangle.setXPos(xPositions[0]);
						triangle.setYPos(yPositions[0]);
						triangle.setWidth(xPositions[2]-xPositions[0]);
						triangle.setHeight(yPositions[2]-yPositions[0]);
					} else if (GFX2.resizeIndex == 7) {
						xPositions[2]=e.getX();
						yPositions[2]=e.getY();
						
						triangle.setXPos(xPositions[0]);
						triangle.setYPos(yPositions[0]);
						triangle.setWidth(xPositions[2]-xPositions[0]);
						triangle.setHeight(yPositions[2]-yPositions[0]);
					} else if (GFX2.resizeIndex == 8) {
						triangle.setXPos((e.getX() - GFX2.startMoveX) + firstx);
						triangle.setYPos((e.getY() - GFX2.startMoveY) + firsty);

					}
					int xAxis = triangle.getXPos();
					int yAxis = triangle.getYPos();
					int width = triangle.getWidth();
					int height = triangle.getHeight();
					//GFX2.myPanel.width = triangle.getWidth();
					//GFX2.myPanel.height = triangle.getHeight();
					
					//GFX2.myPanel.height = e.getY() - GFX2.myPanel.y;
					//GFX2.tempShape = new triangleShape(GFX2.myPanel.x, GFX2.myPanel.y, GFX2.myPanel.width, GFX2.myPanel.height, PaintView.drawingColor);
					ShapeLibrary tempLib = new ShapeLibrary();
					for(int i = 0; i < TheCanvas.myLib.getSize(); i++)
					{
						if(i==GFX2.shapeIndex)
						{
							tempLib.addShape(triangle);
						}
						else
						{
							tempLib.addShape(TheCanvas.myLib.getElement(i));
						}
					}
					TheCanvas.myLib=new ShapeLibrary();
					TheCanvas.myLib = tempLib;
					//GFX2.myPanel.repaint();
				}
				
			}
		});
	}
}
