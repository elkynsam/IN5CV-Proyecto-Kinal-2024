/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.elkynsamayoa.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import org.elkynsamayoa.system.Main;

/**
 *
 * @author elkynsamayoa
 */
public class MenuPrincipalController implements Initializable{
    private Main stage;

    @FXML
    MenuItem btnMenuClientes, btnVendedores, btnSucursales,btnCarros, btnFacturas;
    
    @Override
        public void initialize(URL location, ResourceBundle resources){
            
        }
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    @FXML
     public void handleButtonAction(ActionEvent event){
        if(event.getSource()== btnMenuClientes){
        stage.menuClientesView();
        }else if(event.getSource() == btnVendedores){
            stage.menuVendedoresView();
        }else if(event.getSource() == btnSucursales){
            stage.menuSucursalesView();
        }else if(event.getSource() == btnCarros){
            stage.menuCarrosView();
        }else if(event.getSource() == btnFacturas){
            stage.menuFacturasView();
        }
    }
    
}
