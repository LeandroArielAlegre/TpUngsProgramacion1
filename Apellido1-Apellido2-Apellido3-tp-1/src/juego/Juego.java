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
	private int score;
	private int vida;
	private boolean conVidas = true;
	private int cantEliminados;
	private int CantidadEnemigos;
	Image gameover;
	Image winScreen;
	Image background;
	Image MenuImagen;
	Image prologoImagen;
	
	private Random Xrand;
    private Random Direccionrand ;


	
	// ...
	
	
	Juego()
	{
		
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Lost Galaxian - Grupo ... - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		this.miNave = new Nave(400, 500, 60, 60);
		this.Cohete = new Proyectil (this.miNave.getX(),this.miNave.getY(),10,10,5,Color.RED);
		this.Disparado = true;
		
		
		this.menu = true;
		
		// Asteroides
		
		
		this.Asteroide1 = new Asteroides(100,1, 40, 1);
		this.Asteroide2 = new Asteroides(200,1, 40, 1);
		this.Asteroide3 = new Asteroides(400,1, 40, -1);
		this.Asteroide4 = new Asteroides(500,1, 40, -1);
		this.listaAsteroides =  new Asteroides[]{this.Asteroide1,this.Asteroide2, this.Asteroide3, this.Asteroide4};
		
		
		// Destructores Estelares (naves enemigas)
		this.NaveEnemiga1 = new navesDestructoras(650,1,30,30,-1);
		this.NaveEnemiga2 = new navesDestructoras(450,1,30,30,-1);
		this.NaveEnemiga3 = new navesDestructoras(250,1,30,30,-1);
		this.NaveEnemiga4 = new navesDestructoras(150,1,30,30,-1);
		this.ListaNaves = new navesDestructoras[] {this.NaveEnemiga1, this.NaveEnemiga2, this.NaveEnemiga3, this.NaveEnemiga4};
		this.CantidadEnemigos = 5;


		//Disparo de naves enemigas
		this.iones1 = new Proyectil(this.NaveEnemiga1.getX(),this.NaveEnemiga1.getY(),30,30,5,Color.BLUE);
		this.iones2 = new Proyectil(this.NaveEnemiga2.getX(),this.NaveEnemiga2.getY(),30,30,5,Color.BLUE);
		this.iones3 = new Proyectil(this.NaveEnemiga3.getX(),this.NaveEnemiga3.getY(),30,30,5,Color.BLUE);
		this.iones4 = new Proyectil(this.NaveEnemiga4.getX(),this.NaveEnemiga4.getY(),30,30,5,Color.BLUE);
		this.Listaiones= new Proyectil[] {this.iones1,this.iones2,this.iones3,this.iones4};
		
		//imagenes
		this.gameover=Herramientas.cargarImagen("imagenes/gameover.png");
		this.background=Herramientas.cargarImagen("imagenes/background1.gif");
		this.MenuImagen=Herramientas.cargarImagen("imagenes/MenuImagen.jpg");
		this.prologoImagen=Herramientas.cargarImagen("imagenes/background.jpg");
		this.winScreen = Herramientas.cargarImagen("imagenes/winScreen.jpg");
		
		//vidas
		this.vida = 100;
		
		//Score
		this.score =0;
		//Enemigos eliminados
		this.cantEliminados = 0;
		
		

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
		if (this.conVidas && this.menu != true && this.score<10000) {
			// Procesamiento de un instante de tiempo
			this.entorno.dibujarImagen(background,400,300,0,1.6);
			
			//Nave Jugador
			miNave.dibujarNave(this.entorno);
			miNave.dibujarImagenNave(this.entorno);
			MovimientodeNave();
			
			//score
			PuntosTotales(this.score);
			
			//Cantidad de vida
			VidasTotal(this.vida);
			// Enemigos obliterados
			CantEnemigosEliminados(this.cantEliminados);
		
			
			this. Xrand = new Random();
			this. Direccionrand = new Random();
			//Movimiento Nave enemiga
			
				for (int i = 0; i< ListaNaves.length;i++) {
					if(this.ListaNaves[i] !=null) {
						this.ListaNaves[i].dibujarNaveEnemiga(this.entorno);
						this.ListaNaves[i].dibujarImagenNaveEnemiga(this.entorno);
						this.ListaNaves[i].mover();
						if(this.entorno.alto()/6 == this.ListaNaves[i].getY()) {
							this.ListaNaves[i].InvertirMovimiento();
						}
						if(this.entorno.alto()/3 == this.ListaNaves[i].getY()) {
							this.ListaNaves[i].InvertirMovimiento();
						}
						if(this.entorno.alto()/2 == this.ListaNaves[i].getY()) {
							this.ListaNaves[i].InvertirMovimiento();
						}
						if(this.ListaNaves[i].getY() > 600 ) {
					    	this.ListaNaves[i]= null;
					    	if(this.CantidadEnemigos >=0) {
					    		this.ListaNaves[i]= new navesDestructoras(Xrand.nextInt(200,600),1,30,30,Direccionrand.nextBoolean() ? -1 : 1);
					    	}
					    	
						}
						if(RebotarNaveEnemiga(this.ListaNaves[i])) {
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
						this.Listaiones[i].dibujarImagenIones( entorno);
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
						this.listaAsteroides[i].dibujarImagenAsteroide(entorno);
						this.listaAsteroides[i].mover();
					}// Mover asteroides y verificar colisión con la pantalla
					
					if (RebotarAsteroide(this.listaAsteroides[i])) { //Si el asteroide toca algun borde de la pantalla el asteroide rebota
					    	this.listaAsteroides[i].InvertirMovimiento();
						}
					   
					    if(this.listaAsteroides[i].getY() > 600 ) {
					    	this.listaAsteroides[i]= null;
					    	this.listaAsteroides [i]= new Asteroides(Xrand.nextInt(50,550),0,40,Direccionrand.nextBoolean() ? -1 : 1);
						
					}
					 
					    
					}
				//Movimiento
				// public final char TECLA_D = 68; FALTA
				//public final char TECLA_A = 65; FALTA
				
				//Disparo astro-megaship
				
				
				if (this.entorno.sePresiono(entorno.TECLA_ESPACIO) || this.Cohete.getY() != this.miNave.getY()) {
					this.Cohete.dibujarProyectil(entorno);
					this.Cohete.dibujarImagenCohete( entorno);
					this.Cohete.moverArriba(); //Cuando Se presiona el espacio el cohete sale disparado
					this.Disparado = false; // este boolean axuliar se pone en false
					
					//Colision cohete a asteroide	
				}if(colisionaAsteroideCohete(this.listaAsteroides, this.Cohete)) {
					this.Cohete =null; // el objeto se elimina
					this.Cohete = new Proyectil (this.miNave.getX(),this.miNave.getY(),20,20,4,Color.RED); //se crea uno nuevo
					this.Disparado = true;
					this.score +=50;
					
				}//Colision cohete a Nave	
				if(colisionCoheteNave(this.ListaNaves)) {
					this.Cohete =null; // el objeto se elimina
					this.Cohete = new Proyectil (this.miNave.getX(),this.miNave.getY(),20,20,4,Color.RED); //se crea uno nuevo
					this.Disparado = true;
					this.score +=150;
					this.cantEliminados +=1;
				}
					
				
				
					
				 //Cuando el Cohete sale de la pantalla se puede volver a disparar y no le pega a nada
					if(this.Cohete.getY()==0) { //Cuando el Cohete sale de la pantalla se puede volver a disparar y no le pega a nada
						this.Cohete =null; // el objeto se elimina
						this.Cohete = new Proyectil (this.miNave.getX(),this.miNave.getY(),10,10,5,Color.RED); //se crea uno nuevo
						this.Disparado = true;
						
						
					}
					
					//Colision Asteroides a Astro-MegaShip
				if(colisionaAsteroideNave(listaAsteroides) || colisiondeIones(Listaiones) || colisionNaveEnemigaANave(ListaNaves)) {
					this.vida -= 1;
					
						
					}
				if(this.vida <0) {
					this.conVidas = false;
				}
				
			}
				
			
			
			
			
			

		
		
		//Dibuja una pantalla al morir y al presionar espacio se detiene el programa
		if(this.conVidas != true) {
			PantallaPerder();
			if (this.entorno.sePresiono(entorno.TECLA_ESPACIO)) {
				System.exit(0);
				}
		}
		if(this.score ==	1000) {
			PantallaGanar();
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
		            	this.listaAsteroides[i]= null;
					    this.listaAsteroides [i]= new Asteroides(Xrand.nextInt(50,550),0,40,Direccionrand.nextBoolean() ? -1 : 1);
					    
		                return true; // Hay una colisión
		            }
		            
		           
		             // Hay una colisión
		        }
				
		
			
			
		}
		
        
        return false; // No hay colisión
    }
	public boolean colisionCoheteNave(navesDestructoras[] naveEnemiga) {
        // Verificar si hay una colisión comparando las coordenadas y tamaños de los objetos
		
		for(int i=0; i< naveEnemiga.length;i++) {
			if(this.ListaNaves[i] !=null) { // IMPORTANTE SI ES DESTRUIDO UN ASTEROIDE QUE NO LO ITERE
				if (this.Cohete.getX() < naveEnemiga[i].getX() + naveEnemiga[i].getAncho() &&
		        		this.Cohete.getX() + this.Cohete.getAncho() > naveEnemiga[i].getX() &&
		            this.Cohete.getY() < naveEnemiga[i].getY() + naveEnemiga[i].getAlto() &&
		            this.Cohete.getY() + this.Cohete.getAlto() > naveEnemiga[i].getY()) {
					this.ListaNaves[i]= null;
					this.CantidadEnemigos -=1;
					if(this.CantidadEnemigos >= 0 ) {
						this.ListaNaves[i]= new navesDestructoras(Xrand.nextInt(200,600),1,30,30,Direccionrand.nextBoolean() ? -1 : 1);
					}
			    	
		            return true; // Hay una colisión
		            
		            
		        }
				
				
			}
			
		}
        
        return false; // No hay colisión
    }
	
	public boolean hayColisionNavesEnemigas(navesDestructoras[] navesEnemigas) {
	    for (int i = 0; i < navesEnemigas.length - 1; i++) {
	        for (int j = i + 1; j < navesEnemigas.length; j++) {
	            if (navesEnemigas[i].colisionNaveEnemigaANave(navesEnemigas[j])) {
	                return true; // Se produce una colisión entre navesEnemigas[i] y navesEnemigas[j]
	            }
	        }
	    }
	    
	    return false; // No hay colisión entre ninguna pareja de naves enemigas
	}
	
	
	
	
	
	//Cuando un Asteroide toca el borde de la ventana y cambia de direccion 
	private boolean RebotarAsteroide(Asteroides miAsteroide) { // Recibe un Objeto Asteroide y lo hace rebotar
		if (miAsteroide.getX() < 0 || miAsteroide.getX() > this.entorno.ancho() - 2 * miAsteroide.getRadio()) {
	        return true;
	    }
	    return false;
		
	}
	
	//Cuando una Nave enemiga toca el borde de la ventana y cambia de direccion 
		private boolean RebotarNaveEnemiga(navesDestructoras naveEnemiga) { // Recibe un Objeto naveEnemiga y lo hace rebotar
			if(naveEnemiga != null) {
				int limiteIzquierdo = 0;
				 int limiteDerecho = this.entorno.ancho() - 2 * naveEnemiga.getAncho();

				    if (naveEnemiga.getX() < limiteIzquierdo || naveEnemiga.getX() > limiteDerecho) {
				        return true; // La nave enemiga ha tocado el borde de la ventana
				    }
				
			}
			 

			    return false; // No ha ocurrido colisión con el borde de la ventana
			
		
			
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
		if(this.ListaNaves[i] != null) {
			 this.Listaiones[i] = new Proyectil(this.ListaNaves[i].getX(), this.ListaNaves[i].getY(), 30, 30, 5,Color.BLUE);
		}
	 
	    
	}
	
	
	//Metodos especiales de movimiento de los Destructores estelares:
	
	
	//DIBUJA LA PANTALLA FINAL DEL JUEGO
	public void PantallaPerder() {
		this.entorno.dibujarImagen(gameover,400,300,0,1);
		this.entorno.cambiarFont(Font.DIALOG, 40, Color.RED);
		this.entorno.escribirTexto("PERDISTE",310,150);
		this.entorno.cambiarFont(Font.DIALOG, 30, Color.RED);
		this.entorno.cambiarFont(Font.DIALOG, 30, Color.RED);
		this.entorno.escribirTexto(" { PRESIONE ESPACIO PARA SALIR } ",140,550);
	}
	public void PantallaGanar() {
		this.entorno.dibujarImagen(winScreen,400,300,0,1);
		this.entorno.cambiarFont(Font.DIALOG, 40, Color.cyan);
		this.entorno.escribirTexto("GANASTE",310,150);
		this.entorno.cambiarFont(Font.DIALOG, 30, Color.cyan);
		this.entorno.cambiarFont(Font.DIALOG, 30, Color.cyan);
		this.entorno.escribirTexto(" { PRESIONE ESPACIO PARA SALIR } ",140,550);
	}
	
	public void PuntosTotales(int score) {
		this.entorno.cambiarFont(Font.SANS_SERIF, 30, Color.CYAN);
		this.entorno.escribirTexto("Puntos:"+ " " + score,570,590);
	}
	public void VidasTotal(int vida) {
		this.entorno.cambiarFont(Font.SANS_SERIF, 30, Color.CYAN);
		this.entorno.escribirTexto("❤:"+ " " + vida,100,590);
	}
	
	public void CantEnemigosEliminados(int cantEnemigos) {
		this.entorno.cambiarFont(Font.SANS_SERIF, 30, Color.CYAN);
		this.entorno.escribirTexto("Muertes:"+ " " + cantEnemigos,300,590);
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
