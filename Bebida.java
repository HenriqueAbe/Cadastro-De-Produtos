public class Bebida extends Alimenticio{
    private float volume;
    private String tipoBebida;

    public Bebida(String nome, String validade, float preco, String ingrediente, int quantidade, float volume, String tipoBebida) {
        super(nome, validade, preco, ingrediente, quantidade);
        this.volume = volume;
        this.tipoBebida = tipoBebida;
    }

    @Override
    public void exibirInformacao() {
        System.out.print("Bebida: " + getNomeProduto() + ", ");
        super.exibirInformacao();
        System.out.print(", Volume: " + volume + "L, Tipo: " + tipoBebida);
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
}
