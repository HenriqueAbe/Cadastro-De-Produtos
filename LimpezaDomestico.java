import java.io.Serial;

public class LimpezaDomestico extends Limpeza {
    @Serial
    private static final long serialVersionUID = 1L; //Ajuda a evitar erros em serialização e garante compatibilidade entre versões da classe.

    private String precaucaoUso;
    private String superficieRecomendada;

    public LimpezaDomestico(String nome, String validade, float preco, String instrucoesDeUso, String precaucaoUso, String superficieRecomendada, int quantidade) {
        super(nome, validade, preco, instrucoesDeUso, quantidade);  // Passando quantidade para a superclasse
        this.precaucaoUso = precaucaoUso;
        this.superficieRecomendada = superficieRecomendada;
    }

    public String getPrecaucaoUso() {
        return precaucaoUso;
    }

    public String getSuperficieRecomendada() {
        return superficieRecomendada;
    }

    @Override
    public String exibirInformacao() {
        return String.format("Limpeza Doméstica: %s\nPrecaução: %s\nQuantidade: %s\nSuperfície Recomendada: %s\nValidade: %s\nPreço: R$ %.2f",
                getNomeProduto(), precaucaoUso,getQuantidade(), superficieRecomendada, getValidadeProduto(), getPrecoProduto());
    }

    @Override
    public String toString() {
        return String.format("Limpeza Doméstico - %s   Preço: R$ %.2f",
                getNomeProduto(), getPrecoProduto());
    }
}
