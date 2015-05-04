package paintGUI.model;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class GFX {

	public static JFrame myFrame2;
	
	public static PaintView mainView;
	public static ShapeLibrary resizeLib = new ShapeLibrary();
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		mainView = new PaintView();
		myFrame2 = new JFrame();
		myFrame2.setTitle("Amr & Khatib Paint");
		myFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		myFrame2.add(PaintView.contentPane);
		myFrame2.setSize(900, 600);
		myFrame2.setMinimumSize(new Dimension(900+60, 500));

		myFrame2.setVisible(true);

		// GFX2 mygfx= new GFX2();

		// mygfx.myPanel.setBounds(0, 0,300,200);
		mainView.drawingPaper.start();

		// mygfx.start();
		
	     
	     //Class.forName("paintGUI.model.TriangleShape90");
	     //Class.forName("paintGUI.model.SelectShape");
		try {
			Class.forName("paintGUI.model.LineShape");
		    } catch (ClassNotFoundException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		try {
			Class.forName("paintGUI.model.RectangleShape");
		    } catch (ClassNotFoundException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		try {
			Class.forName("paintGUI.model.SquareShape");
		    } catch (ClassNotFoundException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		try {
			Class.forName("paintGUI.model.EllipseShape");
		    } catch (ClassNotFoundException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		try {
			Class.forName("paintGUI.model.CircleShape");
		    } catch (ClassNotFoundException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		try {
			Class.forName("paintGUI.model.TriangleShape90");
		    } catch (ClassNotFoundException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		try {
			Class.forName("paintGUI.model.SelectShape");
		    } catch (ClassNotFoundException e) {
		     // TODO Auto-generated catch block
		     e.printStackTrace();
		    }
		PaintView.addButtons();
	}

}
