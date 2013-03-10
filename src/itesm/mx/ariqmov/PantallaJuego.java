package itesm.mx.ariqmov;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
/**
 * Clase que administra los Juegos 
 * 
 * */

public class PantallaJuego extends Activity 
{
	// Declarar un glSurfaceView
	private CCGLSurfaceView glSurfaceView;
	private int dificultad;
	private int indicadorMinijuego;
	
	
	public PantallaJuego(){
	
	}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_pantalla_juego);
        // 2. Crear el objeto surfaceView
        this.glSurfaceView = new CCGLSurfaceView(this);
        setContentView(this.glSurfaceView);
        this.dificultad =getIntent().getExtras().getInt("Dificultad");
        this.indicadorMinijuego= getIntent().getIntExtra("tipoJuego", 0);

    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	CCDirector.sharedDirector().attachInView(glSurfaceView);
    	CCDirector.sharedDirector().setDisplayFPS(true);
    	CCDirector.sharedDirector().setAnimationInterval(1.0f/60.0f);
    	CCScene escena = null;
    	//switch para verifcar qu� minijuego 
    	switch (indicadorMinijuego) {


    		//caso juego atrapa 
			case 1:
    			// llamar la pantalla para calibrar y continuar
    			 escena = JuegoAtrapaOperacion.scene(dificultad,this);
				break;    			
    		//caso juego completa 
			case 2:
    			escena = JuegoCompletaOperacion.scene(dificultad,this);
				break; 
    	
			default:
				break;
			}
    	
    	CCDirector.sharedDirector().runWithScene(escena);
    }
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	CCDirector.sharedDirector().pause();
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	CCDirector.sharedDirector().resume();
    }
    
    @Override
    protected void onStop() {
    	// TODO Auto-generated method stub
    	super.onStop();
    	CCDirector.sharedDirector().end();
    	Log.d("**********", "onStop");
    	Intent in = new Intent();
		in.putExtra("marcador", 2500);
		//Log.d("INTENT",in.toString());
		setResult(RESULT_OK, in);
    }
    	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_jugar, menu);
        return true;
    }
    
    
    public  void terminarJuego(int marcador[]){
    	int valorPuntaje=marcador[0];
    	int tipoMiniJuego= marcador[1];
    	int dificultad= marcador[2];
    	cambiarPantalla(valorPuntaje,tipoMiniJuego,dificultad);
    	CCDirector.sharedDirector().pause();
    	CCDirector.sharedDirector().end();
    	this.finish();
    	Log.d("XXXXX","Finish");

    
    }
    
    public void cambiarPantalla(int valorPuntaje, int tipoMiniJuego, int dificultad){
    	
		Intent puntaje = new Intent(PantallaJuego.this,Puntajes.class);
    	CCDirector.sharedDirector().end();
    	puntaje.putExtra("marcador", valorPuntaje);
		puntaje.putExtra("tipoMiniJuego",tipoMiniJuego);
		puntaje.putExtra("dificultad",dificultad);
		startActivity(puntaje);
		this.finish();   	
    }

    
}
