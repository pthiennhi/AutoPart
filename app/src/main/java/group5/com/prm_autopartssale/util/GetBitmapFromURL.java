package group5.com.prm_autopartssale.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetBitmapFromURL {
  public static void loadBitmapFromURL(String src, final BitmapCallback callback) {
    new AsyncTask<String, Void, Bitmap>() {
      @Override
      protected Bitmap doInBackground(String... params) {
        try {
          URL url = new URL(params[0]);
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          connection.setDoInput(true);
          connection.connect();
          InputStream input = connection.getInputStream();
          return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
          e.printStackTrace();
          return null;
        }
      }

      @Override
      protected void onPostExecute(Bitmap result) {
        if (result != null) {
          callback.onBitmapLoaded(result);
        } else {
          callback.onBitmapLoadFailed();
        }
      }
    }.execute(src);
  }

  public interface BitmapCallback {
    void onBitmapLoaded(Bitmap bitmap);
    void onBitmapLoadFailed();
  }
}

