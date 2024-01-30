import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Agenda agenda = new Agenda();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("##### AGENDA #####");

            System.out.println(">>>> Menu <<<<");
            System.out.println("1 - Listar Contatos");
            System.out.println("2 - Adicionar Contato");
            System.out.println("3 - Remover Contato");
            System.out.println("4 - Editar Contato");
            System.out.println("5 - Sair");
            System.out.println("Digite a opção desejada: ");
            String opcaoString = scanner.nextLine();
            int opcao = 0;
            try {
                opcao = Integer.parseInt(opcaoString);
            } catch (NumberFormatException e) {
                System.out.println("Erro ao converter a String para int. Certifique-se de que a String representa um número válido.");
            }

            // Não sei porquê ele não me deixa usar outros scanner.next senão String então desculpe pela
            // quantidade de parse :)

            switch (opcao) {
                case 1:
                    System.out.println("--------------");
                    agenda.listar();
                    break;
                case 2:
                    Contato contato = new Contato();

                    contato.setId(gerarID(arquivo));

                    System.out.println("Digite o primeiro nome:");
                    contato.setNome(scanner.nextLine());

                    System.out.println("Digite o sobrenome:");
                    contato.setSobreNome(scanner.nextLine());

                    contato.setTelefones(new ArrayList<>());

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

                    Telefone telefone = new Telefone(contato.getId(), ddd, numTelefone);
                    contato.getTelefones().add(telefone);

                    if (!agenda.verificarTelefone(numTelefone)) {
                        continue;
                    }

                    agenda.adicionarAgenda(contato);

                    agenda.adicionarNumero(contato);

                    System.out.println("Contato adicionado com sucesso!");

//                    limpaTela();
                    break;
                case 3:
                    System.out.print("ID do Contato para remover: ");
                    Long idRemover = null;
                    String idRemoverString = scanner.nextLine();
                    try {
                        idRemover = Long.parseLong(idRemoverString);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter a String para long. Certifique-se de que a String representa um número válido.");
                    }
                    agenda.removerContato(idRemover);
//                    limpaTela();
                    break;
                case 4:
                    System.out.println("Digite a ID do contato para edição: ");
                    Long idEditar = null;
                    String idEditarString = scanner.nextLine();
                    try {
                        idEditar = Long.parseLong(idEditarString);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter a String para long. Certifique-se de que a String representa um número válido.");
                    }
                    agenda.editarContato(idEditar);
//                    limpaTela();
                    break;
                case 5:
//                    limpaTela();
                    System.out.println("Agradecimentos por utilizar a agenda!");
                    break;
                default:
                    System.out.println("Opção Inválida");
            }
        }
    }

    static String arquivo = "agenda/src/listaDeContatos.txt";
    public static long gerarID(String caminhoArquivo) {
        String id = null;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                // Dividir a linha em um array usando ";" ou "/"
                String[] array = linha.split("[;/]");

                if (array.length > 0) {
                    id = array[0];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long idGerada = 0;

        try {
            idGerada = Long.parseLong(id)+1;
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter a String para long. Certifique-se de que a String representa um número válido.");
        }
        return idGerada;
    }

//    public static void limpaTela() {
//        String os = System.getProperty("os.name").toLowerCase();
//
//        ProcessBuilder processBuilder;
//        if (os.contains("win")) {
//            // Windows
//            processBuilder = new ProcessBuilder("cmd", "/c", "cls");
//        } else {
//            // Unix/Linux/Mac
//            processBuilder = new ProcessBuilder("clear");
//        }
//
//        try {
//            Process process = processBuilder.inheritIO().start();
//            process.waitFor();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}