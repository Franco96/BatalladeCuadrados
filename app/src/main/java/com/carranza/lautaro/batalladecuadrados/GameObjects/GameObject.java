package com.carranza.lautaro.batalladecuadrados.GameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.carranza.lautaro.batalladecuadrados.R;

/**
 * Created by Lautaro on 28/06/2017.
 */

public abstract class GameObject {

    protected Bitmap bmp;
    //Posiciones x e y
    protected float posX;
    protected float posY;
    //Velocidades de x e y
    protected int velocidad;
    protected int xSpeed;
    protected int ySpeed;
    //Tamaño
    protected float width;
    protected float heigth;


    public GameObject(float x , float y , float ancho , float alto , Bitmap bitmap,int velocidad)
    {
        //seteamos las posiciones
        posX = x;
        posY = y;
        //seteamos la velicidad
        this.velocidad = velocidad;
        xSpeed = velocidad;
        ySpeed = velocidad;
        //seteamos el tamaño
        width = ancho;
        heigth = alto;
        //seteamos la imagen
        bmp = bitmap;
        bmp = redimensionarImagen(bmp ,width,heigth);




    }

    public int getVelocidad()
    {
        return velocidad;
    }

    public float getPosX()
    {
        return posX;
    }
    public void setPosX(float posX)
    {
        this.posX = posX;

    }
    public  float getPosY()
    {
        return posY;
    }
    public void setPosY(float posY)
    {
        this.posY = posY;
    }

    public float getWidth()
    {
        return width;
    }
    public float getHeigth()
    {
        return heigth;
    }
    public Bitmap getBmp()
    {
        return bmp;
    }


    public int getxSpeed()
    {
        return xSpeed;
    }
    public void setxSpeed(int velocidad)
    {
        xSpeed = velocidad;
    }
    public int getySpeed()
    {
        return ySpeed;
    }
    public void setySpeed(int velocidad)
    {
        ySpeed = velocidad;
    }



    //METODO PARA PONER EL TAMAÑO DEL CUADRADO
    protected Bitmap redimensionarImagen(Bitmap mBitmap, float newWidth, float newHeigth){
        //Redimensionamos
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeigth) / height;
        // create a matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // recreate the new Bitmap
        return Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, false);
    }


}
