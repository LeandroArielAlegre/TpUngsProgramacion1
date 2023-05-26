package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Asteroides {
	private int x;
	private int y;
	private int radio;
	private int velocidad;
	private int direccion;
	private Image asteroide;
	private Image asteroideDestruccion;
	private Color invisible=new Color(255,0,0, 0);
	
	
	Asteroides(int x, int y, int radio, int direccion){
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.velocidad = 1;
		this.direccion = direccion;
		this.asteroide =Herramientas.cargarImagen("imagenes/asteroide.png");
		this.asteroideDestruccion =Herramientas.cargarImagen("imagenes/asteroideDestruccion.gif");
		
		
	}
	void dibujarAsteroide(Entorno entorno) {
		entorno.dibujarCirculo(this.x, this.y, this.radio, invisible);
		
	}
	public void dibujarImagenAsteroide(Entorno entorno) { 
		entorno.dibujarImagen(this.asteroide, this.x, this.y, 0,0.3);
	}
	public void dibujarImagenAsteroideDestruccion(Entorno entorno) { 
		entorno.dibujarImagen(this.asteroideDestruccion, this.x, this.y, 0,0.3);
	}
	
	public void mover()
	{
		this.y += velocidad;
		this.x = this.x - direccion;
	}

	public void InvertirMovimiento() {
		 this.direccion = -this.direccion;
				
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
	public int getRadio() {
		return radio;
	}
	public void setRadio(int radio) {
		this.radio = radio;
	}
	

}
