/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica3.controlerrores_brian;

import com.mycompany.practica3.controlerrores_brian.controlador.ControladorFacturas;
import com.mycompany.practica3.controlerrores_brian.modelo.GestorFacturas;
import com.mycompany.practica3.controlerrores_brian.vista.VistaFacturas;

/**
 *
 * @author Kelsio
 */
public class Main {
    public static void main(String args[]) {
        VistaFacturas vista = new VistaFacturas();
        GestorFacturas gestor = new GestorFacturas();
        ControladorFacturas controlador = new ControladorFacturas(vista, gestor);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
}
