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
import org.elkynsamayoa.dto.VendedoresDTO;
import org.elkynsamayoa.model.Vendedores;
import org.elkynsamayoa.system.Main;
import org.elkynsamayoa.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MenuVendedoresController implements Initializable {
   private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    @FXML
    Button btnAgregar;
    @FXML
    Button btnEditar, btnEliminar, btnBuscar;
    @FXML
    Button btnRegresar;
    @FXML
    TextField tfvendedorId;
    @FXML
    TableView tblVendedores;
    @FXML
    TableColumn colvendedorId,colnombreVendedor,colapellidoVendedor,coltelefono,colcorreo,colsucursalId;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
           stage.menuPrincipalView(); 
        }
        else if(event.getSource() == btnAgregar){
            stage.formularioVendedoresView(1);
        }
        else if(event.getSource() == btnEditar){
            VendedoresDTO.getVendedoresDTO().setVendedores((Vendedores)tblVendedores.getSelectionModel().getSelectedItem());
            stage.formularioVendedoresView(2);
        }
        else if(event.getSource() == btnEliminar){
            if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(405).get() == ButtonType.OK){
                int venId = ((Vendedores)tblVendedores.getSelectionModel().getSelectedItem()).getVendedorId();
               eliminarVendedores(venId);
                cargarDatos();
            }    
        }
        else if(event.getSource() == btnBuscar){
            tblVendedores.getItems().clear();
            if(tfvendedorId.getText().equals("")){
                cargarDatos();
            }else{
                tblVendedores.getItems().add(buscarVendedores());
                colvendedorId.setCellValueFactory(new PropertyValueFactory<Vendedores, Integer> ("vendedorId"));
                colnombreVendedor.setCellValueFactory(new PropertyValueFactory<Vendedores, String> ("nombreVendedor"));
                colapellidoVendedor.setCellValueFactory(new PropertyValueFactory<Vendedores, String> ("apellidoVendedor"));
                coltelefono.setCellValueFactory(new PropertyValueFactory<Vendedores, String> ("telefono"));
                colcorreo.setCellValueFactory(new PropertyValueFactory<Vendedores, String> ("correo"));
                colsucursalId.setCellValueFactory(new PropertyValueFactory<Vendedores, Integer> ("sucursalId"));

            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    }  
    
    public void cargarDatos(){
                tblVendedores.setItems(listarVendedores());
                colvendedorId.setCellValueFactory(new PropertyValueFactory<Vendedores, Integer> ("vendedorId"));
                colnombreVendedor.setCellValueFactory(new PropertyValueFactory<Vendedores, String> ("nombreVendedor"));
                colapellidoVendedor.setCellValueFactory(new PropertyValueFactory<Vendedores, String> ("apellidoVendedor"));
                coltelefono.setCellValueFactory(new PropertyValueFactory<Vendedores, String> ("telefono"));
                colcorreo.setCellValueFactory(new PropertyValueFactory<Vendedores, String> ("correo"));
                colsucursalId.setCellValueFactory(new PropertyValueFactory<Vendedores, String> ("sucursalId"));

    }
    
    public ObservableList<Vendedores> listarVendedores(){
        ArrayList<Vendedores> vendedores = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarVendedores";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int vendedorId = resultSet.getInt("vendedorId");
                String nombreVendedor = resultSet.getString("nombreVendedor");
                String apellidoVendedor = resultSet.getString("apellidoVendedor");
                String telefono = resultSet.getString("telefono");
                String correo = resultSet.getString("correo");
                int sucursalId = resultSet.getInt("sucursalId");

                vendedores.add(new Vendedores(vendedorId, nombreVendedor, apellidoVendedor, telefono, correo,sucursalId));
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
        
        return FXCollections.observableList(vendedores);
    }
    
    public void eliminarVendedores(int venId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_EliminarVendedores(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,venId);
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
    
    public Vendedores buscarVendedores(){
        Vendedores vendedores = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_BuscarVendedores(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfvendedorId.getText()));
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                int vendedorId = resultSet.getInt("vendedorId");
                String nombreVendedor = resultSet.getString("nombreVendedor");
                String apellidoVendedor = resultSet.getString("apellidoVendedor");
                String telefono = resultSet.getString("telefono");
                String correo = resultSet.getString("correo");
                int sucursalId = resultSet.getInt("sucursalId");

                
                vendedores = (new Vendedores(vendedorId,nombreVendedor,apellidoVendedor,telefono,correo,sucursalId));
            }
        }catch(SQLException e){
            e.printStackTrace();
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
        return vendedores;
    }
    
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }

  
    
      
    
}
