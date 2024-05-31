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
import org.elkynsamayoa.dto.CarrosDTO;
import org.elkynsamayoa.model.Carros;
import org.elkynsamayoa.system.Main;
import org.elkynsamayoa.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MenuCarrosController implements Initializable {
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
    TextField tfcarroId;
    @FXML
    TableView tblCarros;
    @FXML
    TableColumn colcarroId,colmarca,colmodelo,colprecio,colclienteId;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
           stage.menuPrincipalView(); 
        }
        else if(event.getSource() == btnAgregar){
            stage.FormularioCarrosView(1);
        }
        else if(event.getSource() == btnEditar){
            CarrosDTO.getCarrosDTO().setCarros((Carros)tblCarros.getSelectionModel().getSelectedItem());
            stage.FormularioCarrosView(2);
        }
        else if(event.getSource() == btnEliminar){
            if(SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(405).get() == ButtonType.OK){
                int carId = ((Carros)tblCarros.getSelectionModel().getSelectedItem()).getCarroId();
               eliminarCarros (carId);
                cargarDatos();
            }    
        }
        else if(event.getSource() == btnBuscar){
            tblCarros.getItems().clear();
            if(tfcarroId.getText().equals("")){
                cargarDatos();
            }else{
                tblCarros.getItems().add(buscarCarros());
                colcarroId.setCellValueFactory(new PropertyValueFactory<Carros, Integer> ("carroId"));
                colmarca.setCellValueFactory(new PropertyValueFactory<Carros, String> ("marca"));
                colmodelo.setCellValueFactory(new PropertyValueFactory<Carros, String> ("modelo"));
                colprecio.setCellValueFactory(new PropertyValueFactory<Carros, String> ("precio"));
                colclienteId.setCellValueFactory(new PropertyValueFactory<Carros, Integer> ("clienteId"));

            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatos();
    }  
    
    public void cargarDatos(){
                tblCarros.setItems(listarCarros());
                colcarroId.setCellValueFactory(new PropertyValueFactory<Carros, Integer> ("carroId"));
                colmarca.setCellValueFactory(new PropertyValueFactory<Carros, String> ("marca"));
                colmodelo.setCellValueFactory(new PropertyValueFactory<Carros, String> ("modelo"));
                colprecio.setCellValueFactory(new PropertyValueFactory<Carros, String> ("precio"));
                colclienteId.setCellValueFactory(new PropertyValueFactory<Carros, Integer> ("clienteId"));

    }
    
    public ObservableList<Carros> listarCarros(){
        ArrayList<Carros> carros = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarCarros";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int carroId = resultSet.getInt("carroId");
                String marca = resultSet.getString("marca");
                String modelo = resultSet.getString("modelo");
                String precio = resultSet.getString("precio");
                int clienteId = resultSet.getInt("clienteId");

                carros.add(new Carros(carroId, marca, modelo, precio,clienteId));
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
        
        return FXCollections.observableList(carros);
    }
    
    public void eliminarCarros(int carId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_EliminarCarros(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,carId);
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
    
    public Carros buscarCarros(){
        Carros carros = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_BuscarCarros(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfcarroId.getText()));
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                int carroId = resultSet.getInt("carroId");
                String marca = resultSet.getString("marca");
                String modelo = resultSet.getString("modelo");
                String precio = resultSet.getString("precio");
                int clienteId = resultSet.getInt("clienteId");

                
                carros = (new Carros(carroId,marca,modelo,precio,clienteId));
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
        return carros;
    }
    
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }    
    
}
