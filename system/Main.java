/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.elkynsamayoa.system;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import java.io.InputStream;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import org.elkynsamayoa.controller.FormularioCarrosController;
import org.elkynsamayoa.controller.FormularioClienteController;
import org.elkynsamayoa.controller.FormularioSucursalesController;
import org.elkynsamayoa.controller.FormularioVendedoresController;
import org.elkynsamayoa.controller.MenuCarrosController;
import org.elkynsamayoa.controller.MenuClienteController;
import org.elkynsamayoa.controller.MenuFacturasController;
import org.elkynsamayoa.controller.MenuPrincipalController;
import org.elkynsamayoa.controller.MenuSucursalesController;
import org.elkynsamayoa.controller.MenuVendedoresController;
/**
 *
 * @author elkynsamayoa
 */
public class Main extends Application {
    private Stage stage;
    private Scene scene;
    private final String URLVIEW = "/org/elkynsamayoa/view/";
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("Super Kinal App");
        menuPrincipalView();
        stage.show();
    }
    
    public Initializable switchScene(String fxmlName, int width, int height)throws Exception{
        Initializable resultado;
        FXMLLoader loader = new FXMLLoader();
        
        InputStream file = Main.class.getResourceAsStream(URLVIEW + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(URLVIEW + fxmlName));
        
        scene = new Scene((AnchorPane)loader.load(file), width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        
        resultado = (Initializable)loader.getController();
        return resultado;
    }
    
    public void menuPrincipalView(){
        try{
            MenuPrincipalController menuPrincipalView = (MenuPrincipalController)switchScene("MenuPrincipalView.fxml",484,639);
            menuPrincipalView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void formClienteView(int op){
        try{
            FormularioClienteController formClienteView = (FormularioClienteController)switchScene("formClientesView.fxml",380,513);
            formClienteView.setOp(op);
            formClienteView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
        
    public void menuClientesView(){
        try{
           MenuClienteController menuClientesView = (MenuClienteController)switchScene("MenuClienteView.fxml",1200,750); 
           menuClientesView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
     
    public void FormularioCarrosView(int op){
        try{
            FormularioCarrosController formularioCarrosView = (FormularioCarrosController)switchScene("FormularioCarrosView.fxml", 380, 520);
            formularioCarrosView.setOp(op);
            formularioCarrosView.setStage(this);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }   
    
    public void menuCarrosView(){
        try{
            MenuCarrosController MenuCarrosView = (MenuCarrosController)switchScene("MenuCarrosView.fxml",1200,750);
            MenuCarrosView.setStage(this);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    
    public void formularioSucursalesView(int op){
        try{
            FormularioSucursalesController formularioSucursalesView = (FormularioSucursalesController)switchScene("FormularioSucursalesView.fxml", 380, 520);
            formularioSucursalesView.setOp(op);
            formularioSucursalesView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuSucursalesView(){
        try{
            MenuSucursalesController menuSucursalesView = (MenuSucursalesController)switchScene("MenuSucursalesView.fxml",1200,750);
            menuSucursalesView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void formularioVendedoresView(int op){
        try{
            FormularioVendedoresController formularioVendedoresView = (FormularioVendedoresController)switchScene("FormularioVendedoresView.fxml", 580, 720);
            formularioVendedoresView.setOp(op);
            formularioVendedoresView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void menuVendedoresView(){
        try{
            MenuVendedoresController menuVendedoresView = (MenuVendedoresController)switchScene("MenuVendedoresView.fxml",1200,750);
            menuVendedoresView.setStage(this);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    
    public void menuFacturasView(){
        try{
            MenuFacturasController menuFacturasView = (MenuFacturasController)switchScene("MenuFacturasView.fxml",1200,750);
            menuFacturasView.setStage(this);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

   
    public static void main(String[] args) {
        launch(args);
    }

    
    
    

    
}
