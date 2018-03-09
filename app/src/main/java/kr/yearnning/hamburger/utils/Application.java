package kr.yearnning.hamburger.utils;

import android.graphics.BitmapFactory;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import kr.yearnning.hamburger.R;

/**
 * Created by yearnning on 2014. 8. 1..
 * This is singletone.
 */
public class Application extends android.app.Application {
    private static final String TAG = "Application";
    /**
     *
     */
    public DisplayImageOptions options;
    public ImageLoader imageLoader = ImageLoader.getInstance();

    /**
     *
     */
    @Override
    public void onCreate() {
        super.onCreate();

        /**
         *
         */

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .build();
        imageLoader.init(config);

        BitmapFactory.Options resizeOptions = new BitmapFactory.Options();
        resizeOptions.inScaled = true;

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.color.burger_card_background)
                .showImageForEmptyUri(R.color.burger_card_background)
                .showImageOnFail(android.R.color.holo_orange_light)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .delayBeforeLoading(1000)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .considerExifParams(true)
                .decodingOptions(resizeOptions)
                .resetViewBeforeLoading(true)
                .build();

    }

    /**
     *
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /**
     *
     */
    public void onTerminate() {
        super.onTerminate();
    }
}
