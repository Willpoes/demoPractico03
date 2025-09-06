package com.example.demoPractico03.dto;

public class ProductDTO {
    //SOLO LO IMPORTANTE
    private String nombre;
    private double precio;
    //deberia estar en español a ingles con respecto el producto
    public ProductDTO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
