import javax.swing.*;
import java.awt.event.ActionEvent;

public class GerenciadorBotoes {
    private TelaAplicativo tela;
    private GerenciadorProduto gerenciador;
    private GerenciadorCampos gerenciadorCampos;

    public GerenciadorBotoes(TelaAplicativo tela, GerenciadorProduto gerenciador, GerenciadorCampos gerenciadorCampos) {
        this.tela = tela;
        this.gerenciador = gerenciador;
        this.gerenciadorCampos = gerenciadorCampos;
    }

    public JPanel criarPainelBotoes() {
        JPanel botoesPanel = new JPanel();

        JButton informacao = new JButton("Ajuda");
        JButton addButton = new JButton("Adicionar");
        JButton addQuantidadeButton = new JButton("Adicionar Quantidade");
        JButton removerButton = new JButton("Remover Quantidade");

        botoesPanel.add(informacao);
        botoesPanel.add(addButton);
        botoesPanel.add(removerButton);
        botoesPanel.add(addQuantidadeButton);

        // Configurar listeners
        informacao.addActionListener(this::acaoAjuda);
        addButton.addActionListener(this::acaoAdicionar);

        return botoesPanel;
    }

    private void acaoAjuda(ActionEvent e) {
        JOptionPane.showMessageDialog(tela,
                "Como usar o Gerenciador de Produtos ?\n" +
                        "[ Para adicionar novos produtos ], basta preencher os dados que estão em campo ao lado.\n" +
                        "[ Para produtos existentes ], selecione o produto e clique em adicionar ou remover.");
    }

    private void acaoAdicionar(ActionEvent e) {
        Produto p = gerenciadorCampos.criarProdutoAPartirCampos(tela.getTipoProdutoCombo().getSelectedItem().toString());
        if (p != null) {
            gerenciador.adicionarProduto(p);
            tela.getModeloLista().addElement(p);
            gerenciadorCampos.limparCampos();
            tela.getInfoArea().setText("");
        }
    }
}