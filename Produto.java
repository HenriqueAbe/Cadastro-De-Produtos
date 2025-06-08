import java.io.Serializable;

public abstract class Produto implements Serializable {
    private String nomeProduto;
    private String validadeProduto;
    private float precoProduto;

    public Produto(String nomeProduto, String validadeProduto, float precoProduto) {
        this.nomeProduto = nomeProduto;
        this.validadeProduto = validadeProduto;
        this.precoProduto = precoProduto;
    }

    abstract void exibirInformacao();

    public String getNomeProduto() {
        return this.nomeProduto;
    }

    public String getValidadeProduto() {
        return this.validadeProduto;
    }

    public float getPrecoProduto() {
        return this.precoProduto;
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
}
