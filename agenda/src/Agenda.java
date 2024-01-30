import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
            FileReader fileReader = new FileReader(caminhoArquivo);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linha;
            // Criar contato para cada linha do arquivo
            while ((linha = bufferedReader.readLine()) != null) {
                Contato contato = ConversorLinhaPessoa.criarContato(linha);
                agenda.add(contato);
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            // Tratar exceções de leitura de arquivo (??)
            e.printStackTrace();
        }
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

    public boolean verificarTelefone(Long numTelefone) {
        for (Contato contato : agenda
        ) for (Telefone telefone : contato.getTelefones()){
            if (telefone.getNumero().equals(numTelefone)) {
                System.out.println("Telefone já exsitente. Operação cancelada.");
                return false;
            }
        }
        return true;
    }
}
