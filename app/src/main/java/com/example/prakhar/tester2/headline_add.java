package com.example.prakhar.tester2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.mime.TypedString;

public class headline_add extends AppCompatActivity {
    EditText head;
    EditText desc;

    String image = "";

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_headlines);

        image =   getIntent().getStringExtra("image");
        head= (EditText) findViewById(R.id.head);

        desc = (EditText) findViewById(R.id.desc);

        btn = (Button) findViewById(R.id.btn);
        Toast.makeText(this, "" + image,
                Toast.LENGTH_LONG).show();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {






                RestAdapter.Builder builder = new RestAdapter.Builder()
                        .setEndpoint("http://app.newsportal.co.in")
                        .setClient(new OkClient(new OkHttpClient()));

                RestAdapter adapter = builder.build();


                adapter.create(RetrofitClient.class).headlines_news(

                       new TypedString (head.getText().toString())
                        ,
                        new TypedString    (desc.getText().toString())

                        ,   new TypedString ("Aman"),

                                new TypedString     ("prakharNews")
                        ,
                        new retrofit.Callback<String>() {


                            @Override
                            public void success(String s, retrofit.client.Response response) {

                                if (s.equals("true")) {
                                    Toast.makeText(headline_add.this, "COMPLETED", 1000).show();
                                    finish();
                                } else {
                                    Toast.makeText(headline_add.this, "FAILED", 1000).show();
                                }

                                Log.d("BHOSADI 2", "" + s);
                        }

                            @Override
                            public void failure(RetrofitError error) {
                                Toast.makeText(headline_add.this, "FAILED", 1000).show();

                                Log.d("BHOSADI 2", "" + error);
                            }
                        });


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_special_news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
