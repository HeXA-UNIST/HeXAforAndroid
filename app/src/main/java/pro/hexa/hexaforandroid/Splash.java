package pro.hexa.hexaforandroid;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by kdk on 2015-02-26.
 */
public class Splash extends Activity {

@Override
protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {

@Override
public void run() {
        finish();       // 3 초후 이미지를 닫아버림
        }
        }, 1500);
        }
}