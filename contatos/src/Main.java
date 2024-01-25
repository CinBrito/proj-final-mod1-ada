import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int opcao = 0;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("##### AGENDA #####");
            System.out.println(">>>> Menu <<<<");
            System.out.println("1 - Adicionar Contato");
            System.out.println("2 - Remover Contato");
            System.out.println("3 - Editar Contato");
            System.out.println("4 - Sair");
            System.out.println("Digite a opção desejada: ");
            opcao = scanner.nextInt();
        } while (opcao != 4);

        if (opcao == 1) {
            adicionarContato();
        } else if (opcao == 2) {
            removerContato();
        } else if (opcao == 3) {
            editarContato();
        } else if (opcao == 4) {
            System.out.println("Agradecimentos por utilizar a agenda!");
        } else {
            System.out.println("Opção Inválida");
        }
    }

    public static void obterContato() {

    }

    public static void adicionarContato() {

    }

    public static void removerContato() {

    }

    public static void editarContato() {

    }
}