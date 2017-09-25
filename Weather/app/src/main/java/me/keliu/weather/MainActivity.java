package me.keliu.weather;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by KE on 2017/9/21.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_info);
    }
}