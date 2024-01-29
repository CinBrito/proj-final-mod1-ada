import java.util.ArrayList;

public class ConversorLinhaPessoa {

    public static Contato criarContato(String linha) {
        String[] objetos = linha.split("/");

        Contato contato = new Contato();
        contato.setTelefones(new ArrayList<>());

        for (int i = 0; i< objetos.length; i++) {
            String[] valores = objetos[i].split(";");
            Long idArquivo = null;
            if (i == 0) {
                idArquivo = Long.parseLong(valores[0]);
                contato.setId(idArquivo);
                contato.setNome(valores[1]);
                contato.setSobreNome(valores[2]);
            } else {
                String ddd = valores[0];
                Long numTelefone = Long.parseLong(valores[1]);
                Telefone telefone = new Telefone(idArquivo, ddd, numTelefone);
                contato.getTelefones().add(telefone);
            }
        }
        return contato;
    }
}
