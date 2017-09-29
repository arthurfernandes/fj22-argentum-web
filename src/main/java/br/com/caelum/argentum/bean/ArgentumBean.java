package br.com.caelum.argentum.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.ChartModel;

import br.com.caelum.argentum.grafico.GeradorModeloGrafico;
import br.com.caelum.argentum.indicadores.IndicadorFactory;
import br.com.caelum.argentum.modelo.Candle;
import br.com.caelum.argentum.modelo.CandleFactory;
import br.com.caelum.argentum.modelo.Negociacao;
import br.com.caelum.argentum.modelo.SerieTemporal;
import br.com.caelum.argentum.ws.ClienteWebService;

@ManagedBean
@ViewScoped
public class ArgentumBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Negociacao> negociacoes;
	private ChartModel modeloGrafico;
	private String titulo = "Indicadores";
	private String nomeMedia = "MediaMovelSimples";
	private String nomeIndicadorBase = "IndicadorFechamento";
	
	public ArgentumBean() {
		this.negociacoes = new ClienteWebService().getNegociacoes();
	}

	public List<Negociacao> getNegociacoes() {
		return this.negociacoes;
	}
	
	public ChartModel getModeloGrafico() {
		return modeloGrafico;
	}
	
	public void geraGrafico() {
		System.out.println("Plotando: " + nomeMedia + " de " + nomeIndicadorBase);
		List<Candle> candles = new CandleFactory().constroiCandles(negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);
		
		GeradorModeloGrafico geradorGrafico = 
				new GeradorModeloGrafico(serie, 3, serie.getUltimaPosicao(), this.titulo);
		
		IndicadorFactory fabrica = new IndicadorFactory(this.nomeMedia, this.nomeIndicadorBase);
		geradorGrafico.plotaIndicador(fabrica.defineIndicador());
		
		this.modeloGrafico = geradorGrafico.getModeloGrafico();
	}
	
	public String getNomeMedia() {
		return nomeMedia;
	}

	public void setNomeMedia(String nomeMedia) {
		this.nomeMedia = nomeMedia;
	}

	public String getNomeIndicadorBase() {
		return nomeIndicadorBase;
	}

	public void setNomeIndicadorBase(String nomeIndicador) {
		this.nomeIndicadorBase = nomeIndicador;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
}
