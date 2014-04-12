package com.xtuple;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.*;
import org.json.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import java.*;
/**
 * Unit test for simple App.
 */
public class XtupleRestClientTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public XtupleRestClientTest( String testName )
    {
        super( testName );
    }

    public void testreadFile(){
        XtupleRestClient client = new XtupleRestClient();
        String result = client.readFile("data/IssueToShipping.json");
    }
    //change testname
    public void testParseIssueToShipping(){
        XtupleRestClient client = new XtupleRestClient();
        String result = client.readFile("data/IssueToShipping.json");
        //System.out.println(result);
        try{
            String output = client.ParseIssueToShipping(result);
            System.out.println(output);
        }
        catch (Exception e){
             e.printStackTrace();
         }
    }

    public void testActivitySalesOrderWorkflow(){
        System.out.println("Start of testActivitySalesOrderWorkflow");
        XtupleRestClient client = new XtupleRestClient();
        //https://192.168.33.10:8443/inventory/api/v1alpha1/ActivityListItem?attributes[activityType][EQUALS]=SalesOrderWorkflow
        String result = client.readFile("data/activityTypeSalesOrderWorkflow.json");
        try{
            String output = client.ParseSalesOrderWorkflowActivity(result);
            System.out.println(output);
        }
        catch (Exception e){
             e.printStackTrace();
         }
        System.out.println("End of testActivitySalesOrderWorkflow");
    }

    public void testActivitySalesOrderWorkflowI(){
        System.out.println("Start of testActivitySalesOrderWorkflowI");
        XtupleRestClient client = new XtupleRestClient();
        //https://192.168.33.10:8443/inventory/api/v1alpha1/activity-list-item?attributes[activityType][EQUALS]=SalesOrderWorkflow&attributes[status][EQUALS]=I
        String result = client.readFile("data/activityTypeSalesOrderWorkflowI.json");
        try{
            String output = client.ParseSalesOrderWorkflowActivity(result);
            System.out.println(output);
        }
        catch (Exception e){
             e.printStackTrace();
         }
        System.out.println("End of testActivitySalesOrderWorkflowI");
    }
    public void testActivitySalesOrderWorkflowP(){
        System.out.println("Start of testActivitySalesOrderWorkflowP");
        XtupleRestClient client = new XtupleRestClient();
        //https://192.168.33.10:8443/inventory/api/v1alpha1/activity-list-item?attributes[activityType][EQUALS]=SalesOrderWorkflow&attributes[status][EQUALS]=I
        String result = client.readFile("data/activityTypeSalesOrderWorkflowP.json");
        try{
            String output = client.ParseSalesOrderWorkflowActivity(result);
            System.out.println(output);
        }
        catch (Exception e){
             e.printStackTrace();
         }
        System.out.println("End of testActivitySalesOrderWorkflowP");
    }
    public void testSplitCommaString(){
        System.out.println("Start of testSplitCommaString");
        XtupleRestClient client = new XtupleRestClient();
        //https://192.168.33.10:8443/inventory/api/v1alpha1/activity-list-item?attributes[activityType][EQUALS]=SalesOrderWorkflow&attributes[status][EQUALS]=I
        String result = client.readFile("data/activityTypeSalesOrderWorkflowP2.json");
        try{
            String output = client.ParseSalesOrderWorkflowActivity(result);
            System.out.println(output);
            List<String> items = XtupleRestClient.SplitCommaString(output);
            //DEBUG, Returns the UUID items in list
            for(String s: items){
                System.out.println(s);
            }
        }
        catch (Exception e){
             e.printStackTrace();
        }
       System.out.println("End of testSplitCommaString");
    }
    //#Done before hand.. wait what. Server..?
    //test for activityType Step 1
    public void testActivitySalesOrderWorkflowActivityType(){
        System.out.println("Start of testActivitySalesOrderWorkflow~1");
        XtupleRestClient client = new XtupleRestClient();
        //https://192.168.33.10:8443/inventory/api/v1alpha1/activity-list-item?attributes[activityType][EQUALS]=SalesOrderWorkflow&attributes[status][EQUALS]=I
        String result = client.readFile("data/workflow/activityTypeSalesOrderWorkflow.json");
        try{
            String output = client.ParseSalesOrderWorkflowActivity(result);
            System.out.println(output);
        }
        catch (Exception e){
             e.printStackTrace();
         }
        System.out.println("End of testActivitySalesOrderWorkflowP~1");
    }
    //#Done before hand.. wait what. Server..?
    //test for activityType Step 1
    public void testActivitySalesOrderWorkflowActivityTypeOnType(){
        System.out.println("Start of testActivitySalesOrderWorkflow~OnActivityOnType~1");
        XtupleRestClient client = new XtupleRestClient();
        //https://192.168.33.10:8443/inventory/api/v1alpha1/activity-list-item?attributes[activityType][EQUALS]=SalesOrderWorkflow&attributes[status][EQUALS]=I
        String result = client.readFile("data/workflow/activityTypeSalesOrderWorkflow.json");
        try{
            String output = client.ParseSalesOrderWorkflowActivity(result, "PackWorkflow");
            System.out.println(output);
        }
        catch (Exception e){
             e.printStackTrace();
         }
        System.out.println("testActivitySalesOrderWorkflow~OnActivityOnType~1");
    }
    //Fire off a request based off the UUID and return the result. This is one issue to shipping object

    //Step 2
    /*
    * Given a uuid, filter and print all the line items related to that UUID
    * Get the SalesOrderJSONObject Passed in. Submit a call to sales-order for a specific one.
    https://192.168.33.10:8443/inventory/api/v1alpha1/resources/sales-order/?attributes[uuid][EQUALS]=9d6ad555-82cf-4494-972f-cfb9c0dd0e7c
    * Print Sales Order Line Items... with shipment IDs?
    * //pickfirst
    */
    //Shipment ID number where
    // - ordered != shipped &&
    // - shipment data.data.shipment != null; (all of these absorb same shipment info...?)
    public void testIssuable(){
    System.out.println("Start of testIssuable~2");
        XtupleRestClient client = new XtupleRestClient();
        //https://192.168.33.10:8443/inventory/api/v1alpha1/activity-list-item?attributes[activityType][EQUALS]=SalesOrderWorkflow&attributes[status][EQUALS]=P
        //tell it which one to parse for example parse for all
        //example return from activty query
        String result = client.readFile("data/workflow/attributes[order.uuid]mixed.json");
        try{
            //pick first Sales Order UUID && Send in a Result
            //sample UUID = f936ef4b-bd0a-44ab-ce94-7f120ffbb53a
            String output = client.getIssueToShippingAtShipping(result);
            //client.FilterSalesOrderUUID()
            //System.out.println(output);
            //issuetoshipping vs shipped
        }
        catch (Exception e){
             e.printStackTrace();
         }
    System.out.println("End of of testIssuable~2");
    }
   public void testShippable(){
    System.out.println("Start of testShippable~2");
        XtupleRestClient client = new XtupleRestClient();
        //https://192.168.33.10:8443/inventory/api/v1alpha1/activity-list-item?attributes[activityType][EQUALS]=SalesOrderWorkflow&attributes[status][EQUALS]=P
        //tell it which one to parse for example parse for all
        //example return from activty query
        String result = client.readFile("data/workflow/shipActivityWorkflow.json");
        try{
            String output = client.isShippable(result);
            System.out.println(output);
        }
        catch (Exception e){
             e.printStackTrace();
         }
    System.out.println("End of of testShippable~2");
    }
    //test DispatchIssue
    public void testDispatchIssueToShipping(){
    System.out.println("Start of testDispatchIssueToShipping~2");
        HashMap<String, String> info = new HashMap<String, String>();
        info.put("uuid", "0e6fd01e-7dd2-41f0-dd0e-6cad09078fb3");
        info.put("quantity", "1");
        Post asyncHttpPost = new Post(info);
        //#Work
        //asyncHttpPost.execute("http://192.168.10.53:8081/orders");
        //#Home 192.168.33.1
        asyncHttpPost.execute("http://192.168.33.1:8081/dispatchIssue");
        String words = asyncHttpPost.getWords();
        System.out.println(words);
        //asyncHttpPost.execute("http://192.168.10.53:8081/orders");
    System.out.println("End of testDispatchIssueToShipping~2");
    }
    //test DispatchShipment
    public void testDispatchShipment(){
    System.out.println("Start of testDispatchShipment~2");
        HashMap<String, String> info = new HashMap<String, String>();
        info.put("shipID", "60176");
        Post asyncHttpPost = new Post(info);
        //#Work
        //asyncHttpPost.execute("http://192.168.10.53:8081/orders");
        //#Home 192.168.33.1
        asyncHttpPost.execute("http://192.168.33.1:8081/dispatchShip");
        String words = asyncHttpPost.getWords();
        System.out.println(words);
        //asyncHttpPost.execute("http://192.168.10.53:8081/orders");
    System.out.println("End of testDispatchShipment~2");
    }
    //issue a issueToShipping IF a barcode is matched
    /**
     * Rigourous Test :-)
     */
    // public void testApp()
    // {
    //     assertTrue( true );
    // }
}
