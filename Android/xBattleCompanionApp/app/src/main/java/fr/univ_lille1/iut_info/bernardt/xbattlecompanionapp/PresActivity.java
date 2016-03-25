package fr.univ_lille1.iut_info.bernardt.xbattlecompanionapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by mager on 25/03/2016.
 */
public class PresActivity extends AppCompatActivity {

    private int onglet_pres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presentation);

        findViewById(R.id.layout_pres).setOnTouchListener(new OnSwipeTouchListener(PresActivity.this) {
            public void onSwipeRight() {
                if (onglet_pres > 1) {
                    onglet_pres--;
                    changeTab(onglet_pres);
                }
            }

            public void onSwipeLeft() {
                if (onglet_pres < 3) {
                    onglet_pres++;
                    changeTab(onglet_pres);
                }
            }

            public void onSwipeBottom() {
                // changeContentView(R.layout.menu);
                finish();
            }
        });
        onglet_pres = 1;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("onglet_pres", onglet_pres);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("RESTORE : " + savedInstanceState.getInt("onglet_pres"));
        onglet_pres = savedInstanceState.getInt("onglet_pres");
        changeTab(onglet_pres);
    }

    public void changeTab(int iTab){
        TextView presTitle1 = (TextView) findViewById(R.id.pres_title1);
        TextView presTitle2 = (TextView) findViewById(R.id.pres_title2);
        TextView presTitle3 = (TextView) findViewById(R.id.pres_title3);
        TextView presCurrentTitle = (TextView) findViewById(R.id.pres_currentTitle);
        TextView presText = (TextView) findViewById(R.id.pres_text);

        switch (iTab){
            case 1:
                presTitle1.setBackgroundResource(R.color.Color1);
                presTitle2.setBackgroundResource(R.color.Color5);
                presTitle3.setBackgroundResource(R.color.Color5);
                presTitle1.setTextColor(getResources().getColor(R.color.Color3));
                presTitle2.setTextColor(getResources().getColor(R.color.Color1));
                presTitle3.setTextColor(getResources().getColor(R.color.Color1));
                presCurrentTitle.setText(getResources().getString(R.string.pres_title1));
                presText.setText(getResources().getString(R.string.pres_text1));
                break;
            case 2:
                presTitle1.setBackgroundResource(R.color.Color5);
                presTitle2.setBackgroundResource(R.color.Color1);
                presTitle3.setBackgroundResource(R.color.Color5);
                presTitle1.setTextColor(getResources().getColor(R.color.Color1));
                presTitle2.setTextColor(getResources().getColor(R.color.Color3));
                presTitle3.setTextColor(getResources().getColor(R.color.Color1));
                presCurrentTitle.setText(getResources().getString(R.string.pres_title2));
                presText.setText(getResources().getString(R.string.pres_text2));
                break;
            case 3:
                presTitle1.setBackgroundResource(R.color.Color5);
                presTitle2.setBackgroundResource(R.color.Color5);
                presTitle3.setBackgroundResource(R.color.Color1);
                presTitle1.setTextColor(getResources().getColor(R.color.Color1));
                presTitle2.setTextColor(getResources().getColor(R.color.Color1));
                presTitle3.setTextColor(getResources().getColor(R.color.Color3));
                presCurrentTitle.setText(getResources().getString(R.string.pres_title3));
                presText.setText(getResources().getString(R.string.pres_text3));
                break;
        }
    }
}
