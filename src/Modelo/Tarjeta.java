
package Modelo;

import com.itextpdf.text.pdf.PdfPCell;

public class Tarjeta {
    
    private int idTarjeta;
    private String color;
    private int Partido_idPartido;

    public Tarjeta() {
    }

    public Tarjeta(int idTarjeta, String color, int Partido_idPartido) {
        this.idTarjeta = idTarjeta;
        this.color = color;
        this.Partido_idPartido = Partido_idPartido;
    }

    public Tarjeta(String color, int Partido_idPartido) {
        this.color = color;
        this.Partido_idPartido = Partido_idPartido;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPartido_idPartido() {
        return Partido_idPartido;
    }

    public void setPartido_idPartido(int Partido_idPartido) {
        this.Partido_idPartido = Partido_idPartido;
    }

    @Override
    public String toString() {
        return "Tarjeta{" + "idTarjeta=" + idTarjeta + ", color=" + color + ", Partido_idPartido=" + Partido_idPartido + '}';
    }

    public Object getJugador() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getEquipo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public PdfPCell getTipo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public PdfPCell getMotivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    

}
