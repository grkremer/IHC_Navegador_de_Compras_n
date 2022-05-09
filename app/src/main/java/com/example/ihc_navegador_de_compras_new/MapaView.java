package com.example.ihc_navegador_de_compras_new;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MapaView extends View {
    private Paint paint = new Paint();

    public MapaView(Context context) {
        super(context);
    }
    public MapaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MapaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void refresh() {
        setVisibility(View.INVISIBLE);
        setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.LTGRAY);
        paint.setColor(Color.GRAY);
        int nPrateleirasX = 5;
        int nPrateleirasY = 2;
        int tamanho = getContext().getResources().getDimensionPixelOffset(R.dimen.mapa_size);
        int larguraPrateleira = tamanho/(2*nPrateleirasX +1);
        int alturaPrateleira = (tamanho - larguraPrateleira*(nPrateleirasY+1)) / nPrateleirasY;
        for(int x=0; x < nPrateleirasX; x++)
            for(int y=0; y < nPrateleirasY; y++)
                canvas.drawRect(larguraPrateleira+(x*(2*larguraPrateleira)), larguraPrateleira*(y+1) + alturaPrateleira*y, (2*larguraPrateleira)+(x*(2*larguraPrateleira)), larguraPrateleira*(y+1) + alturaPrateleira*(y+1), paint);

    }
}
