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
public static List<String> ParseIssueToShipping(String input) throws IOException{
     // String input = "";
     //String result = "";
     List<String> result = new ArrayList();
	    try {
		    JSONObject jsonObj = new JSONObject(input);
			JSONObject data = jsonObj.getJSONObject("data");
			JSONArray dataDeeper = data.getJSONArray("data");
			for(int i=0;i<dataDeeper.length();i++)
			{
			 JSONObject order = dataDeeper.getJSONObject(i).getJSONObject("order");
			 JSONObject itemSite = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("item");
             String name = itemSite.getString("number");
			 String barcode = itemSite.getString("barcode").toString();
			 String description = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("site").getString("description");
			 String card = name + " " + barcode + " " + description;
             //System.out.println(result);
             //result = card + "," + result;
             result.add(card);
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
public static List<String> ParseSalesOrderWorkflowActivity(String input) throws IOException{
        //String result ="";
        //Parse Sales Order Object
        List<String> result = new ArrayList();
        try {
            JSONObject jsonObj = new JSONObject(input);
	        JSONObject data = jsonObj.getJSONObject("data");
	        //one level deeper
	        JSONArray dataDeeper = data.getJSONArray("data");
		        for(int i=0;i<dataDeeper.length();i++){
		            //System.out.println("@@@" +i+"");
			        //System.out.println("@@@"+dataDeeper.getJSONObject(i).toString());
			        String uuid = dataDeeper.getJSONObject(i).getJSONObject("parent").getString("uuid");
			        //result = uuid +  "," + result;
                    result.add(uuid);
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
public String ParseSalesOrderWorkflowActivity(String input, String type) throws IOException{
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
* Parse a String into an Array List
* @param input string
* Return Returns the Split String
*/
        public static List<String> SplitCommaString(String input) throws IOException{
        String str = input;
        List<String> items = Arrays.asList(str.split("\\s*,\\s*"));

        return items;
        }

//get issuable line items
    //request like..
    //https://192.168.33.10:8443/inventory/api/v1alpha1/resources/issue-to-shipping?attributes[order.uuid][EQUALS]=X
    //where UUID = one of activity
public static List<String> getIssueToShippingAtShipping(String input) throws IOException{
       // String input = "";
     //String result = "";
        List<String> result = new ArrayList();
        try {
            JSONObject jsonObj = new JSONObject(input);
            JSONObject data = jsonObj.getJSONObject("data");
            JSONArray dataDeeper = data.getJSONArray("data");
            for(int i=0;i<dataDeeper.length();i++)
            {
             String shipment = dataDeeper.getJSONObject(i).get("shipment").toString();
             int atShipping = Integer.parseInt(dataDeeper.getJSONObject(i).getString("atShipping"));
             int ordered = Integer.parseInt(dataDeeper.getJSONObject(i).getString("ordered"));

             if(ordered>atShipping){
                 // System.out.println(ordered);
                 // System.out.println(atShipping);
             //description adding
             JSONObject itemSite = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("item");
             String name = itemSite.getString("number");
             String barcode = itemSite.getString("barcode").toString();
             String description = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("site").getString("description");


                 String card = name + " of Quantity " + Integer.toString(ordered-atShipping) + " " + description;
                 //System.out.println(card);
                 System.out.println("Barcode:"+ barcode);
                    //System.out.println("@@@"+barcode);
                 result.add(barcode);
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


/** Return an Array of Barcodes **/
public static List<String> getIssueToShippingDescriptions(String input) throws IOException{
       // String input = "";
     //String result = "";
        List<String> result = new ArrayList();
        try {
            JSONObject jsonObj = new JSONObject(input);
            JSONObject data = jsonObj.getJSONObject("data");
            JSONArray dataDeeper = data.getJSONArray("data");
            for(int i=0;i<dataDeeper.length();i++)
            {
             String shipment = dataDeeper.getJSONObject(i).get("shipment").toString();
             int atShipping = Integer.parseInt(dataDeeper.getJSONObject(i).getString("atShipping"));
             int ordered = Integer.parseInt(dataDeeper.getJSONObject(i).getString("ordered"));

             if(ordered>atShipping){
                 // System.out.println(ordered);
                 // System.out.println(atShipping);
             //description adding
             JSONObject itemSite = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("item");
             String name = itemSite.getString("number");
             String barcode = itemSite.getString("barcode").toString();
             String description = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("site").getString("description");


                 String card = name + "\n Quantity: " + Integer.toString(ordered-atShipping) + "\n From: " + description;
                 System.out.println(card);
                 //System.out.println("Barcode:"+ barcode);
                    //System.out.println("@@@"+barcode);
                 result.add(card);
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
//GET UUIDS
    /** Return an Array of Barcodes **/
public static List<String> getIssueToShippingUUIDs(String input) throws IOException{
       // String input = "";
     //String result = "";
        List<String> result = new ArrayList();
        try {
            JSONObject jsonObj = new JSONObject(input);
            JSONObject data = jsonObj.getJSONObject("data");
            JSONArray dataDeeper = data.getJSONArray("data");
            for(int i=0;i<dataDeeper.length();i++)
            {
             String shipment = dataDeeper.getJSONObject(i).get("shipment").toString();
             int atShipping = Integer.parseInt(dataDeeper.getJSONObject(i).getString("atShipping"));
             int ordered = Integer.parseInt(dataDeeper.getJSONObject(i).getString("ordered"));

             if(ordered>atShipping){
                 // System.out.println(ordered);
                 // System.out.println(atShipping);
             //description adding
             JSONObject itemSite = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("item");
             String name = itemSite.getString("number");
             String barcode = itemSite.getString("barcode").toString();
             String description = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("site").getString("description");

             String uuid = dataDeeper.getJSONObject(i).getString("uuid");
                 String card = name + " of Quantity " + Integer.toString(ordered-atShipping) + " " + description;
                 //System.out.println(uuid);
                 //System.out.println("Barcode:"+ barcode);
                    //System.out.println("@@@"+barcode);
                 result.add(uuid);
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

//Get shipment descriptions
    /** Return an Array of Barcodes **/
public static List<String> getShippingDescriptions(String input) throws IOException{
       // String input = "";
     //String result = "";
        List<String> result = new ArrayList();
        try {
            JSONObject jsonObj = new JSONObject(input);
            JSONObject data = jsonObj.getJSONObject("data");
            JSONArray dataDeeper = data.getJSONArray("data");
            for(int i=0;i<dataDeeper.length();i++)
            {
             String shipment = dataDeeper.getJSONObject(i).get("shipment").toString();
             int atShipping = Integer.parseInt(dataDeeper.getJSONObject(i).getString("atShipping"));
             int ordered = Integer.parseInt(dataDeeper.getJSONObject(i).getString("ordered"));

             if(ordered<=atShipping){
                 // System.out.println(ordered);
                 // System.out.println(atShipping);
             //description adding
             JSONObject itemSite = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("item");
             String name = itemSite.getString("number");
             String barcode = itemSite.getString("barcode").toString();
             String description = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("site").getString("description");


                 String card = name + " of Quantity " + Integer.toString(atShipping) + " Received from: " + description;
                 System.out.println(card);
                 //System.out.println("Barcode:"+ barcode);
                    //System.out.println("@@@"+barcode);
                 result.add(card);
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
    //THIS SHOULD BE CHECKED BASED OFF OF THE ACTIVITYTYPE SHIPACTIVITY
public static List<String> getIssuetoShippingShipmentNumber(String input) throws IOException{
       // String input = "";
        List<String> result = new ArrayList();
        try {
            JSONObject jsonObj = new JSONObject(input);
            JSONObject data = jsonObj.getJSONObject("data");
            JSONArray dataDeeper = data.getJSONArray("data");
            for(int i=0;i<dataDeeper.length();i++)
            {
             String shipment = dataDeeper.getJSONObject(i).get("shipment").toString();
             int atShipping = Integer.parseInt(dataDeeper.getJSONObject(i).getString("atShipping"));
             int ordered = Integer.parseInt(dataDeeper.getJSONObject(i).getString("ordered"));

             if(ordered==atShipping){
                 // System.out.println(atShipping);
                 // System.out.println(ordered);
                shipment = dataDeeper.getJSONObject(i).getJSONObject("shipment").getString("number");
                 JSONObject itemSite = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("item");
                 String barcode = itemSite.getString("barcode").toString();
                    //System.out.println("@@@"+barcode);
                 result.add(shipment);
                 //result = shipment;
                 //System.out.println(result);
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
//DISPATCH FUNCTIONS FIND IN ANDROID
   //dispatchShipShipment
   //dispatchIssueShipping
//isShippable?
//get shippable line items
    //THIS SHOULD BE CHECKED BASED OFF OF THE ACTIVITYTYPE SHIPACTIVITY
public static List<String> isShippable(String input) throws IOException{
     //String result = "";
     ArrayList<String> result = new ArrayList();
     ArrayList<String> check = new ArrayList();
        //Parse Sales Order Object
        try {
            JSONObject jsonObj = new JSONObject(input);
            JSONObject data = jsonObj.getJSONObject("data");
            //one level deeper
            JSONArray dataDeeper = data.getJSONArray("data");
                for(int i=0;i<dataDeeper.length();i++){
            //#Debug
            String workflow = dataDeeper.getJSONObject(i).getString("name");
            String orderNumber = dataDeeper.getJSONObject(i).getJSONObject("parent").getString("name");
            String workflowStatus = dataDeeper.getJSONObject(i).getString("status");
            System.out.println(workflow+orderNumber+workflowStatus);
                   if (workflow.equals("PackWorkflow") && workflowStatus.equals("C")){
                        check.add(orderNumber);
                   }
                   else if (workflow.equals("ShipWorkflow") && !workflowStatus.equals("C")){
                         if(check.contains(orderNumber)){
                            //result = orderNumber + "," + result;
                            result.add(orderNumber);
                         }
                   }
               }// end of for
            } //end of try
         catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        // TADA the orders you want to ship
        //System.out.println(result);
        return result;
    }
//isOnMyPackList
//get issuable line items
    //request like..
    //https://192.168.33.10:8443/inventory/api/v1alpha1/resources/issue-to-shipping?attributes[order.uuid][EQUALS]=X
    //where UUID = one of activity
    //pass in your UUID match
public static String onPacklist(String input, String match) throws IOException{

        try {
            JSONObject jsonObj = new JSONObject(input);
            JSONObject data = jsonObj.getJSONObject("data");
            JSONArray dataDeeper = data.getJSONArray("data");
            for(int i=0;i<dataDeeper.length();i++)
            {
             String shipment = dataDeeper.getJSONObject(i).get("shipment").toString();
             int atShipping = Integer.parseInt(dataDeeper.getJSONObject(i).getString("atShipping"));
             int ordered = Integer.parseInt(dataDeeper.getJSONObject(i).getString("ordered"));
             JSONObject itemSite = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("item");
             String name = itemSite.getString("number");
             String barcode = itemSite.getString("barcode").toString();
             String orderNumber = dataDeeper.getJSONObject(i).getString("uuid");
             //ensure issuable
             if(ordered>atShipping){
                //barcode match
                 if(barcode.equals(match))
                    //return orderID
                    return orderNumber;
                }
            }
         }//end of try
        catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
            //return nothing
            return "NOPE";
    }
//get item position
//get order weight

    //get weight
    public static Double getWeight(String input) throws IOException{
        double totalWeight = 0;
        try {
            JSONObject jsonObj = new JSONObject(input);
            JSONObject data = jsonObj.getJSONObject("data");
            JSONArray dataDeeper = data.getJSONArray("data");
            for(int i=0;i<dataDeeper.length();i++)
            {
             JSONObject itemSite = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("item");
             double productWeight =  Double.parseDouble(itemSite.getString("productWeight"));
             double packageWeight =  Double.parseDouble(itemSite.getString("packageWeight"));
             totalWeight = productWeight + packageWeight +totalWeight;
             //ensure issuable
            }
         }//end of try
        catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
            //return nothing
            return totalWeight;
    }

//get description
//get number of items
    public static int numLineItems(String input) throws IOException{
        double totalWeight = 0;
        try {
            JSONObject jsonObj = new JSONObject(input);
            JSONObject data = jsonObj.getJSONObject("data");
            JSONArray dataDeeper = data.getJSONArray("data");
            return dataDeeper.length();
         }//end of try
        catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
            return -1;
    }
//calculate position
    public static int getIssuetoShippingLinePosition(String input, String match) throws IOException{

        try {
            JSONObject jsonObj = new JSONObject(input);
            JSONObject data = jsonObj.getJSONObject("data");
            JSONArray dataDeeper = data.getJSONArray("data");
            for(int i=0;i<dataDeeper.length();i++)
            {
             JSONObject itemSite = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("item");
             String orderNumber = dataDeeper.getJSONObject(i).getString("uuid");
             //ensure issuable
                 if(orderNumber.equals(match)){
                    return i+1;
                 }
             }

         }//end of try
        catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
            //return nothing
            return -1;
    }
//get uuids
  //get uuids
     public static List<String> getOrderUUIDs(String input) throws IOException{
            // String input = "";
          //String result = "";
             List<String> result = new ArrayList<String>();
             try {
                 JSONObject jsonObj = new JSONObject(input);
                 JSONObject data = jsonObj.getJSONObject("data");
                 JSONArray dataDeeper = data.getJSONArray("data");
                 for(int i=0;i<dataDeeper.length();i++)
                 {
                  String shipment = dataDeeper.getJSONObject(i).get("shipment").toString();
                  int atShipping = Integer.parseInt(dataDeeper.getJSONObject(i).getString("atShipping"));
                  int ordered = Integer.parseInt(dataDeeper.getJSONObject(i).getString("ordered"));

                  if(ordered>atShipping){
                      // System.out.println(ordered);
                      // System.out.println(atShipping);
                  //description adding
                  JSONObject itemSite = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("item");
                  String name = itemSite.getString("number");
                  String barcode = itemSite.getString("barcode").toString();
                  String description = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("site").getString("description");

                  String uuid = dataDeeper.getJSONObject(i).getString("uuid");
                      String card = name + " of Quantity " + Integer.toString(ordered-atShipping) + " " + description;
                      //System.out.println(uuid);
                      //System.out.println("Barcode:"+ barcode);
                         //System.out.println("@@@"+barcode);
                      result.add(uuid);
                    // }
                     }
                 }
              }//end of try
             catch (JSONException e) {
                     // TODO Auto-generated catch block
                    throw new RuntimeException(e);
             }
            return result;
         }

} // end of class