package app.devmedia.com.br.appdevmedia.async;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by erick.amorim on 04/10/2017.
 */

public class AsyncImageHelper extends AsyncTask<String, Void, Bitmap> {

    protected ImageView imageView;
    public AsyncImageHelper(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {

        String imgUrl = urls[0];
        Bitmap bitmapImg = null;
        try {

            InputStream inputStream = new URL(imgUrl).openStream();
            bitmapImg = BitmapFactory.decodeStream(inputStream);

        } catch (Exception e) {

            e.printStackTrace();

        }
        return bitmapImg;

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}
