package paintGUI.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;

public class SelectShape {
	static {
		final JButton select = new JButton("Select");
		select.setBounds(851, 400, 88, 25);
		PaintView.myButtons.add(select);
		JButton delete = new JButton("Delete");
		delete.setBounds(851, 400, 88, 25);
		PaintView.myButtons.add(delete);
		// PaintView.contentPane.repaint();
		select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				GFX2.editMode = 0;
				GFX2.shapeIndex = -1;
				GFX2.prevShapeIndex = -1;
				GFX2.selectMode = false;
GFX2.myPanel.repaint();
				for (int i = 0; i < GFX2.myPanel.getMouseListeners().length; i++) {
					GFX2.myPanel.removeMouseListener(GFX2.myPanel.getMouseListeners()[i]);
				}
				for (int i = 0; i < GFX2.myPanel.getMouseMotionListeners().length; i++) {
					GFX2.myPanel.removeMouseMotionListener(GFX2.myPanel.getMouseMotionListeners()[i]);
				}

				GFX2.myPanel.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						if (GFX2.editMode == 0) {
							GFX2.prevShapeIndex = GFX2.shapeIndex;
							GFX2.shapeIndex = SelectShape.shapeSelect(e.getX(), e.getY());

							// GFX.resizeLib.clear();
							// GFX2.myPanel.reset();
							GFX2.myPanel.repaint();
							if (GFX2.shapeIndex != -1) {
								GFX2.myPanel.repaint();
								TheCanvas.myLib.getElement(GFX2.shapeIndex).edit();
								// GFX2.myPanel.reset();
								GFX2.myPanel.repaint();
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
						 * editMode = PaintView.geteditMode(); // TODO
						 * Auto-generated method stub myPanel.x = e.getX();
						 * myPanel.y = e.getY(); myPanel.width = 0;
						 * myPanel.height = 0; myPanel.running = true;
						 */
					}

					@Override
					public void mouseReleased(MouseEvent e) {

						/*
						 * // TODO Auto-generated method stub if (dragged) {
						 * dragged = false; myPanel.width = e.getX() -
						 * myPanel.x; myPanel.height = e.getY() - myPanel.y;
						 * myPanel.running = false; if (editMode != 0) {
						 * TheCanvas.myLib.addShape(tempShape);
						 * 
						 * ShapeLibrary temp3 = new ShapeLibrary(); for (int i =
						 * 0; i < TheCanvas.myLib.getSize(); i++) {
						 * temp3.addShape(TheCanvas.myLib.getElement(i)); }
						 * 
						 * try { TheCanvas.undoStack.push(temp3);
						 * System.out.println("pushed no color");
						 * TheCanvas.redoStack.clear(); } catch (Exception e2) {
						 * 
						 * // TODO: handle exception }
						 * 
						 * tempShape = null;
						 * 
						 * //debug////////////// for(int i = 0; i <
						 * myPanel.undoStack.size(); i++) { for(int j=0 ;
						 * j<myPanel.undoStack.elementAt(i).getSize();j++){
						 * System.out.println("UNDO: Library# " + i +
						 * " Shape# "+ j + " is " +
						 * myPanel.undoStack.elementAt(i
						 * ).getElement(j).toString()); } }
						 * System.out.println(""); for(int i = 0; i <
						 * myPanel.redoStack.size(); i++) { for(int j=0 ;
						 * j<myPanel.redoStack.elementAt(i).getSize();j++){
						 * System.out.println("REDO: Library# " + i +
						 * " Shape# "+ j + " is " +
						 * myPanel.undoStack.elementAt(i
						 * ).getElement(j).toString()); } }
						 * System.out.println(""); //debug///////////////
						 * 
						 * 
						 * myPanel.reset(); myPanel.repaint(); } }
						 */
					}

				});

				GFX2.myPanel.addMouseMotionListener(new MouseMotionListener() {

					@Override
					public void mouseDragged(MouseEvent e) {

					}

					@Override
					public void mouseMoved(MouseEvent e) {
						// TODO Auto-generated method stub

					}

				});
			}

		});
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < GFX2.myPanel.getMouseListeners().length; i++) {
					GFX2.myPanel.removeMouseListener(GFX2.myPanel.getMouseListeners()[i]);
				}
				for (int i = 0; i < GFX2.myPanel.getMouseMotionListeners().length; i++) {
					GFX2.myPanel.removeMouseMotionListener(GFX2.myPanel.getMouseMotionListeners()[i]);
				}

				if (GFX2.editMode == 0 && GFX2.shapeIndex != -1) {
					ShapeLibrary temp = new ShapeLibrary();
					for (int i = 0; i < TheCanvas.myLib.getSize(); i++) {
						if (GFX2.shapeIndex == i) {

						} else {
							temp.addShape(TheCanvas.myLib.getElement(i));
						}
					}
					try {
						TheCanvas.undoStack.push(temp);
						TheCanvas.redoStack.clear();
					} catch (Exception e2) {

						// TODO: handle exception
					}
					ShapeLibrary temp2 = new ShapeLibrary();
					for (int i = 0; i < TheCanvas.myLib.getSize(); i++) {
						if (GFX2.shapeIndex == i) {

						} else {
							temp2.addShape(TheCanvas.myLib.getElement(i));
						}
					}

					TheCanvas.myLib = new ShapeLibrary();
					TheCanvas.myLib = temp2;
					GFX2.myPanel.reset();
					GFX2.myPanel.repaint();
				}
				select.doClick();
			}
		});
	}

	public static int shapeSelect(int xValue, int yValue) {
		for (int i = TheCanvas.myLib.getSize() - 1; i >= 0; i--) {
			if (TheCanvas.myLib.getElement(i).contain(xValue, yValue)) {
				// System.out.println(i);
				// System.out.println("xpos "+TheCanvas.myLib.getElement(i).getxPos()+" ypos "+TheCanvas.myLib.getElement(i).getyPos());
				// System.out.println("xend "+(TheCanvas.myLib.getElement(i).getxPos()+TheCanvas.myLib.getElement(i).getWidth())+" yend "+(TheCanvas.myLib.getElement(i).getyPos()+TheCanvas.myLib.getElement(i).getHeight()));
				// System.out.println("curser x "+xValue+" curser y "+yValue);
				return i;
			}
		}
		return -1;
	}

}