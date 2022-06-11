package model.interfaces;

/*
 * Interface funcional que ir� servir para ordenar quem executa primeiro, de acordo com a regra
 * definida de cada escalonamento preemptivo. Nela h� um m�todo do tipo long.
 * */
@FunctionalInterface
public interface ModeloCalculo {
	
	public Long calculo();
}
