/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica3.controlerrores_brian.modelo;

import java.util.ArrayList;

/**
 *
 * @author Kelsio
 */
public class GestorFacturas {
    private ArrayList<Factura> facturas;
    
    public GestorFacturas(){
        facturas = new ArrayList<>();
    }
    
    private boolean existeAsunto(String asunto){
        boolean existe = false;
        for(Factura f : facturas){
            if(asunto.equalsIgnoreCase(f.getAsunto())){
                existe = true;
                return existe;
            }
        }
        return existe;
    }
    
    public boolean anadirFactura (Factura f){
        if(existeAsunto(f.getAsunto())){
            return false;
        }
        facturas.add(f);
        return true;
    }
    
    public boolean eliminarFactura(int indice){
        if(indice<0 || indice >= facturas.size()){
            return false;
        }
        facturas.remove(indice);
        return true;
    }
    
    public boolean actualizarFactura(int indice, Factura f){
        if(indice<0 || indice >= facturas.size()){
             return false;
        }
        String asuntoAntiguo = facturas.get(indice).getAsunto();
        if(!asuntoAntiguo.equalsIgnoreCase(f.getAsunto())){
                if(existeAsunto(f.getAsunto())){
                    return false;
                }
            } 
        facturas.set(indice, f);
        return true;
    }
    
    public Factura getFactura(int indice){
        if(indice<0 || indice >= facturas.size()){
             return null;
        }
        Factura f = facturas.get(indice);
        return f;
    }
    
    public ArrayList<Factura> listarFacturas (){
        return facturas;
    }
}
