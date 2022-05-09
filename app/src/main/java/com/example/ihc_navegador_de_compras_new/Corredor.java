package com.example.ihc_navegador_de_compras_new;

import java.util.ArrayList;

public class Corredor {
    private boolean prateleira;
    private int numero;
    private int x;
    private int y;
    private ArrayList<Corredor> vizinhos = new ArrayList<>();
    private boolean caminho;

    public boolean isPrateleira() {
        return prateleira;
    }

    public void setPrateleira(boolean prateleira) {
        this.prateleira = prateleira;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ArrayList<Corredor> getVizinhos() {
        return vizinhos;
    }

    public void setVizinhos(ArrayList<Corredor> vizinhos) {
        this.vizinhos = vizinhos;
    }

    public boolean isCaminho() {
        return caminho;
    }

    public void setCaminho(boolean caminho) {
        this.caminho = caminho;
    }

    public Corredor(boolean prateleira, int numero, int x, int y, ArrayList<Corredor> vizinhos) {
        setPrateleira(prateleira);
        setNumero(numero);
        setX(x);
        setY(y);
        setVizinhos(vizinhos);
        setCaminho(false);
    }
}
