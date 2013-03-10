package itesm.mx.ariqmov;

import itesm.mx.ariqmov.Music.Musica;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MenuCompleta extends Activity {

	int tipoJuego =2;
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.completa);
			
	}
    public void atenderBoton(View v){
    	
    	switch (v.getId()){
    	
    	case R.id.completa_btnLevel1:
    		Intent firstLevel = new Intent(this,PantallaJuego.class);
    		firstLevel.putExtra("tipoJuego", tipoJuego);
    		firstLevel.putExtra("Dificultad", 0);
    		Music.playEffect();
			//startActivity(firstLevel);
			startActivityForResult(firstLevel, 100);
			break;
    	case R.id.completa_btnLevel2:
    		Intent secondLevel = new Intent(this,PantallaJuego.class);
    		secondLevel.putExtra("tipoJuego", tipoJuego);
    		secondLevel.putExtra("Dificultad", 1);
    		Music.playEffect();
			//startActivity(secondLevel);
    		startActivityForResult(secondLevel, 100);
			break;
    	case R.id.completa_btnLevel3:
    		Intent thirdLevel = new Intent(this,PantallaJuego.class);
    		thirdLevel.putExtra("tipoJuego", tipoJuego);
    		thirdLevel.putExtra("Dificultad", 2);
    		Music.playEffect();
			//startActivity(thirdLevel);
    		startActivityForResult(thirdLevel, 100);
			break;
    	}
    }
    
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	// TODO Auto-generated method stub
    	//super.onActivityResult(requestCode, resultCode, data);
    	//int m = data.getIntExtra(name, defaultValue);
    	Log.d("XXXXX","OnActivityResult"+requestCode);
    	
    	Intent puntaje = new Intent(getBaseContext(),Puntajes.class);
    	//CCDirector.sharedDirector().end();
    	/*puntaje.putExtra("marcador", valorPuntaje);
		puntaje.putExtra("tipoMiniJuego",tipoMiniJuego);
		puntaje.putExtra("dificultad",dificultad);*/
		startActivity(puntaje);		
		//this.finish();
    }
    protected void onResume() {
    	super.onResume();
    	Music.playMusic();
    	}
    
    @Override
    protected void onPause() {
    	super.onPause();
    	Music.soundTransition(Musica.PAUSED);

    }
    
   
}
