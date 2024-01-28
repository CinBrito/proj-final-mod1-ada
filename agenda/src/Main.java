import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Main {
    static String arquivo = "agenda/src/listaDeContatos.txt";
    public static void main(String[] args) {
        verMenu();
    }

    public static void verMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("##### AGENDA #####");

        System.out.println(">>>> Menu <<<<");
        System.out.println("1 - Listar Contatos");
        System.out.println("2 - Adicionar Contato");
        System.out.println("3 - Remover Contato");
        System.out.println("4 - Editar Contato");
        System.out.println("5 - Buscar Contato");
        System.out.println("6 - Sair");
        System.out.println("Digite a opção desejada: ");
        String opcaoString = scanner.nextLine();
        int opcao = Integer.parseInt(opcaoString);

        switch (opcao) {
            case 1:
                leitor(arquivo);
                break;
            case 2:
                adicionarContato();
                break;
            case 3:
                removerContato();
                break;
            case 4:
                editarContato();
                break;
            case 5:
                buscarContato();
                break;
            case 6:
                System.out.println("Agradecimentos por utilizar a agenda!");
                break;
            default:
                System.out.println("Opção Inválida");
        }

        scanner.close();
    }

    // LEITOR DE ARQUIVOS
    public static void leitor(String caminhoArquivo) {
        try {
            // Criar um FileReader para ler o arquivo
            FileReader fileReader = new FileReader(caminhoArquivo);

            // Criar um BufferedReader para ler linhas do arquivo
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // Variável para armazenar a linha lida
            String linha;

            // Ler e imprimir cada linha do arquivo
            while ((linha = bufferedReader.readLine()) != null) {
                System.out.println(linha);
            }

            // Fechar o BufferedReader e o FileReader
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            // Tratar exceções de leitura de arquivo
            e.printStackTrace();
        }
        voltarAoMenu();
    }

    public static void voltarAoMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite '1' para voltar ao menu principal ou outra tecla para encerrar o programa.");
        String num = sc.nextLine();
        if (num.equals("1")) {
            verMenu();
        } else {
            System.out.println("Agradecimentos por utilizar a agenda!");
            System.exit(0);
        }
        sc.close();
    }

    // SALVAR NOVOS DADOS NO ARQUIVO

    public static void buscarContato() {

    }


    public static void adicionarContato() {
        // Pedir informação ao usuário
        Scanner sc = new Scanner(System.in);

        //        pessoa.setId(gerarID(arquivo));
        System.out.println("Digite o id:");
        String idContatoString = sc.nextLine();
        Long idContato = null;
        try {
            idContato = Long.parseLong(idContatoString);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter a String para long. Certifique-se de que a String representa um número válido.");
        }
        System.out.println("Digite o primeiro nome:");
        String nome = sc.nextLine();
        System.out.println("Digite o sobrenome:");
        String sobreNome = sc.nextLine();

        System.out.println("Digite o id:");
        String idTelefoneString = sc.nextLine();
        Long idTelefone = null;
        try {
            idTelefone = Long.parseLong(idTelefoneString);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter a String para long. Certifique-se de que a String representa um número válido.");
        }
        System.out.println("Digite o DDD do número:");
        String ddd = sc.nextLine();
        System.out.println("Digite o número de telefone:");
        String numTelefoneString = sc.nextLine();
        Long numTelefone = null;
        try {
            numTelefone = Long.parseLong(numTelefoneString);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter a String para long. Certifique-se de que a String representa um número válido.");
        }

        Telefone telefone = new Telefone(idTelefone, ddd, numTelefone);

        List<Telefone> telefones = new ArrayList<Telefone>();

        Contato pessoa = new Contato(idContato, nome, sobreNome, telefones);

        sc.close();

        System.out.println("Contato adicionado com sucesso!");


    }

    private static long gerarID(String caminhoArquivo) {
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
            idGerada = Long.parseLong(id+1);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter a String para long. Certifique-se de que a String representa um número válido.");
        }

        return idGerada;
    }

    public static void removerContato() {

    }

    public static void editarContato() {

    }

    public boolean verificarID() {
        // Passar por lista de ids dos contatos e verificar se valor já existe nela
        return false;
    }

    public boolean verificarTelefone() {
        // Passar por lista de telefones e verificar se valor já existe nela
        return false;
    }

    public void salvarEmArquivo() {
        // Percorrer arrays criados e adicionar a arquivo txt
    }





}