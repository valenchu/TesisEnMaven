/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Venta;

import java.util.Calendar;
import static java.util.Calendar.DATE;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.SECOND;
import static java.util.Calendar.YEAR;
import java.util.GregorianCalendar;
import javax.swing.JTextField;

/**
 *
 * @author Valentin
 */
public class FncFecha {

    public void fecha(JTextField D, JTextField M, JTextField A) {
        Calendar c = new GregorianCalendar();

        D.setText(Integer.toString(c.get(DATE)));
        M.setText(Integer.toString(c.get(MONTH) + 1));
        A.setText(Integer.toString(c.get(YEAR)));
    }

    public String fechaTodo() {
        Calendar c = new GregorianCalendar();
        int dia = c.get(DATE);
        int mes = c.get((MONTH) + 1);
        int ano = c.get(YEAR);
        int hora = c.get(HOUR_OF_DAY);
        int min = c.get(MINUTE);
        int seg = c.get(SECOND);
        return dia + "/" + mes + "/" + ano + "    Hora: " + hora + ":" + min + ":" + seg;
    }
}
