package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class item {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private int velocidad;
	Image itemVida;
	Color invisible=new Color(255,0,0, 0);
	
	
	item(int x, int y, int ancho, int alto){ // Crea objeto Nave
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad = 2;
		
		this.itemVida =Herramientas.cargarImagen("imagenes/ItemVida.gif");
		
		
	}
	// Dibuja el item
		void dibujarItem(Entorno entorno) {
			entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, invisible);
		}
		void dibujarImagenItem(Entorno entorno) {
			entorno.dibujarImagen(this.itemVida, this.x, this.y, 0,0.5);
		}
		public void mover() {
			this.y += this.velocidad;
		}

	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public Image getItemVida() {
		return itemVida;
	}

	public void setItemVida(Image itemVida) {
		this.itemVida = itemVida;
	}



	
}
