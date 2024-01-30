import java.io.IOException;

public class limpaTela {

    public static void limpaTela() {
        String os = System.getProperty("os.name").toLowerCase();

        ProcessBuilder processBuilder;
        if (os.contains("win")) {
            // Windows
            processBuilder = new ProcessBuilder("cmd", "/c", "cls");
        } else {
            // Unix/Linux/Mac
            processBuilder = new ProcessBuilder("clear");
        }

        try {
            Process process = processBuilder.inheritIO().start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        limpaTela();
        System.out.println("A tela foi limpa!");
    }
}
