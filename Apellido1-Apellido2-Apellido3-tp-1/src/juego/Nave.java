package juego;


import java.awt.Color;

import entorno.Entorno;

public class Nave {
	private int x;
	private int y;
	private int ancho;
	private int alto;
	private int velocidad;
	
	Nave(int x, int y, int ancho, int alto){ // Crea objeto Nave
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad = 3;
	}
	
	// Dibuja la nave
	void dibujarNave(Entorno entorno) {
		entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.BLUE);
		
	}
	
	// Metodos de movimiento
	 void moverDerecha(){
		 this.x=this.x + this.velocidad;
	 }
	 
	 void moverIzquierda(){
		 this.x=this.x - this.velocidad;
	 }
	 
	 void DibujarDisparo(Entorno entorno) {
		 int DisparoX = this.x;
		 int DisparoY = this.y;
		 entorno.dibujarRectangulo(DisparoX, DisparoY, 30, 20, 0, Color.RED);
		 
	 }
	 
	 
	
	 
	 //Getters y Setters
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
	
	


}
