如何利用opencv来实现一个图像滤波器.

第一步当然就是把opencvsdk配置到我们的项目中来了,并且配置好了之后,我们还要进行load成功才可以使用.这一部分可以参照我的[上一篇笔记][fa87a051]

  [fa87a051]: http://imaster.top/2018/03/23/android-opencv%E6%9C%BA%E5%99%A8%E8%A7%86%E8%A7%89%E5%BA%93%E7%9A%84%E9%9B%86%E6%88%90/ "android-opencv机器视觉库的集成部署"
第二步则是调用opencv的函数,进行图像处理,下面是五种滤镜的效果,及实现代码.

### 简单滤镜之自定义核实现自己的线性滤波器 

效果:  

![](/preview/opencv_fs.png)![](/preview/opencv_zdy_r.png)

核心代码:  
```java
public class SharpenProcessor implements ImgProcessor {
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
        kernel.put(0, 0, 0, -1, 0, -1, 5, -1, 0, -1, 0);
        Imgproc.filter2D(src, dec, src.depth(), kernel);
        Utils.matToBitmap(dec, bitmap_dec);
    }

    public void processor(int progress) {
        kernel.put(0, 0, 0, -1, 0, -1, progress, -1, 0, -1, 0);
        Imgproc.filter2D(src, dec, src.depth(), kernel);
        Utils.matToBitmap(dec, bitmap_dec);
    }

    @Override
    public Bitmap getResult() {
        return bitmap_dec;
    }
}

```

### 简单滤镜之高斯模糊
效果:

![opencv高斯模糊](http://miqt.github.io/blogimgs/device-2018-03-27-155048.png)  

核心代码:

```java
//<editor-fold desc="高斯模糊">
ImageView image_src = (ImageView) findViewById(R.id.image_src);
ImageView image_result = (ImageView) findViewById(R.id.image_result);

Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.image);
Mat mat = new Mat(src.getWidth(), src.getHeight(), CvType.CV_8UC4);
Utils.bitmapToMat(src, mat);
Imgproc.blur(mat, mat, new Size(30, 30));
Bitmap bitmap = Bitmap.createBitmap(mat.cols(), mat.rows(), Bitmap.Config.ARGB_8888);
Utils.matToBitmap(mat, bitmap);
image_src.setImageBitmap(src);
image_result.setImageBitmap(bitmap);
//</editor-fold>
```

### 简单滤镜之腐蚀

效果:  

![腐蚀](/preview/opencv_fs.png)![腐蚀](/preview/opencv_fs_r.png)



核心代码:
```java
/**
 * 腐蚀运算
 */
public class ErodeProcessor implements ImgProcessor {
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
        kernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, new Size(wight, wight));
        Imgproc.erode(src, dec, kernel);
        Utils.matToBitmap(dec, bitmap_dec);

    }

    @Override
    public Bitmap getResult() {
        return bitmap_dec;
    }
}

```
