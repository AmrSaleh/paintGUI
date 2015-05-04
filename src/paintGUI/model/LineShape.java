package paintGUI.model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;

import javax.swing.JButton;

public class LineShape extends RandShape {
	
	static{
		
		JButton line = new JButton("Line");
		  line.setBounds(851, 130, 88, 25);
		  PaintView.myButtons.add(line);
		  //PaintView.contentPane.repaint();

		  line.addActionListener(new ActionListener() {
			  
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

								e2.printStackTrace();
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
					GFX2.tempShape = new LineShape(GFX2.myPanel.x, GFX2.myPanel.y, GFX2.myPanel.width, GFX2.myPanel.height, PaintView.drawingColor, 1);
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
	public LineShape()
	{
		
	}
	public LineShape(int xPos, int yPos, int xPos2, int yPos2, Color color, int type) {
		super(xPos, yPos, (xPos2 + xPos), (yPos2 + yPos), color, type);
	}
	
	public boolean contain(double x, double y) {
		Shape line = new Line2D.Double(getXPos(), getYPos(), getWidth(),
				getHeight());
		if (line.intersects(x, y, 5, 5) || line.intersects(x - 5, y - 5, 5, 5)) {
			return true;
		} else {
			return false;
		}

	}

	public Shape drawShape() {
		Shape line = null;
		line = new Line2D.Double(getXPos(), getYPos(), getWidth(), getHeight());
		return line;
	}
	
	public RandShape clone(){
		RandShape newLine = new LineShape(getXPos(), getYPos(), getWidth()-getXPos(), getHeight()-getYPos(), getColor(), 1);
		return newLine;
	}
	public int numOfResizePoints()
	{
		return 3;
	}
	public Shape resizePoints(int i){
		RandShape newResize = null;
		if(i==0){
			newResize = new ResizeShape(getXPos()-4, getYPos()-4,
					8, 8, Color.BLACK, 4);
		}
		else if(i==1){
			newResize = new ResizeShape(getWidth()-4, getHeight()-4,
					8, 8, Color.BLACK, 4);
		}
		else if(i==2){
			newResize = new ResizeShape((getWidth()+getXPos())/2-4, (getHeight()+getYPos())/2-4,
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
						GFX2.myPanel.repaint();
						//TheCanvas.myLib.getElement(GFX2.shapeIndex).edit();
						//GFX2.myPanel.reset();
						//GFX2.myPanel.repaint();
					} else if (GFX2.shapeIndex == -1
							&& GFX2.prevShapeIndex != -1) {
						GFX.resizeLib.clear();
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
						GFX.resizeLib.clear();
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
				GFX2.myPanel.x = arg0.getX();
				GFX2.myPanel.y = arg0.getY();
				GFX2.myPanel.width = 0;
				GFX2.myPanel.height = 0;
				
				//GFX2.resize=false;
				for(int i = 0; i < GFX.resizeLib.getSize(); i++)
				{
					if(GFX.resizeLib.getElement(i).contain(arg0.getX(), arg0.getY()))
					{
						System.out.println(GFX.resizeLib.getElement(i).getXPos() + " " + GFX.resizeLib.getElement(i).getYPos() + " "+GFX.resizeLib.getElement(i).getWidth() + " "+GFX.resizeLib.getElement(i).getHeight() + " " + arg0.getX() + " "+arg0.getY());
						GFX2.resize=true;
						GFX2.resizeIndex=i;
						GFX2.startMoveX=arg0.getX();
						GFX2.startMoveY=arg0.getY();
						GFX2.myPanel.running = true;
						firstx=TheCanvas.myLib.getElement(GFX2.shapeIndex).getXPos();
						firsty=TheCanvas.myLib.getElement(GFX2.shapeIndex).getYPos();
						lastx=TheCanvas.myLib.getElement(GFX2.shapeIndex).getWidth();
						lasty=TheCanvas.myLib.getElement(GFX2.shapeIndex).getHeight();
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
					RandShape line = TheCanvas.myLib.getElement(GFX2.shapeIndex).clone();
					if(GFX2.resizeIndex==0)
					{
						
						line.setXPos(e.getX()) ;
						line.setYPos(e.getY()) ;
//						int x = line.getxPos();
//						int y = line.getyPos();
//						line.setxPos(line.getWidth());
//						line.setyPos(line.getHeight());
//						line.setWidth(x);
//						line.setHeight(y);
					}
					else if(GFX2.resizeIndex==1)
					{
						line.setWidth(e.getX()) ;
						line.setHeight(e.getY()) ;
					}
					else if(GFX2.resizeIndex==2)
					{
						line.setXPos((e.getX()-GFX2.startMoveX)+firstx);
					      line.setYPos((e.getY() - GFX2.startMoveY)+firsty);
					      line.setWidth((e.getX()-GFX2.startMoveX)+lastx);
					      line.setHeight((e.getY() - GFX2.startMoveY)+lasty);
						//line.setWidth(GFX2.myPanel.width + (GFX2.xCoordinate-e.getX())) ;
						//line.setHeight(GFX2.myPanel.height + (GFX2.yCoordinate-e.getY())) ;
					}
					
					//GFX2.myPanel.height = e.getY() - GFX2.myPanel.y;
					//GFX2.tempShape = new LineShape(GFX2.myPanel.x, GFX2.myPanel.y, GFX2.myPanel.width, GFX2.myPanel.height, PaintView.drawingColor);
					ShapeLibrary tempLib = new ShapeLibrary();
					for(int i = 0; i < TheCanvas.myLib.getSize(); i++)
					{
						if(i==GFX2.shapeIndex)
						{
							tempLib.addShape(line);
						}
						else
						{
							tempLib.addShape(TheCanvas.myLib.getElement(i));
						}
					}
					TheCanvas.myLib=new ShapeLibrary();
					TheCanvas.myLib = tempLib;
					GFX2.myPanel.repaint();
				}
				
			}
		});
	}
	

}
