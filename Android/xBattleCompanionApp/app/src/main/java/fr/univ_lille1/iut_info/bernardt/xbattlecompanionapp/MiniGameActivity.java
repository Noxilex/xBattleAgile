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

import java.util.ArrayList;

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

    private Handler[] handler = new Handler[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minigame);

        for(int i=0; i < 4; i++){
            handler[i] = new Handler();
        }

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
                    if(gridPipes[selectedCell.x][selectedCell.y].direction != 5) {
                        handler[selectedCell.x + selectedCell.y * 2].removeCallbacksAndMessages(null);
                    }

                    if (s.toString().equals("7")) {
                        gridPipes[selectedCell.x][selectedCell.y].direction = 7;
                        if(selectedCell.x == 1 && selectedCell.y == 0) {
                            fromC1toC2(new Coord(selectedCell.x, selectedCell.y), new Coord(selectedCell.x - 1, selectedCell.y));
                            fromC1toC2(new Coord(selectedCell.x, selectedCell.y), new Coord(selectedCell.x, selectedCell.y + 1));
                        }
                    } else
                    if (s.toString().equals("8")) {
                        gridPipes[selectedCell.x][selectedCell.y].direction = 8;
                        if(selectedCell.y == 0)
                            fromC1toC2(new Coord(selectedCell.x, selectedCell.y), new Coord(selectedCell.x, selectedCell.y + 1));
                    } else
                    if (s.toString().equals("9")) {
                        gridPipes[selectedCell.x][selectedCell.y].direction = 9;
                        if(selectedCell.x == 0 && selectedCell.y == 0) {
                            fromC1toC2(new Coord(selectedCell.x, selectedCell.y), new Coord(selectedCell.x + 1, selectedCell.y));
                            fromC1toC2(new Coord(selectedCell.x, selectedCell.y), new Coord(selectedCell.x, selectedCell.y + 1));
                        }
                    } else
                    if (s.toString().equals("4")) {
                        gridPipes[selectedCell.x][selectedCell.y].direction = 4;
                        if(selectedCell.x == 1)
                            fromC1toC2(new Coord(selectedCell.x, selectedCell.y), new Coord(selectedCell.x - 1, selectedCell.y));
                    } else
                    if (s.toString().equals("5")) {
                        gridPipes[selectedCell.x][selectedCell.y].direction = 5;
                        handler[selectedCell.x + selectedCell.y * 2].removeCallbacksAndMessages(null);
                    } else
                    if (s.toString().equals("6")) {
                        gridPipes[selectedCell.x][selectedCell.y].direction = 6;
                        if(selectedCell.x == 0)
                            fromC1toC2(new Coord(selectedCell.x, selectedCell.y), new Coord(selectedCell.x + 1, selectedCell.y));
                    } else
                    if (s.toString().equals("1")) {
                        gridPipes[selectedCell.x][selectedCell.y].direction = 1;
                        if(selectedCell.x == 1 && selectedCell.y == 1){
                            fromC1toC2(new Coord(selectedCell.x, selectedCell.y), new Coord(selectedCell.x - 1, selectedCell.y));
                            fromC1toC2(new Coord(selectedCell.x, selectedCell.y), new Coord(selectedCell.x, selectedCell.y - 1));
                        }
                    } else
                    if (s.toString().equals("2")) {
                        gridPipes[selectedCell.x][selectedCell.y].direction = 2;
                        if(selectedCell.y == 1)
                            fromC1toC2(new Coord(selectedCell.x, selectedCell.y), new Coord(selectedCell.x, selectedCell.y - 1));
                    } else
                    if (s.toString().equals("3")) {
                        gridPipes[selectedCell.x][selectedCell.y].direction = 3;
                        if(selectedCell.x == 0 && selectedCell.y == 1) {
                            fromC1toC2(new Coord(selectedCell.x, selectedCell.y), new Coord(selectedCell.x + 1, selectedCell.y));
                            fromC1toC2(new Coord(selectedCell.x, selectedCell.y), new Coord(selectedCell.x, selectedCell.y - 1));
                        }
                    }
                    drawCell(0, 0);
                    drawCell(0, 1);
                    drawCell(1, 0);
                    drawCell(1, 1);
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

    public void fromC1toC2(final Coord c1, final Coord c2){
        Runnable runnable = new Runnable() {
            public void run() {
                if (gridPipes[c1.x][c1.y].quantity > 0 && gridPipes[c2.x][c2.y].quantity < 100){
                    gridPipes[c1.x][c1.y].quantity += -2;
                    gridPipes[c2.x][c2.y].quantity += 2;

                    drawCell(c1.x, c1.y);
                    drawCell(c2.x, c2.y);
                }
                handler[c1.x + c1.y * 2].postDelayed(this, 100);
            }
        };
        handler[c1.x + c1.y * 2].postDelayed(runnable, 0);
    }

    public void launchTimer(int xCel, int yCel, int valuePerTick){
        final int x = xCel;
        final int y = yCel;
        final int value = valuePerTick;
        final Handler handlerPump = new Handler();
        Runnable runnable = new Runnable() {
            public void run() {
                if (gridPipes[x][y].quantity < 100){
                    gridPipes[x][y].quantity += value;
                    drawCell(x, y);
                }
                handlerPump.postDelayed(this, 100);
            }
        };
        handlerPump.postDelayed(runnable, 0);
    }

    public void drawCell(int x, int y){
        int q = gridPipes[x][y].quantity;
        int coord = 50 - q / 2;
        int size = 50 + q / 2;

        Resources r = getResources();
        Drawable[] layers;
        ArrayList<Drawable> drawableList = new ArrayList<Drawable>();

        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#CD5C5C"));
        if(q == 100){
            paint.setColor(Color.parseColor("#AD3C3C"));
        }
        Bitmap bg = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bg);
        canvas.drawRect(coord, coord, size, size, paint);
        Drawable d = new BitmapDrawable(getResources(), bg);

        Paint paintPipe = new Paint();
        paintPipe.setColor(Color.parseColor("#000000"));
        Bitmap bitmapPipe = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Canvas canvasPipe = new Canvas(bitmapPipe);
        switch (gridPipes[x][y].direction){
            case 7:
                canvasPipe.drawRect(0, 45, 50, 55, paintPipe);
                canvasPipe.drawRect(45, 50, 55, 100, paintPipe);
                break;
            case 8:
                canvasPipe.drawRect(45, 50, 55, 100, paintPipe);
                break;
            case 9:
                canvasPipe.drawRect(45, 50, 55, 100, paintPipe);
                canvasPipe.drawRect(50, 45, 100, 55, paintPipe);
                break;
            case 4:
                canvasPipe.drawRect(0, 45, 50, 55, paintPipe);
                break;
            case 5:

                break;
            case 6:
                canvasPipe.drawRect(50, 45, 100, 55, paintPipe);
                break;
            case 1:
                canvasPipe.drawRect(45, 0, 55, 50, paintPipe);
                canvasPipe.drawRect(0, 45, 50, 55, paintPipe);
                break;
            case 2:
                canvasPipe.drawRect(45, 0, 55, 50, paintPipe);
                break;
            case 3:
                canvasPipe.drawRect(45, 0, 55, 50, paintPipe);
                canvasPipe.drawRect(50, 45, 100, 55, paintPipe);
                break;
        }
        Drawable dPipe = new BitmapDrawable(getResources(), bitmapPipe);

        if(x == 0 && y == 0) {
            drawableList.add(r.getDrawable(R.drawable.plain));
            if(q > 0) {
                drawableList.add(d);
            }
            drawableList.add(r.getDrawable(R.drawable.pump));
            drawableList.add(dPipe);
            if(x == selectedCell.x && y == selectedCell.y) {
                drawableList.add(r.getDrawable(R.drawable.frame));
            }
        } else {
            if (x == 0 && y == 1)
                drawableList.add(r.getDrawable(R.drawable.hill2));
            else
                drawableList.add(r.getDrawable(R.drawable.plain));
            if(q > 0) {
                drawableList.add(d);
            }
            drawableList.add(dPipe);
            if(x == selectedCell.x && y == selectedCell.y) {
                drawableList.add(r.getDrawable(R.drawable.frame));
            }
        }

        layers = new Drawable[drawableList.size()];
        int idx = 0;
        for(Drawable draw : drawableList){
            layers[idx] = draw;
            idx++;
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
