import java.io.Serial;

public class Alimenticio extends Produto{
    @Serial
    private static final long serialVersionUID = 1L; //Ajuda a evitar erros em serialização e garante compatibilidade entre versões da classe.

    private String ingrediente;

    public Alimenticio(String nomeProduto, String validadeProduto, float precoProduto, int quantidade, String ingrediente) {
        super(nomeProduto, validadeProduto, precoProduto, quantidade);
        this.ingrediente = ingrediente;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    @Override
    public String exibirInformacao() {
        return String.format("Alimento: %s\nIngrediente: %s\nQuantidade: %d\nValidade: %s\nPreço: R$ %.2f",
                getNomeProduto(), ingrediente, getQuantidade(), getValidadeProduto(), getPrecoProduto());
    }
}
