/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1.tresenraya.controlador;

import com.mycompany.practica1.tresenraya.modelo.ModeloTresEnRaya;
import com.mycompany.practica1.tresenraya.vista.VistaTresEnRaya;
import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author Kelsio
 */
public class ControladorTresEnRaya {
    private final VistaTresEnRaya vista;
    private final ModeloTresEnRaya modelo;
    
    public ControladorTresEnRaya (VistaTresEnRaya vista, ModeloTresEnRaya modelo){
        this.vista = vista;
        this.modelo = modelo;
        iniciarEventos();
        this.vista.actualizarTextoTurno("Turno: Azul");
    }
    
    public void iniciarEventos(){
        vista.addCasillasListener(e -> {
            JButton btn = (JButton) e.getSource();
            int fila = (int) btn.getClientProperty("fila");
            int columna = (int) btn.getClientProperty("columna");
            procesarJugada(fila, columna);
        });
    }
    
    public void procesarJugada(int fila, int columna){
        int turno = modelo.getTurnoActual();
        modelo.hacerJugada(fila, columna);
        if (turno == 1){
            vista.pintarCasilla(fila, columna, Color.BLUE);
        }else{
            vista.pintarCasilla(fila, columna, Color.RED);
        }
        int ganador = modelo.comprobarGanador();
        if(ganador == 1){
            vista.mostrarMensaje("¡Jugador Azul gana la partida!");
            reiniciarJuego();
            return;
        } else if (ganador == 2){
            vista.mostrarMensaje("¡Jugador Rojo gana la partida!");
            reiniciarJuego();
            return;
        }
        if(modelo.esEmpate()){
            vista.mostrarMensaje("¡Tenemos Empate!");
            reiniciarJuego();
            return;
        }
        modelo.cambiarTurno();
        if(modelo.getTurnoActual()==1){
            vista.actualizarTextoTurno("Turno: Azul");
        }else{
            vista.actualizarTextoTurno("Turno: Rojo");
        }
    }
    
    private void reiniciarJuego(){
        modelo.reiniciar();
        vista.reiniciarTablero();
        vista.actualizarTextoTurno("Turno: Azul");
    }
    
}
