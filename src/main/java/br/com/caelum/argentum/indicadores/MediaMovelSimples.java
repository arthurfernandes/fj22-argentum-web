package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelSimples implements Indicador{
	private final int intervalo;
	private final Indicador outroIndicador;

	public MediaMovelSimples(Indicador outroIndicador, int intervalo) {
		if (intervalo < 1){
			throw new IllegalArgumentException("Não é possível criar uma média móvel com intervalo menor que 1");
		}
		
		this.outroIndicador = outroIndicador;
		this.intervalo = intervalo;
	}
	
	public double calcula(int posicao, SerieTemporal serie) {
		double soma = 0;
		
		for(int i = posicao; i > posicao - this.intervalo; i--){
			soma += outroIndicador.calcula(i, serie); 
		}
		
		return soma / this.intervalo;
	}
	
	public String getNome() {
		return "MMS - " + this.outroIndicador.getNome();
	};
}
