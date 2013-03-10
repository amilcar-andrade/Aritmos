package itesm.mx.ariqmov;

import java.util.LinkedList;
import java.util.Random;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;

public class JuegoCompletaOperacion extends CCLayer 
{
	private static int dificultad=1;
	public static int numeroScore;
	private CCSprite fondo;
	// Ni�o
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

	//marcador
	private CCLabel lblMarcador;
	private int marcador;
	private CCSprite fondoMarcador;
	private CCLabel lblMarcador2;
	private int marcador2;
	private CCSprite fondoMarcador2;
	
	//Numeros de para generar ecuacion
	
	private int PrimerNumero;
	private CCLabel lblPrimerNumero;
	
	private int SegundoNumero;
	private CCLabel lblSegundoNumero;
	
	private int Signo;
	private CCLabel lblSigno;
	
	private CCLabel lblIgual;
	private CCLabel lblIncognita;
	
	private int Resultado;
	private CCLabel lblResultado;
	
	private int Opcion1;
	private CCLabel lblOpcion1;
	private boolean Opcion1Moviendo;
	private int Opcion2;
	private CCLabel lblOpcion2;
	private boolean Opcion2Moviendo;
	private int Opcion3;
	private CCLabel lblOpcion3;
	private boolean Opcion3Moviendo;
	private int Correcta;
	
	Random random = new Random();
	private int Var1;
	private int Var2;
	
	private int bandera=0;
	private Ecuacion a;
	
	private CCLabel lbTem;
	private int temporizador=45;
	private CCSprite fondoTem;
	private double tiempo2;
	
	
	
	
	public static CCScene scene(int dif,PantallaJuego pantalla) {
		dificultad = dif;
		CCScene escena = CCScene.node();
		CCLayer juego = new JuegoCompletaOperacion(pantalla);
		escena.addChild(juego);
		return escena;
	}
	
	protected JuegoCompletaOperacion(PantallaJuego pantalla) {
		
		// Dimesiones de la pantalla
		winSize = CCDirector.sharedDirector().displaySize();

		// Fondo

		this.fondo = CCSprite.sprite("blackboard.png");
		this.fondo.setPosition(winSize.width/2, winSize.height/2);
		this.addChild(this.fondo);
		
		this.setIsTouchEnabled(true);
		
		// Tiempo para dejar caer operaciones
		tiempo=0;
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
		
		this.lblPrimerNumero = CCLabel.makeLabel("?", "Arial", 80);
		this.lblPrimerNumero.setPosition(winSize.width-600,winSize.height-210);
		this.addChild(this.lblPrimerNumero);
		
		this.lblSigno = CCLabel.makeLabel("+", "Arial", 80);
		this.lblSigno.setPosition(winSize.width-500,winSize.height-210);
		this.addChild(this.lblSigno);
		
		this.lblSegundoNumero = CCLabel.makeLabel("0", "Arial", 80);
		this.lblSegundoNumero.setPosition(winSize.width-400,winSize.height-210);
		this.addChild(this.lblSegundoNumero);

		this.lblIgual = CCLabel.makeLabel("=", "Arial", 80);
		this.lblIgual.setPosition(winSize.width-300,winSize.height-210);
		this.addChild(this.lblIgual);
		
		this.lblResultado = CCLabel.makeLabel("0", "Arial", 80);
		this.lblResultado.setPosition(winSize.width-200,winSize.height-210);
		this.addChild(this.lblResultado);
		
		this.lblOpcion1 = CCLabel.makeLabel("0", "Arial", 80);
		this.lblOpcion1.setPosition(winSize.width-650,winSize.height-400);
		this.addChild(this.lblOpcion1);
		
		this.lblOpcion2 = CCLabel.makeLabel("0", "Arial", 80);
		this.lblOpcion2.setPosition(winSize.width-400,winSize.height-400);
		this.addChild(this.lblOpcion2);
		
		this.lblOpcion3 = CCLabel.makeLabel("0", "Arial", 80);
		this.lblOpcion3.setPosition(winSize.width-150,winSize.height-400);
		this.addChild(this.lblOpcion3);
		
		this.lbTem = CCLabel.makeLabel("0", "Arial", 30);
		this.lbTem.setPosition(winSize.width-420,winSize.height-140);
		this.addChild(this.lbTem);
		
		// Registrar el m�todo de actualizaci�n
		this.schedule("update");
	}
	public void update(float dt) {
	
		//Crear Operacion
		if(bandera==0){
			bandera=1;
			Var1=random.nextInt(3)+1;
			Var2=random.nextInt(2)+1;
			a= new Ecuacion(dificultad,true);
			if(Var2==1){
				this.lblPrimerNumero.setString("?");
				this.lblSigno.setString(a.getSigno());
				this.lblSegundoNumero.setString(a.getSegundoNumero()+"");
				this.lblResultado.setString(a.getResultado()+"");
				if(Var1==1){
					Opcion1=a.getPrimerNumero();
					Opcion2=random.nextInt(50);
					Opcion3=random.nextInt(50);
					Correcta=Opcion1;
					this.lblOpcion1.setString(Opcion1+"");
					this.lblOpcion2.setString(Opcion2+"");
					this.lblOpcion3.setString(Opcion3+"");
				}
				if(Var1==2){
					Opcion2=a.getPrimerNumero();
					Opcion1=random.nextInt(50);
					Opcion3=random.nextInt(50);
					Correcta=Opcion2;
					this.lblOpcion1.setString(Opcion1+"");
					this.lblOpcion2.setString(Opcion2+"");
					this.lblOpcion3.setString(Opcion3+"");
					
				}
				if(Var1==3){
					Opcion3=a.getPrimerNumero();
					Opcion1=random.nextInt(50);
					Opcion2=random.nextInt(50);
					Correcta=Opcion3;
					this.lblOpcion1.setString(Opcion1+"");
					this.lblOpcion2.setString(Opcion2+"");
					this.lblOpcion3.setString(Opcion3+"");
					
				}
			}
			if(Var2==2){
				this.lblPrimerNumero.setString(a.getPrimerNumero()+"");
				this.lblSigno.setString(a.getSigno());
				this.lblSegundoNumero.setString("?");
				this.lblResultado.setString(a.getResultado()+"");
				if(Var1==1){
					Opcion1=a.getSegundoNumero();
					Opcion2=random.nextInt(50);
					Opcion3=random.nextInt(50);
					Correcta=Opcion1;
					this.lblOpcion1.setString(Opcion1+"");
					this.lblOpcion2.setString(Opcion2+"");
					this.lblOpcion3.setString(Opcion3+"");
				}
				if(Var1==2){
					Opcion2=a.getSegundoNumero();
					Opcion1=random.nextInt(50);
					Opcion3=random.nextInt(50);
					Correcta=Opcion2;
					this.lblOpcion1.setString(Opcion1+"");
					this.lblOpcion2.setString(Opcion2+"");
					this.lblOpcion3.setString(Opcion3+"");
					
				}
				if(Var1==3){
					Opcion3=a.getSegundoNumero();
					Opcion1=random.nextInt(50);
					Opcion2=random.nextInt(50);
					Correcta=Opcion3;
					this.lblOpcion1.setString(Opcion1+"");
					this.lblOpcion2.setString(Opcion2+"");
					this.lblOpcion3.setString(Opcion3+"");
					
				}
				
			}
		}
		float xb = lblOpcion1.getPosition().x;
		float yb = lblOpcion1.getPosition().y;
		float rb = lblOpcion1.getContentSize().height/2;
		
		float xb2 = lblOpcion2.getPosition().x;
		float yb2 = lblOpcion2.getPosition().y;
		float rb2 = lblOpcion2.getContentSize().height/2;
		
		float xb3 = lblOpcion3.getPosition().x;
		float yb3 = lblOpcion3.getPosition().y;
		float rb3 = lblOpcion3.getContentSize().height/2;
		
		float xn = lblPrimerNumero.getPosition().x;
		float yn = lblPrimerNumero.getPosition().y;
		float rn = lblPrimerNumero.getContentSize().height/2;
		
		float xn2 = lblSegundoNumero.getPosition().x;
		float yn2 = lblSegundoNumero.getPosition().y;
		float rn2 = lblSegundoNumero.getContentSize().height/2;
		
		
		double distancia  = Math.sqrt((xb-xn)*(xb-xn)+(yb-yn)*(yb-yn));
		double distancia2 = Math.sqrt((xb2-xn)*(xb2-xn)+(yb2-yn)*(yb2-yn));
		double distancia3 = Math.sqrt((xb3-xn)*(xb3-xn)+(yb3-yn)*(yb3-yn));
		
		double distancia4  = Math.sqrt((xb-xn2)*(xb-xn2)+(yb-yn2)*(yb-yn2));
		double distancia5 = Math.sqrt((xb2-xn2)*(xb2-xn2)+(yb2-yn2)*(yb2-yn2));
		double distancia6 = Math.sqrt((xb3-xn2)*(xb3-xn2)+(yb3-yn2)*(yb3-yn2));
		
		if(Var2==1){
			if ( distancia < rb+rn ) {
				if(Correcta==Opcion1){
					bandera=0;
					
					this.marcador++;
					this.removeChild(a.label(), true);
				}
				else{
					this.lblOpcion1.setString("-");
					this.marcador2++;
				}
				this.lblOpcion1.setPosition(winSize.width-650,winSize.height-400);
				this.Opcion1Moviendo = false;
			}
			if ( distancia2 < rb2+rn ) {
				if(Correcta==Opcion2){
					bandera=0;
				
					this.marcador++;
					this.removeChild(a.label(), true);
				}
				else{
					this.lblOpcion2.setString("-");	
					this.marcador2++;
				}
				this.lblOpcion2.setPosition(winSize.width-400,winSize.height-400);
				this.Opcion2Moviendo = false;
			
			}
			if ( distancia3 < rb3+rn ) {
				if(Correcta==Opcion3){
					bandera=0;
					
					this.marcador++;
					this.removeChild(a.label(), true);
				}else{
					this.lblOpcion3.setString("-");
					this.marcador2++;
				}
				this.lblOpcion3.setPosition(winSize.width-150,winSize.height-400);
				this.Opcion3Moviendo = false;	
			}
			
		}
		if(Var2==2){
			if ( distancia4 < rb+rn2 ) {
				if(Correcta==Opcion1){
					bandera=0;
					
					this.marcador++;
					this.removeChild(a.label(), true);
				}
				else{
					this.lblOpcion1.setString("-");
					this.marcador2++;
				}
				this.lblOpcion1.setPosition(winSize.width-650,winSize.height-400);
				this.Opcion1Moviendo = false;
			}
			if ( distancia5 < rb2+rn2 ) {
				if(Correcta==Opcion2){
					bandera=0;
					
					this.marcador++;
					this.removeChild(a.label(), true);
				}
				else{
					this.lblOpcion2.setString("-");
					this.marcador2++;
					
				}
				this.lblOpcion2.setPosition(winSize.width-400,winSize.height-400);
				this.Opcion2Moviendo = false;
			}
			if ( distancia6 < rb3+rn2 ) {
				if(Correcta==Opcion3){
					bandera=0;
					
					this.marcador++;
					this.removeChild(a.label(), true);
				}else{
					this.lblOpcion3.setString("-");
					this.marcador2++;
				}
				this.lblOpcion3.setPosition(winSize.width-150,winSize.height-400);
				this.Opcion3Moviendo = false;
					
			}
			
		}
		
		tiempo += dt;
		tiempo2 = tiempo2+ dt;
		if ( tiempo2>=1){
			this.temporizador--;
			tiempo2=0;

			if(this.temporizador==0){
				int puntajeTotal=0;
				if(marcador2 > marcador){
					puntajeTotal=0;						
				}else{
					 puntajeTotal = marcador-marcador2;
					 puntajeTotal = puntajeTotal*100;
				}
				
				//int arreglo[]=new int[3];
				JuegoAtrapaOperacion.statusJuego[0]=puntajeTotal;
				JuegoAtrapaOperacion.statusJuego[1]=2;//indica el nivel, si es 1 es juego atrapaOperacion y si es dos es juegoCompletaOperacion
				JuegoAtrapaOperacion.statusJuego[2]=dificultad;
				unschedule("update");
				//pJuego.terminarJuego(arreglo);
				CCDirector.sharedDirector().replaceScene(JuegoTermina.scene("Ganaste/Ganaste/Pierdes..."));
				Log.d("XXXXX","Terminar");
				return;
			}
		}
			
		tiempo=0;
		//}
		this.lblMarcador.setString(this.marcador+"");
		this.lblMarcador2.setString(this.marcador2+"");
		this.lbTem.setString(this.temporizador+"");
	}

	@Override
	public boolean ccTouchesBegan(MotionEvent event) {
		
		CGPoint posicionEvento = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(), event.getY()) );
		float yEvento = posicionEvento.y;
		float xEvento = posicionEvento.x;

		if ( yEvento<this.lblOpcion1.getContentSize().height && 
				xEvento>=this.lblOpcion1.getPosition().x-this.lblOpcion1.getContentSize().width/2 && 
				xEvento<=this.lblOpcion1.getPosition().x+this.lblOpcion1.getContentSize().width/2 ) {
			this.Opcion1Moviendo = true;
		}
		if ( yEvento<this.lblOpcion2.getContentSize().height && 
				xEvento>=this.lblOpcion2.getPosition().x-this.lblOpcion2.getContentSize().width/2 && 
				xEvento<=this.lblOpcion2.getPosition().x+this.lblOpcion2.getContentSize().width/2 ) {
			this.Opcion2Moviendo = true;
		}
		if ( yEvento<this.lblOpcion3.getContentSize().height && 
				xEvento>=this.lblOpcion3.getPosition().x-this.lblOpcion3.getContentSize().width/2 && 
				xEvento<=this.lblOpcion3.getPosition().x+this.lblOpcion3.getContentSize().width/2 ) {
			this.Opcion3Moviendo = true;
		}

		return super.ccTouchesBegan(event);
	}
	
	@Override
	public boolean ccTouchesMoved(MotionEvent event) {
		CGPoint posicionEvento = CCDirector.sharedDirector().convertToGL(CGPoint.ccp(event.getX(), event.getY()) );
		float yEvento = posicionEvento.y;
		float xEvento = posicionEvento.x;
		if ( this.Opcion1Moviendo ) {
			this.lblOpcion1.setPosition(xEvento, yEvento);
		}
		if ( this.Opcion2Moviendo ) {
			this.lblOpcion2.setPosition(xEvento, yEvento);
		}
		if ( this.Opcion3Moviendo ) {
			this.lblOpcion3.setPosition(xEvento, yEvento);
		}

		return super.ccTouchesMoved(event);
	}
	
	@Override
	public boolean ccTouchesEnded(MotionEvent event) {
		this.Opcion1Moviendo = false;
		this.lblOpcion1.setPosition(winSize.width-650,winSize.height-400);
		this.Opcion2Moviendo = false;
		this.lblOpcion2.setPosition(winSize.width-400,winSize.height-400);
		this.Opcion3Moviendo = false;
		this.lblOpcion3.setPosition(winSize.width-150,winSize.height-400);
		
		return super.ccTouchesEnded(event);
	}
	
}





