/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.elkynsamayoa.dto;

import org.elkynsamayoa.model.Carros;

/**
 *
 * @author informatica
 */
public class CarrosDTO {
    private static CarrosDTO instance;
    private Carros carros;
    
    private CarrosDTO(){
        
    }
    
    public static CarrosDTO getCarrosDTO(){
        if(instance == null){
            instance = new CarrosDTO();
        }
        return instance;
    }

    public Carros getCarros() {
        return carros;    
    }

    public void setCarros(Carros carros) {
        this.carros = carros;
    }

    
    
}
