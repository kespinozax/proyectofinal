package ExamenFinal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


        
public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/bd_econova_soluciones";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return conexion;
    }// fin conectar()
    
public static boolean login(String user, String pass) {
    String query = "SELECT * FROM users WHERE user_name = ? AND password = ?";
    try (Connection con = ConexionBD.conectar(); PreparedStatement pst = con.prepareStatement(query)) {
        pst.setString(1, user);
        pst.setString(2, pass);
        try (ResultSet rs = pst.executeQuery()) {
            return rs.next(); // Si hay resultados, el login es exitoso
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false; // Si no se pudo conectar o no hay resultados, el login falla
}

    
    public static void insertarProducto(String nombre, double precio, int cantidad, Date fecha) {
    String query = "INSERT INTO producto ( nombreProducto, precioUnitario, cantidadProducto, fechaVencimiento) VALUES (?, ?, ?, ?)";
    try (Connection con = ConexionBD.conectar(); PreparedStatement pst = con.prepareStatement(query)) {
        pst.setString(1, nombre);
        pst.setDouble(2, precio);
        pst.setInt(3, cantidad);
        pst.setDate(4, fecha);
        pst.executeUpdate();
        System.out.println("Producto insertado correctamente");
    } catch (SQLException e) {
    }
}// fin insertarProducto()
 public static ResultSet listarProductos() {
    String query = "SELECT * FROM producto";
    try {
        Connection con = ConexionBD.conectar();
        Statement st = con.createStatement();
        return st.executeQuery(query);  // Retorna el ResultSet con los datos
    } catch (SQLException e) {
        System.out.println("Error al listar productos: " + e.getMessage());
        return null; // Retorna null si ocurre una excepción
    }
}
 
 public static ResultSet filtrarProductos(String columnaSeleccionada, String filtro) {
    // Define la columna de la tabla en función del campo seleccionado en el ComboBox
    String columna = "";
    switch (columnaSeleccionada) {
        case "Código":
            columna = "codigoProducto";
            break;
        case "Nombre":
            columna = "nombreProducto";
            break;
        case "Precio":
            columna = "precioUnitario";
            break;
        case "Cantidad":
            columna = "cantidadProducto";
            break;
        case "Fecha Vencimiento":
            columna = "fechaVencimiento";
            break;
        default:
            // Si no se seleccionó un valor válido en el ComboBox, se retorna null
            return null;
    }

    // Crear la consulta SQL con el filtro
    String query = "SELECT * FROM producto WHERE " + columna + " LIKE ?";

    try {
        Connection con = ConexionBD.conectar();  // Obtener la conexión a la base de datos
        PreparedStatement pst = con.prepareStatement(query);  // Preparar la consulta SQL
        pst.setString(1, "%" + filtro + "%");  // Establecer el valor del filtro en la consulta

        // Retornar el ResultSet con los resultados de la consulta
        return pst.executeQuery();
    } catch (SQLException e) {
        System.out.println("Error al listar productos: " + e.getMessage());
        return null;  // Si ocurre un error, se retorna null
    }
}

    
    public static void actualizarProducto(String codigoProducto, String nombreProducto, double precioProducto, int cantidadProducto, Date fechaVencimiento) {
    String query = "UPDATE producto SET nombreProducto = ?, precioUnitario = ?, cantidadProducto = ?, fechaVencimiento = ? WHERE codigoProducto = ?";
    try (Connection con = conectar(); PreparedStatement pst = con.prepareStatement(query)) {
        pst.setString(1, nombreProducto);
        pst.setDouble(2, precioProducto);
        pst.setInt(3, cantidadProducto);
        pst.setDate(4,  fechaVencimiento);
        pst.setString(5, codigoProducto);
        pst.executeUpdate();
        System.out.println("Producto actualizado correctamente");
    } catch (SQLException e) {
    }
}

    
public static void eliminarProducto(String codigoProducto) {
    String query = "DELETE FROM producto WHERE codigoProducto = ?";
    try (Connection con = ConexionBD.conectar(); PreparedStatement pst = con.prepareStatement(query)) {
        pst.setString(1, codigoProducto);
        pst.executeUpdate();
        System.out.println("Producto eliminado correctamente");
    } catch (SQLException e) {
        //e.printStackTrace();
    }
}// fin eliminarProducto
    public static void main(String[] args) {
        //conectar();
        //insertarProducto("VRG100", "Virogrip", 50, 150, "2024-10-31");
        listarProductos();
        //actualizarProducto("VRG100","Virogrip funcional",150.99);
        //eliminarProducto("VRG100");
        //listarProductos();     
    }

    static Object getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}// fin main

