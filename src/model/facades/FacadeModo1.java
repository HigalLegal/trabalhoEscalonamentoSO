package model.facades;

import java.util.List;
import java.util.stream.Collectors;

import model.entities.Processo;
import static model.factories.ListaDeProcessosFactory.listaPreenchida;

/*
 * Classe criada para ter todos os m�todos de execu��o de escalonamento, para que
 * o m�todo main n�o tenha v�rias linhas de c�digo. Representa o modo 1 de execu��o.
 * */
public class FacadeModo1 {

	private final static int MULTIPLICADOR_TEMPO = 20;
	
	private final static int QUANTUM = 3;
	
	// ----------------------------------------------------------------------------------------------
	
	/*
	 * M�todo auxiliar, para da um "start" em todos os processos.
	 * Usado em todos os m�todos com o escalonamento preemptivo.
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
	 * M�todo auxiliar, para "zerar" o tempo de rajada de cada processo, usando o "do while"
	 * e o foreach lambda que a cada execu��o tira 3 do tempo rajada. O do while s� ir� parar
	 * quando o tempo de rajada de todos os processos da lista estiverem zerados.
	 * Usado pelo m�todo que representa o round-robin.
	 * */
	private void esvaziarTempoDeRajada(List<Processo> processos) {
		int contador = 0;
		
		System.out.println("Antes da primeira execu��o:\n");
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
	 * Esse m�todo representa a execu��o por tempo de chegada (FCFS). Para ordenar � usado o
	 * m�todo sort, que recebe uma express�o lambda com dois objetos do tipo da lista
	 * e ordena de acordo com os atributos do objeto. Assim � feito com todos os
	 * m�todos que n�o s�o preemptivos, exceto o Round-Robin.
	 * */
	public void tempoDeChegada() {

		List<Processo> processos = listaPreenchida();

		processos.sort((var p1,  var p2) -> p1.getTempoDeChegada().compareTo(p2.getTempoDeChegada()));

		processos.forEach(System.out::println);
	}

	// ----------------------------------------------------------------------------------------------

	/*
	 * M�todo que representa a execu��o por menor tempo (SRTF), usando uma lambda para ordenar
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
	 * M�todo que representa a execu��o por prioridade (PRIO), usando uma lambda para ordenar
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
	 * M�todo referente ao Round Robin (RR). Faz uso do m�todo auxiliar "esvaziarTempoDeRajada"
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
	 * M�todo referente ao tempo de chegada preemptivo (SRTFP). Atrav�s do foreach lambda, "define"
	 * para todos os processos a "regra" de quem ir� executar primeiro.
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
	 * M�todo referente a prioridade preemptiva (PRIOP). Semelhante ao SRTFP, o que vai mudar � apenas
	 * a "regra" de quem executa primeiro, que � feita atrav�s do foreach lambda tamb�m.
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
