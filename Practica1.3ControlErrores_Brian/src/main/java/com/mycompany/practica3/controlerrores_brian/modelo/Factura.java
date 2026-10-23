/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica3.controlerrores_brian.modelo;

import java.time.LocalDate;

/**
 *
 * @author Kelsio
 */
public class Factura {
    private String asunto;
    private LocalDate fecha;
    private double cantidad;
    private String tipo;
    
    public Factura(String asunto, LocalDate fecha, double cantidad, String tipo){
        this.asunto = asunto;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.tipo = tipo;
    }
    @Override
    public String toString(){
        return(asunto+":"+tipo+":"+cantidad+":"+fecha);
    }

    //Getters & Setters
    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
