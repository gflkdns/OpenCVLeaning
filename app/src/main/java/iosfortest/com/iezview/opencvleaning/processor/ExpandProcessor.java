package iosfortest.com.iezview.opencvleaning.processor;

import android.graphics.Bitmap;

import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

/**
 * Created by Administrator on 2018/3/13.
 */

/**
 * 膨胀运算
 */
public class ExpandProcessor implements ImgProcessor {
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

    }

    @Override
    public void processor() {
        processor(3);
    }

    @Override
    public void processor(int wight) {
        kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(wight, wight));
        Imgproc.dilate(src, dec, kernel);
        Utils.matToBitmap(dec, bitmap_dec);

    }

    @Override
    public Bitmap getResult() {
        return bitmap_dec;
    }
}
