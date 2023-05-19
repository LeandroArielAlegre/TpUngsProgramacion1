package juego;


import entorno.Entorno;
import entorno.Herramientas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.Random;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	
	// Variables y métodos propios de cada grupo
	private Nave miNave;
	private Asteroides Asteroide1;
	private Asteroides Asteroide2;
	private Asteroides Asteroide3;
	private Asteroides Asteroide4;
	private Asteroides[] listaAsteroides;
	private Proyectil Cohete;
	private boolean Disparado;
	private int vida;
	private boolean conVidas = true;
	Image gameover;
	
   


	
	// ...
	
	
	Juego()
	{
		Random rand = new Random();
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lost Galaxian - Grupo ... - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		this.miNave = new Nave(400, 500, 40, 40);
		this.Cohete = new Proyectil (this.miNave.getX(),this.miNave.getY(),5,5,1);
		this.Disparado = true;
		
		
		
		// Asteroides
		
		
		this.Asteroide1 = new Asteroides(300,500, 20, 1);
		this.Asteroide2 = new Asteroides(300,300, 20, 1);
		this.Asteroide3 = new Asteroides(520,300, 20, -1);
		this.Asteroide4 = new Asteroides(100,200, 20, -1);
		this.listaAsteroides =  new Asteroides[]{this.Asteroide1,this.Asteroide2, this.Asteroide3, this.Asteroide4};
		
		
		//imagenes
		this.gameover=Herramientas.cargarImagen("imagenes/gameover.png");
		
		//vidas
		

		// ...

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		
		if (this.conVidas) {
			// Procesamiento de un instante de tiempo
			miNave.dibujarNave(this.entorno);
			MovimientodeNave();
			this.Cohete.dibujarProyectil(entorno);
			
			for(int i= 0; i <listaAsteroides.length;i++) {         // Dibuja los Asteroides
				if(this.listaAsteroides[i] !=null) {  // IMPORTANTE SI ES DESTRUIDO UN ASTEROIDE QUE NO LO ITERE
					this.listaAsteroides[i].dibujarAsteroide(entorno);
				}
				
			}

			// Mover asteroides y verificar colisión con la pantalla
			for (int i= 0; i <listaAsteroides.length;i++) {
				if(this.listaAsteroides[i] !=null) {  // IMPORTANTE SI ES DESTRUIDO UN ASTEROIDE QUE NO LO ITERE
					this.listaAsteroides[i].mover();
				    if (RebotarAsteroide(this.listaAsteroides[i])) {
				    	this.listaAsteroides[i].InvertirMovimiento();
				    }
					
				}
				
			}
				
			//Movimiento
			// public final char TECLA_D = 68; FALTA
			//public final char TECLA_A = 65; FALTA
			
			//Disparo
			if (this.entorno.sePresiono(entorno.TECLA_ESPACIO) || this.Cohete.getY() != this.miNave.getY()) {
				this.Cohete.mover(); //Cuando Se presiona el espacio el cohete sale disparado
				this.Disparado = false; // este boolean axuliar se pone en false
				
			}
				
				
				if(this.Cohete.getY()==0) { //Cuando el Cohete sale de la pantalla se puede volver a disparar y no le pega a nada
					this.Disparado = true;
					this.Cohete =null; // el objeto se elimina
					this.Cohete = new Proyectil (this.miNave.getX(),this.miNave.getY(),5,5,1); //se crea uno nuevo
					
					
				}
				
			//Colision cohete a asteroide	
			colisionaAsteroideCohete(this.listaAsteroides, this.Cohete);
				
				//Colision Asteroides a Astro-MegaShip
			if(colisionaAsteroideNave(listaAsteroides)) {
				System.out.println("¡La nave a recibido un impacto!");
				this.conVidas = false;
				
				
					
				}
			
			
		}
		
		//Dibuja una pantalla al morir y al presionar espacio se detiene el programa
		if(this.conVidas != true) {
			PantallaFinal();
			if (this.entorno.sePresiono(entorno.TECLA_ESPACIO)) {
				System.exit(0);
				}
		}
		
		
		
		
		    
		
		

		// ...
		

	}
	
	
	
	
	//Colision Asteroides a Astro-MegaShip //REVISAR
	public boolean colisionaAsteroideNave(Asteroides[] asteroide) {
        // Verificar si hay una colisión comparando las coordenadas y tamaños de los objetos
		
		for(int i=0; i< asteroide.length;i++) {
			if(this.listaAsteroides[i] !=null) { // IMPORTANTE SI ES DESTRUIDO UN ASTEROIDE QUE NO LO ITERE
				if (this.miNave.getX() < asteroide[i].getX() + asteroide[i].getRadio() &&
		        		this.miNave.getX() + this.miNave.getAncho() > asteroide[i].getX() &&
		            this.miNave.getY() < asteroide[i].getY() + asteroide[i].getRadio() &&
		            this.miNave.getY() + this.miNave.getAlto() > asteroide[i].getY()) {
		            return true; // Hay una colisión
		        }
				
				
			}
			
		}
        
        return false; // No hay colisión
    }
	
	public void colisionaAsteroideCohete(Asteroides[] asteroide, Proyectil cohete) {
        // Verificar si hay una colisión comparando las coordenadas y tamaños de los objetos
		
		for(int i=0; i< asteroide.length;i++) {
			if(this.listaAsteroides[i] !=null) {  // IMPORTANTE SI ES DESTRUIDO UN ASTEROIDE QUE NO LO ITERE
				if (cohete.getX() < asteroide[i].getX() + asteroide[i].getRadio() &&
		        		cohete.getX() + cohete.getAncho() > asteroide[i].getX() &&
		            cohete.getY() < asteroide[i].getY() + asteroide[i].getRadio() &&
		            cohete.getY() + cohete.getAlto() > asteroide[i].getY()) {
		            asteroide[i] = null;
		           
		             // Hay una colisión
		        }
				
				
			}
			
		}
        
         // No hay colisión
    }
	
	
	
	
	
	
	
	
	
	
	
	
	//Cuando un Asteroide toca el borde de la ventana y cambia de direccion 
	private boolean RebotarAsteroide(Asteroides miAsteroide) { // Recibe un Objeto Asteroide y lo hace rebotar

		
		boolean ReboteTop = miAsteroide.getY() > this.entorno.alto();
		boolean ReboteBottom = miAsteroide.getY() < 0;
		
		
		return   ReboteTop || ReboteBottom  ;
		
		
	}
	
	private void MovimientodeNave() { //CONTROL DE LA NAVE
		if (this.entorno.estaPresionada(this.entorno.TECLA_DERECHA)
				&& this.miNave.getX() + this.miNave.getAncho() / 2 < this.entorno.ancho()) {
			this.miNave.moverDerecha();
			if(this.Cohete.getY() > 0 && Disparado) { // El cohete nos sigue cuando nos movemos mientras no sea disparado
				this.Cohete.setX(this.miNave.getX());
				this.Cohete.setY(this.miNave.getY());
			}
		}
		
		if (this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)
				&& this.miNave.getX() - this.miNave.getAncho() / 2 > 0) {
			this.miNave.moverIzquierda();
			if(this.Cohete.getY() > 0  && Disparado) { // El cohete nos sigue cuando nos movemos mientras no sea disparado
				this.Cohete.setX(this.miNave.getX());
				this.Cohete.setY(this.miNave.getY());
			}
		}
		
		
	}
	
	
	public void PantallaFinal() {
		this.entorno.dibujarImagen(gameover,400,300,0);
		this.entorno.cambiarFont(Font.DIALOG, 40, Color.RED);
		this.entorno.escribirTexto("PERDISTE",310,150);
		this.entorno.cambiarFont(Font.DIALOG, 30, Color.RED);
		this.entorno.cambiarFont(Font.DIALOG, 30, Color.RED);
		this.entorno.escribirTexto(" { PRESIONE ESPACIO PARA SALIR } ",120,550);
	}
	


	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
