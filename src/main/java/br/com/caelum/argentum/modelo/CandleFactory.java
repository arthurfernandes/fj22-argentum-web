package br.com.caelum.argentum.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CandleFactory {

	public List<Candle> constroiCandles(List<Negociacao> negociacoes){
		if (negociacoes == null)
			throw new IllegalArgumentException("Negociacoes nao pode ser null");
		if (negociacoes.isEmpty())
			return new ArrayList<>();
		
		negociacoes.sort((a, b) -> a.isMesmoDia(b.getData()) ? 0 :
			a.getData().compareTo(b.getData()));
		
		List<Candle> candles = new ArrayList<>();
		List<Negociacao> negociacoesDoDia = new ArrayList<>();
		Calendar diaCorrente = negociacoes.get(0).getData();
		
		for (Negociacao n : negociacoes){
			if (n.isMesmoDia(diaCorrente)){
				negociacoesDoDia.add(n);
			}
			else{
				criaEGuardaCandle(candles, negociacoesDoDia, diaCorrente);
				diaCorrente = n.getData();
				negociacoesDoDia = new ArrayList<>();
				negociacoesDoDia.add(n);
			}
		}
		if (!negociacoesDoDia.isEmpty()){
			criaEGuardaCandle(candles, negociacoesDoDia, diaCorrente);
		}
		
		return candles;
	}

	private void criaEGuardaCandle(List<Candle> candles,
			List<Negociacao> negociacoesDoDia, Calendar diaCorrente) {
		Candle candleDoDia = constroiCandleParaData(diaCorrente, negociacoesDoDia);
		candles.add(candleDoDia);
	}
	
	public Candle constroiCandleParaData(Calendar data, List<Negociacao> negociacoes) {
		if (negociacoes.isEmpty()){
			return CandleBuilder.getIdentity(data);
		}
		
		BigDecimal abertura = negociacoes.get(0).getPreco();
		BigDecimal fechamento = negociacoes.get(negociacoes.size() - 1).getPreco();
		
		BigDecimal minimo = negociacoes
				.stream()
				.map( n -> n.getPreco())
				.reduce(
					abertura,
					(a,b) -> a.min(b));
		
		BigDecimal maximo = negociacoes
				.stream()
				.map( n -> n.getPreco())
				.reduce(
					abertura,
					(a,b) -> a.max(b));
		
		BigDecimal volume = negociacoes
				.stream()
				.map ( n -> n.getVolume())
				.reduce(
					BigDecimal.ZERO,
					(a,b) -> a.add(b));

		return new CandleBuilder().comAbertura(abertura).comFechamento(fechamento)
				.comMinimo(minimo).comMaximo(maximo).comVolume(volume).comData(data)
				.geraCandle();
	}
}
