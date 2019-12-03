/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CargaDescarga;

/**
 *
 * @author Valentin
 */
public class Proveedores {

    private String nomb, ape, email, dire;
    private int DNI, ID;
    private long tel;

    public Proveedores(String nomb, String ape, String email, int DNI, int ID, long tel, String dire) {
        this.nomb = nomb;
        this.ape = ape;
        this.email = email;
        this.DNI = DNI;
        this.ID = ID;
        this.tel = tel;
        this.dire = dire;
    }

    public String getDire() {
        return dire;
    }

    public void setDire(String dire) {
        this.dire = dire;
    }

    public String getNomb() {
        return nomb;
    }

    public void setNomb(String nomb) {
        this.nomb = nomb;
    }

    public String getApe() {
        return ape;
    }

    public void setApe(String ape) {
        this.ape = ape;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        String devolver = "<html><body>Apellido: <strong style='font-weight: normal'>" + ape + "</strong> | DNI: "
                + "<strong style='font-weight: normal'>" + DNI + "</strong></body></html>";
        return devolver;
    }

}
