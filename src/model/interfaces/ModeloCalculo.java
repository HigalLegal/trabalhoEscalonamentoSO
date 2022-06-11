package model.interfaces;

/*
 * Interface funcional que irá servir para ordenar quem executa primeiro, de acordo com a regra
 * definida de cada escalonamento preemptivo. Nela há um método do tipo long.
 * */
@FunctionalInterface
public interface ModeloCalculo {
	
	public Long calculo();
}
