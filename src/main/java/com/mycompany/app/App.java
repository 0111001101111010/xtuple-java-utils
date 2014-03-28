//package com.mycompany.app;

import java.io.*;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
/**
 * Hello world!
 *
 */

public class App
{
    public static void main( String[] args )
    {

     String result ="";
	 //InputStream is;

	     try {
             FileReadable input = new FileReadable("input.json");
             System.out.println(input);
	     } catch (FileNotFoundException e) {
	    //     // TODO Auto-generated catch block
	    //     e.printStackTrace();
	     } //catch (IOException e) {
	    //     // TODO Auto-generated catch block
	    //     e.printStackTrace();
	     //}

			//JSONObject jsonObj = new JSONObject(input);
		//	  Log.d("@@@@",jsonObj.toString());
			  //lets try to parse this object..
			  //get the names
			  //get the orderIds
			  //JSONObject data = jsonObj.getJSONObject("data");
			  //one level deeper
			  //data = data.getJSONObject("data");
			  //JSONArray array = new JSONArray(data);
			  //Log.d("@@@@",data.toString());


        System.out.println( "Hello World!" );



    }


}
