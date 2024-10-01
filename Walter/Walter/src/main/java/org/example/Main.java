package org.example;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        RepositorioProducto.cargarProductos();
        RepositorioCliente.cargarClientes();
        Scanner scanner = new Scanner(System.in);
        Integer opcion = 0;
        do {
            System.out.println("1. Realizar una compra");
            System.out.println("2. Verificar Stock de Productos");
            System.out.println("3. Ver Clientes y Stock de Compras");
            System.out.println();
            System.out.println("- Presione 99 para salir: ");


            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    realizarCompra(scanner);
                    break;
                case 2:
                    RepositorioProducto.getProductos().forEach(producto -> {
                        System.out.println("Id: " + producto.getIdProducto() + " Nombre: " + producto.getNombre() + " Estado: " + producto.getEstado());
                    });
                    System.out.println();
                    break;
                case 3:
                    RepositorioCliente.getClientes().forEach(cliente -> {
                        System.out.println("Cliente: " + cliente.getNombre());
                        System.out.println("Nit: " + cliente.getNit());
                        System.out.println("Direccion: " + cliente.getDireccion());
                        System.out.println("Telefono: " + cliente.getTelefono());
                        System.out.println("Pedidos: ");
                        cliente.getPedidos().forEach(pedido -> {
                            System.out.println("    Id Pedido:" + pedido.getIdPedido());
                            System.out.println("    Estado: " + pedido.getEstado());
                            System.out.println("    Fecha de la venta: " + pedido.getFecha());
                            List<Producto> productos = pedido.getProductos();
                            if (productos != null) {
                                for (Producto producto : productos) {
                                    System.out.println("        Producto: " + producto.getNombre());
                                    System.out.println("        Cantidad: " + producto.getCantidad());
                                    System.out.println("        Id del producto: " + producto.getIdProducto());
                                    System.out.println();
                                }
                            }
                            System.out.println();
                        });
                    });
                    break;
            }
        } while (opcion != 99);
        scanner.close();
    }

    private static void realizarCompra(Scanner scanner) throws IOException {
        Cliente cliente = new Cliente();
        System.out.println("Ingrese el NIT del consumidor: ");
        cliente.setNit(scanner.next());
        System.out.println("Ingrese el Nombre del consumidor: ");
        cliente.setNombre(scanner.next());
        System.out.println("Ingrese el Telefono del consumidor: ");
        cliente.setTelefono(scanner.next());
        System.out.println("Ingrese la Direccion del consumidor: ");
        cliente.setDireccion(scanner.next());
        

        Pedido pedido = new Pedido();
        pedido.setIdPedido((int) (Math.random() * 100));
        pedido.setFecha(new Date());
        pedido.setNit(cliente.getNit());
        pedido.modificarEstado("Despachado");

        while (true) {
            RepositorioProducto.getProductos().forEach(producto -> {
                System.out.println("Id: " + producto.getIdProducto() + " Nombre: " + producto.getNombre() + " Estado: " + producto.getEstado());
            });
            scanner.nextLine();

            System.out.println("Ingrese el Numero de ID que necesita: ");
            Integer idProducto = scanner.nextInt();
            System.out.println("Ingrese la Cantidad que desea comprar: ");
            Integer cantidad = scanner.nextInt();

            Producto producto = RepositorioProducto.despacharProducto(idProducto, cantidad);
            pedido.agregarProducto(producto);

            System.out.println("Necesita agregar mas productos (si/no): ");
            String respuesta = scanner.next();
            if ("no".equalsIgnoreCase(respuesta)) {
                break;
            }
        }
        cliente.setPedidos(List.of(pedido));
        cliente.realizarPedido();
        RepositorioCliente.agregarCliente(cliente);
        RepositorioCliente.guardarClientes();
        RepositorioProducto.guardarProductos();
    }


}