package test;

import controlador.Inventario;
import java.util.List;
import modelo.Producto;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InventarioTest {
    private Inventario inventario;

    @Before
    public void setUp() {
        inventario = new Inventario();
    }

    @Test
    public void testRegistrarYSumarStock() {
        inventario.registrarProducto("Arroz", 10);
        inventario.registrarProducto("arroz", 5);
        inventario.registrarProducto("Fideos", 8);

        List<Producto> productos = inventario.listarInventario();
        assertEquals(2, productos.size());
        assertEquals("Arroz", productos.get(0).getNombre());
        assertEquals(15, productos.get(0).getStock());
        assertEquals(8, productos.get(1).getStock());
    }

    @Test
    public void testVenderProducto() {
        inventario.registrarProducto("Leche", 10);
        inventario.venderProducto("LECHE", 3);

        assertEquals(7, inventario.listarInventario().get(0).getStock());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEvitarStockNegativo() {
        inventario.registrarProducto("Pan", 2);
        inventario.venderProducto("Pan", 3);
    }

    @Test
    public void testNoPermitirStockNegativoEnProducto() {
        try {
            new Producto("Queso", -1);
            fail("Se esperaba una excepcion al crear un producto con stock negativo");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("stock"));
        }
    }

    @Test
    public void testNoPermitirNombreVacio() {
        try {
            inventario.registrarProducto("   ", 5);
            fail("Se esperaba una excepcion al registrar un producto con nombre vacío");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("nombre"));
        }
    }
}
