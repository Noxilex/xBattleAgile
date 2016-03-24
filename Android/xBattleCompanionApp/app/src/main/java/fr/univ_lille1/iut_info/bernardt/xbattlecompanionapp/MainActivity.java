package fr.univ_lille1.iut_info.bernardt.xbattlecompanionapp;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int onglet_pres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        onglet_pres = 1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onClick_Pres(View view){
        setContentView(R.layout.presentation);

        findViewById(R.id.layout_pres).setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            public void onSwipeRight() {
                if(onglet_pres > 1) {
                    onglet_pres--;
                    changeTab(onglet_pres);
                }
            }

            public void onSwipeLeft() {
                if(onglet_pres < 3) {
                    onglet_pres++;
                    changeTab(onglet_pres);
                }
            }

            public void onSwipeBottom() {
                setContentView(R.layout.menu);
            }
        });
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
