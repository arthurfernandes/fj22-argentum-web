package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class IndicadorVolume implements Indicador{
	
	@Override
	public double calcula(int i, SerieTemporal serie) {
		return serie.getCandle(i)
				.getVolume()
				.doubleValue();
	}
	
	@Override
	public String getNome() {
		return "Volume";
	}
}
