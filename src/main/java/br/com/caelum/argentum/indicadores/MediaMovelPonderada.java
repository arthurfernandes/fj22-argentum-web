package br.com.caelum.argentum.indicadores;

import br.com.caelum.argentum.modelo.SerieTemporal;

public class MediaMovelPonderada implements Indicador{
	
	private final int intervalo;
	private final Indicador outroIndicador;

	
	public MediaMovelPonderada(Indicador outroIndicador, int intervalo) {
		if (intervalo < 1){
			throw new IllegalArgumentException("Não é possível criar uma média móvel com intervalo menor que 1");
		}
		
		this.outroIndicador = outroIndicador;
		this.intervalo = intervalo;
	}
	
	public double calcula(int posicao, SerieTemporal serie) {
		
		double soma = 0;
		int peso = this.intervalo;
		int somaPeso = 0;
		
		for(int i = posicao; i > posicao - this.intervalo; i--) {
			soma += outroIndicador.calcula(i, serie) * peso;
			somaPeso += peso;
			peso  -= 1;
		}
		
		return soma / somaPeso;
	}

	public String getNome() {
		return "MMP - " + this.outroIndicador.getNome();
	};
}
