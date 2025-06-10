import java.io.Serial;

public class LimpezaPessoal extends Limpeza {
    @Serial
    private static final long serialVersionUID = 1L; //Ajuda a evitar erros em serialização e garante compatibilidade entre versões da classe.

    private String nivelSensibilidade;
    private String areaAplicacao;

    public LimpezaPessoal(String nome, String validade, float preco, String instrucoesDeUso,
                          String nivelSensibilidade, String areaAplicacao, int quantidade) {
        super(nome, validade, preco, instrucoesDeUso, quantidade);
        this.nivelSensibilidade = nivelSensibilidade;
        this.areaAplicacao = areaAplicacao;
    }

    public String getNivelSensibilidade() {
        return nivelSensibilidade;
    }

    public String getAreaAplicacao() {
        return areaAplicacao;
    }

    @Override
    public String exibirInformacao() {
        return String.format("Limpeza Pessoal: %s\nNível de Sensibilidade: %s\nQuantidade: %s\nÁrea de Aplicação: %s\nValidade: %s\nPreço: R$ %.2f",
                getNomeProduto(), nivelSensibilidade, getQuantidade() ,areaAplicacao, getValidadeProduto(), getPrecoProduto());
    }

    @Override
    public String toString() {
        return String.format("Limpeza Pessoal - %s   Preço: R$ %.2f",
                getNomeProduto(), getPrecoProduto());
    }
}

