package com.example.pangrong.myapplication;


import android.content.Context;
import android.graphics.*;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;


public class GameView extends SurfaceView implements Callback,Runnable {
    static int SCREEN_HEIGHT;
    private Thread thread;
    private GameBgPicture gameBgPicture;
    private Canvas canvas;
    private Paint paint;
    private boolean flag;
    private SurfaceHolder sfh;
    private Bitmap bgmap1= BitmapFactory.decodeResource(this.getResources(),R.drawable.bg);
    private Bitmap bgmap2= BitmapFactory.decodeResource(this.getResources(),R.drawable.bg);


    GameView(Context context){
        super(context);
        sfh = this.getHolder();
        sfh.addCallback(this);
        gameBgPicture=new GameBgPicture(bgmap1,bgmap2);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        this.setKeepScreenOn(true);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        SCREEN_HEIGHT=this.getHeight();
        flag=true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag=false;
    }

    public void logic(){
        gameBgPicture.logic();
    }

    public void draw(){
        try {
            canvas=sfh.lockCanvas();
            if (canvas != null )
                canvas.drawColor(Color.WHITE);
            gameBgPicture.drawSelf(canvas, paint);
        }catch (Exception e){

        }finally {
            if (canvas != null ){
                sfh.unlockCanvasAndPost(canvas);
            }
        }

    }

    @Override
    public void run() {
        while (flag) {
            long start = System.currentTimeMillis();
            draw();
            logic();
            long end = System.currentTimeMillis();
            try {
                if (end - start < 50) {
                    Thread.sleep(50 - (end - start));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
