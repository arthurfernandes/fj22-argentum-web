package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class IndicadorMinimo implements Indicador{
	
	@Override
	public double calcula(int i, SerieTemporal serie) {
		return serie.getCandle(i)
				.getMinimo()
				.doubleValue();
	}
	
	@Override
	public String getNome() {
		return "Mínimo";
	}
}
