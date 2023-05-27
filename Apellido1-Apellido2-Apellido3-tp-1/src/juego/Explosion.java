package juego;

import java.awt.Image;

public class Explosion {
	private int x;
    private int y;
    private Image imagen;
    private int duracion;
    private int tiempoTranscurrido;
    
    public Explosion(Image imagen, int x, int y, int duracion) {
        this.imagen = imagen;
        this.x = x;
        this.y = y;
        this.duracion = duracion;
        this.tiempoTranscurrido = 0;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public Image getImagen() {
        return imagen;
    }
    
    public boolean haTerminado() {
        return tiempoTranscurrido >= duracion;
    }
    
    public void actualizar() {
        tiempoTranscurrido++;
    }

}
