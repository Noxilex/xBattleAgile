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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by mager on 25/03/2016.
 */
public class MiniGameActivity extends AppCompatActivity {

    private class Cell{

        public int quantity;
        public int id;
        public int height;
        public int direction;

        public Cell(int id, int height){
            this.quantity = 0;
            this.id = id;
            this.height = height;
            this.direction = 5;
        }

    }

    private class Coord{
        public int x;
        public int y;

        public Coord(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private Cell[][] gridPipes;
    private Coord selectedCell;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame);

        gridPipes = new Cell[2][2];
        gridPipes[0][0] = new Cell(R.id.cell1, 0);
        gridPipes[0][1] = new Cell(R.id.cell2, 0);
        gridPipes[1][0] = new Cell(R.id.cell3, 1);
        gridPipes[1][1] = new Cell(R.id.cell4, 0);
        selectedCell = new Coord(0, 0);

        launchTimer(0, 0, 1);

        final EditText keyPad = (EditText) findViewById(R.id.keyPad);
        keyPad.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().isEmpty()) {
                    System.out.println(s.toString());
                    if(s.toString().equals("6")) {
                        launchTimer(1, 0, 1);
                        launchTimer(0, 0, -1);
                    }
                    keyPad.setText("");
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        addSwipeListener(R.id.layout_minigame);
    }

    public void launchTimer(int xCel, int yCel, int valuePerTick){
        final int x = xCel;
        final int y = yCel;
        final int value = valuePerTick;
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                if (gridPipes[x][y].quantity < 100){
                    if(x == 1 && y == 0)
                        System.out.println(gridPipes[1][0].quantity);
                    gridPipes[x][y].quantity += value;
                    drawCell(x, y);
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

        Resources r = getResources();
        Drawable[] layers;
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

            if(x == 0 && y == 0) {
                if(x != selectedCell.x || y != selectedCell.y) {
                    layers = new Drawable[3];
                    layers[0] = r.getDrawable(R.drawable.plain);
                    layers[1] = d;
                    layers[2] = r.getDrawable(R.drawable.pump);
                } else {
                    layers = new Drawable[4];
                    layers[0] = r.getDrawable(R.drawable.plain);
                    layers[1] = d;
                    layers[2] = r.getDrawable(R.drawable.pump);
                    layers[3] = r.getDrawable(R.drawable.frame);
                }
            } else {
                if(x != selectedCell.x || y != selectedCell.y) {
                    layers = new Drawable[2];
                    if (x == 0 && y == 1)
                        layers[0] = r.getDrawable(R.drawable.hill2);
                    else
                        layers[0] = r.getDrawable(R.drawable.plain);
                    layers[1] = d;
                } else {
                    layers = new Drawable[3];
                    if (x == 0 && y == 1)
                        layers[0] = r.getDrawable(R.drawable.hill2);
                    else
                        layers[0] = r.getDrawable(R.drawable.plain);
                    layers[1] = d;
                    layers[2] = r.getDrawable(R.drawable.frame);
                }
            }
        }  else {
            if (x == 0 && y == 0) {

                if(x != selectedCell.x || y != selectedCell.y) {
                    layers = new Drawable[2];
                    layers[0] = r.getDrawable(R.drawable.plain);
                    layers[1] = r.getDrawable(R.drawable.pump);
                } else {
                    layers = new Drawable[3];
                    layers[0] = r.getDrawable(R.drawable.plain);
                    layers[1] = r.getDrawable(R.drawable.pump);
                    layers[2] = r.getDrawable(R.drawable.frame);
                }
            } else {
                if(x != selectedCell.x || y != selectedCell.y) {
                    layers = new Drawable[1];
                    if (x == 0 && y == 1)
                        layers[0] = r.getDrawable(R.drawable.hill2);
                    else
                        layers[0] = r.getDrawable(R.drawable.plain);
                } else {
                    layers = new Drawable[2];
                    if (x == 0 && y == 1)
                        layers[0] = r.getDrawable(R.drawable.hill2);
                    else
                        layers[0] = r.getDrawable(R.drawable.plain);
                    layers[1] = r.getDrawable(R.drawable.frame);
                }
            }
        }
            LayerDrawable layerDrawable = new LayerDrawable(layers);
            ImageView cell1 = (ImageView) findViewById(gridPipes[x][y].id);
            cell1.setImageDrawable(layerDrawable);


    }

    public void addSwipeListener(int idLayout){
        findViewById(idLayout).setOnTouchListener(new OnSwipeTouchListener(MiniGameActivity.this) {
            public void onSwipeBottom() {
                finish();
            }
        });
    }

    public void onClick_Cell(View view){

        switch(view.getId()){
            case R.id.cell1:
                selectedCell = new Coord(0, 0);
                break;
            case R.id.cell2:
                selectedCell = new Coord(0, 1);
                break;
            case R.id.cell3:
                selectedCell = new Coord(1, 0);
                break;
            case R.id.cell4:
                selectedCell = new Coord(1, 1);
                break;
        }

        drawCell(0, 0);
        drawCell(0, 1);
        drawCell(1, 0);
        drawCell(1, 1);
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
