package iosfortest.com.iezview.opencvleaning.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import iosfortest.com.iezview.opencvleaning.BasePresenter;
import iosfortest.com.iezview.opencvleaning.model.ImageModelImpt;
import iosfortest.com.iezview.opencvleaning.processor.ErodeProcessor;
import iosfortest.com.iezview.opencvleaning.processor.ExpandProcessor;
import iosfortest.com.iezview.opencvleaning.processor.ImgProcessor;
import iosfortest.com.iezview.opencvleaning.processor.MyProcessor;
import iosfortest.com.iezview.opencvleaning.processor.SharpenProcessor;
import iosfortest.com.iezview.opencvleaning.view.MainView;

/**
 * Created by Administrator on 2018/3/12.
 */

public class MainPresenter implements BasePresenter<MainView> {
    Context context;

    MainView view;
    ImageModelImpt modelImpt;
    private Handler handler;
    private Thread thread;
    private static Integer progress = 5;
    private boolean isRun = true;
    private Bitmap bitmap_src;
    private ImgProcessor processor;

    public MainPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void start() {
        modelImpt = new ImageModelImpt();
        handler = new Handler(Looper.getMainLooper(), new InnerCallback());
        thread = initProcessThread();
        //开始加载OpevCV库
        //使用前必须要loader成功才可以.
        if (!OpenCVLoader.initDebug()) {
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_13, context, mLoaderCallback);
        } else {
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @NonNull
    private Thread initProcessThread() {
        return new Thread(new Runnable() {
            int progress = 0;

            @Override
            public void run() {
                while (isRun) {
                    synchronized (MainPresenter.this) {
                        if (progress != MainPresenter.progress) {
                            progress = MainPresenter.progress;
                            processor.processor(progress);
                            Message.obtain(handler, 0, processor.getResult()).sendToTarget();
                        }
                        try {
                            MainPresenter.this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });
    }

    @Override
    public void setView(MainView view) {
        this.view = view;
    }

    public void onProgressChanged(int progress) {
        MainPresenter.progress = progress;
        synchronized (MainPresenter.this) {
            MainPresenter.this.notifyAll();
        }
    }

    class InnerCallback implements Handler.Callback {

        @Override
        public boolean handleMessage(Message msg) {
            view.showImage((Bitmap) msg.obj);
            return true;
        }
    }

    private LoaderCallbackInterface mLoaderCallback = new BaseLoaderCallback(context) {


        @Override
        public void onManagerConnected(int status) {

            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                    view.toast("OpenCV库已经加载成功!");
                    bitmap_src = modelImpt.loadImage(context);
                    processor = new SharpenProcessor();
                    processor.initProcessor(bitmap_src);
                    thread.start();
                    break;
                default: {
                    super.onManagerConnected(status);
                    view.toast("OpenCV库已经加载失败!");
                }
            }
        }
    };

}
