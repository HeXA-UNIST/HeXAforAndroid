package pro.hexa.hexaforandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by kdk on 2015-02-26.
 */
public class UNISTBap extends Fragment {
    Context mContext;
    public UNISTBap(){this.mContext=mContext;}
    private Bundle ImageBundle;
    TextView textView;
    private Exception mException;
    int i=2;
    PhotoViewAttacher mAttacher;
    ImageView img;
    String strCurYear,strCurMonth,strCurDay;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat CurMonthFormat = new SimpleDateFormat("MM");
        SimpleDateFormat CurDayFormat = new SimpleDateFormat("dd");
         strCurYear = CurYearFormat.format(date);
         strCurMonth = CurMonthFormat.format(date);
         strCurDay = CurDayFormat.format(date);
        Log.e("date",strCurYear+strCurMonth+strCurDay);


        View view = inflater.inflate(R.layout.unist_bap,null);
        img = (ImageView) view.findViewById(R.id.bap);
        mAttacher = new PhotoViewAttacher(img);
        textView = (TextView)view.findViewById(R.id.text);
        new DownloadImageTask(img)
                .execute("http://lab.hexa.pro/assets/fbnotice/files/"+strCurYear+"-"+strCurMonth+"-"+strCurDay+"_"+i+".png");
        return view;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                mException = e;
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }


        protected void onPostExecute(Bitmap result) {
            if(mException !=null){
                if(i>0){
                    i--;
                    Log.e("i", String.valueOf(i));
                    mException = null;
                    new DownloadImageTask(img)
                            .execute("http://lab.hexa.pro/assets/fbnotice/files/" + strCurYear + "-" + strCurMonth + "-" + strCurDay + "_" + i + ".png");
                }
                else{
                bmImage.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.VISIBLE);
                    mException = null;}

            }else{
                bmImage.setVisibility(View.VISIBLE);
            textView.setVisibility(View.INVISIBLE);
            bmImage.setImageBitmap(result);}
        }
    }

}
