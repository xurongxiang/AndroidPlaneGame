package com.example.pangrong.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class GameBgPicture {

    private Bitmap bgmap1;
    private Bitmap bgmap2;
    private int bg_1;
    private int bg_2;
    private int speed;

    GameBgPicture(Bitmap bitmap,Bitmap bgmaps){

        this.bgmap1=bitmap;
        this.bgmap2=bgmaps;
        this.speed=3;
        bg_1=Math.abs(bgmap1.getHeight()-GameView.SCREEN_HEIGHT);
        bg_2=bg_1-bgmap1.getHeight() ;
    }

    public void drawSelf(Canvas canvas, Paint paint){
        canvas.drawBitmap(bgmap1,0,bg_1,paint);
        canvas.drawBitmap(bgmap2,0,bg_2,paint);
    }

    public void logic(){
        bg_1 += speed;
        bg_2 += speed;

        if (bg_1 > GameView.SCREEN_HEIGHT) {
            bg_1 = bg_2 - bgmap1.getHeight();
        }

        if (bg_2 > GameView.SCREEN_HEIGHT) {
            bg_2 = bg_1 - bgmap2.getHeight();
        }
    }
}
