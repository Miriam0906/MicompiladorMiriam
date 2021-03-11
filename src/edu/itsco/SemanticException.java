package edu.itsco;

public class SemanticException extends Exception{
	

	public static final int  VARIABLE_NO_DEFINIDA = 10;
	public static final int  VARIABLE_DUPLICADA = 20;
	public static final int  VARIABLE_NO_INICIALIZADA = 30;
	public static final int  TIPOS_NO_COINCIDEN = 40;
	
	//Atrapalos errores de las variables 
	public SemanticException(Variable variable, int tipoException){
		 super(getMessage(variable,tipoException));
	}
	
	public static String getMessage(Variable variable, int tipoException){
		 String mensaje ="";
		 switch (tipoException){
		 
		 case VARIABLE_NO_DEFINIDA:
			 mensaje = "La variable "+ variable.getId() + "nose declario";
			 break;
		 case VARIABLE_DUPLICADA:
			 mensaje = "La variable "+ variable.getId() + "ya fue definida";
			 break;
		 case VARIABLE_NO_INICIALIZADA:
			 mensaje = "La variable "+ variable.getId() + "no ha sido inicializada";
			 break;
		 case TIPOS_NO_COINCIDEN :
			 mensaje = "El tipo de dato no coincide con el de la variable"+ variable.getId();
			 break;
		 }
		 return mensaje;
	}
}
