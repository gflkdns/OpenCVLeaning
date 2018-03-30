package iosfortest.com.iezview.opencvleaning.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import iosfortest.com.iezview.opencvleaning.R;

/**
 * Created by Administrator on 2018/3/12.
 */

public class ImageModelImpt implements ImageModel {

    @Override
    public Bitmap loadImage(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.img);
    }
}
