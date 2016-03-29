package fr.univ_lille1.iut_info.bernardt.xbattlecompanionapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by mager on 25/03/2016.
 */
public class MiniGameActivity extends AppCompatActivity {

    private class Cell{

        public int quantity;
        public int id;
        public int height;

        public Cell(int id, int height){
            this.quantity = 0;
            this.id = id;
            this.height = height;
        }

    }

    private Cell[][] gridPipes = new Cell[2][2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame);

        gridPipes[0][0] = new Cell(R.id.cell1, 0);
        gridPipes[0][1] = new Cell(R.id.cell2, 0);
        gridPipes[1][0] = new Cell(R.id.cell3, 1);
        gridPipes[1][1] = new Cell(R.id.cell4, 0);

        launchTimer();

        addSwipeListener(R.id.layout_minigame);
    }

    public void launchTimer(){
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                if (gridPipes[0][0].quantity < 100){
                    gridPipes[0][0].quantity++;
                    drawCell(0, 0);
                }
                handler.postDelayed(this, 100);
            }
        };
        handler.postDelayed(runnable, 0);
    }

    public void drawCell(int x, int y){
        int q = gridPipes[x][y].quantity;
        int coord = 50 - q / 2;
        int size = 50 + q / 2;
        if(q > 0) {
            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#CD5C5C"));
            if(q == 100){
                paint.setColor(Color.parseColor("#5C5CCD"));
            }
            Bitmap bg = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bg);
            canvas.drawRect(coord, coord, size, size, paint);
            Drawable d = new BitmapDrawable(getResources(), bg);

            Resources r = getResources();
            Drawable[] layers = new Drawable[3];
            layers[0] = r.getDrawable(R.drawable.plain);
            layers[1] = d;
            layers[2] = r.getDrawable(R.drawable.pump);
            LayerDrawable layerDrawable = new LayerDrawable(layers);
            ImageView cell1 = (ImageView) findViewById(gridPipes[x][y].id);
            cell1.setImageDrawable(layerDrawable);
        }  else {
            Resources r = getResources();
            Drawable[] layers = new Drawable[2];
            layers[0] = r.getDrawable(R.drawable.plain);
            layers[1] = r.getDrawable(R.drawable.pump);
            LayerDrawable layerDrawable = new LayerDrawable(layers);
            ImageView cell1 = (ImageView) findViewById(gridPipes[x][y].id);
            cell1.setImageDrawable(layerDrawable);
        }

    }

    public void addSwipeListener(int idLayout){
        findViewById(idLayout).setOnTouchListener(new OnSwipeTouchListener(MiniGameActivity.this) {
            public void onSwipeBottom() {
                finish();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
