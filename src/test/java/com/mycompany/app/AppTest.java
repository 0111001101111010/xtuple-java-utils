package com.mycompany.app;

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
public class AppTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    public void testParseIssueToShipping() throws IOException{
        String input = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader("IssueToShipping.json"));
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                input =  input+ sCurrentLine;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

     System.out.println("helloworld");
     //System.out.println(input);
     //convert to JSON object
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
              {     System.out.println("@@@"+i+"");
//                  System.out.println("@@@"+dataDeeper.getJSONObject(i).toString());
                 JSONObject order = dataDeeper.getJSONObject(i).getJSONObject("order");
                 JSONObject itemSite = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("item");
                 String number = order.getString("number");
                    System.out.println("@@@"+number);
                 String barcode = itemSite.getString("barcode").toString();
                    System.out.println("@@@"+barcode);
                 String description = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("site").getString("description");
                    System.out.println("@@@"+description);
                 //String Id=dataDeeper.) ;
                 //String terminalType=dataDeeper.getString("terminal_type");
              }
              //JSONArray array = new JSONArray(data);
               //  System.out.println("@@@@"+dataDeeper.toString());
    }
     catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
    }

    }
    public void testParseSalesOrder() throws IOException{
        String input = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader("SalesOrderWorkflow.json"));
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                //System.out.println(sCurrentLine);
                input =  input+ sCurrentLine;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
//Parse Sales Order Object
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
{     System.out.println("@@@"+i+"");
//                  System.out.println("@@@"+dataDeeper.getJSONObject(i).toString());
String uuid = dataDeeper.getJSONObject(i).getJSONObject("parent").getString("uuid");
System.out.println(uuid);
}
/*
 JSONObject order = dataDeeper.getJSONObject(i).getJSONObject("order");
 JSONObject itemSite = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("item");
 String number = order.getString("number");
    System.out.println("@@@"+number);
 String barcode = itemSite.getString("barcode").toString();
    System.out.println("@@@"+barcode);
 String description = dataDeeper.getJSONObject(i).getJSONObject("itemSite").getJSONObject("site").getString("description");
    System.out.println("@@@"+description);
 //String Id=dataDeeper.) ;
 //String terminalType=dataDeeper.getString("terminal_type");
}
//JSONArray array = new JSONArray(data);
 System.out.println("@@@@"+dataDeeper.toString());*/
    }
     catch (JSONException e) {
            // TODO Auto-generated catch block
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
