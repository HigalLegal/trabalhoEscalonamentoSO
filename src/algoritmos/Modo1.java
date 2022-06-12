package algoritmos;

import java.util.Scanner;

import model.facades.FacadeModo1;

public class Modo1 {

	public static void main(String[] args) {

		var scan = new Scanner(System.in);

		FacadeModo1 facade = new FacadeModo1();
		
		boolean continuarWhile = true;
		
		while(continuarWhile) {
			
			System.out.println("DIGITE ALGUM NÚMERO PARA EXECUTAR ALGUM ALGORITMO");
			System.out.println("1 PARA EXECUTAR FCFS (Primeiro que chega  ́e o primeiro atendido).");
			System.out.println("2 PARA EXECUTAR O SRTF (Menor tempo restante  ́e o primeiro).");
			System.out.println("3 PARA EXECUTAR O ROUND-ROUBIN.");
			System.out.println("4 PARA EXECUTAR O SRTF PREEMPTIVO.");
			System.out.println("5 PARA EXECUTAR O PRIO (por prioridade).");
			System.out.println("6 PARA EXECUTAR O PRIO PREEMPTIVO");
			System.out.println("DIGITE QUALQUER OUTRO NÚMERO PARA ENCERRAR O PROGRAMA.");

			int numeroEscolhido = scan.nextInt();
			
			
			switch (numeroEscolhido) {

			case 1:
				facade.tempoDeChegada();
				System.out.println();
				break;

			case 2:
				facade.menorTempoPrimeiro();
				System.out.println();
				break;

			case 3:
				facade.roundRoubin();
				System.out.println();
				break;

			case 4:
				facade.tempoDeChegadaPremptivo();
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println();
				break;

			case 5:
				facade.prioridade();
				System.out.println();
				break;

			case 6:
				facade.prioridadePreemptivo();
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println();
				break;

			default:
				System.out.println("Programa encerrado.");
				continuarWhile = false;
				break;

			}
		}
		
		scan.close();
	}
}
//https://sistemas-operacionais.github.io/process/scheduler.pdf