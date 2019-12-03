/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FNC;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Valentin
 */
//Clase que se encarga de generar la mayoría de las consultas de paginado
public class ConsultasDePaginado {

    private String SQLLL = "";
//Carga sql para la parte de busqueda de stock

    public String getsqlBuscarStok(String filtro, String valor) {

        if (filtro.equals("Descripción")) {
            SQLLL = "SELECT DISTINCT count(*) AS total "
                    + " FROM productos WHERE (Descripcio LIKE  '" + valor + "%')ORDER BY IDProducto ASC";

        } else if (filtro.equals("IDProducto")) {
            SQLLL = "SELECT DISTINCT count(*) AS total "
                    + " FROM productos WHERE (IDProducto LIKE  '" + valor + "%')ORDER BY IDProducto ASC";

        }

        return SQLLL;
    }

    //carga sql para el ingreso instantaneo de stock
    public String getAncestorAddedTablaStock() {
        SQLLL = "SELECT count(*) AS total "
                + " FROM productos ORDER BY IDProducto ASC ";
        return SQLLL;
    }

    //Consulta sql de las fechas de movimiento venta
    public String getMovimientoConsulta(Date desdee, Date hastaa) {
        // tipo de formato que tendra la fecha
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        //Paso fechas a string para hacer consulta
        String desde = f.format(desdee);
        String hasta = f.format(hastaa);
        SQLLL = "SELECT DISTINCT count(*) AS total "
                + "FROM venta INNER JOIN  detallevta ON venta.idVenta = detallevta.idVenta "
                + " WHERE (detallevta.Fecha BETWEEN '" + desde + "' AND '" + hasta + "') GROUP BY detallevta.idventa_idproducto  ";
        return SQLLL;
    }

    public String movimientoTbl() {
        SQLLL = "SELECT count(*) AS total FROM detallevta";

        return SQLLL;
    }
    //sql de busqueda por usuario en la pestaña movimiento de ventas !
    public String buscarPorUsuarioMovimiento(String user){
       SQLLL = "SELECT DISTINCT count(*) AS total "
                    + "FROM venta INNER JOIN  detallevta ON venta.idVenta = detallevta.idVenta "
                    + "WHERE (venta.Usuario LIKE '" + user + "%') GROUP BY  venta.idVenta AND detallevta.idventa_idproducto ";
       return SQLLL;
    }
}
