package TPControlador;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDeDatos {

    private Connection conexion = null;
    private String user = "root";
    private String password = "mysql";
    private String puerto = "3306";
    private String catalogo = "restdb";
    private String host = "localhost";

    public Connection estableceConexion() {
        if (conexion != null) {
            return conexion;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String urlConexion = "jdbc:mysql://" + host + ":" + puerto + "/" + catalogo;
            conexion = DriverManager.getConnection(urlConexion, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexion;
    }

    public void cierraConexion() {
        try {
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
