package br.com.caelum.argentum.indicadores;

import static org.junit.Assert.*;
import org.junit.Test;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelPonderadaTest {

	@Test
	public void sequenciaSimplesDeCandlesIntervalo3() {
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 5, 6);
		
		MediaMovelPonderada mmp = new MediaMovelPonderada(new IndicadorFechamento(), 3);
		
		//ex: calcula(2): 1*1 + 2*2 + 3*3 = 14, divide por 6 => 14/6
		assertEquals(14.0/6, mmp.calcula(2, serie), 0.00001);
		assertEquals(20.0/6, mmp.calcula(3, serie), 0.00001);
		assertEquals(26.0/6, mmp.calcula(4, serie), 0.00001);
		assertEquals(32.0/6, mmp.calcula(5, serie), 0.00001);
	}
	
	@Test
	public void sequenciaSimplesDeCandlesIntervalo2() {
		SerieTemporal serie = GeradorDeSerie.criaSerie(1, 2, 3, 4, 5, 6);
		
		MediaMovelPonderada mmp = new MediaMovelPonderada(new IndicadorFechamento(), 2);
		
		//ex: calcula(2): 1*1 + 2*2 + 3*3 = 14, divide por 6 => 14/6
		assertEquals(5.0/3, mmp.calcula(1, serie), 0.00001);
		assertEquals(8.0/3, mmp.calcula(2, serie), 0.00001);
		assertEquals(11.0/3, mmp.calcula(3, serie), 0.00001);
		assertEquals(14.0/3, mmp.calcula(4, serie), 0.00001);
		assertEquals(17.0/3, mmp.calcula(5, serie), 0.00001);
	}

}
