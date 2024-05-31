/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.elkynsamayoa.model;

/**
 *
 * @author elkynsamayoa
 */
public class Sucursales {
    private int sucursalId;
    private String direccion;
    private String telefono;

    public Sucursales() {
    }
    

    public Sucursales(int sucursalId, String direccion, String telefono) {
        this.sucursalId = sucursalId;
        this.direccion = direccion;
        this.telefono = telefono;
    }
   

    public int getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(int sucursalId) {
        this.sucursalId = sucursalId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    
    @Override
    public String toString() {
        return "Sucursales{" + "sucursalId=" + sucursalId + ", direccion=" + direccion + ", telefono=" + telefono + '}';
    }

    public int getsucursalId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getdireccion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String gettelefono() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
