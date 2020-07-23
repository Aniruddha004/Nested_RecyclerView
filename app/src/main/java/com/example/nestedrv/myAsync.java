package com.example.nestedrv;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class myAsync extends AsyncTask<String,Integer, Bitmap> {
    ImageView iV;

    public myAsync(ImageView iV, Context context) {
        this.iV = iV;
        mContext = context;
    }

    Context mContext;
    @Override
    protected Bitmap doInBackground(String... strings) {
        for(String params:strings)
            try {
                URL urlConnection = new URL(params);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        iV.setImageBitmap(bitmap);
    }
}

