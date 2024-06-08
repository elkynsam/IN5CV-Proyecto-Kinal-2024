
package org.elkynsamayoa.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.elkynsamayoa.dao.Conexion;
import org.elkynsamayoa.model.Carros;
import org.elkynsamayoa.model.Cliente;
import org.elkynsamayoa.model.Facturas;
import org.elkynsamayoa.model.Vendedores;
import org.elkynsamayoa.system.Main;


public class MenuFacturasController implements Initializable {
    
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultset = null;
    
    @FXML
    Button btnRegresar, btnGuardar, btnVaciar;
    
    @FXML
    TextField tfFacturaId, tfHora, tfTotal, tfFecha;
    
    @FXML
    ComboBox cmbCliente, cmbVendedor, cmbCarro;
    
    @FXML
    TableView tblFacturas;
    
    @FXML
    TableColumn colFacturaId, colCliente, colEmpleado, colFecha, colHora, colTotal, colVendedor, colCarro;
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnGuardar){
            if(tfFacturaId.getText().equals("")){
                agregarFacturas();
                cargarDatos();
        }
        }else if(event.getSource() == btnVaciar){
                vaciarCampos();
        }
    }
    
    
    public void vaciarCampos(){
        tfFacturaId.clear();
        tfTotal.clear();
        cmbCliente.getSelectionModel().clearSelection();
        cmbVendedor.getSelectionModel().clearSelection();
        cmbCarro.getSelectionModel().clearSelection();
    }
    
    public void cargarDatos(){
        tblFacturas.setItems(listarFacturas());
        colFacturaId.setCellValueFactory(new PropertyValueFactory<Facturas, Integer>("facturaId"));
        colFecha.setCellValueFactory(new PropertyValueFactory<Facturas, LocalDate>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<Facturas, LocalTime>("hora"));
        colTotal.setCellValueFactory(new PropertyValueFactory<Facturas, Double>("total"));
        colCliente.setCellValueFactory(new PropertyValueFactory<Facturas, String>("cliente"));
        colVendedor.setCellValueFactory(new PropertyValueFactory<Facturas, String>("vendedor"));
        colCarro.setCellValueFactory(new PropertyValueFactory<Facturas, String>("carro"));
        tblFacturas.getSortOrder().add(colFacturaId);
    }
   
    
    public ObservableList<Facturas> listarFacturas(){
        ArrayList<Facturas> facturas = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarFacturas()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int facturaId = resultset.getInt("facturaId");
                Date fecha = resultset.getDate("fecha");
                Time hora = resultset.getTime("hora");
                Double total = resultset.getDouble("total");
                String cliente = resultset.getString("cliente");
                String vendedor = resultset.getString("vendedor");
                String carro = resultset.getString("carro");
                
                facturas.add(new Facturas(facturaId, fecha.toLocalDate(), hora.toLocalTime(), total, cliente, vendedor, carro));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultset != null){
                    resultset.close();
                }
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                
            }
        }
        
        return FXCollections.observableList(facturas);
    }
    
    public ObservableList<Cliente> listarClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarClientes()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int clienteId = resultset.getInt("clienteId");
                String nombre = resultset.getString("nombreCliente");
                String apellido = resultset.getString("apellidoCliente");
                String telefono = resultset.getString("telefono");
                String direccion = resultset.getString("direccion");
                String nit = resultset.getString("nit");
                
                clientes.add(new Cliente(clienteId, nombre, apellido, telefono, direccion, nit));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally{
            try{
                if(resultset != null){
                    resultset.close();
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
        return FXCollections.observableList(clientes);
    }
    
        
    public void agregarFacturas(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarFacturas(?, ?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setTime(2, Time.valueOf(LocalTime.now()));
            statement.setDouble(3, 0);
            statement.setInt(4, ((Cliente)cmbCliente.getSelectionModel().getSelectedItem()).getClienteId());
            statement.setInt(5, ((Vendedores)cmbVendedor.getSelectionModel().getSelectedItem()).getVendedorId());
            statement.setInt(6, ((Carros)cmbCarro.getSelectionModel().getSelectedItem()).getCarroId());
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
     
    public ObservableList<Vendedores> listarVendedores(){
        ArrayList<Vendedores> vendedores = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarVendedores";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int vendedorId = resultset.getInt("vendedorId");
                String nombreVendedor = resultset.getString("nombreVendedor");
                String apellidoVendedor = resultset.getString("apellidoVendedor");
                String telefono = resultset.getString("telefono");
                String correo = resultset.getString("correo");
                int sucursalId = resultset.getInt("sucursalId");

                vendedores.add(new Vendedores(vendedorId, nombreVendedor, apellidoVendedor, telefono, correo,sucursalId));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            
        }finally{
            try{
                if(resultset != null){
                    resultset.close();
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
 
public ObservableList<Carros> listarCarros(){
        ArrayList<Carros> carros = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarCarros";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int carroId = resultset.getInt("carroId");
                String marca = resultset.getString("marca");
                String modelo = resultset.getString("modelo");
                String precio = resultset.getString("precio");
                int clienteId = resultset.getInt("clienteId");

                carros.add(new Carros(carroId, marca, modelo, precio,clienteId));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            
        }finally{
            try{
                if(resultset != null){
                    resultset.close();
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
    
        
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbCarro.setItems(listarCarros());
        cmbCliente.setItems(listarClientes());
        cmbVendedor.setItems(listarVendedores());
        cargarDatos();
        tfFecha.setText(LocalDate.now().toString());
        tfHora.setText(LocalTime.now().toString());
    }  
    
    
       
    
}
