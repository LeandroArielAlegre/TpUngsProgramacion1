package juego;

import java.awt.Color;

import entorno.Entorno;

public class Proyectil {
	private int x;
	private int y;
	private int alto;
	private int ancho;
	private int velocidad;
	private Color color;
	public Proyectil(int x, int y, int alto, int ancho, int velocidad, Color color) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.velocidad = velocidad;
		this.color = color;
	}
	
	public void dibujarProyectil(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0 , color);
	}
		
	public void moverArriba() {
		this.y -= this.velocidad;
	}
	public void moverAbajo() {
		this.y += this.velocidad;
	}
	
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y= y;
	}
	public void setX(int x) {
		this.x= x;
	}

	public int getAncho() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getAlto() {
		// TODO Auto-generated method stub
		return 0;
	}
}


