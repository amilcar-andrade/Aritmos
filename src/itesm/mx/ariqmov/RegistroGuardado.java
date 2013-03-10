package itesm.mx.ariqmov;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * 
 * @author Amilcar Andrade Garc��a
 * @Version 1.0.0
 * @since 1.0.0
 * Pantalla que despliega el xml de registro guardado, despues que se recibe una respuesta correcta del servidor.
 *
 */

public class RegistroGuardado extends Activity {	
	public void onCreate(Bundle savedInstanceState) {    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_guardado);  
        Thread splashLogo = new Thread(){
        	public void run(){
        		int wait = 0;
        		try{
        			
        			while (wait < 500){
        				sleep(100);
        				wait = wait + 100;
        			}
        		}catch (InterruptedException e){
        	} finally { 
        		finish();
        		/*
        		Intent intentoMenu = new Intent();
        		intentoMenu.setClassName("itesm.mx.ariqmov","itesm.mx.ariqmov.SeleccionaJuego");
        		startActivity(intentoMenu);*/
        	}
        }
    };
    splashLogo.start();
    }
        
}
