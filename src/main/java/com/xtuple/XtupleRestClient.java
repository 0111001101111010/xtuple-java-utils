package com.xtuple;

import java.io.*;
import org.json.*;
import java.util.*;

public class XtupleRestClient{

/*    public static void main(String[] args)
    {
	    try{
	    	JSONObject foo = new JSONObject("{}");
	    }
	    catch(Exception e){

	    }

    }*/
    void thing(){
	   	try{
	    	JSONObject foo = new JSONObject("{}");
	    }
	    catch(Exception e){

	    }

    }
    String readFile(String filename){
        String input = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                input =  input+ sCurrentLine;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }//readFile End

    /*
    * Parse Issue to SHipping
    * @param
    */
   public String ParseIssueToShipping(String input) throws IOException{
       // String input = "";
     String result = "";
    try {
     JSONObject jsonObj = new JSONObject(input);
	//   System.out.println("@@@@",jsonObj.toString());
	//lets try to parse this object..
	//get the names
	//get the orderIds
	JSONObject data = jsonObj.getJSONObject("data");
	//one level deeper
	JSONArray dataDeeper = data.getJSONArray("data");
	for(int i=0;i<dataDeeper.length();i++)
	{     //System.out.println("@@@"+i+"");
	//                  //System.out.println("@@@"+dataDeeper.getJSONObject(i).toString());
	 JSONObject order = dataDeeper.getJSONObject(i).getJSONObject("order");
	 JSONObject itemSite = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("item");
	 String number = order.getString("number");
	    //System.out.println("@@@"+number);
	 String barcode = itemSite.getString("barcode").toString();
	    //System.out.println("@@@"+barcode);
	 String description = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("site").getString("description");
	    //System.out.println("@@@"+description);
	 //String Id=dataDeeper.) ;
	 //String terminalType=dataDeeper.getString("terminal_type");
	 result = description;
	}
	 }//end of try
	catch (JSONException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
		    return result;
    }//end of parse method

}