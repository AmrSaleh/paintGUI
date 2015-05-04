package paintGUI.model;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//
//import org.*;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

//import com.google.gson.Gson;
//import com.owlike.genson.Genson;
//import com.owlike.genson.TransformationException;
//import com.owlike.genson.convert.DefaultConverters.PrimitiveConverterFactory.longConverter;
//package com.mkyong.core;

//import java.io.File;
//import java.io.IOException;
//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.annotate.JsonIgnoreProperties;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;

//@JsonIgnoreProperties(ignoreUnknown = true)

public class ToJson {
	
//	 public static void main(String[] args) {
//		 
//		 RandShape line= new LineShape(5, 6,7, 8, Color.BLACK, 1);
//		 ShapeLibrary ss=new ShapeLibrary();
//		 ss.addShape(line);
//		 ss.addShape(line);
//		 SaveToJson(ss, "D:\\duck.json");
//		 LoadfromJson("D:\\duck.json");
//	 }

	public static void SaveToJson(ShapeLibrary myShapes, String path) {
		
		JSONObject obj = new JSONObject();
		JSONArray arr = new JSONArray();
		for(int i = 0; i < myShapes.getSize(); i++)
		{
			arr.add(myShapes.getElement(i).toString());
		}
		obj.put("Shape", arr);
	 
		try {
	 
			FileWriter file = new FileWriter(path);
			file.write(obj.toString());
			file.flush();
			file.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
		//System.out.print(obj);
	 
	     }
		
		
//		ObjectMapper mapper = new ObjectMapper();
//	 
//		try {
//	 
//			// convert user object to json string, and save to a file
//			mapper.writeValue(new File(path), myShapes);
//	 
//			// display to console
//			System.out.println(mapper.writeValueAsString(myShapes));
//	 
//		} catch (JsonGenerationException e) {
//	 
//			e.printStackTrace();
//	 
//		} catch (JsonMappingException e) {
//	 
//			e.printStackTrace();
//	 
//		} catch (IOException e) {
//	 
//			e.printStackTrace();
//	 
//		}
		
		
		
//		Genson genson = new Genson.Builder().setWithClassMetadata(true).setUseRuntimeTypeForSerialization(true).create();
//
//		// and now just use it to serialize/deser
//		String json = null;
//		try {
//			
//				json = genson.serialize(myShapes.getlist());
//
//				try {
//					// write converted json data to a file named "file.json"
//					FileWriter writer = new FileWriter(path);
//					writer.write(json);
//					writer.close();
//
//				} catch (IOException e) {
//					e.printStackTrace();
//					System.out.println("error in saving");
//				}
//			
//		} catch (TransformationException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		
	
		
//		 Gson gson = new Gson();
//
////		 convert java object to JSON format,
////		 and returned as JSON formatted string
//
//		 String json = gson.toJson(myShapes.getElement(0));
//
//		System.out.println("Saved " + json);
//
//		try {
//			// write converted json data to a file named "file.json"
//			FileWriter writer = new FileWriter(path);
//			writer.write(json);
//			writer.close();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.out.println("error in saving");
//		}
	

	public static ShapeLibrary LoadfromJson(String path) {

		ShapeLibrary shapeLoad = new ShapeLibrary();
		JSONParser parser = new JSONParser();
		//TheCanvas.myLib.clear();
		try {
	 
			Object obj = parser.parse(new FileReader(path));
			ShapeLibrary myShapes = new ShapeLibrary();
			JSONObject jsonObject = (JSONObject) obj;
	 
			
			// loop array
			JSONArray msg = (JSONArray) jsonObject.get("Shape");
			Iterator<String> iterator = msg.iterator();
			while (iterator.hasNext()) {
				String s = iterator.next();
				System.out.println(s);
				String[] elements = s.split(" ");
				RandShape shape = identify(elements);
				shapeLoad.addShape(shape);
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
//		ObjectMapper mapper = new ObjectMapper();
//		 
//		try {
//	 
//			// read from file, convert it to user class
//			ShapeLibrary lib = mapper.readValue(new File(path), ShapeLibrary.class);
//	 
//			// display to console
//			System.out.println(lib.toString());
//			return lib;
//		} catch (JsonGenerationException e) {
//	 
//			e.printStackTrace();
//	 
//		} catch (JsonMappingException e) {
//	 
//			e.printStackTrace();
//	 
//		} catch (IOException e) {
//	 
//			e.printStackTrace();
//	 
//		}
//		return null;
		
		// Gson gson = new Gson();
//		JSONParser parser=new JSONParser();
		
		
		
//		Genson genson = new Genson.Builder().setWithClassMetadata(true).setUseRuntimeTypeForSerialization(true).create();
//
////		Genson genson = new Genson();
//		try {
//
//			BufferedReader br = new BufferedReader(new FileReader(path));
//
//			// convert the json string back to object
//			ShapeLibrary obj = new ShapeLibrary();
//			try {
//				ArrayList<RandShape> tempList;
//			
//				tempList = genson.deserialize(br, ArrayList.class );
//				System.out.println("loaded " + tempList.toString());
//				
//				obj.addList(tempList);
//			} catch (TransformationException e) {
//				// TODO Auto-generated catch block
//				System.out.println("error in loading");
//				e.printStackTrace();
//			}
//
//			
//			System.out.println("loaded2 " + obj.toString());
//
//			return obj;
//		} catch (IOException e) {
//			// e.printStackTrace();
//			System.out.println("error in loading");
//		}
//
//		return null;

		//

		return shapeLoad;
		
	}
	public static RandShape identify(String[] elements)
	{
		Color color;
		switch(Integer.parseInt(elements[7]))
		{
		case 1:
			RandShape shape1 = new LineShape(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), Integer.parseInt(elements[2])-Integer.parseInt(elements[0]), Integer.parseInt(elements[3])-Integer.parseInt(elements[1]), color=new Color(Integer.parseInt(elements[4]),Integer.parseInt(elements[5]),Integer.parseInt(elements[6])), Integer.parseInt(elements[7]));
			return shape1;
			
		case 2:
			RandShape shape2 = new EllipseShape(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), Integer.parseInt(elements[2]), Integer.parseInt(elements[3]), color=new Color(Integer.parseInt(elements[4]),Integer.parseInt(elements[5]),Integer.parseInt(elements[6])), Integer.parseInt(elements[7]));
			return shape2;
		case 3:
			RandShape shape3 = new CircleShape(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), Integer.parseInt(elements[2]), Integer.parseInt(elements[3]), color=new Color(Integer.parseInt(elements[4]),Integer.parseInt(elements[5]),Integer.parseInt(elements[6])), Integer.parseInt(elements[7]));
			return shape3;
		case 4:
			RandShape shape4 = new RectangleShape(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), Integer.parseInt(elements[2]), Integer.parseInt(elements[3]), color=new Color(Integer.parseInt(elements[4]),Integer.parseInt(elements[5]),Integer.parseInt(elements[6])), Integer.parseInt(elements[7]));
			return shape4;
		case 5:
			RandShape shape5 = new SquareShape(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), Integer.parseInt(elements[2]), Integer.parseInt(elements[3]), color=new Color(Integer.parseInt(elements[4]),Integer.parseInt(elements[5]),Integer.parseInt(elements[6])), Integer.parseInt(elements[7]));
			return shape5;
		case 6:
			RandShape shape6 = new TriangleShape90(Integer.parseInt(elements[0]), Integer.parseInt(elements[1]), Integer.parseInt(elements[2]), Integer.parseInt(elements[3]), color=new Color(Integer.parseInt(elements[4]),Integer.parseInt(elements[5]),Integer.parseInt(elements[6])), Integer.parseInt(elements[7]));
			return shape6;
			
			default:
				return null;
			
		}
	}
}
