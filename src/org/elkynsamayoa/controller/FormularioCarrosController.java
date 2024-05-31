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
import org.elkynsamayoa.dto.CarrosDTO;
import org.elkynsamayoa.dto.VendedoresDTO;
import org.elkynsamayoa.model.Carros;
import org.elkynsamayoa.model.Vendedores;
import org.elkynsamayoa.system.Main;
import org.elkynsamayoa.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class FormularioCarrosController implements Initializable {
    
    private Main stage;    
    private int op;
    private static Connection conexion;
    private static PreparedStatement statement;
    @FXML
    ComboBox cmbCarros, cmbClientes;
    @FXML
    TextField tfcarroId, tfmarca, tfmodelo, tfprecio, tfclienteId;
    @FXML
    Button btnGuardar, btnCancelar;
    @FXML
    TableView tblCarros;

 
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(CarrosDTO.getCarrosDTO().getCarros() != null){
            cargarDatos(CarrosDTO.getCarrosDTO().getCarros());
        }
    }    
    public void cargarDatos(Carros carros){
        tfcarroId.setText(Integer.toString(carros.getCarroId()));
        tfmarca.setText(carros.getMarca());
        tfmodelo.setText(carros.getModelo());
        tfprecio.setText(carros.getPrecio());
        tfclienteId.setText(Integer.toString(carros.getClienteId()));
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
    public void agregarCarros(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_AgregarCarros(?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfmarca.getText());
            statement.setString(2, tfmodelo.getText());
            statement.setString(3, tfprecio.getText());
            statement.setString(4, tfclienteId  .getText());

 
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
    public void editarCarros(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_EditarCarros(?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfcarroId.getText());
            statement.setString(2, tfmarca.getText());
            statement.setString(3, tfmodelo.getText());
            statement.setString(4, tfprecio.getText());
            statement.setString(5, tfclienteId.getText());
            statement.execute();
 
        }catch(SQLException e){
            e.printStackTrace();
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
            stage.menuCarrosView();
            CarrosDTO.getCarrosDTO().setCarros(null);
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                if(!tfmarca.getText().equals("") && !tfmodelo.getText().equals("")&& !tfprecio.getText().equals("")&& !tfclienteId.getText().equals("")){
                    agregarCarros();
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(401);
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfmarca.requestFocus();
                    return;
                }
                stage.menuCarrosView();
            }else if(op == 2){
                
                if(!tfmarca.getText().equals("") && !tfmodelo.getText().equals("")&& !tfprecio.getText().equals("")&& !tfclienteId.getText().equals("")){
                     if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(406).get() == ButtonType.OK){
                        editarCarros();
                        CarrosDTO.getCarrosDTO().setCarros(null);
                        stage.menuCarrosView();
                     }
                    
                }else{
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                    tfmarca.requestFocus();
                    return;
                }
            }
        }
    }
    
   

    
    
}
