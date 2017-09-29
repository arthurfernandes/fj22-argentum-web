package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class IndicadorFechamento implements Indicador {
	
	@Override
	public double calcula(int i, SerieTemporal serie) {
		return serie.getCandle(i)
				.getFechamento()
				.doubleValue();
	}
	
	@Override
	public String getNome() {
		return "Fechamento";
	}
}
