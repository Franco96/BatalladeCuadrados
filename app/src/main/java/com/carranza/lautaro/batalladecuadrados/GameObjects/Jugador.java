package com.carranza.lautaro.batalladecuadrados.GameObjects;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.carranza.lautaro.batalladecuadrados.R;

/**
 * Created by Lautaro on 28/06/2017.
 */

public class Jugador extends GameObject {

    public Jugador(Context context) {
        super(150, 800,150,150, BitmapFactory.decodeResource(context.getResources(), R.mipmap.cuadrado_jugador),5);
    }
}
