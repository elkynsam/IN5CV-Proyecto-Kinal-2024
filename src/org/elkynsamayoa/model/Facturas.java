package org.elkynsamayoa.model;

import java.time.LocalDate;
import java.time.LocalTime;


public class Facturas {
    private int facturaId;
    private LocalDate fecha;
    private LocalTime hora;
    private double total;
    private int clienteId;
    private String cliente;
    private int vendedorId;
    private String vendedor;
    private int carroId;
    private String carro;

    public Facturas() {
    }

    public Facturas(int facturaId, LocalDate fecha, LocalTime hora, double total, int clienteId, int vendedorId, int carroId) {
        this.facturaId = facturaId;
        this.fecha = fecha;
        this.hora = hora;
        this.total = total;
        this.clienteId = clienteId;
        this.vendedorId = vendedorId;
        this.carroId = carroId;
    }

    public Facturas(int facturaId, LocalDate fecha, LocalTime hora, double total, String cliente, String vendedor, String carro) {
        this.facturaId = facturaId;
        this.fecha = fecha;
        this.hora = hora;
        this.total = total;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.carro = carro;
    }

    
    

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(int vendedorId) {
        this.vendedorId = vendedorId;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public int getCarroId() {
        return carroId;
    }

    public void setCarroId(int carroId) {
        this.carroId = carroId;
    }

    public String getCarro() {
        return carro;
    }

    public void setCarro(String carro) {
        this.carro = carro;
    }

    

    @Override
    public String toString() {
        return "Id:" + facturaId + " Fecha: " + fecha + "  Hora: " + hora + "   Total: " + total + "";
    }
}
