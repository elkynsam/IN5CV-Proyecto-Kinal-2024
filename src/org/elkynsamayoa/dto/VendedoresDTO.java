/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.elkynsamayoa.dto;

import org.elkynsamayoa.model.Vendedores;

/**
 *
 * @author informatica
 */
public class VendedoresDTO {
    private static VendedoresDTO instance;
    private Vendedores vendedores;
    
    private VendedoresDTO(){
        
    }
    
    public static VendedoresDTO getVendedoresDTO(){
        if(instance == null){
            instance = new VendedoresDTO();
        }
        return instance;
    }

    public Vendedores getVendedores() {
        return vendedores;    
    }

    public void setEmpleado(Vendedores vendedores) {
        this.vendedores = vendedores;
    }

    public void setVendedores(Vendedores vendedores) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
