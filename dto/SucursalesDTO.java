/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.elkynsamayoa.dto;

import org.elkynsamayoa.model.Sucursales;

/**
 *
 * @author elkynsamayoa
 */
public class SucursalesDTO {
private static SucursalesDTO instance;
    private Sucursales sucursales;

    public SucursalesDTO() {
    }
    
    public static SucursalesDTO getSucursalesDTO(){
        if(instance == null){
            instance = new SucursalesDTO();
        }
        return instance;
    }

    public Sucursales getSucursales() {
        return sucursales;
    }

    public void setSucursales(Sucursales sucursales) {
        this.sucursales = sucursales;
    }
    
}
