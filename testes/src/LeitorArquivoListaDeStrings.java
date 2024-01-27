import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeitorArquivoListaDeStrings {

    public static void main(String[] args) {
        // Caminho do arquivo a ser lido
        String caminhoArquivo = "caminho/do/seu/arquivo.txt";

        // Lista para armazenar listas de strings
        List<List<String>> listaDeListas = new ArrayList<>();

        try {
            // Criar um FileReader para ler o arquivo
            FileReader fileReader = new FileReader(caminhoArquivo);

            // Criar um BufferedReader para ler linhas do arquivo
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Variável para armazenar a linha lida
            String linha;

            // Ler cada linha do arquivo
            while ((linha = bufferedReader.readLine()) != null) {
                // Dividir a linha em uma lista de strings usando ";" como separador
                List<String> listaDeStrings = Arrays.asList(linha.split(";"));

                // Adicionar a lista de strings à lista principal
                listaDeListas.add(listaDeStrings);
            }

            // Fechar o BufferedReader e o FileReader
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            // Tratar exceções de leitura de arquivo
            e.printStackTrace();
        }

        // Imprimir as listas de strings obtidas do arquivo
        for (List<String> lista : listaDeListas) {
            System.out.println(lista);
        }
    }
}
