package itesm.mx.ariqmov;

import java.util.Random;

import org.cocos2d.nodes.CCLabel;
/**
 * 
 * @author 
 *
 *Clase que representa una operación matemática 
 *
 */
public class Ecuacion {

	CCLabel operacion;
	Random random = new Random();
	private int verificacion;
	private String ecuacion;
	int dificultad;
	private int primerNumero=0;
	private int segundoNumero=0;
	private int dificultadKinder = 0;
	private int dificultadPrimaria = 1;
	private int dificutladSecundaria = 2;
	int resultado = 0;
	private String signo = "";
	/**
	 * Constructor clase Ecuacion para crear ecuaciones correctas e incorrectas
	 * @param dificultad
	 */
	public Ecuacion(int dificultad) {
		verificacion = random.nextInt(2);
		ecuacion = calcularEcuacion(dificultad);
		operacion = CCLabel.makeLabel(ecuacion, "Arial", 30);
	}
	/**
	 * Constructor clase Ecuacion para crear sólo ecuaciones correctas 
	 * @param dificultad
	 * @param atrapa
	 */
	public Ecuacion(int dificultad, boolean atrapa) {
		verificacion = 1;
		ecuacion = calcularEcuacion(dificultad);
		operacion = CCLabel.makeLabel(ecuacion, "Arial", 30);
	}
	// regresa si la operacion es correcta
	/**
	 * Regresa si la operación es correcta
	 * @return valor que indica si es correcta la operacion
	 */
	public int getVerificacion() {
		return verificacion;
	}
	
	//regresa la operacion
	/**
	 * 
	 * @return ecuacion
	 */
	
	public String getEcuacion() {
		return ecuacion;
	}
	/**
	 * 
	 * @return regresa el label que contiene la operacion completa
	 */
	public CCLabel label() {
		return operacion;

	}
	/**
	 * Crea una operación dependiendo de la dificutlad que reciba 
	 * @param dificultad
	 * @return el String de la operación
	 */
	private String calcularEcuacion(int dificultad) {
		//String signo = "";
		

		if (verificacion == 1) {

			if (dificultad == dificultadKinder) {
				primerNumero = random.nextInt(10);
				segundoNumero = random.nextInt(10);
				int operacion = random.nextInt(2);
				
				switch (operacion) {
				case 0:
					if (primerNumero<segundoNumero){
						int copiaPrimerValor = primerNumero;
						primerNumero=segundoNumero;
						segundoNumero=copiaPrimerValor;
					}
					signo = "-";
					resultado = primerNumero - segundoNumero;
					break;
				case 1:
					signo = "+";
					resultado = primerNumero + segundoNumero;
					break;
				}
			}

			else if (dificultad == dificultadPrimaria) {
				primerNumero = random.nextInt(25);
				
				int operacion = random.nextInt(3);
				switch (operacion) {
				case 0:
					signo = "x";
					if (primerNumero<=10){
						segundoNumero = random.nextInt(5);
					}
					segundoNumero = random.nextInt(10);
					resultado = primerNumero * segundoNumero;
					break;
				case 1:  
					segundoNumero = random.nextInt(25);
					signo = "+";
					resultado = primerNumero + segundoNumero;
					break;
				case 2:
					segundoNumero = random.nextInt(25); 
					if (primerNumero<segundoNumero){
						
						int copiaPrimerValor = primerNumero;
						primerNumero=segundoNumero;
						segundoNumero=copiaPrimerValor;
					}
					signo = "-";
					resultado = primerNumero - segundoNumero;
					break;
				}

			} else {
				primerNumero = random.nextInt(50);
				
				int operacion = random.nextInt(4);
				switch (operacion) {
				case 0:
					signo = "x";
					primerNumero = random.nextInt(10);
					if (primerNumero>5){
						segundoNumero = random.nextInt(5);
					}else{
						segundoNumero =random.nextInt(10);
					}
					
					
					resultado = primerNumero * segundoNumero;
					break;
				case 1:
					segundoNumero =random.nextInt(25);
					signo = "+";
					resultado = primerNumero + segundoNumero;
					break;
				case 2:
					segundoNumero =random.nextInt(50);
					signo = "-";
					resultado = primerNumero - segundoNumero;
					break;
					
				case 3:
					segundoNumero =2;
					signo ="/";
					while(primerNumero%segundoNumero !=0){
						segundoNumero++;
						
					}
					resultado = primerNumero / segundoNumero;
					
					
				}
			}
		}

		else {
			if (dificultad == dificultadKinder) {
				primerNumero = random.nextInt(10);
				segundoNumero = random.nextInt(10);
				int operacion = random.nextInt(2);
				switch (operacion) {
				case 0:
					signo = "-";
					resultado = random.nextInt(50);
					if (resultado==(primerNumero-segundoNumero)){
						int mitadResultado= resultado/2;
						resultado=resultado-mitadResultado;
						
						
					}
					break;
				case 1:
					signo = "+";
					resultado = random.nextInt(50);
					
					if (resultado==(primerNumero+segundoNumero)){
						int mitadResultado= resultado/2;
						resultado=resultado-mitadResultado;	
						
					}
					break;
				}
			}

			else if (dificultad == dificultadPrimaria) {
				primerNumero = random.nextInt(50);
				segundoNumero = random.nextInt(50);
				int operacion = random.nextInt(3);
				switch (operacion) {
				case 0:
					signo = "x";
					resultado = random.nextInt(50);
					if (resultado==(primerNumero*segundoNumero)){
						int mitadResultado= resultado/2;
						resultado=resultado-mitadResultado;
						
						
					}
					break;
				case 1:
					signo = "+";
					resultado = random.nextInt(50);
					if (resultado==(primerNumero+segundoNumero)){
						int mitadResultado= resultado/2;
						resultado=resultado-mitadResultado;
						
						
					}
					break;
				case 2:
					signo = "-";
					resultado = random.nextInt(50);
					if (resultado==(primerNumero-segundoNumero)){
						int mitadResultado= resultado/2;
						resultado=resultado-mitadResultado;
						
						
					}
					break;
				}

			} else {
				primerNumero = random.nextInt(50);
				segundoNumero = random.nextInt(50);
				int operacion = random.nextInt(4);
				switch (operacion) {
				case 0:
					signo = "x";
					resultado = random.nextInt(50);
					if (resultado==(primerNumero*segundoNumero)){
						int mitadResultado= resultado/2;
						resultado=resultado-mitadResultado;
						
						
					}
					break;
				case 1:
					signo = "+";
					resultado = random.nextInt(50);
					if (resultado==(primerNumero+segundoNumero)){
						int mitadResultado= resultado/2;
						resultado=resultado-mitadResultado;
						
						
					}
					break;
				case 2:
					signo = "-";
					resultado = random.nextInt(50);
					if (resultado==(primerNumero-segundoNumero)){
						int mitadResultado= resultado/2;
						resultado=resultado-mitadResultado;	
						
					}
					break;
				case 3:
					signo ="/";
					if (resultado==(primerNumero/segundoNumero)){
						int mitadResultado= resultado/2;
						resultado=resultado-mitadResultado;
					}
					break;
					
				}
			}
		}

		return primerNumero + signo + segundoNumero + "=" + resultado;

	}
	
	
	/**
	 * Regresa el primer número antes del operador de la operacion
	 * @return entero de regresa primerNumero
	 */
	public int getPrimerNumero() {

		return this.primerNumero;
	}

	/**
	 * Regresa el primer número después del operador de la operacion
	 * @return entero de Segundo Numero
	 */
	public int getSegundoNumero() {

		return this.segundoNumero;
	}
	
	/**
	 * Regresa el resultado de la operacion
	 * @return Resultado
	 */
	public int getResultado() {

		return this.resultado;
	}
	
	public String getSigno() {

		return this.signo + "";
	}

}
