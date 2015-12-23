package com.example.myfirst.bitmapdemo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Bitmap b=Bitmap.createBitmap(500,500,Bitmap.Config.ARGB_8888);
        b.eraseColor(Color.RED);*/
        /*ImageView imageView=(ImageView)findViewById(R.id.imageView);
        imageView.setImageBitmap(b);*/

        Bitmap b = Bitmap.createBitmap(500, 500,
                Bitmap.Config.ARGB_8888);
        b.eraseColor(Color.RED);
        Canvas c = new Canvas(b);
        c = new Canvas(b);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        c.drawLine(0.0F, 0.0F, 500.0F, 500.0F, paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.YELLOW);
        c.drawCircle(400F, 200F, 50F, paint);
        paint.setColor(Color.GREEN);
        c.rotate(45,100,350);
        c.drawRect(20F, 300F, 180F, 400F, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(50F);
        c.drawText("Hello Graphics",0,14,90,80,paint);
        c.rotate(50f);
        ImageView imageview=
                (ImageView) findViewById(R.id.imageView);
        imageview.setImageBitmap(b);
    }
}
