package PooldeConexion;

import static java.lang.Class.forName;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import javax.sql.DataSource;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDataSource;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.log4j.Logger;
import static org.apache.log4j.Logger.getLogger;

public class PoolManager {

    public static DataSource dataSource;
    private static ObjectPool connectionPool;
    Logger log = getLogger(getClass());

    public PoolManager() {

        dataSource = setupDataSource();
    }

    /**
     * Inicializa el Pool de objetos con las conexiones
     *
     * @return Datasource
     */
    private DataSource setupDataSource() {

        try {
            forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        // Creamos un ObjectPool que será el actual pool de conexiones
        // e Instanciamos un ObjectPool que permitirá 5 conexiones como máximo
        int max = 5;
        log.info("Configurando Pool de Conexiones con " + max + " conexiones maximas");
        connectionPool = new GenericObjectPool(null, max);

        // Creamos el ConnectionFactory que el pool usará para crear las
        // conexiones
        String url = "jdbc:mysql://localhost/tesis";
        String user = "root";
        String passw = "1532469571";
        log.info("Conectando a " + url + "/user:" + user + "/password:" + passw);
        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(url, user, passw);

        // Creamos el PoolableConnectionFactory que creará las conexiones e
        // implementará las
        // funciones necesarias para la funcionalidad de Pooling
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
                connectionFactory, connectionPool, null, null, false, true);

        // Crearemos el PoolingDriver y crearemos el dataSource
        PoolingDataSource dataSource = new PoolingDataSource(connectionPool);
        return dataSource;
    }

    /**
     * Obtiene una conexion del pool
     *
     * @return Conenction
     * @throws SQLException Si existe error de conexion con la base de datos
     *
     */
    public synchronized Connection getConnection() throws Exception,
            SQLException {

        Object obj = null;
        try {
            obj = connectionPool.borrowObject();
            return (Connection) obj;
        } catch (Exception exception) {

            if (exception instanceof SQLException) {
                SQLException er = (SQLException) exception;
                log.error("No hay conexion con la BD: "
                        + exception.getMessage());
                throw er;
            }
            if (exception instanceof NoSuchElementException) {
                log.error("No existe disponibilidad en el pool: "
                        + exception.getMessage());
                throw new Exception(exception.getMessage(), exception
                        .getCause());
            }
            if (exception instanceof IllegalStateException) {
                log.error("El objeto del pool esta cerrado: "
                        + exception.getMessage());
                throw new Exception(exception.getMessage(), exception
                        .getCause());
            }

            log.error("No se ha podido crear el objeto: "
                    + exception.getMessage());
            throw new Exception(exception.getMessage(), exception
                    .getCause());

        }

    }

    /**
     * Metodo que prueba la conexion a la bd
     *
     * @return True si se puede conectar False si no
     */
    public synchronized boolean testPool() {

        log.debug(" ManagePool :: testPool  Se probara el Pool");
        PreparedStatement stm = null;
        String sql = "Select * from dual";
        try {
            log.debug(" PoolManager :: testPool  Sacando una conexion del pool");
            Connection obj = (Connection) connectionPool.borrowObject();

            log.debug(" PoolManager :: testPool  probando conexion SQL:" + sql);
            stm = obj.prepareStatement(sql);
            if (stm.execute()) {
                log.debug(" PoolManager :: testPool  Retornando la conexion al pool");
                connectionPool.returnObject(obj);
                log.debug(" PoolManager :: testPool  Conexion probada");
                return true;
            } else {
                log.debug(" PoolManager :: testPool  Conexion fallida");
                return false;
            }
        } catch (Exception ex) {
            log.error("Error probando el Pool");
            return false;
        } finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (Exception ex) {
                log.error("Error probando el Pool");
                return false;
            }
        }

    }

    /**
     * Obtiene el numero de conexiones activas
     *
     * @return Conexiones Activas
     */
    public synchronized int getBorrowedConnections() {
        return connectionPool.getNumActive();
    }

    /**
     * Retorna el Objeto al Pool de conexiones
     *
     * @param obj conexion a devolver al pool
     */
    public synchronized void returnConnection(Connection obj)
            throws Exception {

        try {
            connectionPool.returnObject(obj);
        } catch (Exception exception) {
            log.error("Ha ocurrido un Error al devolver la conexion al Pool: "
                    + exception.getMessage());
            throw new Exception(exception.getMessage(), exception
                    .getCause());
        }
    }

    public void invalidateConnection(Connection obj) throws Exception {

        try {
            connectionPool.invalidateObject(obj);
        } catch (Exception exception) {
            log.error("Ha ocurrido un Error al Invalidar la conexion: " + exception.getMessage());
            throw new Exception(exception.getMessage(), exception.getCause());
        }
    }

}
