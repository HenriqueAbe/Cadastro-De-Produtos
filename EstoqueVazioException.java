public class EstoqueVazioException extends Exception {
    public EstoqueVazioException(String nomeProduto) {
        super("Estoque zerado para o produto: " + nomeProduto);
    }
}