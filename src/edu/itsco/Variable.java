package edu.itsco;

import java.util.Objects;

public class Variable {

	private String id;
	private String tipoDato;
	private boolean estaInicializada;
	
	public Variable(){}
	public Variable(String id, String tipoDato) 
	{
		this.id=id;
		this.tipoDato= tipoDato;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipoDato() {
		return tipoDato;
	}

	public void setTipoDato(String tipoDato) {
		this.tipoDato = tipoDato;
	}

	public boolean isEstaInicializada() {
		return estaInicializada;
	}

	public void setEstaInicializada(boolean estaInicializada) {
		this.estaInicializada = estaInicializada;
	}
	
	@Override
	public boolean equals(Object obj) 	{
		if(obj instanceof Variable) {
			Variable v = (Variable) obj;
			return v.hashCode() == this.hashCode();
		}
		return false;
	}
	
	@Override
	public int hashCode() 	{
		int hash= 8;
		return hash * 54 + Objects.hashCode(this.id);
	}
	
	
	
}
