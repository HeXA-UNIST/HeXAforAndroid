package pro.hexa.hexaforandroid;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by kdk on 2015-02-24.
 */
public class BackPressCloseHandler extends Service {

    private long backKeyPressedTime = 0;
    private Toast toast;

    private Activity activity;

    public BackPressCloseHandler(Activity context) {
        this.activity = context;
    }

    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            activity.finish();
            toast.cancel();
        }
    }


    private void showGuide() {
        toast = Toast.makeText(activity, "뒤로가기를 한번 더 누르시면 종료됩니다.",
                Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}