import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Agenda {
    private List<Contato> agenda;
    String arquivo = "agenda/src/listaDeContatos.txt";

    public Agenda() {
        agenda = new ArrayList<Contato>();
        carregarContatos(arquivo);
    }

    // LEITOR DE ARQUIVOS
    public void carregarContatos(String caminhoArquivo) {
        try {
            // Criar um FileReader para ler o arquivo
            FileReader fileReader = new FileReader(caminhoArquivo);

            // Criar um BufferedReader para ler linhas do arquivo
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Variável para armazenar a linha lida
            String linha;

            // Ler e imprimir cada linha do arquivo
            while ((linha = bufferedReader.readLine()) != null) {
                Contato contato = ConversorLinhaPessoa.criarContato(linha);
                agenda.add(contato);
            }

            // Fechar o BufferedReader e o FileReader
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            // Tratar exceções de leitura de arquivo
            e.printStackTrace();
        }
    }

    private long gerarID(String caminhoArquivo) {
        String id = null;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                // Dividir a linha em um array usando ";" ou "/"
                String[] array = linha.split("[;/]");

                // Atribuir o primeiro item do array à variável
                if (array.length > 0) {
                    id = array[0];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long idGerada = 0;

        try {
            idGerada = Long.parseLong(id + 1);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter a String para long. Certifique-se de que a String representa um número válido.");
        }

        return idGerada;
    }

    // MÉTODO PARA SALVAR CONTATO EM ARQUIVO
    private void salvarEmArquivo() {
        try (PrintWriter out = new PrintWriter(new FileWriter(arquivo, false))) {
            for (Contato contato : agenda) {
                StringBuilder linha = new StringBuilder();
                linha.append(contato.getId()).append(";").append(contato.getNome()).append(";").append(contato.getSobreNome()).append("/");
                for (Telefone telefone : contato.getTelefones()) {
                    linha.append(telefone.getDdd()).append(";").append(telefone.getNumero()).append("/");
                }
                out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar contatos: " + e.getMessage());
        }
    }

    public void adicionarAgenda(Contato contato) {
        agenda.add(contato);
        salvarEmArquivo();
    }

    public void removerContato(Long id) {
        Contato contatoARemover = null;
        for (Contato contato : agenda) {
            if (contato.getId().equals(id)) {
                contatoARemover = contato;
                break;
            }
        }
        if (contatoARemover != null) {
            agenda.remove(contatoARemover);
            salvarEmArquivo();
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    public void editarContato(Long idEditar) {
    }

    public void listar() {
        for (Contato contato : agenda
             ) {
            System.out.println(contato);
        }
    }

    public boolean verificarID(Long id) {
        for (Contato contato : agenda
             ) if (contato.getId().equals(id)) {
            System.out.println("ID já exsitente. Operação cancelada.");
            return false;
        }
        return true;
    }

//    public boolean verificarTelefone(Long numTelefone) {
//        Long numExistente = null;
//        for (Contato contato : agenda
//        ) contato.getTelefones();
//
//
//
//        if (numTelefone.equals(numExistente)) {
//            return false;
//        }
//        return true;
//    }
}
