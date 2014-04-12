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


public class Post{
    private HashMap<String, String> mData = null;// post data
    public String words = "xtuple";
    /**
     * constructor
     */
    public Post(HashMap<String, String> data) {
        mData = data;
    }

    /**
     * background
     */

    protected String execute(String... params) {
        byte[] result = null;
        String str = "";
        //first try
        try{
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(params[0]);// in this case, params[0] is URL
            //inner try
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
        }//end try first
        catch (Exception e) {
        }//end first
        words=str;
        return str;
    }

    /**
     * on getting result
     */
    protected void onPostExecute(String result) {
    	    words=result;
    }
   @SuppressWarnings("unused")
	public String getWords(){
	   return words;
	   }
}//end of file