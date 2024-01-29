import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Agenda agenda = new Agenda();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

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
            opcao = Integer.parseInt(opcaoString);

            switch (opcao) {
                case 1:
                    agenda.listar();
                    break;
                case 2:
                    Contato contato = new Contato();

                    //        contato.setId(gerarID(arquivo));
                    System.out.println("Digite o id:");
                    String idString = scanner.nextLine();
                    Long id = null;
                    try {
                        id = Long.parseLong(idString);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter a String para long. Certifique-se de que a String representa um número válido.");
                    }
                    contato.setId(id);

                    if (!agenda.verificarID(id)) {
                        continue;
                    }

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

                    Telefone telefone = new Telefone(id, ddd, numTelefone);
                    contato.getTelefones().add(telefone);

                    if (!agenda.verificarTelefone(numTelefone)) {
                        continue;
                    }

                    agenda.adicionarAgenda(contato);

//                    System.out.println(contato);

                    System.out.println("Contato adicionado com sucesso!");
                    break;
                case 3:
                    System.out.print("ID do Contato para remover: ");
                    Long idRemover = scanner.nextLong();
                    agenda.removerContato(idRemover);
                    break;
                case 4:
                    System.out.println("Digite a ID do contato para edição: ");
                    Long idEditar = scanner.nextLong();
                    agenda.editarContato(idEditar);
                    break;
                case 5:
                    System.out.println("Agradecimentos por utilizar a agenda!");
                    break;
                default:
                    System.out.println("Opção Inválida");
            }
        }
    }
}