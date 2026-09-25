package entities;
public class Mesa {

    private int numMesa;
    private boolean disponible;
    private Mesero mesero;

   
    public Mesa(int numMesa) {
        this.numMesa = numMesa;
        this.disponible = true;
        this.mesero = null;
    }
    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    
    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

  
    public Mesero getMesero() {
        return mesero;
    }

    public void setMesero(Mesero mesero) {
        this.mesero = mesero;
    }
}
