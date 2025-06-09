public class Alimenticio extends Produto{
    private String ingrediente;
    private int quantidade;

    public Alimenticio(String nomeProduto, String validadeProduto, float precoProduto, String ingrediente, int quantidade) {
        super(nomeProduto, validadeProduto, precoProduto);
        this.ingrediente = ingrediente;
        this.quantidade = quantidade;
    }

    @Override
    public void exibirInformacao() {
        System.out.print(", Ingrediente: " + ingrediente + ", Quantidade: " + quantidade);
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
