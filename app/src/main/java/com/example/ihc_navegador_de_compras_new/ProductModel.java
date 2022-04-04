package com.example.ihc_navegador_de_compras_new;

public class ProductModel {
    private Boolean selected;
    private String name;

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

    ProductModel(String name) {
        setName(name);
        setSelected(false);
    }
}
