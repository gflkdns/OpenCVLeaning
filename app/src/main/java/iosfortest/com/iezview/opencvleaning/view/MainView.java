package iosfortest.com.iezview.opencvleaning.view;

import android.graphics.Bitmap;

import iosfortest.com.iezview.opencvleaning.BasePresenter;
import iosfortest.com.iezview.opencvleaning.BaseView;
import iosfortest.com.iezview.opencvleaning.presenter.MainPresenter;

/**
 * Created by Administrator on 2018/3/12.
 */

public interface MainView<T extends BasePresenter> extends BaseView<T> {
    /**
     * 展示opencv处理之后的图片
     *
     * @param bitmap 要展示的图片
     */
    void showImage(Bitmap bitmap);

    void toast(String s);
}
