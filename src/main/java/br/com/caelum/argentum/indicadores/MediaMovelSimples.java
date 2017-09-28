package br.com.caelum.argentum.indicadores;

import java.math.BigDecimal;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelSimples {
	public double calcula(int posicao, SerieTemporal serie) {
		BigDecimal soma = BigDecimal.ZERO;
		
		for(int i = posicao; i > posicao - 3; i--){
			soma = soma.add(serie.getCandle(i).getFechamento());
		}
		
		return soma.doubleValue() / 3;
	}
}
