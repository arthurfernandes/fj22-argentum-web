package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.SerieTemporal;

public interface Indicador {
	public double calcula(int pos, SerieTemporal serie);
	public String getNome();
}