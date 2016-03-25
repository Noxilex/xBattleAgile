package fr.univ_lille1.iut_info.bernardt.xbattlecompanionapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mager on 25/03/2016.
 */
public class TutoActivity extends AppCompatActivity {

    private ImageView hillView;
    private int[] hills={R.drawable.hill1,R.drawable.hill2,R.drawable.hill3};

    private ImageView waterView;
    private int[] waters={R.drawable.water1,R.drawable.water2,R.drawable.water3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutoriel);

        hillView=(ImageView)findViewById(R.id.hillView);
        waterView=(ImageView)findViewById(R.id.waterView);

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i=0;
            public void run() {
                hillView.setImageResource(hills[i]);
                waterView.setImageResource(waters[i]);
                i++;
                if(i>hills.length-1) {
                    i=0;
                }
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(runnable, 0);


        findViewById(R.id.layout_tuto).setOnTouchListener(new OnSwipeTouchListener(TutoActivity.this) {

            public void onSwipeBottom() {
                finish();
            }
        });
    }
}
