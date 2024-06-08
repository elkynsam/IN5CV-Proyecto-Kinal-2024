/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.elkynsamayoa.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class MenuSucursalesController implements Initializable {
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
    @FXML
    TableView tblSucursales;
    @FXML
    TableColumn colsucursalId, coldireccion, coltelefono;
    @FXML
    Button btnAgregar, btnEditar, btnRegresar, btnEliminar, btnBuscar;
    @FXML
    TextField tfsucursalId;
    private Sucursales Sucursales;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
           stage.menuPrincipalView(); 
        }
        else if(event.getSource() == btnAgregar){
            stage.formularioSucursalesView(1);
        }
        else if(event.getSource() == btnEditar){
            SucursalesDTO.getSucursalesDTO().setSucursales((Sucursales)tblSucursales.getSelectionModel().getSelectedItem());
            stage.formularioSucursalesView(2);
        }
        else if(event.getSource() == btnEliminar){
            if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(405).get() == ButtonType.OK){
                int sucId = ((Sucursales)tblSucursales.getSelectionModel().getSelectedItem()).getSucursalId();
                eliminarSucursales(sucId);
                cargarDatos();
            }    
        }
        else if(event.getSource() == btnBuscar){
            tblSucursales.getItems().clear();
            if(tfsucursalId.getText().equals("")){
                cargarDatos();
            }else{
                tblSucursales.getItems().add(buscarSucursales());
                colsucursalId.setCellValueFactory(new PropertyValueFactory<Sucursales, Integer>("sucursalId"));
                coldireccion.setCellValueFactory(new PropertyValueFactory<Sucursales, String>("direccion"));
                coltelefono.setCellValueFactory(new PropertyValueFactory<Sucursales, String>("telefono"));
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    }  
    
    public void cargarDatos(){
        tblSucursales.setItems(listarSucursales());
        colsucursalId.setCellValueFactory(new PropertyValueFactory<Sucursales, Integer>("sucursalId"));
        coldireccion.setCellValueFactory(new PropertyValueFactory<Sucursales, String>("direccion"));
        coltelefono.setCellValueFactory(new PropertyValueFactory<Sucursales, String>("telefono"));
    }
    
    
    
    public ObservableList<Sucursales> listarSucursales(){
        ArrayList<Sucursales> sucursales = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarSucursales()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int sucursalId = resultSet.getInt("sucursalId");
                String nomSucursales = resultSet.getString("direccion");
                String telSucursales = resultSet.getString("telefono");
                
                sucursales.add(new Sucursales(sucursalId, nomSucursales, telSucursales));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
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
        return FXCollections.observableList(sucursales);
    }
     
        public void eliminarSucursales(int sucId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_EliminarSucursales(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, sucId);
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
        
        public Sucursales buscarSucursales(){
        Sucursales sucursal = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_buscarSucursales(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfsucursalId.getText()));
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                int sucursalId = resultSet.getInt("sucursalId");
                String nomSucursales = resultSet.getString("direccion");
                String telSucursales = resultSet.getString("telefono");
                
                sucursal = (new Sucursales(sucursalId, nomSucursales, telSucursales));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
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
        return sucursal;
    }  
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    
    
}
