import java.util.ArrayList;
import java.util.List;

public class Contato {
    private Long id;
    private String nome;
    private String sobreNome;
    private List<Telefone> telefones;


    // MÉTODOS GET
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }


    // MÉTODOS SET
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public void setId(Long id) {
        this.id = id;
        // Chama a função gerarID que percorre a lista em arquivo.
        // Verifica valor de id do último contato ao ler o primeiro item da última linha
        // Soma +1 a esse valor e retorna um long com valor de novo ID
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }


    @Override
    public String toString() {
        return id + " | " + nome + " " + sobreNome + " | " + telefones;
    }
}


