package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil {
	private int x;
	private int y;
	private int alto;
	private int ancho;
	private int velocidad;
	private Image cohete;
	private Image iones;
	private Color invisible=new Color(255,0,0, 0);
	
	public Proyectil(int x, int y, int alto, int ancho, int velocidad, Color color) {
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.velocidad = velocidad;
		this.cohete =Herramientas.cargarImagen("imagenes/cohete.gif");
		this.iones = Herramientas.cargarImagen("imagenes/ion.gif");
	}
	
	public void dibujarProyectil(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0 , invisible);
	}
	public void dibujarImagenCohete(Entorno entorno) { 
		entorno.dibujarImagen(this.cohete, this.x, this.y, 0,0.8);
	}
	public void dibujarImagenIones(Entorno entorno) { 
		entorno.dibujarImagen(this.iones, this.x, this.y, 1.5,0.4);
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
		return this.ancho;
	}

	public int getAlto() {
		// TODO Auto-generated method stub
		return this.alto;
	}
}


