import java.io.Serial;

public class Bebida extends Alimenticio{
    @Serial
    private static final long serialVersionUID = 1L; //Ajuda a evitar erros em serialização e garante compatibilidade entre versões da classe.

    private float volume;
    private String tipoBebida;

    public Bebida(String nome, String validade, float preco, String ingrediente, int quantidade, float volume, String tipoBebida) {
        super(nome, validade, preco, quantidade, ingrediente);
        this.volume = volume;
        this.tipoBebida = tipoBebida;
    }

    public float getVolume() {
        return volume;
    }

    public String getTipoBebida() {
        return tipoBebida;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setTipoBebida(String tipoBebida) {
        this.tipoBebida = tipoBebida;
    }

    @Override
    public String exibirInformacao() {
        return super.exibirInformacao() + String.format("\nVolume: %.2fL\nTipo: %s", volume, tipoBebida);
    }

    @Override
    public String toString() {
        return String.format("Bebida - %s   Preço: R$ %.2f",
                getNomeProduto(), getPrecoProduto());
    }
}
