package iosfortest.com.iezview.opencvleaning;

/**
 * Created by Administrator on 2018/3/12.
 */

public interface BaseView<T extends BasePresenter> {
    void setPresenter(T presenter);
}
