package juego;


import entorno.Entorno;
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
		this.miAsteroide1 = RespawnAsteroides(6); //Inicializa n cantidad de Asteroides en pantalla
												  // nos devuelve un array de asteroides

		

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
		
		for (int i= 0; i< this.miAsteroide1.length; i++) {  //Dibuja n de cantidad de asteroides
			this.miAsteroide2 = this.miAsteroide1[i];
			miAsteroide2.dibujarAsteroide(this.entorno);
			miAsteroide2.mover();
		}
		
		
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
		
		

		
		
		
		//Reaparicion de Asteroides

		if(this.miAsteroide2.getY() > this.entorno.alto() ||this.miAsteroide2.getX()> entorno.ancho()){
			System.out.println("sali del mapa");
		}
		// ...
		

	}
	
	// FUNCION A MEJORAR
	
	//Devuelve un array de n asteroides, se determina la direccion de caida en diagonal (izquierda/derecha)
	private Asteroides[] RespawnAsteroides(int a) { //Aparicion de Asteroides 
		Asteroides[] asteroide = new Asteroides[a] ;
		for (int i = 0; i<a; i++) {
			 Random rand = new Random();
			 int x;
			 if (i %2 ==0) {
				  x = rand.nextInt(350,650);
			 }else {
				  x = rand.nextInt(200,250);
			 }
			 
	         int y = -50;
	         int radio = 20;
	         int direccion = i % 2 == 0 ? 1 : -1;
			asteroide[i] = new Asteroides(x,y, radio,direccion);
			
			
		}
		return asteroide;
		
		
	}
	
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
