package model.facades;

import static model.factories.ListaDeProcessosFactory.listaPreenchida;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

import model.entities.Processo;

public class FacadeModo2 {

	private final static int NUMERO_DE_CASAS_DECIMAIS = 2;

	private Integer tempoDeRetornoTotal() {
		List<Processo> processos = listaPreenchida();
		List<Integer> tempoDeChegada = processos.stream().map((var processo) -> processo.getTempoDeChegada())
				.collect(Collectors.toList());

		tempoDeChegada.sort((t1, t2) -> t2.compareTo(t1));

		int total = 0;

		for (int i = 0; i < tempoDeChegada.size() - 1; i++) {
			total += tempoDeChegada.get(i) - tempoDeChegada.get(i + 1);
		}

		return total;

	}

	// ----------------------------------------------------------------------------------------------

	private int trocasDeContexto(List<Integer> lista) {
		int aux;
		int total = 0;

		int[] numeros = new int[lista.size()];

		for (int i = 0; i < numeros.length; i++) {
			numeros[i] = lista.get(i);
		}

		for (int i = 0; i < numeros.length; i++) {
			for (int j = 0; j < numeros.length - 1; j++) {
				if (numeros[j] > numeros[j + 1]) {
					aux = numeros[j];
					numeros[j] = numeros[j + 1];
					numeros[j + 1] = aux;
					total++;
				}
			}
		}

		return total;

	}

	// ----------------------------------------------------------------------------------------------

	public Integer tempoDeEsperaTotal() {
		List<Processo> processos = listaPreenchida();
		List<Integer> tempoDeChegada = processos.stream().map((var processo) -> processo.getTempoRajada())
				.collect(Collectors.toList());

		tempoDeChegada.sort((t1, t2) -> t2.compareTo(t1));

		int total = 0;

		for (int i = 0; i < tempoDeChegada.size() - 1; i++) {
			total += tempoDeChegada.get(i) - tempoDeChegada.get(i + 1);
		}

		return total;
	}

	// ----------------------------------------------------------------------------------------------

	public BigDecimal calculoTempoMedioDoRetorno() {

		BigDecimal tempoTotalDeRetorno = BigDecimal.valueOf(tempoDeRetornoTotal());
		BigDecimal tamanhoDaLista = BigDecimal.valueOf(listaPreenchida().size());

		return tempoTotalDeRetorno.divide(tamanhoDaLista, NUMERO_DE_CASAS_DECIMAIS, RoundingMode.HALF_UP);
	}

	// ----------------------------------------------------------------------------------------------

	public BigDecimal calculoTempoMedioDeEspera() {

		BigDecimal tempoTotalDeRetorno = BigDecimal.valueOf(tempoDeEsperaTotal());
		BigDecimal tamanhoDaLista = BigDecimal.valueOf(listaPreenchida().size());

		return tempoTotalDeRetorno.divide(tamanhoDaLista, NUMERO_DE_CASAS_DECIMAIS, RoundingMode.HALF_UP);
	}

	// ----------------------------------------------------------------------------------------------

	public Long tempoTotalDeProcessamento() {
		long startTime = System.currentTimeMillis();
		listaPreenchida();
		long totalTime = System.currentTimeMillis() - startTime;

		return totalTime;
	}

	// ----------------------------------------------------------------------------------------------

	public Integer quantidadeTrocaDeContextosPorPrioridadePreemptiva() {
		List<Processo> processos = listaPreenchida();

		List<Integer> prioridadePorProcesso = processos.stream().map((var processo) -> processo.getPrioridade())
				.collect(Collectors.toList());

		return trocasDeContexto(prioridadePorProcesso);

	}

	// ----------------------------------------------------------------------------------------------

	public Integer quantidadeTrocaDeContextosPorTempoDeChegadaPreemptiva() {
		List<Processo> processos = listaPreenchida();

		List<Integer> prioridadePorProcesso = processos.stream().map((var processo) -> processo.getTempoDeChegada())
				.collect(Collectors.toList());

		return trocasDeContexto(prioridadePorProcesso);

	}

}
