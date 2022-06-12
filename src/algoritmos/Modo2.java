package algoritmos;

import java.math.BigDecimal;

import model.facades.FacadeModo2;

public class Modo2 {

	public static void main(String[] args) {
		FacadeModo2 facade = new FacadeModo2();
		
		Long tempoDeProcessamento = facade.tempoTotalDeProcessamento();
		BigDecimal tempoDeRetornoMedia = facade.calculoTempoMedioDoRetorno();
		BigDecimal tempoDeEsperaMedia = facade.calculoTempoMedioDeEspera();
		Integer trocaDeContextoPrioridade = facade.quantidadeTrocaDeContextosPorPrioridadePreemptiva();
		Integer trocaDeContextoChegada = facade.quantidadeTrocaDeContextosPorTempoDeChegadaPreemptiva();
		
		System.out.println("Tempo de processamento: " + tempoDeProcessamento);
		System.out.println("Tempo médio de retorno: " + tempoDeRetornoMedia);
		System.out.println("Tempo médio de espera: " + tempoDeEsperaMedia);
		System.out.println("Troca de contextos (prioridade preemptiva): " + trocaDeContextoPrioridade);
		System.out.println("Troca de contextos (chegada preemptiva): " + trocaDeContextoChegada);
		
	}

}
