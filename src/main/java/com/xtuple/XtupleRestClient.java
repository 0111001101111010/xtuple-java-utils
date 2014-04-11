package com.xtuple;

import java.io.*;
import org.json.*;
import java.util.*;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

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
public String ParseSalesOrderWorkflowActivity(String input) throws IOException{
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
    //request like..
    //https://192.168.33.10:8443/inventory/api/v1alpha1/resources/issue-to-shipping?attributes[order.uuid][EQUALS]=X
    //where UUID = one of activity
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
             if(ordered<atShipping){
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
    //THIS SHOULD BE CHECKED BASED OFF OF THE ACTIVITYTYPE SHIPACTIVITY
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
                    //System.out.println("@@@"+barcode);
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
//DISPATCH FUNCTIONS FIND IN ANDROID
   //dispatchShipShipment
   //dispatchIssueShipping
//isShippable?
//get issuable line items
    //THIS SHOULD BE CHECKED BASED OFF OF THE ACTIVITYTYPE SHIPACTIVITY
public String isShippable(String input) throws IOException{
     String result = "";
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
                            result = orderNumber + "," + result;
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
//post
     public String Post(String... params){
      HashMap<String, String> mData = null;// post data
      String words = "xtuple";

        byte[] result = null;
        String str = "";
        //first try
        try {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(params[0]);// in this case, params[0] is URL

            try {
                // set up post data
                ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
                Iterator<String> it = mData.keySet().iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    nameValuePair.add(new BasicNameValuePair(key, mData.get(key)));
                }

                post.setEntity(new UrlEncodedFormEntity(nameValuePair, "UTF-8"));
                HttpResponse response = client.execute(post);
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == HttpURLConnection.HTTP_OK){
                    result = EntityUtils.toByteArray(response.getEntity());
                    str = new String(result, "UTF-8");
                    words = str;
                }
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            catch (Exception e) {
            }
        } //inner try end
        catch (Exception e){

        }// end of large try catch
        return str;
     }
} // end of class