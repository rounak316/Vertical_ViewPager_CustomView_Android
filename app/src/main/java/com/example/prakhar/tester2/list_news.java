package com.example.prakhar.tester2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import retrofit.RestAdapter;
import retrofit.RetrofitError;


public class list_news extends android.support.v4.app.Fragment {


    class headlines
    {
String timestamp;
        String id;
        String head;
        String desc;



    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        rest_adapter
                 =((app)getActivity().getApplication()).adapter;
    }




    class async_ss extends AsyncTask<View,String,ArrayList<Uri>>
    {
        ProgressDialog dia;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

             dia = ProgressDialog.show(getActivity(),"Please wait","");

        }

        @Override
        protected ArrayList<Uri> doInBackground(View... params) {


            return  saveScreenshot(params[0]);



        }

        @Override
        protected void onPostExecute(ArrayList<Uri> imageUriArray) {
            super.onPostExecute(imageUriArray);

            try {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Visit: vidhayakanews.com!!");
                intent.setType("text/plain");
                intent.setType("image/jpeg");
                intent.setPackage("com.whatsapp");
                intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUriArray);
                startActivity(intent);
            }
            catch (Exception e)
            {
                Toast.makeText(getActivity() , "Whatsapp Not Installed", 1000).show();
            }
            finally {
                dia.dismiss();
            }

        }
    }

    public  ArrayList<Uri>  saveScreenshot(View v) {



        Bitmap bm = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bm);


        v.draw(canvas);


        String barcodeNumber = "poopy";

        String pathy =   MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bm,
                barcodeNumber + ".jpg", barcodeNumber + ".jpg Card Image");



        Uri uri1 = Uri.parse(pathy);


        ArrayList<Uri> imageUriArray = new ArrayList<Uri>();
        imageUriArray.add(uri1);
        return imageUriArray;

    }

    void shareScreenShot(View v)
    {

        v.setDrawingCacheEnabled(true);
Log.d("Dimmensions" ,v.getHeight()  + " "+v.getY() + " " + v.getX() + " " + v.getWidth()  + "" );



// this is the important code :)
// Without it the view will have a dimension of 0,0 and the bitmap will be null



        v.measure(View.MeasureSpec.makeMeasureSpec(  v.getWidth() , View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(v.getHeight() , View.MeasureSpec.EXACTLY));
//                v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
//v.layout( (int) v.getX() ,(int) v.getY()  , v.getMeasuredWidth(), v.getMeasuredHeight() );
        v.buildDrawingCache(true);


        Bitmap bm = Bitmap.createBitmap(v.getDrawingCache()  , 0 , 0 , 300 , 300);
        v.setDrawingCacheEnabled(false); // clear drawing cache

        String barcodeNumber = "poopy";

        String pathy =   MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bm,
                barcodeNumber + ".jpg", barcodeNumber + ".jpg Card Image");



        Uri uri1 = Uri.parse(pathy);


        ArrayList<Uri> imageUriArray = new ArrayList<Uri>();
        imageUriArray.add(uri1);
try {

    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_SEND);
    intent.putExtra(Intent.EXTRA_TEXT, "Visit: vidhayakanews.com!!");
    intent.setType("text/plain");
    intent.setType("image/jpeg");
    intent.setPackage("com.whatsapp");
    intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUriArray);
    startActivity(intent);
}
catch (Exception e)
{
    Toast.makeText(getActivity() , "Whatsapp Not Installed", 1000).show();
}

    }
RecyclerView recyclerView;
adap adapter;

    SwipeRefreshLayout swiper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.recview , null);;
        swiper = (SwipeRefreshLayout) view.findViewById(R.id.swiper);
        recyclerView = (RecyclerView) view.findViewById(R.id.recview);
        SwipeRefreshLayout.LayoutParams params = new FrameLayout.LayoutParams(SwipeRefreshLayout.LayoutParams.MATCH_PARENT, SwipeRefreshLayout.LayoutParams.MATCH_PARENT);


        view.setLayoutParams(params);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new adap();
        recyclerView.setAdapter(adapter);
recyclerView.setClickable(true);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "clicked", 1000).show();
            }
        });



        return view;


    }



  public void refresher()
{


    getGT_SN_start("" + 0);
}


    @Override
    public void onStart() {
        super.onStart();

        refresher();

        swiper.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresher();
            }
        });


    }

    ArrayList<headlines> news = new ArrayList<>();



    RestAdapter rest_adapter;
    void getGT_SN_start(String i)
    {

        rest_adapter.create(RetrofitClient.class).getHeadlines_lt(

                i

                ,

                new retrofit.Callback<ArrayList<_news>>() {
                    @Override
                    public void success(ArrayList<_news> newses, retrofit.client.Response response) {


                        if(newses.size()==0)
                        {
                            threashhold_not = false;

                        }


                        for (int i = 0; i < newses.size(); i++) {

                            _news tmp = newses.get(i);


                            addNews( tmp.id ,tmp.head, tmp.desc , tmp.timestamp);


                        }
                     String hg =   new Gson().toJson(news);

                        Log.d("BHOSADI", "CHUTIYA "+ hg);

                        getActivity().getSharedPreferences("news" , 1).edit().putString("headlines" , hg.toString()).commit();

                        adapter.notifyDataSetChanged();











                        swiper.setRefreshing(false);


                    }

                    @Override
                    public void failure(RetrofitError error) {




                        String tmp =   getActivity().getSharedPreferences("news" , 1).getString("headlines", "[]");


                        try {
                            JSONArray ar = new JSONArray(tmp);

                            ArrayList<headlines> tmp_news = new ArrayList<headlines>();


                            for(int i=0;i<ar.length();i++)
                            {


                                headlines headlines = new headlines();
                                headlines.head =   ar.getJSONObject(i).getString("head");
                                headlines.desc =   ar.getJSONObject(i).getString("desc");
                                headlines.id =   ar.getJSONObject(i).getString("id");

                                tmp_news.add(headlines);


                            }
                            news = tmp_news;
                            adapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        swiper.setRefreshing(false);
                    }
                });

    }

    void getGT_update(String i)
    {

        rest_adapter.create(RetrofitClient.class).getHeadlines_lt(

                i

                ,

                new retrofit.Callback<ArrayList<_news>>() {
                    @Override
                    public void success(ArrayList<_news> newses, retrofit.client.Response response) {


                        if(newses.size()==0)
                        {
                            threashhold_not = false;

                        }


                        for (int i = 0; i < newses.size(); i++) {

                            _news tmp = newses.get(i);


                            addNews( tmp.id ,tmp.head, tmp.desc , tmp.timestamp);


                        }
                        String hg =   new Gson().toJson(news);

                        Log.d("BHOSADI", "CHUTIYA "+ hg);

                        getActivity().getSharedPreferences("news" , 1).edit().putString("headlines" , hg.toString()).commit();

                        adapter.notifyDataSetChanged();














                    }

                    @Override
                    public void failure(RetrofitError error) {








                    }
                });

    }

    class gson_news
    {
        ArrayList<_news> array;

    }

    void resetAdap()
    {
        news = new ArrayList<>();


        adapter.notifyDataSetChanged();

    }

    void refresh()
    {


        adapter.notifyDataSetChanged();

    }

    void addNews(String id ,String head , String desc , String timestamp)
    {
        headlines tmp =new  headlines();
        tmp.desc = desc;
        tmp.head = head;
        tmp.id = (id);
        tmp.timestamp = timestamp;

        news.add(tmp);


    }

    class adap extends RecyclerView.Adapter<adap.vh> {

        class vh extends RecyclerView.ViewHolder
        {

            View screenshoty;
TextView _h, _d,date;

View share,delete;
            public vh(View itemView) {
                super(itemView);

                screenshoty = itemView.findViewById(R.id.layou);
                _h = (TextView) itemView.findViewById(R.id._h);

                date = (TextView) itemView.findViewById(R.id.date);
                _d = (TextView) itemView.findViewById(R.id._d);
                share = itemView.findViewById(R.id.share);
                delete = itemView.findViewById(R.id.delete);


            }
        }


        @Override
        public vh onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view =    getActivity().getLayoutInflater().inflate(R.layout.list_entity , null);

            return new vh(view);
        }

        @Override
        public void onBindViewHolder(final vh vh, int i) {

vh._h.setText(news.get(i).head);
            vh._d.setText(news.get(i).desc);
vh.date.setText(  news.get(i).timestamp  );
            vh.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //    saveScreenshot(vh.itemView);

                    new async_ss().execute(vh.screenshoty);

                }
            });

            vh.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //    saveScreenshot(vh.itemView);

Log.d("BIND" , "");
                    String shareBody = vh._h.getText() + "\n\t" +  vh._d.getText()+"\n" + "Download app :  ";
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Vidhayaka News");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Vidhayaka News"));


                }
            });


            if(i==news.size() -1 && threashhold_not)
            {
                getGT_update(news.get(news.size() - 1).id);
                Log.d("LAST DEMANDED BHOSADI " , "WALE");

            }

        }



        @Override
        public int getItemCount() {
            return news.size();
        }
    }

boolean threashhold_not = true;
}
