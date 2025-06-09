public class Comida extends Alimenticio{
    private float peso;
    private int calorias;

    public Comida(String nome, String validade, float preco, String ingrediente, int quantidade, float peso, int calorias) {
        super(nome, validade, preco, ingrediente, quantidade);
        this.peso = peso;
        this.calorias = calorias;
    }

    @Override
    public void exibirInformacao() {
        System.out.print("Comida: " + getNomeProduto() + ", ");
        super.exibirInformacao();
        System.out.print(", Peso: " + peso + "g, Calorias: " + calorias);
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
}
