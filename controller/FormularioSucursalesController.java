/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.elkynsamayoa.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.elkynsamayoa.dao.Conexion;
import org.elkynsamayoa.dto.SucursalesDTO;
import org.elkynsamayoa.model.Sucursales;
import org.elkynsamayoa.system.Main;
import org.elkynsamayoa.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author elkynsamayoa
 */
public class FormularioSucursalesController implements Initializable {
    private Main stage;
    private int op;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    
    @FXML
    TextField tfsucursalId, tfdireccion, tftelefono;

    
    @FXML
    Button btnGuardar, btnCancelar;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(SucursalesDTO.getSucursalesDTO().getSucursales() != null){
            cargarDatos(SucursalesDTO.getSucursalesDTO().getSucursales());
        }
    }
    
    public void agregarSucursales(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarSucursales(?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfdireccion.getText());
            statement.setString(2, tftelefono.getText());
            statement.execute();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void cargarDatos(Sucursales sucursales){
        tfsucursalId.setText(Integer.toString(sucursales.getSucursalId()));
        tfdireccion.setText(sucursales.getDireccion());
        tftelefono.setText(sucursales.getTelefono());
    }
    
    public void editarSucursales(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_EditarSucursales(?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfsucursalId.getText()));
            statement.setString(2, tfdireccion.getText());
            statement.setString(3, tftelefono.getText());
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnCancelar){
            stage.menuSucursalesView();
            SucursalesDTO.getSucursalesDTO().setSucursales(null);
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                if(!tfdireccion.getText().equals("") && !tftelefono.getText().equals("")){
                    
                agregarSucursales();
                SuperKinalAlert.getInstance().mostrarAlertaInfo(401);
                stage.menuSucursalesView(); 
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfdireccion.requestFocus();
                    return;
                }  
            }else if(op == 2){
                if(!tfdireccion.getText().equals("") && !tftelefono.getText().equals("")){
                    if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(406).get() == ButtonType.OK){
                        editarSucursales();
                        SucursalesDTO.getSucursalesDTO().setSucursales(null);
                        stage.menuSucursalesView();   
                    }
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfdireccion.requestFocus();
                    return;
                }
                
            }

        }
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }

    public void setOp(int op) {
        this.op = op;
    }
    
    
        
}
