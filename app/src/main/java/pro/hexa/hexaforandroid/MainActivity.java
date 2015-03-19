package pro.hexa.hexaforandroid;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;


public class MainActivity extends ActionBarActivity {
    FragmentTabHost tabHost;
//    ActionBar actionBar;
    private BackPressCloseHandler backPressCloseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this,Splash.class));

        setContentView(R.layout.activity_main);
        backPressCloseHandler = new BackPressCloseHandler(this);
        tabHost = (FragmentTabHost) findViewById(R.id.tabHost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            }
        });
        tabHost.addTab(tabHost.newTabSpec("버스정보").setIndicator("버스\n정보"), UNISTBus.class, null);
        tabHost.addTab(tabHost.newTabSpec("밥먹기 십오분전").setIndicator("밥먹기 십오분전"), UNISTBap.class, null);
        tabHost.addTab(tabHost.newTabSpec("포탈게시판").setIndicator("포탈\n게시판"), PortalBBS.class, null);
        tabHost.addTab(tabHost.newTabSpec("스터디룸 예약").setIndicator("스터디룸 예약"), StudyRoom.class, null);
        tabHost.addTab(tabHost.newTabSpec("익명게시판").setIndicator("익명\n게시판"), Anonymous.class, null);
//        actionBar = getSupportActionBar();
//        actionBar.setHideOnContentScrollEnabled(true);
        FrameLayout frameLayout =(FrameLayout)findViewById(R.id.realtabcontent);
        frameLayout.setOnTouchListener(new View.OnTouchListener() {

            float mY;
            float swipeDistance;
            final float REQUIRED_SWIPE = 50;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float y = event.getY();

                switch(event.getAction()) {

                    case MotionEvent.ACTION_DOWN :
                        swipeDistance = 0;
                        mY = y;
                        break;

                    case MotionEvent.ACTION_MOVE :
                        swipeDistance += y - mY;
                        if(Math.abs(swipeDistance) > REQUIRED_SWIPE) {
                            if(swipeDistance < 0) {
                                if(tabHost.getVisibility() == View.VISIBLE) {
                                    TranslateAnimation animate = new TranslateAnimation(
                                            0,0,0,tabHost.getHeight());
                                    animate.setDuration(500);
                                    animate.setFillAfter(false);
                                    tabHost.startAnimation(animate);
                                    tabHost.setVisibility(View.GONE);
                                    swipeDistance = 0;
                                }
                            } else {
                                if(tabHost.getVisibility() != View.VISIBLE) {
                                    TranslateAnimation animate = new TranslateAnimation(
                                            0,0,tabHost.getHeight(),0);
                                    animate.setDuration(500);
                                    animate.setFillAfter(false);
                                    tabHost.startAnimation(animate);
                                    tabHost.setVisibility(View.VISIBLE);
                                    swipeDistance = 0;
                                }
                            }
                        }

                        mY = y;
                        break;

                }

                mY = y;
                return false;

            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        backPressCloseHandler.onBackPressed();
    }
}
