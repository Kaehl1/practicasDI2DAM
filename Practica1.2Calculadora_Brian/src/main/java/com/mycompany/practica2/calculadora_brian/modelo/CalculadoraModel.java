/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica2.calculadora_brian.modelo;

/**
 *
 * @author Kelsio
 */
public class CalculadoraModel {
    private double operando1 = 0;
    private String operacionPendiente = "";
    private boolean nuevoNumero = true;

    public void setOperando1(double valor) {
        this.operando1 = valor;
    }

    public double getOperando1() {
        return operando1;
    }

    public String getOperacionPendiente() {
        return operacionPendiente;
    }

    public void setOperacionPendiente(String operacion) {
        this.operacionPendiente = operacion;
    }

    public boolean isNuevoNumero() {
        return nuevoNumero;
    }

    public void setNuevoNumero(boolean nuevoNumero) {
        this.nuevoNumero = nuevoNumero;
    }

    public double calcular(double operando2) {
        switch (operacionPendiente) {
            case "+": return operando1 + operando2;
            case "-": return operando1 - operando2;
            case "*": return operando1 * operando2;
            case "/": return operando2 != 0 ? operando1 / operando2 : 0;
            default: return operando2;
        }
    }

    public void limpiar() {
        operando1 = 0;
        operacionPendiente = "";
        nuevoNumero = true;
    }
}
