package itesm.mx.ariqmov;

import java.util.LinkedList;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;
import android.content.Intent;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;

import android.content.*;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Clase encargada de crear la pantalla del juego atrapa operaciones
 * @author Mario Alberto Esquivel Gonzalez
 * @Version 1.0.0
 * @since 1.0.0
 *
 */

public class JuegoAtrapaOperacion extends CCLayer 
{
	PantallaJuego pJuego = new PantallaJuego();
	
	public static int statusJuego[]=new int[3];
	
	// dificultad 
	private static int dificultad;
	public static int numeroScore;
	private CCSprite fondo;
	// Niño
	private CCSprite nino;
	private boolean ninoMoviendo;
	
	// Lanzador
	private CCSprite lanzador;
	private int incrementoXLanzador=3;
	
	private CGSize winSize;
	
	private int incrementoXOperacion=-3;
	// LISTA de ecuaciones
	private LinkedList<Ecuacion> listaEcuacion;
	
	// Timer para dejar caer operaciones
	private double tiempo;
	private double tiempo2;

	//marcador
	private CCLabel lblMarcador;
	private int marcador;
	private CCSprite fondoMarcador;
	private CCLabel lblMarcador2;
	private int marcador2;
	private CCSprite fondoMarcador2;
	
	//Temporizador
	private CCLabel lbTem;
	private int temporizador=45;
	private CCSprite fondoTem;
	private int bandera=0;
	private int fijoX;
	private PantallaJuego pantalla;
	/**
	 * Crea un la escena del juego
	 * @param dif
	 * @param pantalla
	 * @return escena
	 */
	
	public static CCScene scene(int dif, PantallaJuego pantalla) {
		dificultad = dif;
		CCScene escena = CCScene.node();
		CCLayer juego = new JuegoAtrapaOperacion(pantalla);
		escena.addChild(juego);
		return escena;
	}
	/**
	 * 
	 * @param pantalla
	 */
	
	protected JuegoAtrapaOperacion(PantallaJuego pantalla) {
		this.pantalla=pantalla;
		// Dimesiones de la pantalla
		winSize = CCDirector.sharedDirector().displaySize();
		
		// Fondo
		//this.fondo = CCSprite.sprite("blackboard.png");
		if (dificultad==0){
			this.fondo = CCSprite.sprite("blackboard.png");
		}
		
		else if (dificultad==1){
			this.fondo = CCSprite.sprite("blackboard2.png");
		}
		
		else {
			this.fondo=CCSprite.sprite("Blackboard3.png");
		}
		this.fondo.setPosition(winSize.width/2, winSize.height/2);
		this.addChild(this.fondo);
		
		// nino
		this.nino = CCSprite.sprite("duster.png");
		this.nino.setPosition(winSize.width/2, winSize.height/2);
		this.addChild(this.nino);
		
		// Lanzador
		this.lanzador = CCSprite.sprite("enemigo.png");
		this.lanzador.setPosition(winSize.width/2,3*winSize.height/2);
		this.addChild(this.lanzador);
		//AelerOmetrO
		
		this.setIsAccelerometerEnabled(true);
		// Registra los eventos de touch
		this.setIsTouchEnabled(true);
		
		// Tiempo para dejar caer operaciones
		tiempo=0;
		tiempo2=0;
		this.listaEcuacion = new LinkedList<Ecuacion>();
		
		this.fondoMarcador = CCSprite.sprite("font.jpg");
		this .fondoMarcador.setPosition(winSize.width-110,winSize.height-60);
		
		this.fondoMarcador2 = CCSprite.sprite("font2.jpg");
		this .fondoMarcador2.setPosition(winSize.width-730,winSize.height-60);
		
		this.addChild(this.fondoMarcador);
		this.lblMarcador = CCLabel.makeLabel("0", "Arial", 30);
		this.lblMarcador.setPosition(winSize.width-110,winSize.height-60);
		this.addChild(this.lblMarcador);
		
		this.addChild(this.fondoMarcador2);
		this.lblMarcador2 = CCLabel.makeLabel("0", "Arial", 30);
		this.lblMarcador2.setPosition(winSize.width-730,winSize.height-60);
		this.addChild(this.lblMarcador2);
		
		//this.addChild(this.fondoTem);
		this.lbTem = CCLabel.makeLabel("0", "Arial", 30);
		this.lbTem.setPosition(winSize.width-420,winSize.height-140);
		this.addChild(this.lbTem);
		
		// Registrar el método de actualización
		this.schedule("update");
	
	}
	//Metodo Acelerometro
	@Override
	public void ccAccelerometerChanged(float accelX,float accelY,float accelZ){
		//Log.d("ACELEROMETRO", accelX+" , "+accelY+"="+accelZ);
		if(bandera==0){
			 fijoX =2*(int) accelX; 
		}
		bandera=1;
		int incrementox = 3*(int)accelY;
		int incrementoy = 3*(int)accelX-fijoX;
		float posicionX = this.nino.getPosition().x+incrementox;
		float posicionY = this.nino.getPosition().y-incrementoy;
		if(posicionX >= winSize.width){
			posicionX=winSize.width;
		}
		if(posicionX <= 0){
			posicionX=0;
		}
		if(posicionY >= winSize.height){
			posicionY=winSize.height;
		}
		if(posicionY <= 0){
			posicionY=0;
		}
		this.nino.setPosition(posicionX ,posicionY );
	}
	/**
	 * Actualiza la pantalla 
	 * @param dt
	 */
	public void update(float dt) {
		
		this.lanzador.setPosition(this.lanzador.getPosition().x+
					this.incrementoXLanzador, this.lanzador.getPosition().y);
		
		if ( this.lanzador.getPosition().x >= (winSize.width-50) || 
				this.lanzador.getPosition().x<=50 ) {
			this.incrementoXLanzador = -this.incrementoXLanzador;
			this.lanzador.setScaleX(this.incrementoXLanzador/Math.abs(this.incrementoXLanzador));
		}
		
		// Mover la operaciones
		
		for (int i=this.listaEcuacion.size()-1; i>=0; i--) {
			Ecuacion bomba =this.listaEcuacion.get(i);
			bomba.label().setPosition(bomba.label().getPosition().x,
					bomba.label().getPosition().y+incrementoXOperacion);
			if ( bomba.label().getPosition().y<0 ) {
				this.listaEcuacion.remove(i);
				this.removeChild(bomba.label(), true);
			} else {
				// Probar la colisión con el personaje (nino)
				float xb = bomba.label().getPosition().x;
				float yb = bomba.label().getPosition().y;
				float rb = bomba.label().getContentSize().height/2;
				float xn = this.nino.getPosition().x;
				float yn = this.nino.getPosition().y;
				float rn = this.nino.getContentSize().height/2;
				double distancia = Math.sqrt((xb-xn)*(xb-xn)+(yb-yn)*(yb-yn));
				if ( distancia < rb+rn ) {
					//SoundEngine.sharedEngine().playEffect(CCDirector.sharedDirector().getActivity(), R.raw.)
					if(bomba.getVerificacion()==1){
						this.marcador++;
					}
					if(bomba.getVerificacion()==0){
						this.marcador2++;
					}
					this.listaEcuacion.remove(i);
					this.removeChild(bomba.label(), true);
					
				}
			}
		}
		
		// Manejo del tiempo
		
		tiempo += dt;
		tiempo2 = tiempo2+ dt;
		if ( tiempo2>=1){
			this.temporizador--;
			tiempo2=0;
			if(this.temporizador==0){
				//marcador = respuestas correctas
				//marcador2 = respuestas incorrectas
				int puntajeTotal=0;
				if(marcador2 > marcador){
					puntajeTotal=0;						
				}else{
					 puntajeTotal = marcador-marcador2;
					 puntajeTotal = puntajeTotal*100;
				}
				
				
				statusJuego[0]=puntajeTotal;
				statusJuego[1]=1;//indica el nivel, si es 1 es juego atrapaOperacion y si es dos es juegoCompletaOperacion
				statusJuego[2]=dificultad;
				unschedule("update");
				//pJuego.terminarJuego(arreglo);
				CCDirector.sharedDirector().replaceScene(JuegoTermina.scene("Ganaste/Ganaste/Pierdes..."));
				Log.d("XXXXX","Terminar");
				return;
			}
		}
		if ( tiempo>2 ) {
			Ecuacion a=new Ecuacion(dificultad);
			a.label().setPosition(this.lanzador.getPosition().x, 
					this.lanzador.getPosition().y);
			
			this.listaEcuacion.add(a);
			
			this.addChild(a.label());
			tiempo=0;
		}
		this.lblMarcador.setString(this.marcador+"");
		this.lblMarcador2.setString(this.marcador2+"");
		this.lbTem.setString(this.temporizador+"");
	}
	@Override
	/***
	 * detecta cuando se toca la pantalla
	 * @param event 
	 */
	public boolean ccTouchesBegan(MotionEvent event) {
		
		CGPoint posicionEvento = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(), event.getY()) );
		float yEvento = posicionEvento.y;
		float xEvento = posicionEvento.x;
		//this.nino.setPosition(xEvento, yEvento);	
		return super.ccTouchesBegan(event);
	}
	
	/**
	 * detecta cuando hay un movimiento
	 * @param event 
	 */
	
	@Override
	public boolean ccTouchesMoved(MotionEvent event) {
		float xEvento = event.getX();
		float yEvento = -event.getY()+winSize.height;
		
			this.nino.setPosition(xEvento,yEvento);
		
		return super.ccTouchesMoved(event);
	}
	
	/**
	 * detecta cuando se dejó de presionar la pantalla
	 * @param event 
	 */
	@Override
	public boolean ccTouchesEnded(MotionEvent event) {
		return super.ccTouchesEnded(event);
	}
	
}





