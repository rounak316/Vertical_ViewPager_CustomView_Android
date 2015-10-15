package com.example.prakhar.tester2;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Prakhar on 9/27/2015.
 */
public class DataStructures {

    public class Headlines
    {

        String headline = "" ;
        String detail = "";
        String date = "";
        int ID = 0;


        public  Headlines()
        {


        }


        boolean addData(String str)
        {

            try {
                JSONObject obj = new JSONObject(str);

                headline =   obj.getString("headline");
                detail =   obj.getString("detail");
                date =   obj.getString("date");
                ID =   obj.getInt("ID");
                return  true;

            } catch (JSONException e) {
                e.printStackTrace();
                return  false;
            }


        }

    }

    public class SexyNews
    {

        String headline = "" ;
        String detail = "";
        String date = "";
        int ID = 0;
        String img_url= "";


        public  SexyNews()
        {


        }


        boolean addData(String str)
        {

            try {
                JSONObject obj = new JSONObject(str);

                headline =   obj.getString("headline");
                detail =   obj.getString("detail");
                date =   obj.getString("date");
                img_url =   obj.getString("img_url");
                ID =   obj.getInt("ID");

                return  true;

            } catch (JSONException e) {
                e.printStackTrace();
                return  false;
            }


        }

    }
}
