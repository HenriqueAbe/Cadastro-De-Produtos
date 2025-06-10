import java.io.Serial;

public class Comida extends Alimenticio{
    @Serial
    private static final long serialVersionUID = 1L; //Ajuda a evitar erros em serialização e garante compatibilidade entre versões da classe.

    private float peso;
    private int calorias;

    public Comida(String nome, String validade, float preco, String ingrediente, int quantidade, float peso, int calorias) {
        super(nome, validade, preco, quantidade, ingrediente);
        this.peso = peso;
        this.calorias = calorias;
    }

    public float getPeso() {
        return peso;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    @Override
    public String exibirInformacao() {
        return super.exibirInformacao() + String.format("\nPeso: %.2fg\nCalorias: %d cal", peso, calorias);
    }

    @Override
    public String toString() {
        return String.format("Comida - %s   Preço: R$ %.2f",
                getNomeProduto(), getPrecoProduto());
    }
}
