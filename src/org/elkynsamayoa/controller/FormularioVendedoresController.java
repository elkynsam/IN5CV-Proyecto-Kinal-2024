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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.elkynsamayoa.dao.Conexion;
import org.elkynsamayoa.dto.VendedoresDTO;
import org.elkynsamayoa.model.Vendedores;
import org.elkynsamayoa.system.Main;
import org.elkynsamayoa.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class FormularioVendedoresController implements Initializable {
    
    private Main stage;    
    private int op;
    private static Connection conexion;
    private static PreparedStatement statement;
    @FXML
    ComboBox cmbVendedores, cmbSucursales;
    @FXML
    TextField tfvendedorId, tfnombreVendedor, tfapellidoVendedor, tftelefono, tfcorreo, tfsucursalId;
    @FXML
    Button btnGuardar, btnCancelar;
    @FXML
    TableView tblVendedores;

 
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(VendedoresDTO.getVendedoresDTO().getVendedores() != null){
            cargarDatos(VendedoresDTO.getVendedoresDTO().getVendedores());
        }
    }    
    public void cargarDatos(Vendedores vendedores){
        tfvendedorId.setText(Integer.toString(vendedores.getvendedorId()));
        tfnombreVendedor.setText(vendedores.getapellidoVendedor());
        tfapellidoVendedor.setText(vendedores.getapellidoVendedor());
        tftelefono.setText(vendedores.gettelefono());
        tfcorreo.setText(vendedores.getcorreo());
        tfsucursalId.setText(vendedores.getsucursalId());

 
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
    public void agregarVendedores(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_AgregarVendedores(?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfnombreVendedor.getText());
            statement.setString(2, tfapellidoVendedor.getText());
            statement.setString(3, tftelefono.getText());
            statement.setString(4, tfcorreo.getText());
            statement.setString(5, tfsucursalId.getText());

 
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(conexion != null){
                    conexion.close();
                }else if(statement != null){
                    statement.close();
                }
            }catch(SQLException e){
            }
        }
    }
    public void editarEmpleado(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_EditarVendedores(?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfvendedorId.getText());
            statement.setString(2, tfnombreVendedor.getText());
            statement.setString(3, tfapellidoVendedor.getText());
            statement.setString(4, tftelefono.getText());
            statement.setString(5, tfcorreo.getText());
            statement.setString(6, tfsucursalId.getText());
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
            }
        }
    }

    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnCancelar){
            stage.menuVendedoresView();
            VendedoresDTO.getVendedoresDTO().setVendedores(null);
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                if(!tfnombreVendedor.getText().equals("") && !tfapellidoVendedor.getText().equals("")&& !tftelefono.getText().equals("")&& !tfcorreo.getText().equals("")&& !tfsucursalId.getText().equals("")){
                    agregarVendedores();
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(401);
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfnombreVendedor.requestFocus();
                    return;
                }
                stage.menuVendedoresView();
            }else if(op == 2){
                
                if(!tfnombreVendedor.getText().equals("") && !tfapellidoVendedor.getText().equals("")&& !tftelefono.getText().equals("")&& !tfcorreo.getText().equals("")&& !tfsucursalId.getText().equals("")){
                     if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(406).get() == ButtonType.OK){
                        //editarVendedores();
                        VendedoresDTO.getVendedoresDTO().setVendedores(null);
                        stage.menuClientesView();
                     }
                    
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfnombreVendedor.requestFocus();
                    return;
                }
            }
        }
    }
        
}
