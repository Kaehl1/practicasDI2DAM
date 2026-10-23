/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practica2.calculadora_brian;

import com.mycompany.practica2.calculadora_brian.controlador.ControladorCalculadora;
import com.mycompany.practica2.calculadora_brian.modelo.CalculadoraModel;
import com.mycompany.practica2.calculadora_brian.vista.VistaCalculadora;

/**
 *
 * @author Kelsio
 */
public class Main {

    public static void main(String[] args) {
        VistaCalculadora vista = new VistaCalculadora();
        CalculadoraModel modelo = new CalculadoraModel();
        new ControladorCalculadora(vista, modelo);
        
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
}
