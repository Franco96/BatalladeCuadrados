package com.carranza.lautaro.batalladecuadrados.GameObjects;

/**
 * Created by Lautaro on 27/06/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.carranza.lautaro.batalladecuadrados.Activities.GameOverActivity;
import com.carranza.lautaro.batalladecuadrados.R;


public class GameObjectGraphics extends View {

    private Paint paint = new Paint();

    private GameThread gameThread;

    private GameObject[] objetos;

    private int xSpeedBall, ySpeedBall;
    private int speedBall = 2;
    private float posXBall, posYBall;


    private  Context context;
    public GameObjectGraphics(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        this.context = context;
        objetos = new GameObject[3];
        objetos[0] = new Jugador(context);
        objetos[1] = new Enemigo_Nivel_1(context, 0, 0, 10);
        objetos[2] = new Enemigo_Nivel_1(context, 150, 0, 5);
     //  objetos[3] = new Enemigo_Nivel_1(context,110, 600, 5);
     //   objetos[4] = new Enemigo_Nivel_1(context, 400, 600, 5);


        posXBall = 20;
        posYBall = 20;

        xSpeedBall = speedBall;
        ySpeedBall = speedBall;


        gameThread = new GameThread(this);
        gameThread.setRunning(true);
        gameThread.start();

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {


        float xTocado = event.getX();
        float yTocado = event.getY();


        if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {


            if ((xTocado >= 60) && (xTocado <= (getWidth() - objetos[0].getWidth() / 2)))
                objetos[0].setPosX(xTocado - (objetos[0].getWidth() / 2));

            if ((yTocado >= (60)) && (yTocado <= (getHeight() - objetos[0].getHeigth() / 2)))
                objetos[0].setPosY(yTocado - (objetos[0].getHeigth() / 2));

        }


        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        paint.setColor(Color.WHITE);

        update();
        gameOver();


        for (int i = 1; i < objetos.length; i++) {
            if (objetos[i].getPosX() + 150 >= getWidth())
                objetos[i].setxSpeed(-objetos[i].getVelocidad());
            if (objetos[i].getPosX() <= 0)
                objetos[i].setxSpeed(+objetos[i].getVelocidad());
            if (objetos[i].getPosY() + 150 >= (getHeight()/2-20))
                objetos[i].setySpeed(-objetos[i].getVelocidad());
            if (objetos[i].getPosY() <= 0)
                objetos[i].setySpeed(+objetos[i].getVelocidad());

            objetos[i].setPosX(objetos[i].getPosX() + objetos[i].getxSpeed());
            objetos[i].setPosY(objetos[i].getPosY() + objetos[i].getySpeed());
        }


        if (posXBall >= getWidth() - 30)
            xSpeedBall = -speedBall;
        if (posXBall - 30 <= 0)
            xSpeedBall = +speedBall;
        if (posYBall >= getHeight() - 30)
            ySpeedBall = -speedBall;
        if (posYBall - 30 <= 0)
            ySpeedBall = speedBall;

        posXBall = posXBall + xSpeedBall;
        posYBall = posYBall + ySpeedBall;

        canvas.drawRect(0,getHeight()/2-20,getWidth(),20+getHeight()/2,paint);

        canvas.drawCircle(posXBall, posYBall, 30, paint);





        for (int i = 0; i < objetos.length; i++)
            canvas.drawBitmap(objetos[i].getBmp(), objetos[i].getPosX(), objetos[i].getPosY(), null);


        postInvalidateDelayed(1);


    }


    //Metodo para el rebote de la pelota
    private void update() {

        Intent perdi = new Intent(context, GameOverActivity.class);

        for (int i = 0; i < objetos.length; i++) {
            if (((posYBall + 30 >= objetos[i].getPosY()) && (posYBall + 30 <= (objetos[i].getPosY() + objetos[i].getHeigth() - 130))) && ((objetos[i].getPosX() <= posXBall) && (posXBall <= (objetos[i].getPosX() + objetos[i].getWidth())))) {
                {  if(i==0)
               {
                   context.startActivity(perdi);
                   break;
               }
                ySpeedBall = -speedBall;}
    }

            if (((posYBall <= (objetos[i].getPosY() + objetos[i].getHeigth())) && (posYBall + 30 >= (objetos[i].getPosY() + 130))) && ((objetos[i].getPosX() <= posXBall) && (posXBall <= (objetos[i].getPosX() + objetos[i].getWidth())))) {

                {    if(i==0)
                {
                    context.startActivity(perdi);
                    break;
                }
                ySpeedBall = speedBall;}
            }


            if (((posXBall >= objetos[i].getPosX()) && (posXBall <= (objetos[i].getPosX() + objetos[i].getWidth() - 130))) && ((objetos[i].getPosY() <= posYBall) && (posYBall <= (objetos[i].getPosY() + objetos[i].getHeigth()))))
            {  if(i==0)
                {
                    context.startActivity(perdi);
                    break;
                }

                xSpeedBall = -speedBall;}

            if (((posXBall <= (objetos[i].getPosX() + objetos[i].getWidth())) && (posXBall >= (objetos[i].getPosX() + 130))) && ((objetos[i].getPosY() <= posYBall) && (posYBall <= (objetos[i].getPosY() + objetos[i].getHeigth())))) {
                if (i == 0) {
                    context.startActivity(perdi);
                    break;
                }

                xSpeedBall = speedBall;
            }

            posXBall += xSpeedBall;
            posYBall += ySpeedBall;

        }


    }


    //Metodo para el game over

    private void gameOver() {

        Intent perdi = new Intent(context, GameOverActivity.class);

        for (int i = 1; i < objetos.length; i++) {
            if (((objetos[0].getPosY() + objetos[0].getHeigth() >= objetos[i].getPosY()) && (objetos[0].getPosY() + objetos[0].getHeigth() <= (objetos[i].getPosY() + objetos[i].getHeigth() - 130))) && ((objetos[i].getPosX() <= objetos[0].getPosX()) && (objetos[0].getPosX() <= (objetos[i].getPosX() + objetos[i].getWidth())))) {


                context.startActivity(perdi);
                break;

            }

            if (((objetos[0].getPosY() <= (objetos[i].getPosY() + objetos[i].getHeigth())) && (objetos[0].getPosY() + objetos[0].getHeigth() >= (objetos[i].getPosY() + 130))) && ((objetos[i].getPosX() <= objetos[0].getPosX()) && (objetos[0].getPosX() <= (objetos[i].getPosX() + objetos[i].getWidth())))) {


                context.startActivity(perdi);
                break;
            }

            if (((objetos[0].getPosX() >= objetos[i].getPosX()) && (objetos[0].getPosX() <= (objetos[i].getPosX() + objetos[i].getWidth() - 130))) && ((objetos[i].getPosY() <= objetos[0].getPosY()) && (objetos[0].getPosY() <= (objetos[i].getPosY() + objetos[i].getHeigth())))) {


                context.startActivity(perdi);
                break;
            }
            if (((objetos[0].getPosX() <= (objetos[i].getPosX() + objetos[i].getWidth())) && (objetos[0].getPosX() >= (objetos[i].getPosX() + 130))) && ((objetos[i].getPosY() <= objetos[0].getPosY()) && (objetos[0].getPosY() <= (objetos[i].getPosY() + objetos[i].getHeigth())))) {
                context.startActivity(perdi);
                break;


            }
            }







    }

}