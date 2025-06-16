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

        //Adicionar Quantidade
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

                        if (selecionado instanceof Alimenticio alimento) {
                            alimento.setQuantidade(alimento.getQuantidade() + qtdAdicionar);
                        } else {
                            Limpeza limpeza = (Limpeza) selecionado;
                            limpeza.setQuantidade(limpeza.getQuantidade() + qtdAdicionar);
                        }

                        gerenciador.salvarProdutos();

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

        //Remover quantidade
        removerButton.addActionListener(e -> {
            Produto selecionado = tela.getProdutoJList().getSelectedValue();
            if (selecionado == null) return;

            if (selecionado instanceof Alimenticio || selecionado instanceof Limpeza) {
                String input = JOptionPane.showInputDialog(tela, "Quantidade a remover:", "Remover Quantidade", JOptionPane.PLAIN_MESSAGE);
                if (input != null) {
                    try {
                        int qtdRemover = Integer.parseInt(input);
                        if (qtdRemover <= 0) {
                            JOptionPane.showMessageDialog(tela, "A quantidade deve ser maior que zero.");
                            return;
                        }

                        int quantidadeAtual;

                        if (selecionado instanceof Alimenticio alimento) {
                            quantidadeAtual = alimento.getQuantidade();

                            if (qtdRemover > quantidadeAtual) {
                                JOptionPane.showMessageDialog(tela, "Quantidade a remover maior que a disponível.");
                                return;
                            }

                            int novaQuantidade = quantidadeAtual - qtdRemover;

                            if (novaQuantidade == 0) {
                                throw new EstoqueVazioException(alimento.getNomeProduto());
                            }

                            alimento.setQuantidade(novaQuantidade);

                        } else {
                            Limpeza limpeza = (Limpeza) selecionado;
                            quantidadeAtual = limpeza.getQuantidade();

                            if (qtdRemover > quantidadeAtual) {
                                JOptionPane.showMessageDialog(tela, "Quantidade a remover maior que a disponível.");
                                return;
                            }

                            int novaQuantidade = quantidadeAtual - qtdRemover;

                            if (novaQuantidade == 0) {
                                throw new EstoqueVazioException(limpeza.getNomeProduto());
                            }

                            limpeza.setQuantidade(novaQuantidade);
                        }

                        gerenciador.salvarProdutos(); // salva após alteração
                        tela.getProdutoJList().repaint();
                        tela.getInfoArea().setText(selecionado.exibirInformacao());

                    } catch (EstoqueVazioException ex) {
                        JOptionPane.showMessageDialog(tela, ex.getMessage());
                        gerenciador.removerProduto(selecionado);
                        tela.getModeloLista().removeElement(selecionado);
                        tela.getInfoArea().setText("");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(tela, "Entrada inválida.");
                    }
                }
            } else {
                // Produto sem quantidade (por exemplo, não Alimenticio ou Limpeza)
                gerenciador.removerProduto(selecionado);
                tela.getModeloLista().removeElement(selecionado);
                tela.getInfoArea().setText("");
            }
        });

        return botoesPanel;
    }

    private void acaoAjuda(ActionEvent e) {
        JOptionPane.showMessageDialog(tela,
                """
                        Como usar o Gerenciador de Produtos ?
                        [ Para adicionar novos produtos ], basta preencher os dados que estão em campo ao lado.
                        [ Para produtos existentes ], selecione o produto e clique em adicionar ou remover.""");
    }

    private void acaoAdicionar(ActionEvent e) {
        Produto p = gerenciadorCampos.criarProdutoAPartirCampos(tela.getTipoProdutoCombo().getSelectedItem().toString());
        if (p != null) {
            if (p.getQuantidade() <= 0) {
                JOptionPane.showMessageDialog(tela, "Quantidade não pode ser negativa", "Error", JOptionPane.PLAIN_MESSAGE);
            }
            gerenciador.adicionarProduto(p);
            tela.getModeloLista().addElement(p);
            gerenciadorCampos.limparCampos();
            tela.getInfoArea().setText("");
        }
    }
}