package iosfortest.com.iezview.opencvleaning.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import iosfortest.com.iezview.opencvleaning.BasePresenter;
import iosfortest.com.iezview.opencvleaning.BaseView;
import iosfortest.com.iezview.opencvleaning.R;
import iosfortest.com.iezview.opencvleaning.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainView<MainPresenter> {

    private static final String TAG = "MainActivity";
    private ImageView imageView;
    private MainPresenter presenter;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        presenter = new MainPresenter(this);
        setPresenter(presenter);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                presenter.onProgressChanged(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void showImage(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @Override
    public void toast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(MainPresenter presenter) {
        presenter.setView(this);
    }

    public void onClick(View view) {

    }
}
