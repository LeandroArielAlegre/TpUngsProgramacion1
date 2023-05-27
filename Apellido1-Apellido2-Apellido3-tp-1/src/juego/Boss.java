package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Boss {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private int velocidad;
	private Image boss;
	private Color invisible=new Color(255,0,0, 0);
	Boss(int x, int y, int ancho, int alto){ // Crea objeto Nave
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad = 1;
		
		this.boss =Herramientas.cargarImagen("imagenes/boss.gif");
		
		
	}
	
	// Dibuja el boss
	void dibujarBoss(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, invisible);
		}
		void dibujarImagenBoss(Entorno entorno) {
			entorno.dibujarImagen(this.boss, this.x, this.y, 0,0.3);
		}
		public void moverVertical() {
			this.y += this.velocidad;
		}
		public void moverHorizontal() {
			this.x = this.x + this.velocidad;
		}
		public void InvertirMoverHorizontal() {
			this.velocidad = -this.velocidad;
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
	public Image getBoss() {
		return boss;
	}
	public void setBoss(Image boss) {
		this.boss = boss;
	}
	
			

}
