package paintGUI.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TheCanvas extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int x = 0;
	int y = 0;
	int width = 0;
	int height = 0;
	boolean running;
	static JButton button;
	static ShapeLibrary myLib;
	static Stack<ShapeLibrary> undoStack;
	static Stack<ShapeLibrary> redoStack;

	TheCanvas() {
		running = true;
		myLib = new ShapeLibrary();
		undoStack = new Stack<ShapeLibrary>();
		redoStack = new Stack<ShapeLibrary>();

		undoStack.push(new ShapeLibrary());
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// g2d.setStroke(new BasicStroke(3, BasicStroke.CAP_BUTT,
		// BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
		setBackground(Color.WHITE);

		for (int i = 0; i < myLib.getSize(); i++) {
			if (GFX2.editMode == 0 && GFX2.shapeIndex == i) {

			} else {
				g2d.setColor(myLib.getElement(i).getColor());
				// System.out.println(myLib.getElement(i).getColor());
				g2d.draw(myLib.getElement(i).drawShape());
			}
			// (myLib.getElement(i).getxPos(), myLib.getElement(i).getyPos(),
			// myLib.getElement(i).getWidth(), myLib.getElement(i).getHeight());
		}
		// if (width < 0 && height < 0) {
		if (GFX2.tempShape != null) {
			g2d.setColor(GFX2.tempShape.getColor());
			// GFX2.koko.setColor(Color.BLACK);
			g2d.draw(GFX2.tempShape.drawShape());
		}
		if (GFX2.editMode == 0 && GFX2.shapeIndex != -1) {
			g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0));
			g2d.setColor(PaintView.drawingColor);
			g2d.draw(myLib.getElement(GFX2.shapeIndex).drawShape());
			for(int i = 0 ; i < myLib.getElement(GFX2.shapeIndex).numOfResizePoints(); i++)
			{
				//System.out.println("HI");
				g2d.fill(myLib.getElement(GFX2.shapeIndex).resizePoints(i));
			}
			
		}
		// } else if (width < 0) {
		// g.fillOval(x + width, y, Math.abs(width), height);
		// } else if (height < 0) {
		// g.fillOval(x, y + height, width, Math.abs(height));
		// } else {
		// g.fillOval(x, y, width, height);
		// }
		// System.out.println("aaaaa");
	}

	public void reset() {
		x = 0;
		y = 0;
		width = 0;
		height = 0;
		GFX2.shapeIndex = -1;
		GFX2.prevShapeIndex = -1;
		//
		// GFX2.koko.setWidth(0); GFX2.koko.setHeight(0); GFX2.koko.setxPos(0);
		// GFX2.koko.setyPos(0);

	}

	// public static void main() {
	//
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	//
	// button = new JButton("color");
	// PanelTest window = new PanelTest();
	// window.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	// @Override
	public void run() {

		// TODO Auto-generated method stub

		// while (true) {
		Timer timer = new Timer(0, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try { Thread.sleep(20); } catch (InterruptedException e) {
				    e.printStackTrace(); }
				if (running)
					repaint();
				// System.out.println("asdasd");

				GFX2.myPanel.setBounds(77, 34, GFX.myFrame2.getWidth() - 100-100, GFX.myFrame2.getHeight() - 80);
				GFX.mainView.stateChanged(null);
			}
		});
		timer.start();
		
		 try { Thread.sleep(100); } catch (InterruptedException e) {
		  e.printStackTrace(); }
		 
		// }
	}

	public void undo() {
		try {
			if (undoStack.size() > 1) {

				System.out.println(myLib.getSize());

				//redoStack.push(undoStack.pop());
				ShapeLibrary temp = new ShapeLibrary();
				for (int i = 0; i < undoStack.peek().getSize(); i++) {
					temp.addShape(undoStack.peek().getElement(i));
				}
				redoStack.push(temp);
				undoStack.pop();
				System.out.println("redo size " + redoStack.size());
				myLib = new ShapeLibrary();
				for (int j = 0; j < undoStack.peek().getSize(); j++) {
					myLib.addShape(undoStack.peek().getElement(j));
				}

				reset();
				repaint();
			} else {
				System.out.println("mada5alsh fel if");
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("undo");
		}
	}

	public void redo() {
		try {
			
			redoStack.peek();
			ShapeLibrary temp2 = new ShapeLibrary();
			for (int i = 0; i < redoStack.peek().getSize(); i++) {
				temp2.addShape(redoStack.peek().getElement(i));
			}
			undoStack.push(temp2);
			//temp2.clear();
			System.out.println("pushed back to undo after redo");
			redoStack.pop();
			myLib = new ShapeLibrary();
			
			ShapeLibrary temp3 = new ShapeLibrary();
			for (int i = 0; i < temp2.getSize(); i++) {
				temp3.addShape(temp2.getElement(i));
			}
			myLib=temp3;
			
			//debug
			
			//debug
			
			reset();
			repaint();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("redo");
			// e.printStackTrace();
		}
	}
}
