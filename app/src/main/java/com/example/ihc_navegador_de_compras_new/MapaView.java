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
        int nPrateleirasX = LoginActivity.nPrateleirasX;
        int nPrateleirasY = LoginActivity.nPrateleirasY;
        int tamanho = getContext().getResources().getDimensionPixelOffset(R.dimen.mapa_size);
        int larguraPrateleira = tamanho/(2*nPrateleirasX +1);
        int alturaPrateleira = (tamanho - larguraPrateleira*(nPrateleirasY+1)) / nPrateleirasY;
        for(int x=0; x < nPrateleirasX; x++) {
            for (int y = 0; y < nPrateleirasY; y++) {
                canvas.drawRect(larguraPrateleira + (x * (2 * larguraPrateleira)), larguraPrateleira * (y + 1) + alturaPrateleira * y, (2 * larguraPrateleira) + (x * (2 * larguraPrateleira)), larguraPrateleira * (y + 1) + alturaPrateleira * (y + 1), paint);
            }
        }
        paint.setTextSize(larguraPrateleira/2);
        paint.setTextAlign(Paint.Align.CENTER);
        for(Corredor corredor : LoginActivity.corredores) {
            if(corredor.isPrateleira()) {
                int y;
                if(corredor.getY() == 0) {
                    y = larguraPrateleira + alturaPrateleira/2;
                }
                else if(corredor.getY() == 1) {
                    y = larguraPrateleira + alturaPrateleira + larguraPrateleira/2;
                }
                else {
                    y = 2*larguraPrateleira + alturaPrateleira + alturaPrateleira/2;
                }
                int x = corredor.getX()*larguraPrateleira + larguraPrateleira/2;
                canvas.drawText(Integer.toString(corredor.getNumero()), x, y, paint);
            }
        }
        paint.setColor(Color.RED);
        for(Corredor corredor : LoginActivity.corredores) {
            for(Corredor vizinho : corredor.getVizinhos()) {
                if(corredor.isCaminho() && vizinho.isCaminho()) {
                    int y1, y2;
                    if(corredor.getY() == 0) {
                        y1 = larguraPrateleira + alturaPrateleira/2;
                    }
                    else if(corredor.getY() == 1) {
                        y1 = larguraPrateleira + alturaPrateleira + larguraPrateleira/2;
                    }
                    else {
                        y1 = 2*larguraPrateleira + alturaPrateleira + alturaPrateleira/2;
                    }
                    if(vizinho.getY() == 0) {
                        y2 = larguraPrateleira + alturaPrateleira/2;
                    }
                    else if(vizinho.getY() == 1) {
                        y2 = larguraPrateleira + alturaPrateleira + larguraPrateleira/2;
                    }
                    else {
                        y2 = 2*larguraPrateleira + alturaPrateleira + alturaPrateleira/2;
                    }
                    canvas.drawLine(corredor.getX()*larguraPrateleira + larguraPrateleira/2, y1, vizinho.getX()*larguraPrateleira + larguraPrateleira/2, y2, paint);
                    canvas.drawLine(corredor.getX()*larguraPrateleira + larguraPrateleira/2, y1 +1, vizinho.getX()*larguraPrateleira + larguraPrateleira/2, y2+1, paint);
                    canvas.drawLine(corredor.getX()*larguraPrateleira + larguraPrateleira/2, y1-1, vizinho.getX()*larguraPrateleira + larguraPrateleira/2, y2-1, paint);
                    canvas.drawLine(corredor.getX()*larguraPrateleira + larguraPrateleira/2 +1, y1, vizinho.getX()*larguraPrateleira + larguraPrateleira/2 +1, y2, paint);
                    canvas.drawLine(corredor.getX()*larguraPrateleira + larguraPrateleira/2 -1, y1, vizinho.getX()*larguraPrateleira + larguraPrateleira/2 -1, y2, paint);
                }
            }
        }
        if(LoginActivity.inicio != -1) {
            int x = LoginActivity.corredores.get(LoginActivity.inicio-1).getX();
            int y = LoginActivity.corredores.get(LoginActivity.inicio-1).getY();
            int raio = larguraPrateleira/8;
            if(y == 0) {
                y = larguraPrateleira + alturaPrateleira/2;
            }
            else if(y == 1) {
                y = larguraPrateleira + alturaPrateleira + larguraPrateleira/2;
            }
            else {
                y = 2*larguraPrateleira + alturaPrateleira + alturaPrateleira/2;
            }
            canvas.drawCircle(x*larguraPrateleira + larguraPrateleira/2, y, raio, paint);
        }
        if(LoginActivity.fim != -1) {
            int x = LoginActivity.corredores.get(LoginActivity.fim-1).getX();
            int y = LoginActivity.corredores.get(LoginActivity.fim-1).getY();
            if(y == 0) {
                y = larguraPrateleira + alturaPrateleira/2;
            }
            else if(y == 1) {
                y = larguraPrateleira + alturaPrateleira + larguraPrateleira/2;
            }
            else {
                y = 2*larguraPrateleira + alturaPrateleira + alturaPrateleira/2;
            }
            x = x*larguraPrateleira + larguraPrateleira/2;
            if(LoginActivity.fim >= 7) {
                canvas.drawLine(x, y, x-(larguraPrateleira/5), y-(larguraPrateleira/5), paint);
                canvas.drawLine(x, y +1, x-(larguraPrateleira/5), y-(larguraPrateleira/5) + 1, paint);
                canvas.drawLine(x, y -1, x-(larguraPrateleira/5), y-(larguraPrateleira/5) - 1, paint);
                canvas.drawLine(x, y, x+(larguraPrateleira/5), y-(larguraPrateleira/5), paint);
                canvas.drawLine(x, y +1, x+(larguraPrateleira/5), y-(larguraPrateleira/5) + 1, paint);
                canvas.drawLine(x, y -1, x+(larguraPrateleira/5), y-(larguraPrateleira/5) - 1, paint);
            }
            else {
                canvas.drawLine(x, y, x-(larguraPrateleira/5), y+(larguraPrateleira/5), paint);
                canvas.drawLine(x, y +1, x-(larguraPrateleira/5), y+(larguraPrateleira/5) + 1, paint);
                canvas.drawLine(x, y -1, x-(larguraPrateleira/5), y+(larguraPrateleira/5) - 1, paint);
                canvas.drawLine(x, y, x+(larguraPrateleira/5), y+(larguraPrateleira/5), paint);
                canvas.drawLine(x, y +1, x+(larguraPrateleira/5), y+(larguraPrateleira/5) + 1, paint);
                canvas.drawLine(x, y -1, x+(larguraPrateleira/5), y+(larguraPrateleira/5) - 1, paint);
            }
        }
    }
}
