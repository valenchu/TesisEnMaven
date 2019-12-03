package coneccion;

import static java.lang.Class.forName;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    /**
     * Parametros de conexion
     */
    static String bd = "tesis2";
    static String login = "root";
    static String password = "1234";
    static String url = "jdbc:mysql://localhost:3306/" + bd + "?useTimezone=true&serverTimezone=UTC";

    static Connection connection = null;

    /**
     * Constructor de DbConnection
     */
    public ConexionDB() {
        try {
            //obtenemos el driver de para mysql
            forName("com.mysql.cj.jdbc.Driver");

            //Intentamos la conexi�n con los par�metros de conexion
            connection = DriverManager.getConnection(url, login, password);

            // Si la conexion se realiza con �xito avisa
            if (connection != null) {
                out.println("Conexión a base de datos " + bd + " OK\n");
            } else if (connection == null) {
                out.println("No se pudo realizar la conexion a la BD");
            }
        } catch (SQLException e) {
            out.println(e);
        } catch (ClassNotFoundException e) {
            out.println(e);
        } catch (Exception e) {
            out.println(e);
        }
    }

    /**
     * Permite retornar la conexi�n
     *
     * @return
     */
    //Conecta BD
    public Connection getConnection() {
        return connection;
    }

//Desconecta BD
    public void desconectar() {
        try {
            connection.close();
        } catch (SQLException ex) {
            out.println("Error al cerrar la conexion de tipo = " + ex);
        }
    }

}
