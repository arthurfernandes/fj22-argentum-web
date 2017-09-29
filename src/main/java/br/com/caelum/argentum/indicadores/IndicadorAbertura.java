package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class IndicadorAbertura implements Indicador{
	
	@Override
	public double calcula(int i, SerieTemporal serie) {
		return serie.getCandle(i)
				.getAbertura()
				.doubleValue();
	}
	
	@Override
	public String getNome() {
		return "Abertura";
	}
}
