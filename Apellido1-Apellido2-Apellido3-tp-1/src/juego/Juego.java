package juego;


import entorno.Entorno;

import java.awt.Color;
import java.awt.Font;
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
	
   


	
	// ...
	
	
	Juego()
	{
		Random rand = new Random();
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lost Galaxian - Grupo ... - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		this.miNave = new Nave(400, 500, 60, 80);
		this.Cohete = new Proyectil (this.miNave.getX(),this.miNave.getY(),5,5,1);
		this.Disparado = true;
		
		
		
		// Asteroides
		
		
		this.Asteroide1 = new Asteroides(300,500, 20, 1);
		this.Asteroide2 = new Asteroides(300,300, 20, 1);
		this.Asteroide3 = new Asteroides(520,300, 20, -1);
		this.Asteroide4 = new Asteroides(100,200, 20, -1);
		this.listaAsteroides =  new Asteroides[]{this.Asteroide1,this.Asteroide2, this.Asteroide3, this.Asteroide4};
		
		

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
		// Procesamiento de un instante de tiempo
		miNave.dibujarNave(this.entorno);
		this.Cohete.dibujarProyectil(entorno);
		
		for(int i= 0; i <listaAsteroides.length;i++) {         // Dibuja los Asteroides
			this.listaAsteroides[i].dibujarAsteroide(entorno);
		}
		
		

		// Mover asteroides y verificar colisión
		for (int i= 0; i <listaAsteroides.length;i++) {
			this.listaAsteroides[i].mover();
		    if (RebotarAsteroide(this.listaAsteroides[i])) {
		    	this.listaAsteroides[i].InvertirMovimiento();
		    }
		}
			
		//Movimiento
		// public final char TECLA_D = 68; FALTA
		//public final char TECLA_A = 65; FALTA
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
		    

		// ...
		

	}
	
	
	
	
	//Colision Asteroides a Astro-MegaShip
	
	
	
	
	//Cuando un Asteroide toca el borde de la ventana y cambia de direccion 
	private boolean RebotarAsteroide(Asteroides miAsteroide) { // Recibe un Objeto Asteroide y lo hace rebotar

		
		boolean ReboteTop = miAsteroide.getY() > this.entorno.alto();
		boolean ReboteBottom = miAsteroide.getY() < 0;
		
		
		return   ReboteTop || ReboteBottom  ;
		
		
	}
	
	


	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
