package com.example.ihc_navegador_de_compras_new;

public class ProductModel {
    private Boolean selected;
    private String name;
    private int localizacao;

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocalizacao(int localizacao) {
        this.localizacao = localizacao;
    }

    public int getLocalizacao() {
        return localizacao;
    }

    ProductModel(String name) {
        setName(name);
        setSelected(false);
    }

    ProductModel(String name, int localizacao) {
        setName(name);
        setSelected(false);
        setLocalizacao(localizacao);
    }

    ProductModel(String name, boolean selected) {
        setName(name);
        setSelected(selected);
    }
}
