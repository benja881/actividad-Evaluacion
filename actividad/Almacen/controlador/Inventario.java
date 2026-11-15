package controlador;

import java.util.ArrayList;
import java.util.List;
import modelo.Producto;

public class Inventario {
    private ArrayList<Producto> Productos;

    public Inventario() {
        this.Productos = new ArrayList<>();
    }

    public void registrarProducto(String nombre, int cantidad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
        }

        Producto productoExistente = buscarProductoPorNombre(nombre);
        if (productoExistente != null) {
            productoExistente.setStock(productoExistente.getStock() + cantidad);
        } else {
            Productos.add(new Producto(nombre.trim(), cantidad));
        }
    }

    public void venderProducto(String nombre, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a vender debe ser mayor a 0.");
        }

        Producto producto = buscarProductoPorNombre(nombre);
        if (producto == null) {
            throw new IllegalArgumentException("El producto no existe en el inventario.");
        }

        if (cantidad > producto.getStock()) {
            throw new IllegalArgumentException("Stock insuficiente. No se puede vender más de lo disponible.");
        }

        producto.setStock(producto.getStock() - cantidad);
    }

    public List<Producto> listarInventario() {
        return Productos;
    }

    public List<Producto> obtenerStockCritico() {
        ArrayList<Producto> criticos = new ArrayList<>();
        for (Producto p : Productos) {
            if (p.getStock() <= 5) {
                criticos.add(p);
            }
        }
        return criticos;
    }

    private Producto buscarProductoPorNombre(String nombre) {
        String nombreBuscado = nombre == null ? "" : nombre.trim();
        for (Producto p : Productos) {
            if (p.getNombre().equalsIgnoreCase(nombreBuscado)) {
                return p;
            }
        }
        return null;
    }
}
