package com.mycompany.app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Hello world!
 *
 */

public class App
{
    public static void main( String[] args )
    {

     String result ="";
	 InputStream is;

	    try {
	        is = new FileInputStream("input.json");

	        is.close();
	    } catch (FileNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    try {
			JSONObject jsonObj = new JSONObject(input);
		//	  Log.d("@@@@",jsonObj.toString());
			  //lets try to parse this object..
			  //get the names
			  //get the orderIds
			  //JSONObject data = jsonObj.getJSONObject("data");
			  //one level deeper
			  //data = data.getJSONObject("data");
			  //JSONArray array = new JSONArray(data);
			  //Log.d("@@@@",data.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        System.out.println( "Hello World!" );



    }


}
