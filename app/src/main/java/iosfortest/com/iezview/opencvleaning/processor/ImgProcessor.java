package iosfortest.com.iezview.opencvleaning.processor;

import android.graphics.Bitmap;

import org.opencv.core.Mat;

/**
 * Created by Administrator on 2018/3/13.
 */

public interface ImgProcessor {
    /**
     * 初始化处理器
     *
     * @param src
     */
    void initProcessor(Bitmap src);

    /**
     * 进行处理
     */
    void processor();

    /**
     * 进行处理
     */
    void processor(int wight);

    /**
     * 获得处理结果
     *
     * @return
     */
    Bitmap getResult();
}
