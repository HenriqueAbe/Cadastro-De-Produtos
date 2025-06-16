import java.io.*;
import java.util.ArrayList;

public class GerenciadorProduto {
    private ArrayList<Produto> produtos; // 10. Coleção de objeto
    private static final String ARQUIVO = "produtos.txt";  // continua .txt

    public GerenciadorProduto() {
        this.produtos = carregarProdutos();
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto p) {
        produtos.add(p);
        salvarProdutos();
    }

    public void removerProduto(Produto p) {
        produtos.remove(p);
        salvarProdutos();
    }

    // Serializa toda a lista dentro de "produtos.txt" public para outras classes conseguirem usar | 14. Persistência de objetos.
    public void salvarProdutos() {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(produtos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Produto> carregarProdutos() {
        File f = new File(ARQUIVO);
        if (!f.exists()) {
            return new ArrayList<>();  // sem arquivo, lista vazia
        }

        //13. Leitura de arquivo
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (ArrayList<Produto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar produtos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
