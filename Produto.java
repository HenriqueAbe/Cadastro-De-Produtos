import java.io.Serial;
import java.io.Serializable;

public abstract class Produto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L; //Ajuda a evitar erros em serialização e garante compatibilidade entre versões da classe.

    private String nomeProduto;
    private String validadeProduto;
    private float precoProduto;
    private int quantidade;

    public Produto(String nomeProduto, String validadeProduto, float precoProduto, int quantidade) {
        this.nomeProduto = nomeProduto;
        this.validadeProduto = validadeProduto;
        this.precoProduto = precoProduto;
        this.quantidade = quantidade;
    }

    public String getNomeProduto() {
        return this.nomeProduto;
    }

    public String getValidadeProduto() {
        return this.validadeProduto;
    }

    public float getPrecoProduto() {
        return this.precoProduto;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public void setValidadeProduto(String validadeProduto) {
        this.validadeProduto = validadeProduto;
    }

    public void setPrecoProduto(float precoProduto) {
        this.precoProduto = precoProduto;
    }

    public abstract String exibirInformacao();

    @Override
    public String toString() {
        return "";
    }
}
