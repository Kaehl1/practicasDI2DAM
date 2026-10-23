/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica1.tresenraya.modelo;

/**
 *
 * @author Kelsio
 */
public class ModeloTresEnRaya {
    private int[][] tablero; // 0 -> Libre / 1 -> Azul / 2 -> Rojo
    private int turnoActual; //puede ser 1 o 2: 1 Azul / 2 Rojo
    private int movimientos; //va de 1 a 9 para saber cuando hay empate
    
    public ModeloTresEnRaya(){
        tablero = new int[3][3];
        reiniciar();
    }
    
    public void reiniciar(){
        movimientos = 0;
        turnoActual = 1;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                tablero[i][j] = 0;
            }
        }
    }
    
    public boolean hacerJugada(int fila, int columna){
        if(tablero[fila][columna] != 0){
            return false;
        }
        tablero[fila][columna] = turnoActual;
        movimientos++;
        return true;
    }
    
    public void cambiarTurno(){
        if (turnoActual == 1){
            turnoActual = 2;
        }else if (turnoActual == 2){
            turnoActual = 1;
        }
    }
    
    // Devuelve: 0 -> Empate / 1 -> Azul / 2 -> Rojo
    public int comprobarGanador(){
        //Comprobacion diagonales
        if(tablero[0][0]==turnoActual && tablero[1][1]==turnoActual && tablero[2][2]==turnoActual){
            return turnoActual;
        }
        if(tablero[2][0]==turnoActual && tablero[1][1]==turnoActual && tablero[0][2]==turnoActual){
            return turnoActual;
        }
        //Comprobacion horizontal
        for(int i=0;i<3;i++){
            if(tablero[i][0]==turnoActual && tablero[i][1]==turnoActual && tablero[i][2]==turnoActual){
                return turnoActual;
            }
        }
        //Comprobacion vertical
        for(int i = 0; i<3;i++){
            if(tablero[0][i]==turnoActual && tablero[1][i]==turnoActual && tablero[2][i]==turnoActual){
                return turnoActual;
            }
        }
        return 0;
    }
    
    public boolean esEmpate(){
        if(movimientos == 9 && comprobarGanador() == 0){
            return true;
        }
        return false;
    }

    public int getTurnoActual() {
        return turnoActual;
    }
    
}
