package br.com.caelum.argentum.indicadores;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.SerieTemporal;


public class GeradorDeSerie {
	/*
	 * Serve para ajudar a fazer os testes
	 * 
	 * Recebe uma sequência de valores e cria candles com abertura, fechamento,
	 * minimo e maximo iguais, mil de volume e data de hoje. Finalmente, devolve
	 * tais candles encapusaladas em uma Serie Temporal
	 */
	public static SerieTemporal criaSerie(double... valores) {
		List<Candle> candles = new ArrayList<>();
		
		for (double d : valores) {
			BigDecimal bd = new BigDecimal(d);
			candles.add(new Candle(bd, bd, bd, bd, new BigDecimal(1000),
					Calendar.getInstance()));
		}
		
		return new SerieTemporal(candles);
 	}
}
