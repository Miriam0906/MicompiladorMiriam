package edu.itsco;

public class SemanticException extends Exception{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public enum Tipo {
		VARIABLE_NO_DEFINIDA,VARIABLE_NO_INICIALIZADA,
		VARIABLE_DUPLICADA,TIPOS_NO_COINCIDEN
	}
	public SemanticException() {
		super();
	}
	
	//Atrapalos errores de las variables 
	public SemanticException(Variable variable, Tipo tipo){
		 super(obtenerMensaje(variable,tipo));
	}
	
	public static String obtenerMensaje(Variable variable, Tipo tipo){
		 String mensaje ="";
		 switch (tipo){
		 
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
