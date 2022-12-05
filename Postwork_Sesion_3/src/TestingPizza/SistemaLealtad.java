package TestingPizza;

public class SistemaLealtad {

    private boolean estatusTajeta;
    private int saldoPuntos;
    private boolean estatusCupon = false;


    public boolean getEstatusTarjeta() {
        return this.estatusTajeta;
    }

    public void setEstatusTarjeta(boolean estatusTarjeta) {
        this.estatusTajeta = estatusTarjeta;
    }

    public int getSaldoPuntos() {
        return saldoPuntos;
    }

    public void setSaldoPuntos(int saldoPuntos) {
        this.saldoPuntos = saldoPuntos;
    }

    public boolean getEstatusCupon() {
        return estatusCupon;
    }

    public void setEstatusCupon(boolean estatusCupon) {
        this.estatusCupon = estatusCupon;
    }

    public String mostrarProductosExtras() {
        String productosExtras = "";

        if (getSaldoPuntos() >= 50 ) {
            productosExtras += "Espagueti";
        }  
        if (getSaldoPuntos() >= 101 ) {
            productosExtras += ",Queso Extra";
        } 
        if (getSaldoPuntos() >= 150) {
            productosExtras += ",Cupon Pizza Gratis";
            setEstatusCupon(true);
        }

        return productosExtras;
    }

    public void usarCupon(String respuesta) {

        if (respuesta.equals("Y")) {
            setEstatusCupon(false);
        } else if (respuesta.equals("N")) {
            setEstatusCupon(true);
        } else {

        }
    }

}