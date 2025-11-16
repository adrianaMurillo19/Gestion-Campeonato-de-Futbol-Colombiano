package Modelo;


public class Gol {
    
    private int idGol;
    private int minuto;
    private int Partido_idPartido;

    public Gol() {
    }

    public Gol(int idGol, int minuto, int Partido_idPartido) {
        this.idGol = idGol;
        this.minuto = minuto;
        this.Partido_idPartido = Partido_idPartido;
    }

    public Gol(int minuto, int Partido_idPartido) {
        this.minuto = minuto;
        this.Partido_idPartido = Partido_idPartido;
    }

    public int getIdGol() {
        return idGol;
    }

    public void setIdGol(int idGol) {
        this.idGol = idGol;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getPartido_idPartido() {
        return Partido_idPartido;
    }

    public void setPartido_idPartido(int Partido_idPartido) {
        this.Partido_idPartido = Partido_idPartido;
    }

    @Override
    public String toString() {
        return "Gol{" + "idGol=" + idGol + ", minuto=" + minuto + ", Partido_idPartido=" + Partido_idPartido + '}';
    }
    
    
    
}
