import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
        Scanner scanner = new Scanner(System.in);
        Contato contatoEditar = null;

        for (Contato contato : agenda) {
            if (contato.getId().equals(idEditar)) {
                contatoEditar = contato;
                break;
            }
        }

        if (contatoEditar != null) {
            System.out.println("Digite novo nome: ");
            contatoEditar.setNome(scanner.nextLine());
            System.out.println("Digite novo sobrenome: ");
            contatoEditar.setSobreNome(scanner.nextLine());

            contatoEditar.getTelefones().clear();
            System.out.println("Digite o DDD do número:");
            String ddd = scanner.nextLine();

            System.out.println("Digite o número de telefone:");
            String numTelefoneString = scanner.nextLine();
            Long numTelefone = null;
            try {
                numTelefone = Long.parseLong(numTelefoneString);
            } catch (NumberFormatException e) {
                System.out.println("Erro ao converter a String para long. Certifique-se de que a String representa um número válido.");
            }

            if (verificarTelefone(numTelefone)) {
                Telefone telefone = new Telefone(contatoEditar.getId(), ddd, numTelefone);
                contatoEditar.getTelefones().add(telefone);
                adicionarNumero(contatoEditar);
                salvarEmArquivo();
                System.out.println("Contato editado com sucesso!");
            } else {
                System.out.println("Tente alterar o contato novamente!");
                System.out.println("----------------------------------");
            }
        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    public void listar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("ID | NOME");
        for (Contato contato : agenda
             ) {
            System.out.println(contato.getId() + "  | " + contato.getNome() + " " + contato.getSobreNome());
        }
        System.out.println("--------------");

        String resposta;
        do {
            System.out.println("Deseja acessar algum contato? Digite '1' para sim ou '2' para não.");
            resposta = scanner.nextLine();
            if (resposta.equals("1")) {
                System.out.println("Informe o ID do contato a acessar: ");
                String idAcessarString = scanner.nextLine();
                Long idAcessar = null;
                try {
                    idAcessar = Long.parseLong(idAcessarString);
                } catch (NumberFormatException e) {
                    System.out.println("Erro ao converter a String para long. Certifique-se de que a String representa um número válido.");
                }
                Contato contatoAcessar = null;
                for (Contato contato : agenda) {
                    if (contato.getId().equals(idAcessar)) contatoAcessar = contato;
                }
                assert contatoAcessar != null;
                System.out.println("------------------------");
                System.out.println("ID: " + contatoAcessar.getId() + "\n" +
                        "Nome: " + contatoAcessar.getNome() + " " + contatoAcessar.getSobreNome() + "\n" +
                        "Telefones: " + contatoAcessar.getTelefones());
                System.out.println("------------------------");
            }
        } while (!resposta.equals("2"));
    }

//    public boolean verificarID(Long id) {
//        for (Contato contato : agenda
//             ) if (contato.getId().equals(id)) {
//                System.out.println("ID já exsitente. Operação cancelada.");
//                return false;
//        }
//        return true;
//    }

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

    public void novoNumero(Long id) {
        Scanner scanner = new Scanner(System.in);
        Contato novoContato = null;
        for (Contato contato : agenda) {
            if (contato.getId().equals(id)) novoContato = contato;
        }
        assert novoContato != null;

        for (Telefone telefone : novoContato.getTelefones()) {
            System.out.println("Digite o DDD: ");
            String novoDDD = scanner.nextLine();
            telefone.setDdd(novoDDD);
            System.out.println("Digite o telefone: ");
            String novoNumTelefoneString = scanner.nextLine();
            Long novoNumTelefone = null;
            try {
                novoNumTelefone = Long.parseLong(novoNumTelefoneString);
            } catch (NumberFormatException e) {
                System.out.println("Erro ao converter a String para long. Certifique-se de que a String representa um número válido.");
            }
            if (verificarTelefone(novoNumTelefone)) {
                break;
            }
            telefone.setNumero(novoNumTelefone);
        }
        salvarEmArquivo();
    }

    public void adicionarNumero (Contato contato){
       Scanner scanner = new Scanner(System.in);
        String resposta;
        do {
            System.out.println("Deseja adicionar mais um número para esse contato? Digite '1' para sim ou '2' para não.");
            resposta = scanner.nextLine();
            if (resposta.equals("1")) {
                novoNumero(contato.getId());
            }
        } while (!resposta.equals("2"));
    }
}

