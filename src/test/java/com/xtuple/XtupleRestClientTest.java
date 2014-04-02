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

    //read in things

    public static void  thingTest()
    {
        XtupleRestClient client = new XtupleRestClient();
        client.thing();
    }

    public void testInput(){
        XtupleRestClient client = new XtupleRestClient();
        String result = client.readFile("data/IssueToShipping.json");
        //System.out.println(result);
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
    public void testParseSalesOrder(){
//https://192.168.33.10:8443/inventory/api/v1alpha1/ActivityListItem?attributes[activityType][EQUALS]=SalesOrderWorkflow
        XtupleRestClient client = new XtupleRestClient();
        String result = client.readFile("data/activityTypeSalesOrderWorkflow.json");
        //System.out.println(result);
        try{
            String output = client.ParseSalesOrder(result);
            System.out.println(output);
        }
        catch (Exception e){
             e.printStackTrace();
         }
    }
    public void testActivitySalesOrderWorkflow(){
        XtupleRestClient client = new XtupleRestClient();
        //https://192.168.33.10:8443/inventory/api/v1alpha1/ActivityListItem?attributes[activityType][EQUALS]=SalesOrderWorkflow
        String result = client.readFile("./data/activityTypeSalesOrderWorkflow.json");
        //System.out.println(result);
        try{
            String output = client.ParseSalesOrder(result);
            System.out.println(output);
        }
        catch (Exception e){
             e.printStackTrace();
         }
    }
    /**
     * Rigourous Test :-)
     */
    // public void testApp()
    // {
    //     assertTrue( true );
    // }
}
