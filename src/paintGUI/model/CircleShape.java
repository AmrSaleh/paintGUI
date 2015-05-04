package paintGUI.model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;


public class CircleShape extends RandShape {
	static{
		  JButton circ = new JButton("Circle");
		  circ.setBounds(851, 100, 88, 25);
		  PaintView.myButtons.add(circ);
		  //PaintView.contentPane.repaint();
		  circ.addActionListener(new ActionListener() {
			   
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
							GFX2.tempShape = new CircleShape(GFX2.myPanel.x, GFX2.myPanel.y, GFX2.myPanel.width, GFX2.myPanel.height, PaintView.drawingColor, 3);
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
	public CircleShape() {
		// TODO Auto-generated constructor stub
	}
	public CircleShape(int xPos, int yPos, int diameter, int secondDiameter,
			Color color, int type) {
		super(xPos, yPos, (diameter), (diameter), color, type);
	}

	public boolean contain(double x, double y) {
		Shape circle = new Ellipse2D.Double(getXPos(), getYPos(), getWidth(),
				getWidth());
		if (circle.contains(x, y)) {
			return true;
		} else {
			return false;
		}
	}

	public Shape drawShape() {
		Shape circle = null;
		circle = new Ellipse2D.Double(getXPos(), getYPos(), getWidth(),
				getWidth());
		return circle;
	}
	public RandShape clone(){
		RandShape newCircle = new CircleShape(getXPos(), getYPos(), getWidth(), getHeight(), getColor(), 3);
		return newCircle;
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
					
					RandShape circle = TheCanvas.myLib.getElement(GFX2.shapeIndex).clone();
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
							circle.setYPos(e.getY());
							circle.setXPos(firstx+(e.getY()-firsty));
							circle.setHeight(lasty - (e.getY() - firsty));
							circle.setWidth(lasty - (e.getY() - firsty));
						}else{
							circle.setYPos(firsty+lasty);
							circle.setXPos((firstx+lastx)-(e.getY()-(firsty+lasty)));
							circle.setHeight(e.getY()-(firsty+lasty));
							circle.setWidth(e.getY()-(firsty+lasty));
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
							circle.setXPos(e.getX());
							circle.setYPos(firsty+(e.getX()-firstx));
							circle.setWidth((lastx)+(firstx-e.getX()));
							circle.setHeight(circle.getWidth());
						
						}else{
							circle.setXPos(firstx+lastx);
							circle.setYPos((firsty+lasty)-(e.getX()-(firstx+lastx)));
							circle.setWidth(e.getX()-(firstx+lastx));
							circle.setHeight(circle.getWidth());
						}
					} else if (GFX2.resizeIndex == 4) {
						if (e.getX() < firstx){
							circle.setXPos(e.getX());
							circle.setYPos((firsty+lasty)-(firstx-e.getX()));
							circle.setWidth((firstx-e.getX()));
							circle.setHeight((firstx-e.getX()));
							
						}else{
							circle.setXPos(firstx);
							circle.setYPos((firsty)-(e.getX()-(firstx+lastx)));
							
							circle.setWidth(e.getX()-(firstx));
							circle.setHeight(e.getX()-(firstx));
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
							circle.setYPos(firsty);
//							square.setxPos(firstx+lastx+(e.getY()-(firsty+lasty)));
							circle.setHeight(e.getY()-firsty);
							circle.setWidth(circle.getHeight());
						}else{
							circle.setYPos(e.getY());
							//square.setxPos(xPos);
							circle.setHeight(firsty-e.getY());
							circle.setWidth(circle.getHeight());
							
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
						circle.setXPos((e.getX() - GFX2.startMoveX) + firstx);
						circle.setYPos((e.getY() - GFX2.startMoveY) + firsty);

					}
					int xAxis = circle.getXPos();
					int yAxis = circle.getYPos();
					int width = circle.getWidth();
					int height = circle.getHeight();
					//GFX2.myPanel.width = circle.getWidth();
					//GFX2.myPanel.height = circle.getHeight();
					if (circle.getWidth() < 0 && circle.getHeight() < 0) {
						circle.setXPos(e.getX());
						circle.setWidth(Math.abs(width));
						circle.setYPos(e.getY());
						circle.setHeight(Math.abs(height));
					} else if (circle.getWidth() < 0) {
						circle.setXPos(e.getX());
						circle.setWidth(Math.abs(width));
					} else if (circle.getHeight() < 0) {
						circle.setYPos(e.getY());
						circle.setHeight(Math.abs(height));
					}
					
					//GFX2.myPanel.height = e.getY() - GFX2.myPanel.y;
					//GFX2.tempShape = new circleShape(GFX2.myPanel.x, GFX2.myPanel.y, GFX2.myPanel.width, GFX2.myPanel.height, PaintView.drawingColor);
					ShapeLibrary tempLib = new ShapeLibrary();
					for(int i = 0; i < TheCanvas.myLib.getSize(); i++)
					{
						if(i==GFX2.shapeIndex)
						{
							tempLib.addShape(circle);
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
