package com.xtuple;

import java.io.*;
import org.json.*;
import java.util.*;

public class XtupleRestClient{

static String readFile(String filename){
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
    * @param inputjson
    * Returns Pending Ids of the Immediate Sales Order
    * #should return actually an array?
    */
public String ParseIssueToShipping(String input) throws IOException{
       // String input = "";
     String result = "";
	    try {
		    JSONObject jsonObj = new JSONObject(input);
			JSONObject data = jsonObj.getJSONObject("data");
			JSONArray dataDeeper = data.getJSONArray("data");
			for(int i=0;i<dataDeeper.length();i++)
			{
			 JSONObject order = dataDeeper.getJSONObject(i).getJSONObject("order");
			 JSONObject itemSite = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("item");
             //name
             String name = itemSite.getString("number");
                //System.out.println("@@@"+number);
			 //String number = order.getString("number");
			    //System.out.println("@@@"+number);
			 String barcode = itemSite.getString("barcode").toString();
			    //System.out.println("@@@"+barcode);
			 String description = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("site").getString("description");
			    //System.out.println("@@@"+description);
			 result = name + "," + barcode + "," + description;
             System.out.println(result);
			}
		 }//end of try
		catch (JSONException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
		}

		  	return result;
}//end of parseSalesOrder method

/*
*
* Parse Sales Order
* Return Returns the UUID of Pending Sales Orders
*/
public String ParseSalesOrderWorkflow(String input) throws IOException{
        String result ="";
        //Parse Sales Order Object
        try {
            JSONObject jsonObj = new JSONObject(input);
	        JSONObject data = jsonObj.getJSONObject("data");
	        //one level deeper
	        JSONArray dataDeeper = data.getJSONArray("data");
		        for(int i=0;i<dataDeeper.length();i++){
		            //System.out.println("@@@" +i+"");
			        //System.out.println("@@@"+dataDeeper.getJSONObject(i).toString());
			        String uuid = dataDeeper.getJSONObject(i).getJSONObject("parent").getString("uuid");
			        result = uuid +  "," + result;
		    }
            }
         catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        return result;
    }
/*
*
* Parse Sales Order workflow, on specific ActivityType
* Return Returns the UUID of Pending Sales Orders of Activty type
*/
public String ParseSalesOrderWorkflow(String input, String type) throws IOException{
        String result ="";
        //Parse Sales Order Object
        try {
            JSONObject jsonObj = new JSONObject(input);
            JSONObject data = jsonObj.getJSONObject("data");
            //one level deeper
            JSONArray dataDeeper = data.getJSONArray("data");
                for(int i=0;i<dataDeeper.length();i++){
                    //System.out.println("@@@" +i+"");
                    //System.out.println("@@@"+dataDeeper.getJSONObject(i).toString());
                if(type.equals(dataDeeper.getJSONObject(i).getString("name"))){
                    String uuid = dataDeeper.getJSONObject(i).getJSONObject("parent").getString("uuid");
                    result = uuid +  "," + result;
                }
            }
            }
         catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        return result;
    }
/*
*
* Parse a String into an Array List
* @param input string
* Return Returns the Split String
*/
        public static List<String> SplitCommaString(String input) throws IOException{
        String str = input;
        List<String> items = Arrays.asList(str.split("\\s*,\\s*"));

        return items;
        }
/*
*
* Filter and print a order from UUID
* @param input UUID string, JSON object
* Return Return the sales order object with all line items
*/
        public String FilterSalesOrderUUID(String uuid, String salesOrders) throws IOException{
        String str = uuid;
        List<String> items = Arrays.asList(str.split("\\s*,\\s*"));

        return uuid;
        }
/*
*
* Parsed Sales Order, Serialized Items
*/
public String ParseSalesOrder(String input) throws IOException{
        String result ="";
        //Parse Sales Order Object
        try {
            JSONObject jsonObj = new JSONObject(input);
            JSONObject data = jsonObj.getJSONObject("data");
            //one level deeper
            JSONArray dataDeeper = data.getJSONArray("data");
            //order information
            //Sales Order Number
             JSONObject order = dataDeeper.getJSONObject(0).getJSONObject("order");
             String number = order.getString("number");
                System.out.println("@@@"+number);
                for(int i=0;i<dataDeeper.length();i++){
                    //Barcode of item
                     JSONObject itemSite = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("item");
                     String barcode = itemSite.getString("barcode");
                        System.out.println("@@@"+barcode);
                    //Name of item
                     String itemNumber = itemSite.getString("number");
                        System.out.println("@@@"+itemNumber);
                    //item description1
                     String description = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("site").getString("description");
                    System.out.println("@@@"+description);
                    //quantity
                     String quantity = dataDeeper.getJSONObject(i).getString("ordered");
                    System.out.println("@@@Quantity:"+quantity);
                }
            }
             catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            return result;
    }

//get issuable line items
public String getIssueToShippingAtShipping(String input) throws IOException{
       // String input = "";
     String result = "";
        try {
            JSONObject jsonObj = new JSONObject(input);
            JSONObject data = jsonObj.getJSONObject("data");
            JSONArray dataDeeper = data.getJSONArray("data");
            for(int i=0;i<dataDeeper.length();i++)
            {
             String shipment = dataDeeper.getJSONObject(i).get("shipment").toString();
             int atShipping = Integer.parseInt(dataDeeper.getJSONObject(i).getString("atShipping"));
             int ordered = Integer.parseInt(dataDeeper.getJSONObject(i).getString("ordered"));
             //if (!shipment.equals(JSONObject.NULL)){
             //objects i want issuable
             if(ordered!=atShipping){
                 System.out.println(atShipping);
                 System.out.println(ordered);
                 JSONObject itemSite = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("item");
                 String barcode = itemSite.getString("barcode").toString();
                    //System.out.println("@@@"+barcode);
                 result = barcode ;
                 System.out.println(result);
               // }
                }
            }
         }//end of try
        catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

            return result;
    }
//get issuable line items
public String getIssuetoShippingShipmentNumber(String input) throws IOException{
       // String input = "";
     String result = "";
        try {
            JSONObject jsonObj = new JSONObject(input);
            JSONObject data = jsonObj.getJSONObject("data");
            JSONArray dataDeeper = data.getJSONArray("data");
            for(int i=0;i<dataDeeper.length();i++)
            {
             String shipment = dataDeeper.getJSONObject(i).get("shipment").toString();
             int atShipping = Integer.parseInt(dataDeeper.getJSONObject(i).getString("atShipping"));
             int ordered = Integer.parseInt(dataDeeper.getJSONObject(i).getString("ordered"));
             //if (!shipment.equals(JSONObject.NULL)){
             //objects i want issuable
             if(ordered==atShipping){
                 System.out.println(atShipping);
                 System.out.println(ordered);
                shipment = dataDeeper.getJSONObject(i).getJSONObject("shipment").getString("number");
                 JSONObject itemSite = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("item");
                 String barcode = itemSite.getString("barcode").toString();

                 result = shipment;
                 System.out.println(result);
               // }
                }
            }
         }//end of try
        catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }

            return result;
    }
} // end of class