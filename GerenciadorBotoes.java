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

        //Adiciona Quantidade
        addQuantidadeButton.addActionListener(e -> {
            Produto selecionado = tela.getProdutoJList().getSelectedValue();
            if (selecionado == null) return;

            if (selecionado instanceof Alimenticio || selecionado instanceof Limpeza) {
                String input = JOptionPane.showInputDialog(tela, "Quantidade a adicionar:", "Adicionar Quantidade", JOptionPane.PLAIN_MESSAGE);
                if (input != null) {
                    try {
                        int qtdAdicionar = Integer.parseInt(input);
                        if (qtdAdicionar <= 0) {
                            JOptionPane.showMessageDialog(tela, "A quantidade deve ser maior que zero.");
                            return;
                        }

                        if (selecionado instanceof Alimenticio) {
                            Alimenticio alimento = (Alimenticio) selecionado;
                            alimento.setQuantidade(alimento.getQuantidade() + qtdAdicionar);
                        } else if (selecionado instanceof Limpeza) {
                            Limpeza limpeza = (Limpeza) selecionado;
                            limpeza.setQuantidade(limpeza.getQuantidade() + qtdAdicionar);
                        }

                        tela.getProdutoJList().repaint();
                        tela.getInfoArea().setText(selecionado.exibirInformacao());

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(tela, "Entrada inválida.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(tela, "Esse tipo de produto não possui quantidade para ser adicionada.");
            }
        });

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