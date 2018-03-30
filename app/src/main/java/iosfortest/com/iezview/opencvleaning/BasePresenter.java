package iosfortest.com.iezview.opencvleaning;

/**
 * Created by Administrator on 2018/3/12.
 */

public interface BasePresenter<T extends BaseView> {
    void start();

    void setView(T view);
}
