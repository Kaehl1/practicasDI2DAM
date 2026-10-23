/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica2.calculadora_brian.controlador;


import com.mycompany.practica2.calculadora_brian.modelo.CalculadoraModel;
import com.mycompany.practica2.calculadora_brian.vista.VistaCalculadora;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;

/**
 *
 * @author Kelsio
 */
public class ControladorCalculadora {
    private final VistaCalculadora vista;
    private final CalculadoraModel modelo;

    public ControladorCalculadora(VistaCalculadora vista, CalculadoraModel modelo) {
        this.vista = vista;
        this.modelo = modelo;
        iniciarEventos();
    }

    private void iniciarEventos() {
        vista.addNumerosListener(e -> {
            JButton btn = (JButton) e.getSource();
            concatenarNumero(btn.getText());
        });

        vista.addDecimalListener(e -> eventoDecimal());
        vista.addOperacionesListener(this::eventosOperaciones);
        vista.addResultadoListener(e -> resultado());
        vista.addLimpiarListener(e -> limpiar());

        vista.addTecladoListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (Character.isDigit(c)) {
                    concatenarNumero(String.valueOf(c));
                } else if (c == '.' || c == ',') {
                    eventoDecimal();
                } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                    procesarOperacion(String.valueOf(c));
                } else if (c == '\n') {
                    resultado();
                } else if (c == 'c' || c == 'C') {
                    limpiar();
                }
            }
        });
    }

    private void concatenarNumero(String num) {
        if (modelo.isNuevoNumero()) {
            vista.setDisplay(num);
            modelo.setNuevoNumero(false);
        } else {
            vista.setDisplay(vista.getDisplay() + num);
        }
    }

    public void eventoDecimal() {
        String actual = vista.getDisplay();
        if (modelo.isNuevoNumero()) {
            vista.setDisplay("0.");
            modelo.setNuevoNumero(false);
        } else if (!actual.contains(".")) {
            vista.setDisplay(actual + ".");
        }
    }

    public void eventosOperaciones(ActionEvent e) {
        JButton b = (JButton) e.getSource();
        procesarOperacion(b.getText());
    }

    private void procesarOperacion(String op) {
        double valorActual = Double.parseDouble(vista.getDisplay());
        if (!modelo.getOperacionPendiente().isEmpty()) {
            double resultado = modelo.calcular(valorActual);
            vista.setDisplay(String.valueOf(resultado));
            modelo.setOperando1(resultado);
        } else {
            modelo.setOperando1(valorActual);
        }
        modelo.setOperacionPendiente(op);
        modelo.setNuevoNumero(true);
    }

    public void resultado() {
        if (!modelo.getOperacionPendiente().isEmpty()) {
            double valorActual = Double.parseDouble(vista.getDisplay());
            double reultado = modelo.calcular(valorActual);
            vista.setDisplay(String.valueOf(reultado));
            modelo.setOperando1(reultado);
            modelo.setOperacionPendiente("");
            modelo.setNuevoNumero(true);
        }
    }

    public void limpiar() {
        modelo.limpiar();
        vista.setDisplay("0");
    }
}
