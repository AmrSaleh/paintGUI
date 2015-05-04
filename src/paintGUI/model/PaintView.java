package paintGUI.model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PaintView implements ChangeListener {
	private class theHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if (temp != null)
				temp.setSelected(false);

			GFX2.myPanel.reset();
			TheCanvas.myLib = new ShapeLibrary();
			GFX2.myPanel.repaint();

			ShapeLibrary temp = new ShapeLibrary();
			for (int k = 0; k < TheCanvas.myLib.getSize(); k++) {
				temp.addShape(TheCanvas.myLib.getElement(k));
			}

			try {
				TheCanvas.undoStack.push(temp);
				System.out.println("pushed");
				TheCanvas.redoStack.clear();
			} catch (Exception e2) {

				// TODO: handle exception
			}
			// GFX2.myPanel.undoStack.push(TheCanvas.myLib);

			// System.out.println("btnClear"+shapeToDraw);

		}
	}

	public static ArrayList<JButton> myButtons;
	private static int shapeToDraw = -1;
	public static Color drawingColor = Color.BLACK;
	// static JButton button;
	GFX2 drawingPaper;
	public static JPanel contentPane;

	public static int getShapeToDraw() {
		return shapeToDraw;
	}

	static JButton btnClear, btnUndo, btnRedo, btnAddClass, btnSave, btnLoad;

	private JButton temp;

	/**
	 * Create the frame.
	 */
	public PaintView() {

		// System.out.println("cons");
		myButtons = new ArrayList<JButton>();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setSize(851, 485);

		// setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setVisible(true);

		final JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(12, 34, 60, 376);
		panel.setBackground(Color.BLACK);
		contentPane.add(panel);
		theHandler handler = new theHandler();

		btnClear = new JButton("Clear");
		btnClear.setBounds(12, 34 + 376 + 10, 60, 30);
		btnClear.addActionListener(handler);
		contentPane.add(btnClear);

		btnUndo = new JButton("Undo");
		btnUndo.setBounds(12, 34 + 376 + 10 + 30 + 10, 60, 25);
		btnUndo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GFX2.myPanel.undo();
			}
		});
		contentPane.add(btnUndo);

		btnRedo = new JButton("Redo");
		btnRedo.setBounds(12, 34 + 376 + 10 + 30 + 10 + 25 + 10, 60, 25);
		btnRedo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GFX2.myPanel.redo();
			}
		});
		contentPane.add(btnRedo);

		drawingPaper = new GFX2();
		// drawingPaper.myPanel.setBounds(77,34,contentPane.getWidth(),contentPane.getHeight());
		contentPane.add(GFX2.myPanel);

		JButton createButton = new JButton("Choose color");
		createButton.setBounds(32, 5, 107, 25);
		createButton.setActionCommand("Create Color Dialog");
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// this users a JColorchooser instance in combination
				// with a JDialog to create a color chooser dialog. It's
				// modeless
				// and the OK and Cancel buttons can be listened to.
				final JColorChooser colorChooser = new JColorChooser();
				JDialog dialog = JColorChooser.createDialog(contentPane, "Choose Color", false, colorChooser, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						// this actionListenerr is for the OK button

						drawingColor = colorChooser.getColor();
						panel.setBackground(colorChooser.getColor());
					}
				}, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent event) {
						// this actionListener is for the cancel button
					}
				});
				dialog.setVisible(true);
				drawingPaper.myPanel.repaint();
			}
		});
		contentPane.add(createButton);
		btnAddClass = new JButton("Add");
		btnAddClass.setBounds(12, 34 + 376 + 10 + 30 + 10 + 25 + 10 + 25 + 10, 60, 25);
		btnAddClass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String userhome = System.getProperty("user.home");
				JFileChooser chooser = new JFileChooser(userhome + "\\Desktop");

				FileNameExtensionFilter filter = new FileNameExtensionFilter("CLASS Files", "class");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(contentPane);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());

					File source = new File(chooser.getSelectedFile().getPath());
					File dest = new File("bin\\paintGUI\\model\\" + chooser.getSelectedFile().getName());
					try {
						copyFile(source, dest);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					try {
						Class.forName("paintGUI.model." + chooser.getSelectedFile().getName().replace(".class", ""));
					} catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					addButtons();

				}
			}
		});
		contentPane.add(btnAddClass);

		btnSave = new JButton("Save");
		btnSave.setBounds(32 + 107 + 20, 5, 107, 25);
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String userhome = System.getProperty("user.home");
				JFileChooser chooser = new JFileChooser(userhome + "\\Desktop");

				FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON & XML Files", "json" ,"xml");
				chooser.setFileFilter(filter);

				int returnVal = chooser.showSaveDialog(contentPane);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to save this file: " + chooser.getSelectedFile().getName());

					java.io.File file = chooser.getSelectedFile();
					String file_name = file.toString();

					if (!file_name.endsWith(".JSON") && !file_name.endsWith(".json")) {
						if (!file_name.endsWith(".XML") && !file_name.endsWith(".xml")) {
							file_name += ".json";
							System.out.println(file_name);

							ToJson.SaveToJson(TheCanvas.myLib, file_name);
						}else{
							System.out.println(file_name);

							try {
								toXML.serializeObjectToXML(file_name, TheCanvas.myLib.getlist());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}else{

					System.out.println(file_name);

					ToJson.SaveToJson(TheCanvas.myLib, file_name);
					}

				}
			}
		});
		contentPane.add(btnSave);

		btnLoad = new JButton("Load");
		btnLoad.setBounds(32 + (2 * (107 + 20)), 5, 107, 25);
		btnLoad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String userhome = System.getProperty("user.home");
				JFileChooser chooser = new JFileChooser(userhome + "\\Desktop");

				FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON & XML Files", "json" ,"xml");
				chooser.setFileFilter(filter);

				int returnVal = chooser.showOpenDialog(contentPane);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());

					java.io.File file = chooser.getSelectedFile();
					String file_name = file.toString();
					System.out.println(file_name);

					ShapeLibrary loadedShapes=new ShapeLibrary();
					if (file_name.endsWith(".JSON") || file_name.endsWith(".json")) {

						System.out.println(file_name);
						loadedShapes = ToJson.LoadfromJson(file_name);
						if(loadedShapes!=null)
						{
							TheCanvas.myLib=new ShapeLibrary();
							TheCanvas.myLib=loadedShapes;
							GFX2.myPanel.repaint();
						}
						for (int i = 0; i < loadedShapes.getSize(); i++) {
							System.out.println("element " + i + " " + loadedShapes.getElement(i));
						}

						for (int i = 0; i < TheCanvas.myLib.getSize(); i++) {
							System.out.println("mylib " + i + " " + TheCanvas.myLib.getElement(i));
						}

					} else if (file_name.endsWith(".XML") || file_name.endsWith(".xml")) {
						System.out.println(file_name);
						try {
							loadedShapes.addList(toXML.deserializeXMLToObject(file_name));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if(loadedShapes!=null)
						{
							TheCanvas.myLib=new ShapeLibrary();
							TheCanvas.myLib=loadedShapes;
							GFX2.myPanel.repaint();
						}
						for (int i = 0; i < loadedShapes.getSize(); i++) {
							System.out.println("element " + i + " " + loadedShapes.getElement(i));
						}

						for (int i = 0; i < TheCanvas.myLib.getSize(); i++) {
							System.out.println("mylib " + i + " " + TheCanvas.myLib.getElement(i));
						}

					}

				}
			}
		});
		contentPane.add(btnLoad);

	}

	private static void copyFile(File sourceFile, File destFile) throws IOException {
		if (!sourceFile.exists()) {
			return;
		}
		if (!destFile.exists()) {
			destFile.createNewFile();
		}
		FileChannel source = null;
		FileChannel destination = null;
		source = new FileInputStream(sourceFile).getChannel();
		destination = new FileOutputStream(destFile).getChannel();
		if (destination != null && source != null) {
			destination.transferFrom(source, 0, source.size());
		}
		if (source != null) {
			source.close();
		}
		if (destination != null) {
			destination.close();
		}

	}

	public static void addButtons() {
		for (int k = 0; k < myButtons.size(); k++) {
			contentPane.add(myButtons.get(k));

		}
		contentPane.repaint();

	}

	public void setShapeToDraw(int shapeToDraw) {
		PaintView.shapeToDraw = shapeToDraw;
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		for (int i = 0; i < myButtons.size(); i++) {
			myButtons.get(i).setBounds(contentPane.getWidth() - 88 - 10, 33 + (i * 30), 88, 25);
		}

	}

}
