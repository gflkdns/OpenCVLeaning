package iosfortest.com.iezview.opencvleaning.processor;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

/**
 * Created by Administrator on 2018/3/13.
 */

public class MyProcessor implements ImgProcessor {
    private Mat dec;
    private Mat src;
    private Bitmap bitmap_dec;

    private Mat kernel;

    @Override
    public void initProcessor(Bitmap bitmap_src) {
        bitmap_dec = Bitmap.createBitmap(bitmap_src.getWidth(), bitmap_src.getHeight(), Bitmap.Config.ARGB_8888);
        src = new Mat();
        dec = new Mat();
        Utils.bitmapToMat(bitmap_src, src);
        kernel = new Mat(3, 3, CvType.CV_16SC1);
    }

    @Override
    public void processor() {
        float[] he = {
                9f, 1f, 0f,
                1f, 0f, -1f,
                0f, -1f, -2f
        };
        kernel.put(0, 0, he);
        Imgproc.filter2D(src, dec, src.depth(), kernel);
        Utils.matToBitmap(dec, bitmap_dec);
    }

    public void processor(int progress) {
        double[] he = {
                0,1,2,3,4,5,
                0,1,2,3,4,5,
                0,1,2,3,4,5,
                0,1,2,3,4,5,
                0,1,2,3,4,5,
                0,1,2,3,4,5,
        };
        kernel.put(0, 0, he);
        Imgproc.filter2D(src, dec, src.depth(), kernel);
        Utils.matToBitmap(dec, bitmap_dec);
    }

    @Override
    public Bitmap getResult() {
        return bitmap_dec;
    }
}
