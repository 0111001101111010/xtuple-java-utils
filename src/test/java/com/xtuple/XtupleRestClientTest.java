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
            String output = client.ParseSalesOrderWorkflow(result);
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
            String output = client.ParseSalesOrderWorkflow(result);
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
            String output = client.ParseSalesOrderWorkflow(result);
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
            String output = client.ParseSalesOrderWorkflow(result);
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
    /*
    * Given a uuid, filter and print all the line items related to that UUID
    * Get the SalesOrderJSONObject Passed in. Submit a call to sales-order for a specific one.
    https://192.168.33.10:8443/inventory/api/v1alpha1/sales-order/?attributes[uuid][EQUALS]=9d6ad555-82cf-4494-972f-cfb9c0dd0e7c
    * Print Sales Order Line Items...
    *
    */
    public void testFilterSalesOrderUUID(){
    System.out.println("Start of testFilterSalesOrderUUID");
        XtupleRestClient client = new XtupleRestClient();
        //https://192.168.33.10:8443/inventory/api/v1alpha1/activity-list-item?attributes[activityType][EQUALS]=SalesOrderWorkflow&attributes[status][EQUALS]=P
        //tell it which one to parse for example parse for all
        //example return from activty query
        String result = client.readFile("data/attributes[order.uuid]2.json");
        try{
            //pick first Sales Order UUID && Send in a Result
            //sample UUID = f936ef4b-bd0a-44ab-ce94-7f120ffbb53a
            String output = client.ParseSalesOrder(result);
            //client.FilterSalesOrderUUID()
            //System.out.println(output);
            //issuetoshipping vs shipped
        }
        catch (Exception e){
             e.printStackTrace();
         }
    System.out.println("End of of testFilterSalesOrderUUID");
    }
    /**
     * Rigourous Test :-)
     */
    // public void testApp()
    // {
    //     assertTrue( true );
    // }
}
