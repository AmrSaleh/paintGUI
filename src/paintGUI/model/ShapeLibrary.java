package paintGUI.model;

import java.util.ArrayList;



public class ShapeLibrary {
	private ArrayList<RandShape> library;

	ShapeLibrary() {
		library = new ArrayList<RandShape>();
	}
	
//	ShapeLibrary(ArrayList<RandShape>) {
//		library = new ArrayList<RandShape>();
//	}


	public void addShape(RandShape shape) {
		library.add(shape);
	}

	public RandShape getElement(int i) {
		return library.get(i);
	}
	public int getSize() {
		
		return library.size();
	}

	public void removeShape(RandShape shape) {
		library.remove(shape);
	}

	public void clear() {
		// TODO Auto-generated method stub
		library.clear();
		
	}
	
	public void set(int i,RandShape shape){
		library.set(i, shape);
	}
	 public String toString() {
		  // TODO Auto-generated method stub
		  return library.toString();
		 }

		 public ArrayList<RandShape> getlist() {
		  // TODO Auto-generated method stub
		  return library;
		 }

		 public void addList(ArrayList<RandShape> tempList) {
		  // TODO Auto-generated method stub
		  library.clear();
		  for(int i=0;i<tempList.size();i++){
		   library.add(tempList.get(i));
		  }
		 }

}
