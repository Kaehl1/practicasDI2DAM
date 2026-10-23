/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practica3.controlerrores_brian.controlador;

import com.mycompany.practica3.controlerrores_brian.modelo.Factura;
import com.mycompany.practica3.controlerrores_brian.modelo.GestorFacturas;
import com.mycompany.practica3.controlerrores_brian.vista.VistaFacturas;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Kelsio
 */
public class ControladorFacturas {
    private VistaFacturas vista;
    private GestorFacturas gestor;
    private DefaultListModel<String> modeloLista;
    
    public ControladorFacturas(VistaFacturas vista, GestorFacturas gestor){
        this.vista = vista;
        this.gestor = gestor;
        modeloLista = new DefaultListModel<>();
        this.vista.getjListFacturas().setModel(this.modeloLista);
        iniciarEventos();
        limpiarFormulario();
    }
    
    private void refrescarLista(){
        modeloLista.clear();
        ArrayList<Factura> lista = gestor.listarFacturas();
        for(Factura f : lista){
            modeloLista.addElement(f.toString());
        }
    }
    
    private void limpiarFormulario(){
        vista.getjTextFieldAsunto().setText("");
        vista.getjTextFieldCantidad().setText("");
        vista.getjComboBoxTipo().setSelectedIndex(0);
        vista.getjSpinnerDia().setValue(1);
        vista.getjSpinnerMes().setValue(1);
        vista.getjSpinnerAnno().setValue(2026);
        vista.getjListFacturas().clearSelection();
        vista.getjLabelError().setText("");
    }
    
    private Factura validarCampos(){
        String asunto = vista.getjTextFieldAsunto().getText().trim();
        if(asunto.length()<1||asunto.length()>10){
            vista.getjLabelError().setText("El asunto debe tener entre 1 y 10 caractéres.");
            return null;
        }
        int dia = (int) vista.getjSpinnerDia().getValue();
        int mes = (int) vista.getjSpinnerMes().getValue();
        int anno = (int) vista.getjSpinnerAnno().getValue();
        LocalDate fecha;
        try{
            fecha = LocalDate.of(anno, mes, dia);
        }catch(DateTimeException e){
            vista.getjLabelError().setText("La fecha no es válida.");
            return null;
        }
        if(fecha.isBefore(LocalDate.of(2000,1,1))){
            vista.getjLabelError().setText("La fecha no puede ser inferior al 1/1/2000.");
            return null;
        }
        double cantidad;
        try{
            cantidad = Double.parseDouble(vista.getjTextFieldCantidad().getText().trim());
            if(cantidad < 0){
                vista.getjLabelError().setText("La cantidad debe ser mayor o igual que 0.");
                return null;
            }
        }catch(NumberFormatException e){
            vista.getjLabelError().setText("La cantidad debe ser un numero decimal válido.");
            return null;
        }
        String tipo = (String) vista.getjComboBoxTipo().getSelectedItem();
        vista.getjLabelError().setText("");
        return new Factura(asunto,fecha,cantidad,tipo);
    }
    
    public void iniciarEventos(){
        vista.getjButtonEliminar().addActionListener(e ->{
            int indice = vista.getjListFacturas().getSelectedIndex();
            if(indice == -1){
                vista.getjLabelError().setText("Debe seleccionar una factura para eliminar.");
            }else{
                gestor.eliminarFactura(indice);
                refrescarLista();
                limpiarFormulario();
            }
        });
        vista.getjButtonEditar().addActionListener(e -> {
            int indice = vista.getjListFacturas().getSelectedIndex();
            if(indice == -1){
                vista.getjLabelError().setText("Debe seleccionar una factura para editar.");
            }else{
                Factura f = gestor.getFactura(indice);
                vista.getjTextFieldAsunto().setText(f.getAsunto());
                vista.getjTextFieldCantidad().setText(String.valueOf(f.getCantidad()));
                vista.getjSpinnerDia().setValue(f.getFecha().getDayOfMonth());
                vista.getjSpinnerMes().setValue(f.getFecha().getMonthValue());
                vista.getjSpinnerAnno().setValue(f.getFecha().getYear());
                vista.getjComboBoxTipo().setSelectedItem(f.getTipo());
                vista.getjLabelError().setText("");
            }
        });
        vista.getjButtonAnadir().addActionListener(e -> {
            Factura nuevaFactura = validarCampos();
            if(nuevaFactura == null){
                return;
            }
            int indice = vista.getjListFacturas().getSelectedIndex();
            if(indice == -1){
                boolean exito = gestor.anadirFactura(nuevaFactura);
                if(!exito){
                    vista.getjLabelError().setText("Ya existe una factura con ese asunto.");
                }else{
                    refrescarLista();
                    limpiarFormulario();
                }
            }else{
                boolean exito = gestor.actualizarFactura(indice, nuevaFactura);
                if(!exito){
                    vista.getjLabelError().setText("El asunto debe ser único.");
                }else{
                    refrescarLista();
                    limpiarFormulario();
                }
            }
        });
    }
}
