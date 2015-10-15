package com.example.prakhar.tester2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.mime.TypedString;

public class add_special_news extends AppCompatActivity {
    EditText head;
    EditText desc;
    private int RESULT_LOAD_IMG = 9;
ImageView picture;
    String image = "";
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                final Cursor cursor = this.getContentResolver().query(selectedImage, new String[]{android.provider.MediaStore.Images.ImageColumns.DATA}, null, null, null);



                cursor.moveToFirst();
                String filePath = cursor.getString(0);
                cursor.close();

                Picasso.with(getApplicationContext()).load(new File(filePath)).fit().into(picture, new Callback() {
                    @Override
                    public void onSuccess() {
                        {
                            Bitmap bm =  ((BitmapDrawable)picture.getDrawable()).getBitmap();



                            String barcodeNumber = "poopy2";

                            String pathy =   MediaStore.Images.Media.insertImage(getContentResolver(), bm,
                                    barcodeNumber + ".jpg", barcodeNumber + ".jpg Card Image");



                            Uri uri1 = Uri.parse(pathy);


                            Cursor cursor2 = add_special_news.this.getContentResolver().query(uri1, new String[]{android.provider.MediaStore.Images.ImageColumns.DATA}, null, null, null);
                            cursor2.moveToFirst();
                            String filePath2 = cursor2.getString(0);
                            cursor2.close();

                            image = filePath2;



                        }
                    }

                    @Override
                    public void onError() {
                        image = null;


                    }
                });



            } else {
                Toast.makeText(this, "You haven't picked Image " +  requestCode +"  " + resultCode + " " + data,
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }
    RestAdapter.Builder builder;
    RestAdapter adapter;

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_special_news);


 builder = new RestAdapter.Builder()
                .setEndpoint("http://app.newsportal.co.in")
                .setClient(new OkClient(new OkHttpClient()));

         adapter = builder.build();

        picture = (ImageView) findViewById(R.id.picture);
picture.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // Create intent to Open Image applications like Gallery, Google Photos
//        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//// Start the Intent
//        startActivityForResult(galleryIntent, RESULT_LOAD_IMG );

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), RESULT_LOAD_IMG);

    }
});

        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
// Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });


       image = null;
        head= (EditText) findViewById(R.id.head);

        desc = (EditText) findViewById(R.id.desc);

        btn = (Button) findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(image==null)
                {
                    Toast.makeText(add_special_news.this, "Please Select an Image " , 1000).show();
                    return;

                }



                if(image.length() < 2)
                {
                    Toast.makeText(add_special_news.this, "Please Select an Image " , 1000).show();
                    return;

                }

                if(head.getText().toString().length() < 2)
                {
                    Toast.makeText(add_special_news.this, "Please enter a heading" , 1000).show();
                    return;

                }

                if(desc.getText().toString().length() < 2)
                {
                    Toast.makeText(add_special_news.this, "Please Select a Description " , 1000).show();
                    return;

                }


                final ProgressDialog dia =       ProgressDialog.show(add_special_news.this, "Uploading Image", "0% Completed");

dia.setCancelable(false);
                CountingTypedFile typedImage = null;

                    typedImage = new CountingTypedFile("image/*", new File(image), new ProgressListener() {
                        @Override
                        public void transferred(final float num) {
                            Log.d("PROGRESSSED ", num + " ");


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            dia.setMessage( Math.min((int)num , 95) +"%"  + " Completed");



                          //  Toast.makeText(add_special_news.this, "COMPLETED " + Math.min( 95, num), 1000).show();


                        }
                    });

                        }
                    });






          adapter.create(RetrofitClient.class).special_news(typedImage,

                        new TypedString(head.getText().toString())
                        ,
                        new TypedString(desc.getText().toString())

                        ,  new TypedString ("Name"),

                  new TypedString( "prakharNews")
                        ,
                        new retrofit.Callback<String>() {


                            @Override
                            public void success(String s, retrofit.client.Response response) {

                                if(s.equals("true"))
                                {
                                    dia.dismiss();
startActivity(new Intent(add_special_news.this, Fill_Splash.class));
                                    Toast.makeText(add_special_news.this , "Posted SuccessFully" , 1000).show();
                                    finish();

                                }
                                else
                                {
                                    dia.dismiss();
                                    Toast.makeText(add_special_news.this , "Failed to Post" , 1000).show();
                                }

                                Log.d("BHOSADI 2", "" + s);
                            }

                            @Override
                            public void failure(RetrofitError error) {

                                dia.dismiss();
                                Toast.makeText(add_special_news.this , "Failed to Post" , 1000).show();

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
