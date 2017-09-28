package br.com.caelum.argentum.indicadores;

import java.math.BigDecimal;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelPonderada {

	public double calcula(int posicao, SerieTemporal serie) {
		BigDecimal soma = BigDecimal.ZERO;
		BigDecimal peso = new BigDecimal("3");
		
		for(int i = posicao; i > posicao - 3; i--) {
			soma = soma.add(serie.getCandle(i).getFechamento().multiply(peso));
			peso = peso.subtract(new BigDecimal("1"));
		}
		
		return soma.doubleValue()/6;
	}
}
