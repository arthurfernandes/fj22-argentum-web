package br.com.caelum.argentum.indicadores;

import java.lang.reflect.Constructor;

public class IndicadorFactory {
	private final String nomeIndicadorBase;
	private final String nomeMedia;

	public IndicadorFactory(String nomeMedia, String nomeIndicadorBase) {
		this.nomeMedia = nomeMedia;
		this.nomeIndicadorBase = nomeIndicadorBase;
	}
	
	public Indicador defineIndicador() {
		try {
			String pacote = "br.com.caelum.argentum.indicadores.";
			Class<?> classeIndicadorBase = Class.forName(pacote + this.nomeIndicadorBase);
			Indicador indicadorBase = (Indicador) classeIndicadorBase.newInstance();
			
			Class<?> classeMedia = Class.forName(pacote + this.nomeMedia);
			Constructor<?> construtorMedia = classeMedia.getConstructor(Indicador.class, int.class);
			Indicador indicador = (Indicador) construtorMedia.newInstance(indicadorBase, 3);
			
			return indicador;
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}
