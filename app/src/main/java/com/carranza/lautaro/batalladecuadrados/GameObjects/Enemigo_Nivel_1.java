package com.carranza.lautaro.batalladecuadrados.GameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.carranza.lautaro.batalladecuadrados.R;

/**
 * Created by Lautaro on 30/06/2017.
 */

public class Enemigo_Nivel_1 extends GameObject {


    public Enemigo_Nivel_1(Context context,int posX,int posY,int velocidad) {
        super(posX, posY,150,150, BitmapFactory.decodeResource(context.getResources(), R.mipmap.enemigo_nivel1),velocidad);
    }
}
