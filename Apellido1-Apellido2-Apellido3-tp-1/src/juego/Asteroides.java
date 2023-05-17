package juego;

import java.awt.Color;
import java.util.Random;

import entorno.Entorno;

public class Asteroides {
	private int x;
	private int y;
	private int radio;
	private int velocidad;
	private int direccion;
	
	
	Asteroides(int x, int y, int radio, int direccion){
		this.x = x;
		this.y = y;
		this.radio = radio;
		this.velocidad = 1;
		this.direccion = direccion;
		
	}
	void dibujarAsteroide(Entorno entorno) {
		entorno.dibujarCirculo(this.x, this.y, this.radio, Color.ORANGE);
		
	}
	public void mover()
	{
		this.y += velocidad;
		this.x = this.x - direccion;
	}

	public void InvertirMovimiento() {
		this.velocidad = (-velocidad);
		this.direccion =(-direccion);
				
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
