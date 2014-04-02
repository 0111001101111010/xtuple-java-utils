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
/*    public void testParseSalesOrder(){
//https://192.168.33.10:8443/inventory/api/v1alpha1/ActivityListItem?attributes[activityType][EQUALS]=SalesOrder
        XtupleRestClient client = new XtupleRestClient();
        String result = client.readFile("data/activityTypeSalesOrder.json");
        try{
            String output = client.ParseSalesOrder(result);
            System.out.println(output);
        }
        catch (Exception e){
             e.printStackTrace();
         }
    }*/
    public void testActivitySalesOrderWorkflow(){
        XtupleRestClient client = new XtupleRestClient();
        //https://192.168.33.10:8443/inventory/api/v1alpha1/ActivityListItem?attributes[activityType][EQUALS]=SalesOrderWorkflow
        //https://192.168.33.10:8443/inventory/api/v1alpha1/activity-list-item?attributes[activityType][EQUALS]=SalesOrderWorkflow&attributes[status][EQUALS]=I
        String result = client.readFile("data/activityTypeSalesOrderWorkflow.json");
        try{
            String output = client.ParseSalesOrderWorkflow(result);
            System.out.println(output);
        }
        catch (Exception e){
             e.printStackTrace();
         }
    }
    // public void testActivitySalesOrderWorkflow(){
    //     XtupleRestClient client = new XtupleRestClient();
    //     //https://192.168.33.10:8443/inventory/api/v1alpha1/ActivityListItem?attributes[activityType][EQUALS]=SalesOrderWorkflow
    //     String result = client.readFile("data/activityTypeSalesOrderWorkflow.json");
    //     try{
    //         String output = client.ParseSalesOrderWorkflow(result);
    //         System.out.println(output);
    //     }
    //     catch (Exception e){
    //          e.printStackTrace();
    //      }
    // }
    /**
     * Rigourous Test :-)
     */
    // public void testApp()
    // {
    //     assertTrue( true );
    // }
}
