package model.facades;

import java.util.List;
import java.util.stream.Collectors;

import model.entities.Processo;
import static model.factories.ListaDeProcessosFactory.listaPreenchida;

/*
 * Classe criada para ter todos os métodos de execução de escalonamento, para que
 * o método main não tenha várias linhas de código. Representa o modo 1 de execução.
 * */
public class FacadeModo1 {

	private final static int MULTIPLICADOR_TEMPO = 20;
	
	private final static int QUANTUM = 3;
	
	// ----------------------------------------------------------------------------------------------
	
	/*
	 * Método auxiliar, para da um "start" em todos os processos.
	 * Usado em todos os métodos com o escalonamento preemptivo.
	 * */
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

	/*
	 * Método auxiliar, para "zerar" o tempo de rajada de cada processo, usando o "do while"
	 * e o foreach lambda que a cada execução tira 3 do tempo rajada. O do while só irá parar
	 * quando o tempo de rajada de todos os processos da lista estiverem zerados.
	 * Usado pelo método que representa o round-robin.
	 * */
	private void esvaziarTempoDeRajada(List<Processo> processos) {
		int contador = 0;
		
		System.out.println("Antes da primeira execução:\n");
		processos.forEach((var processo) -> {
			System.out.println(processo);
		});

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
	
	/*
	 * Esse método representa a execução por tempo de chegada (FCFS). Para ordenar é usado o
	 * método sort, que recebe uma expressão lambda com dois objetos do tipo da lista
	 * e ordena de acordo com os atributos do objeto. Assim é feito com todos os
	 * métodos que não são preemptivos, exceto o Round-Robin.
	 * */
	public void tempoDeChegada() {

		List<Processo> processos = listaPreenchida();

		processos.sort((var p1,  var p2) -> p1.getTempoDeChegada().compareTo(p2.getTempoDeChegada()));

		processos.forEach(System.out::println);
	}

	// ----------------------------------------------------------------------------------------------

	/*
	 * Método que representa a execução por menor tempo (SRTF), usando uma lambda para ordenar
	 * a lista de processos de acordo com o atributo referente ao tempo de rajada (do menor
	 * para o maior).
	 * */
	public void menorTempoPrimeiro() {

		List<Processo> processos = listaPreenchida();

		processos.sort((var p1, var p2) -> p1.getTempoRajada().compareTo(p2.getTempoRajada()));

		processos.forEach(System.out::println);

	}

	// ----------------------------------------------------------------------------------------------

	/*
	 * Método que representa a execução por prioridade (PRIO), usando uma lambda para ordenar
	 * a lista de processos de acordo com o atributo referente a prioridade de cada processo
	 * (do menor para o maior).
	 * */
	public void prioridade() {
		List<Processo> processos = listaPreenchida();

		processos.sort((p1, p2) -> p1.getPrioridade().compareTo(p2.getPrioridade()));

		processos.forEach(System.out::println);
	}

	// ----------------------------------------------------------------------------------------------

	/*
	 * Método referente ao Round Robin (RR). Faz uso do método auxiliar "esvaziarTempoDeRajada"
	 * para zerar o tempo de cada processo.
	 * */
	public void roundRoubin() {
		List<Processo> processos = listaPreenchida();

		esvaziarTempoDeRajada(processos);

		System.out.println("RESULTADO: \n");
		processos.forEach(System.out::println);
	}

	// ----------------------------------------------------------------------------------------------

	/*
	 * Método referente ao tempo de chegada preemptivo (SRTFP). Através do foreach lambda, "define"
	 * para todos os processos a "regra" de quem irá executar primeiro.
	 * */
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

	/*
	 * Método referente a prioridade preemptiva (PRIOP). Semelhante ao SRTFP, o que vai mudar é apenas
	 * a "regra" de quem executa primeiro, que é feita através do foreach lambda também.
	 * */
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
