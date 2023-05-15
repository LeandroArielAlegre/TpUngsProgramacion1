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
	private Asteroides[] miAsteroide1;
	private Asteroides miAsteroide2;



	
	// ...
	
	
	Juego()
	{
		Random rand = new Random();
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lost Galaxian - Grupo ... - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		this.miNave = new Nave(400, 500, 60, 80);
		
		
		// Asteroides
		//this.miAsteroide1 = RespawnAsteroides(6); //Inicializa n cantidad de Asteroides en pantalla
												  // nos devuelve un array de asteroides
		this.miAsteroide1= Asteroides.ListaAsteroides(6);

		

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
		dibujarAsteroides();
		
		
		//Movimiento
		// public final char TECLA_D = 68; FALTA
		//public final char TECLA_A = 65; FALTA
		if (this.entorno.estaPresionada(this.entorno.TECLA_DERECHA)
				&& this.miNave.getX() + this.miNave.getAncho() / 2 < this.entorno.ancho()) {
			this.miNave.moverDerecha();
		}
		
		if (this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)
				&& this.miNave.getX() - this.miNave.getAncho() / 2 > 0) {
			this.miNave.moverIzquierda();
		}
		
		
		//Colisiones:
		if (this.miAsteroide2.getY() == this.miNave.getY()) { // test si andaba
			System.out.println("a");
		}
		
		
		
		
		//Reaparicion de Asteroides

		if(this.miAsteroide2.getY() > this.entorno.alto() ||this.miAsteroide2.getX()> entorno.ancho()){
			this.miAsteroide1= Asteroides.ListaAsteroides(6);
			dibujarAsteroides();
			
		
		
			
			
		}
		// ...
		

	}
	
	// FUNCION A MEJORAR
	
	
	
	
	private void dibujarAsteroides() {        //DIBUJA LOS ASTEROIDES
		int contador1 = 0;
		while(contador1< this.miAsteroide1.length) {
			this.miAsteroide2 = this.miAsteroide1[contador1];
			miAsteroide2.dibujarAsteroide(this.entorno);
			miAsteroide2.mover();
			contador1++;
		}
		
	}
	
	
	//Colision Asteroides a Astro-MegaShip
	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
