package org.example;

import java.util.List;

public class Cliente {
    private String nit;
    private String nombre;
    private String direccion;
    private String telefono;

    private List<Pedido> pedidos;


    public void realizarPedido() {
        System.out.println("Realizando pedido");
        System.out.println("/************************************/");
        System.out.println("Cliente: " + nombre);
        System.out.println("Nit: " + nit);
        System.out.println("Telefono: " + telefono);
        System.out.println("Direccion: " + direccion);
        

        for (Pedido pedido : pedidos) {
            System.out.println("----------------------------------");
            System.out.println("Id Pedido:" + pedido.getIdPedido());
            System.out.println("Estado: " + pedido.getEstado());
            System.out.println("Fecha de la venta: " + pedido.getFecha());
            List<Producto> productos = pedido.getProductos();
            if (productos != null) {
                for (Producto producto : productos) {
                    System.out.println("Producto: " + producto.getNombre());
                    System.out.println("Cantidad: " + producto.getCantidad());
                    System.out.println("Id del producto: " + producto.getIdProducto());

                    System.out.println();
                }
            }
            System.out.println();
        }
        System.out.println("/************************************/");
        System.out.println("Pedido realizado");
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
