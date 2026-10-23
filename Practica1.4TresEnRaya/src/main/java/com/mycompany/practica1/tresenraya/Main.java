/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practica1.tresenraya;

import com.mycompany.practica1.tresenraya.controlador.ControladorTresEnRaya;
import com.mycompany.practica1.tresenraya.modelo.ModeloTresEnRaya;
import com.mycompany.practica1.tresenraya.vista.VistaTresEnRaya;

/**
 *
 * @author Kelsio
 */
public class Main {

    public static void main(String[] args) {
        VistaTresEnRaya vista = new VistaTresEnRaya();
        ModeloTresEnRaya modelo = new ModeloTresEnRaya();
        new ControladorTresEnRaya(vista,modelo);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
}
