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
}