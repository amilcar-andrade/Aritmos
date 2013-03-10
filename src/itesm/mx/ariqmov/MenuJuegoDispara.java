package itesm.mx.ariqmov;

import itesm.mx.ariqmov.Music.Musica;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MenuJuegoDispara extends Activity {
	int tipoJuego =3;
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dispara);
			
	}
    public void atenderBoton(View v){
    	
    	switch (v.getId()){
    	
    	case R.id.dispara_btnLevel1:
    		Intent firstLevel = new Intent(MenuJuegoDispara.this,FirstLevel.class);
    		firstLevel.putExtra("tipoJuego", tipoJuego);
    		firstLevel.putExtra("Dificultad", 1);
    		Music.playEffect();
			startActivity(firstLevel);
			break;
    	case R.id.dispara_btnLevel2:
    		Intent secondLevel = new Intent(MenuJuegoDispara.this,SecondLevel.class);
    		secondLevel.putExtra("tipoJuego", tipoJuego);
    		secondLevel.putExtra("Dificultad", 2);
    		Music.playEffect();
			startActivity(secondLevel);
			break;
    	case R.id.dispara_btnLevel3:
    		Intent thirdLevel = new Intent(MenuJuegoDispara.this,ThirdLevel.class);
    		thirdLevel.putExtra("tipoJuego", tipoJuego);
    		thirdLevel.putExtra("Dificultad", 3);
    		Music.playEffect();
			startActivity(thirdLevel);
			break;
    	}
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
