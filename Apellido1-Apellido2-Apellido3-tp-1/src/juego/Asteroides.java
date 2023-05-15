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

	public static Asteroides[] ListaAsteroides(int a) { //Aparicion de Asteroides 
		Asteroides[] asteroide = new Asteroides[a] ;    //Inicializa n cantidad de Asteroides en pantalla
		for (int i = 0; i<a; i++) {                     // nos devuelve un array de asteroides
			 Random rand = new Random();
			 int x;
			 if (i %2 ==0) {
				  x = rand.nextInt(350,650);
			 }else {
				  x = rand.nextInt(200,250);
			 }
			 
	         int y = i % 2 == 0 ? -50 : -20;
	         int radio = 20;
	         int direccion = i % 2 == 0 ? 1 : -1;
			asteroide[i] = new Asteroides(x,y, radio,direccion);
			
			
		}
		return asteroide;
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
