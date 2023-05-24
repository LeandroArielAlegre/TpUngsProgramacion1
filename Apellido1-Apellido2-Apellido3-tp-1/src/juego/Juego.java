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
	private Proyectil iones1;
	private Proyectil iones2;
	private Proyectil iones3;
	private Proyectil iones4;
	private Proyectil[] Listaiones;
	private navesDestructoras NaveEnemiga1;
	private navesDestructoras NaveEnemiga2;
	private navesDestructoras NaveEnemiga3;
	private navesDestructoras NaveEnemiga4;
	private navesDestructoras[] ListaNaves;
	private boolean Disparado;
	private boolean menu;
	
	private int vida;
	private boolean conVidas = true;
	Image gameover;
	Image background;
	Image MenuImagen;
	Image prologoImagen;
	
   


	
	// ...
	
	
	Juego()
	{
		
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lost Galaxian - Grupo ... - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		this.miNave = new Nave(400, 500, 40, 40);
		this.Cohete = new Proyectil (this.miNave.getX(),this.miNave.getY(),5,5,2,Color.RED);
		this.Disparado = true;
		
		
		this.menu = true;
		
		// Asteroides
		
		
		this.Asteroide1 = new Asteroides(100,1, 10, 1);
		this.Asteroide2 = new Asteroides(200,1, 10, 1);
		this.Asteroide3 = new Asteroides(400,1, 10, -1);
		this.Asteroide4 = new Asteroides(500,1, 10, -1);
		this.listaAsteroides =  new Asteroides[]{this.Asteroide1,this.Asteroide2, this.Asteroide3, this.Asteroide4};
		
		
		// Destructores Estelares
		this.NaveEnemiga1 = new navesDestructoras(450,20,10,10,1);
		this.NaveEnemiga2 = new navesDestructoras(400,50,10,10,-1);
		this.NaveEnemiga3 = new navesDestructoras(350,50,10,10,1);
		this.NaveEnemiga4 = new navesDestructoras(250,50,10,10,-1);
		this.ListaNaves = new navesDestructoras[] {this.NaveEnemiga1, this.NaveEnemiga2, this.NaveEnemiga3, this.NaveEnemiga4};
		
		//Proyectiles de la nave enemiga
		this.iones1 = new Proyectil(this.NaveEnemiga1.getX(),this.NaveEnemiga1.getY(),20,20,2,Color.BLUE);
		this.iones2 = new Proyectil(this.NaveEnemiga2.getX(),this.NaveEnemiga2.getY(),20,20,2,Color.BLUE);
		this.iones3 = new Proyectil(this.NaveEnemiga3.getX(),this.NaveEnemiga3.getY(),20,20,2,Color.BLUE);
		this.iones4 = new Proyectil(this.NaveEnemiga4.getX(),this.NaveEnemiga4.getY(),20,20,2,Color.BLUE);
		this.Listaiones= new Proyectil[] {this.iones1,this.iones2,this.iones3,this.iones4};
		
		//imagenes
		this.gameover=Herramientas.cargarImagen("imagenes/gameover.png");
		this.background=Herramientas.cargarImagen("imagenes/background.jpg");
		this.MenuImagen=Herramientas.cargarImagen("imagenes/MenuImagen.jpg");
		this.prologoImagen=Herramientas.cargarImagen("imagenes/background.jpg");
		
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
		MenuInicial();
		
		if (this.conVidas && this.menu != true) {
			// Procesamiento de un instante de tiempo
			this.entorno.dibujarImagen(background,400,300,0);
			miNave.dibujarNave(this.entorno);
			MovimientodeNave();
			this.Cohete.dibujarProyectil(entorno);
			Random Xrand = new Random();
			Random Direccionrand = new Random();
			//Movimiento Nave enemiga
			for (int i = 0; i< ListaNaves.length;i++) {
				this.ListaNaves[i].dibujarNaveEnemiga(this.entorno);
				if (this.entorno.alto()/4 > this.ListaNaves[i].getY()) {
				
					this.ListaNaves[i].moverVertical();
					if(this.entorno.alto()/2 > this.ListaNaves[i].getY()) {
						this.ListaNaves[i].invertirDireccion();
						
					}
						
					if(RebotarNave(this.ListaNaves[i])) {
						this.ListaNaves[i].InvertirMovimiento();
						
					}
				}else {
					this.ListaNaves[i].moverDiagonal();
					if(RebotarNave(this.ListaNaves[i])) {
						this.ListaNaves[i].InvertirMovimiento();
						
						
					}
					
				}
				
			}
		
			
			//Disparo naveEnemiga
			
			for(int i=0; i<Listaiones.length;i++) {
				colisiondeIones(Listaiones);
				if(this.Listaiones[i]==null) {
					generarIones(i); // Si es null crea un objeto iones
				}else {
					this.Listaiones[i].moverAbajo();
					this.Listaiones[i].dibujarProyectil(entorno);
				}
				if (this.Listaiones[i] != null) {
				    if (this.Listaiones[i].getY() >= this.entorno.alto()) {
				        this.Listaiones[i] = null;
				    }
				}

				
				
			}
			
				
			// Asteroides
			for (int i= 0; i <listaAsteroides.length;i++) {
				
				// Dibuja Asteroides
				if(this.listaAsteroides[i] !=null) {  // IMPORTANTE SI ES DESTRUIDO UN ASTEROIDE QUE NO LO ITERE
					this.listaAsteroides[i].dibujarAsteroide(entorno); 
					this.listaAsteroides[i].mover();
				}// Mover asteroides y verificar colisión con la pantalla
				
				if (RebotarAsteroide(this.listaAsteroides[i])) { //Si el asteroide toca algun borde de la pantalla el asteroide rebota
				    	this.listaAsteroides[i].InvertirMovimiento();
					}
				   
				    if(this.listaAsteroides[i].getY() > 600 ) {
				    	this.listaAsteroides[i]= null;
				    	this.listaAsteroides [i]= new Asteroides(Xrand.nextInt(50,550),0,15,Direccionrand.nextInt(-1,1));
					
				} 
				 if(colisionaAsteroideCohete(this.listaAsteroides, this.Cohete)) {
					 this.listaAsteroides[i]= null;
				    this.listaAsteroides [i]= new Asteroides(Xrand.nextInt(50,550),0,15,Direccionrand.nextInt(-1,1));
				 }
				    
				}
				
			}
				
			//Movimiento
			// public final char TECLA_D = 68; FALTA
			//public final char TECLA_A = 65; FALTA
			
			//Disparo astro-megaship
			if (this.entorno.sePresiono(entorno.TECLA_ESPACIO) || this.Cohete.getY() != this.miNave.getY()) {
				this.Cohete.moverArriba(); //Cuando Se presiona el espacio el cohete sale disparado
				this.Disparado = false; // este boolean axuliar se pone en false
				
				//Colision cohete a asteroide	
			}if(colisionaAsteroideCohete(this.listaAsteroides, this.Cohete)) {
				this.Cohete =null; // el objeto se elimina
				this.Cohete = new Proyectil (this.miNave.getX(),this.miNave.getY(),5,5,1,Color.RED); //se crea uno nuevo
				this.Disparado = true;
				
			}
			
				
			 //Cuando el Cohete sale de la pantalla se puede volver a disparar y no le pega a nada
				if(this.Cohete.getY()==0) { //Cuando el Cohete sale de la pantalla se puede volver a disparar y no le pega a nada
					this.Cohete =null; // el objeto se elimina
					this.Cohete = new Proyectil (this.miNave.getX(),this.miNave.getY(),5,5,1,Color.RED); //se crea uno nuevo
					this.Disparado = true;
					
					
				}
				
				
				//Colision Asteroides a Astro-MegaShip
			if(colisionaAsteroideNave(listaAsteroides) || colisiondeIones(Listaiones) || colisionNaveEnemigaANave(ListaNaves)) {
				System.out.println("¡La nave a recibido un impacto!");
				this.conVidas = false;
				
				
					
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
	
	
	
	
	//Colision Asteroides a Astro-MegaShip 
	public boolean colisionaAsteroideNave(Asteroides[] asteroide) {
        // Verificar si hay una colisión comparando las coordenadas y tamaños de los objetos
		
		for(int i=0; i< asteroide.length;i++) {
			if(this.listaAsteroides[i] !=null) { // IMPORTANTE SI ES DESTRUIDO UN ASTEROIDE QUE NO LO ITERE
				 int asteroidX = asteroide[i].getX();
		            int asteroidY = asteroide[i].getY();
		            int asteroidRadio = asteroide[i].getRadio();
		            int naveX = miNave.getX();
		            int naveY = miNave.getY();
		            int naveAncho = miNave.getAncho();
		            int naveAlto = miNave.getAlto();

		            // Verificar si hay colisión comparando las coordenadas y tamaños de los objetos
		            if (naveX < asteroidX + asteroidRadio &&
		                naveX + naveAncho > asteroidX &&
		                naveY < asteroidY + asteroidRadio &&
		                naveY + naveAlto > asteroidY) {
		                return true; // Hay una colisión
		            }
				
				
			}
			
		}
        
        return false; // No hay colisión
    }
	
	//Colision Destructores estelares a Astro-MegaShip 
		public boolean colisionNaveEnemigaANave(navesDestructoras[] naveEnemiga) {
	        // Verificar si hay una colisión comparando las coordenadas y tamaños de los objetos
			
			for(int i=0; i< naveEnemiga.length;i++) {
				if(this.ListaNaves[i] !=null) { // IMPORTANTE SI ES DESTRUIDO UN ASTEROIDE QUE NO LO ITERE
					if (this.miNave.getX() < naveEnemiga[i].getX() + naveEnemiga[i].getAncho() &&
			        		this.miNave.getX() + this.miNave.getAncho() > naveEnemiga[i].getX() &&
			            this.miNave.getY() < naveEnemiga[i].getY() + naveEnemiga[i].getAlto() &&
			            this.miNave.getY() + this.miNave.getAlto() > naveEnemiga[i].getY()) {
			            return true; // Hay una colisión
			        }
					
					
				}
				
			}
	        
	        return false; // No hay colisión
	    }
	
	//Colision Disparo de iones de Destructor estelar a Astro-MegaShip 
	public boolean colisiondeIones(Proyectil[] iones) {
        // Verificar si hay una colisión comparando las coordenadas y tamaños de los objetos
		
		for(int i=0; i< iones.length;i++) {
			if (iones[i] != null) {
	            if (iones[i].getX() < miNave.getX() + miNave.getAncho() &&
	                iones[i].getX() + iones[i].getAncho() > miNave.getX() &&
	                iones[i].getY() < miNave.getY() + miNave.getAlto() &&
	                iones[i].getY() + iones[i].getAlto() > miNave.getY()) {
	                return true; // Hay una colisión
	            }
			}
		}
        
        return false; // No hay colisión
    }
	
	public boolean colisionaAsteroideCohete(Asteroides[] asteroide, Proyectil cohete) {
        // Verificar si hay una colisión comparando las coordenadas y tamaños de los objetos
		
		for(int i=0; i< asteroide.length;i++) {
			if(this.listaAsteroides[i] !=null) {  // IMPORTANTE SI ES DESTRUIDO UN ASTEROIDE QUE NO LO ITERE
				 int asteroidX = asteroide[i].getX();
		            int asteroidY = asteroide[i].getY();
		            int asteroidRadio = asteroide[i].getRadio();
		            int coheteX = cohete.getX();
		            int coheteY = cohete.getY();
		            int coheteAncho = cohete.getAncho();
		            int coheteAlto = cohete.getAlto();

		            // Verificar si hay colisión comparando las coordenadas y tamaños de los objetos
		            if (coheteX < asteroidX + asteroidRadio &&
		            	coheteX + coheteAncho > asteroidX &&
		            	coheteY < asteroidY + asteroidRadio &&
		            	coheteY + coheteAlto > asteroidY) {
		                return true; // Hay una colisión
		            }
				
				
			}
			
		}
		
        
        return false; // No hay colisión
    }
	
	
	
	//Cuando un Asteroide toca el borde de la ventana y cambia de direccion 
	private boolean RebotarAsteroide(Asteroides miAsteroide) { // Recibe un Objeto Asteroide y lo hace rebotar
		if (miAsteroide.getX() < 0 || miAsteroide.getX() > this.entorno.ancho() - 2 * miAsteroide.getRadio()) {
	        return true;
	    }
	    return false;
		
	
		
	}
	
	//Cuando una NaveEnemiga toca el borde de la ventana y cambia de direccion 
		private boolean RebotarNave(navesDestructoras naveEnemiga) { // Recibe un Objeto Asteroide y lo hace rebotar

			boolean ReboteTop = naveEnemiga.getY() > this.entorno.alto();
			boolean ReboteBottom = naveEnemiga.getY() < 0;
		
			return   ReboteTop || ReboteBottom  ;
			
			
		}
	
	private void MovimientodeNave() { //CONTROL DE LA NAVE
		if (this.entorno.estaPresionada(this.entorno.TECLA_DERECHA)
				&& this.miNave.getX() + this.miNave.getAncho() / 2 < this.entorno.ancho()) {
			this.miNave.moverDerecha();
			CoheteNave();
		}
		
		if (this.entorno.estaPresionada(this.entorno.TECLA_IZQUIERDA)
				&& this.miNave.getX() - this.miNave.getAncho() / 2 > 0) {
			this.miNave.moverIzquierda();
			CoheteNave();
		}
		
		
	}
	private void CoheteNave() {
		if(this.Cohete.getY() > 0  && Disparado) { // El cohete nos sigue cuando nos movemos mientras no sea disparado
			this.Cohete.setX(this.miNave.getX());
			this.Cohete.setY(this.miNave.getY());
		}
		
	}
	
	//Crea un nuevo proyectil
	private void generarIones(int i) {
	    this.Listaiones[i] = new Proyectil(this.ListaNaves[i].getX(), this.ListaNaves[i].getY(), 20, 20, 2,Color.BLUE);
	}
	
	
	//Metodos especiales de movimiento de los Destructores estelares:
	
	
	//DIBUJA LA PANTALLA FINAL DEL JUEGO
	public void PantallaFinal() {
		this.entorno.dibujarImagen(gameover,400,300,0);
		this.entorno.cambiarFont(Font.DIALOG, 40, Color.RED);
		this.entorno.escribirTexto("PERDISTE",310,150);
		this.entorno.cambiarFont(Font.DIALOG, 30, Color.RED);
		this.entorno.cambiarFont(Font.DIALOG, 30, Color.RED);
		this.entorno.escribirTexto(" { PRESIONE ESPACIO PARA SALIR } ",120,550);
	}
	
	public void MenuInicial() {
		
		if(this.menu) {
			this.entorno.dibujarImagen(MenuImagen,400,300,0);}
			if(this.entorno.sePresiono(this.entorno.TECLA_ENTER)) {
				this.menu = false;
			

		}
		
	}
	

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}
