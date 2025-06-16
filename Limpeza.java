import java.io.Serial;

public class Limpeza extends Produto {
    @Serial
    private static final long serialVersionUID = 1L; //Ajuda a evitar erros em serialização e garante compatibilidade entre versões da classe.

    private String instrucoesDeUso;

    public Limpeza(String nomeProduto, String validadeProduto, float precoProduto, String instrucoesDeUso, int quantidade) {
        super(nomeProduto, validadeProduto, precoProduto, quantidade);
        this.instrucoesDeUso = instrucoesDeUso;
    }

    public String getInstrucoesDeUso() {
        return instrucoesDeUso;
    }

    public void setInstrucoesDeUso(String instrucoesDeUso) {
        this.instrucoesDeUso = instrucoesDeUso;
    }

    @Override
    public String exibirInformacao() {
        return String.format("Produto de Limpeza: %s\nInstruções: %s\nQuantidade: %d\nValidade: %s\nPreço: R$ %.2f",
                getNomeProduto(), getInstrucoesDeUso(), getQuantidade(), getValidadeProduto(), getPrecoProduto());
    }
}