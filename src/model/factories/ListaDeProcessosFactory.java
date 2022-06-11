package model.factories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.entities.Processo;

public class ListaDeProcessosFactory {
	
	public static List<Processo> listaPreenchida() {
		String caminho = "exemplo.tsv";

		List<Processo> processos = new ArrayList<>();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(caminho))) {

			String linha = bufferedReader.readLine();

			while (linha != null) {
				List<String> dados = Arrays.asList(linha.split("	"));

				String id = dados.get(0);
				Integer tempoDeChegada = Integer.parseInt(dados.get(1));
				String tempoRajada = dados.get(2);
				Integer prioridade = Integer.parseInt(dados.get(3));

				processos.add(new Processo(id, tempoDeChegada, tempoRajada, prioridade));

				linha = bufferedReader.readLine();
			}

			return processos;

		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
