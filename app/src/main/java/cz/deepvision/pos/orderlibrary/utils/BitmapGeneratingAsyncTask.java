package cz.deepvision.pos.orderlibrary.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.izettle.html2bitmap.Html2Bitmap;
import com.izettle.html2bitmap.content.WebViewContent;

import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;

import cz.deepvision.pos.orderlibrary.models.BillModel;
import cz.deepvision.pos.orderlibrary.models.PrintArguments;

public class BitmapGeneratingAsyncTask extends AsyncTask<Void, Void, Bitmap> {

    private final WeakReference<Context> context;
    private final String html;
    private final int width;
    private final int zoom;
    private volatile PrintArguments arguments;
    private volatile BillModel bill;
    private final WeakReference<Callback> callback;

    public BitmapGeneratingAsyncTask(Context context, String html, int width, Callback callback, int zoom, PrintArguments arguments, BillModel bill) {
        this.context = new WeakReference<>(context.getApplicationContext());
        this.html = html;
        this.width = width;
        this.zoom = zoom;
        arguments.setSmallBitmap(width == 384);
        this.arguments = arguments;
        this.bill = bill;
        this.callback = new WeakReference<>(callback);
    }

    @Override
    protected Bitmap doInBackground(Void... voids) {
        Context context = this.context.get();

        Html2Bitmap build = new Html2Bitmap.Builder()
                .setContext(context)
                .setContent(WebViewContent.html(html))
                .setBitmapWidth(width)
                .setMeasureDelay(10)
                .setScreenshotDelay(10)
                .setStrictMode(true)
                .setTimeout(5)
                .setTextZoom(zoom)
                .setConfigurator(BitmapUtil.getHtml2BitmapConfigurator(zoom))
                .build();

        return build.getBitmap();

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        Callback bitmapCallback = this.callback.get();
        Bitmap compressedBitmap = compressor(bitmap, Bitmap.CompressFormat.JPEG, 50);
        if (bitmapCallback != null) {
            if (bill.isStorno()) bitmap = null;
            bitmapCallback.done(bitmap, this.arguments, bill);
        }
    }

    private Bitmap compressor(Bitmap bitmap, Bitmap.CompressFormat format, Integer quality) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(format, quality, stream);
        byte[] byteArray = stream.toByteArray();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 2;

        Bitmap compressedBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, options);


        return BitmapUtil.scaleImage(compressedBitmap, 580, true);
    }

    public interface Callback {
        void done(Bitmap bitmap, PrintArguments arguments, BillModel bill);
    }
}