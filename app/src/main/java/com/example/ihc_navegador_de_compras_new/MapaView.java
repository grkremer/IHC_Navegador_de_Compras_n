package com.example.ihc_navegador_de_compras_new;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class MapaView extends View {
    private Paint paint = new Paint();

    public MapaView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        canvas.drawRect(2, 3, 4, 5, paint);
    }
}
