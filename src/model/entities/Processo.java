package model.entities;

import model.interfaces.ModeloCalculo;

public class Processo extends Thread {

	private Integer codigo;
	private Integer tempoDeChegada;
	private Integer tempoRajada;
	private Integer prioridade;

	private ModeloCalculo modeloCalculo;

	private final long TEMPO_INICIO = System.currentTimeMillis();

	// ----------------------------------------------

	public Processo() {
	}

	public Processo(String codigo, Integer tempoDeChegada, String tempoRajada, Integer prioridade) {
		this.codigo = Integer.parseInt(codigo.substring(1));
		this.tempoDeChegada = tempoDeChegada;
		this.tempoRajada = Integer.parseInt(tempoRajada);
		this.prioridade = prioridade;
	}

	// ----------------------------------------------

	public Integer getCodigo() {
		return codigo;
	}

	public void setId(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getTempoDeChegada() {
		return tempoDeChegada;
	}

	public void setTempoDeChegada(Integer tempoDeChegada) {
		this.tempoDeChegada = tempoDeChegada;
	}

	public Integer getTempoRajada() {
		return tempoRajada;
	}

	public void setTempoRajada(Integer tempoRajada) {
		this.tempoRajada = tempoRajada;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public void setModeloCalculo(ModeloCalculo modeloCalculo) {
		this.modeloCalculo = modeloCalculo;
	}

	// ----------------------------------------------

	@Override
	public String toString() {
		return "Id: " + codigo + " || Tempo de chegada: " + tempoDeChegada + " || Tempo de Rajada: " + tempoRajada
				+ " || Prioridade: " + prioridade + " || Tempo de processamento: "
				+ (System.currentTimeMillis() - TEMPO_INICIO);
	}

	@Override
	public void run() {

		long tempo = 0;

		if (modeloCalculo != null) {
			tempo = modeloCalculo.calculo();
		}

		try {
			Thread.sleep(tempo);
			System.out.println(this);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
