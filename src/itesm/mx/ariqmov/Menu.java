package itesm.mx.ariqmov;

import itesm.mx.ariqmov.Music.Musica;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;

public class Menu extends Activity {
	
	public static boolean noConexion = false;
	public static boolean actividadPreviaPuntajes = false;
	public static int statusJuego[]=new int[3];


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		ConnectivityManager conMgr = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnected() && netInfo.isAvailable()) {
			noConexion = false;
		} else {
			noConexion = true;
		}
	}
         
	public void atenderBotonMenu(View v) {
		
		
		switch (v.getId()) {
		case R.id.btnJugar:
			actividadPreviaPuntajes = false;
			if (!noConexion) {
				Intent selJuego = new Intent(Menu.this, SeleccionaJuego.class);
				Music.playEffect();
				startActivity(selJuego);
			} else {
				Intent noConexion = new Intent(Menu.this, NoConexion.class);
				Music.playEffect();
				startActivity(noConexion);
			}
			break;
		case R.id.btnScore:
			actividadPreviaPuntajes = true;
			Intent puntajes = new Intent(Menu.this, WebPuntajes.class);
			Music.playEffect();
			startActivity(puntajes);
			break;
		case R.id.btnInstrucciones:
			Intent instruc = new Intent(Menu.this, Instrucciones.class);
			Music.playEffect();
			startActivity(instruc);
			break;
		case R.id.btnConfiguraciones:
			Intent conf = new Intent(Menu.this, Ajustes.class);
			Music.playEffect();
			startActivity(conf);
			break;
		case R.id.btnAcerca:
			Intent acerca = new Intent(Menu.this, AcercaDe.class);
			Music.playEffect();
			startActivity(acerca);
			break;
		}
    }
    @Override
    protected void onResume() {
    	super.onResume();
    	Music.playMusic();
    	}
    
    @Override
    protected void onPause() {
    	super.onPause();
    	Music.soundTransition(Musica.PAUSED);

    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	Music.destruyeSonidosyEfectos();
    }
    
    
    
   
}
