package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class navesDestructoras {
	private int x;
		private int y;
	private int ancho;
	private int alto;
	private int velocidad;
	private int direccion;
	private Image destructorEstelar;
	private Color invisible=new Color(255,0,0, 0);
	
	
	
	navesDestructoras(int x, int y, int ancho, int alto, int direccion){
		this.x = x;
		this.y = y;
		this.alto = alto;
		this.ancho = ancho;
		this.velocidad = 1;
		this.direccion= direccion;
		this.destructorEstelar =Herramientas.cargarImagen("imagenes/destructor.gif");
		
		
		
	}
	
	
	
	
	
	// Dibuja la nave
		void dibujarNaveEnemiga(Entorno entorno) {
			entorno.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, invisible);
			
		}
		public void dibujarImagenNaveEnemiga(Entorno entorno) { 
			entorno.dibujarImagen(this.destructorEstelar, this.x, this.y, 0,1);
		}
		
		void mover() {
			this.y += this.velocidad;
			this.x = this.x - direccion;
		}
		
		public void InvertirMovimiento() {
			this.direccion = -this.direccion;
					
		}
		void rebote() {
			this.x = this.x + 2;
		}
		
		//Colision de con otra nave
		public boolean colisionNaveEnemigaANave(navesDestructoras otraNave) {
		    // Verificar si hay una colisión comparando las coordenadas y tamaños de los objetos
		    if (this.getX() < otraNave.getX() + otraNave.getAncho() &&
		        this.getX() + this.getAncho() > otraNave.getX() &&
		        this.getY() < otraNave.getY() + otraNave.getAlto() &&
		        this.getY() + this.getAlto() > otraNave.getY()) {
		        return true; // Hay una colisión
		    }
		    
		    return false; // No hay colisión
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

		public int getDireccion() {
			return direccion;
		}

		public void setDireccion(int direccion) {
			this.direccion = direccion;
		}
	
	
	}


