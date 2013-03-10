package itesm.mx.ariqmov;

import itesm.mx.ariqmov.Music.Musica;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


/**
 * 
 * @author Amilcar Andrade García
 * @Version 1.0.0
 * @since 1.0.0
 * Pantalla que despliega el xml de opening por 2 segundos
 *
 */
public class TecLogo extends Activity {	
	public void onCreate(Bundle savedInstanceState) { 
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening);  
        Music.soundInit(this);
        Music.soundEffectInit(this);
        Music.soundTransition(Musica.PLAYED);
        Thread splashLogo = new Thread(){
        	public void run(){
        		
        		int wait = 0;
        		try{
        			
        			while (wait < 1300){
        				sleep(100);
        				wait = wait + 100;
        			}
        		}catch (InterruptedException e){
        	} finally { 
        		finish();
        		
        		Intent intentoMenu = new Intent();
        		intentoMenu.setClassName("itesm.mx.ariqmov","itesm.mx.ariqmov.Menu");
        		startActivity(intentoMenu);
        	}
        }
    };
    splashLogo.start();
    }
 
	
}
