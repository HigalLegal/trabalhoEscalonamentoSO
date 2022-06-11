package model.facades;

import java.util.List;
import java.util.stream.Collectors;

import model.entities.Processo;
import static model.factories.ListaDeProcessosFactory.listaPreenchida;

public class FacadeModo1 {

	private final static int MULTIPLICADOR_TEMPO = 20;
	
	private final static int QUANTUM = 3;
	
	// ----------------------------------------------------------------------------------------------

	private void daStart(List<Processo> processos) {

		try {
			processos.get(0).start();
			processos.get(1).start();
			processos.get(2).start();
			processos.get(3).start();
			processos.get(4).start();
			processos.get(5).start();
			processos.get(6).start();
			processos.get(7).start();
			processos.get(8).start();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	
	// ----------------------------------------------------------------------------------------------

	private void esvaziarTempoDeRajada(List<Processo> processos) {
		int contador = 0;

		do {
			processos.forEach((var processo) -> {
				if (processo.getTempoRajada() >= QUANTUM) {
					processo.setTempoRajada(processo.getTempoRajada() - QUANTUM);
				} else if(processo.getTempoRajada() < QUANTUM || processo.getTempoRajada() > 0) {
					processo.setTempoRajada(processo.getTempoRajada() - processo.getTempoRajada());
				}
			});

			List<Integer> tempoPorProcesso = processos.stream().map((var processo) -> processo.getTempoRajada())
					.collect(Collectors.toList());
			contador = 0;

			for (int i : tempoPorProcesso) {
				if (i > 0) {
					contador++;
				}
			}

			System.out.println("PROCESSOS:\n ");

			processos.forEach(System.out::println);

			System.out.println();

		} while (contador > 0);
	}
	
	// ----------------------------------------------------------------------------------------------
	
	public void tempoDeChegada() {

		List<Processo> processos = listaPreenchida();

		processos.sort((var p1,  var p2) -> p1.getTempoDeChegada().compareTo(p2.getTempoDeChegada()));

		processos.forEach(System.out::println);
	}

	// ----------------------------------------------------------------------------------------------

	public void menorTempoPrimeiro() {

		List<Processo> processos = listaPreenchida();

		processos.sort((var p1, var p2) -> p1.getTempoRajada().compareTo(p2.getTempoRajada()));

		processos.forEach(System.out::println);

	}

	// ----------------------------------------------------------------------------------------------

	public void prioridade() {
		List<Processo> processos = listaPreenchida();

		processos.sort((p1, p2) -> p1.getPrioridade().compareTo(p2.getPrioridade()));

		processos.forEach(System.out::println);
	}

	// ----------------------------------------------------------------------------------------------

	public void roundRoubin() {
		List<Processo> processos = listaPreenchida();

		esvaziarTempoDeRajada(processos);

		System.out.println("RESULTADO: \n");
		processos.forEach(System.out::println);
	}

	// ----------------------------------------------------------------------------------------------

	public void tempoDeChegadaPremptivo() {

		List<Processo> processos = listaPreenchida();

		processos.forEach((var parametro) -> {
			parametro.setModeloCalculo(() -> {
				return Long.valueOf(parametro.getTempoRajada() * MULTIPLICADOR_TEMPO);
			});
		});

		daStart(processos);

	}

	// ----------------------------------------------------------------------------------------------

	public void prioridadePreemptivo() {
		List<Processo> processos = listaPreenchida();

		processos.forEach((var parametro) -> {
			parametro.setModeloCalculo(() -> {
				return Long.valueOf(parametro.getPrioridade() * MULTIPLICADOR_TEMPO);
			});
		});

		daStart(processos);

	}
	
	
}
