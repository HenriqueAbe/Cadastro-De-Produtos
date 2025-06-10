import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TelaAplicativo extends JFrame {
    private JComboBox<String> tipoProdutoCombo;
    private JPanel painelCampos;
    private DefaultListModel<Produto> modeloLista;
    private JList<Produto> produtoJList;
    private JTextArea infoArea;
    private GerenciadorProduto gerenciador;
    private GerenciadorCampos gerenciadorCampos;
    private GerenciadorBotoes gerenciadorBotoes;

    public TelaAplicativo() {
        super("Gerenciador de Produtos");
        gerenciador = new GerenciadorProduto();
        gerenciadorCampos = new GerenciadorCampos();
        gerenciadorBotoes = new GerenciadorBotoes(this, gerenciador, gerenciadorCampos);

        configurarJanela();
        inicializarComponentes();
        carregarLista();
    }

    private void configurarJanela() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());
    }

    private void inicializarComponentes() {
        // Painel esquerdo
        JPanel painelEsquerdo = new JPanel(new BorderLayout());
        tipoProdutoCombo = new JComboBox<>(new String[]{"Comida", "Bebida", "Limpeza Pessoal", "Limpeza Doméstica"});
        painelEsquerdo.add(tipoProdutoCombo, BorderLayout.NORTH);

        painelCampos = new JPanel();
        painelCampos.setLayout(new BoxLayout(painelCampos, BoxLayout.Y_AXIS));
        painelEsquerdo.add(new JScrollPane(painelCampos), BorderLayout.CENTER);

        painelEsquerdo.add(gerenciadorBotoes.criarPainelBotoes(), BorderLayout.SOUTH);
        add(painelEsquerdo, BorderLayout.WEST);

        // Painel central
        modeloLista = new DefaultListModel<>();
        produtoJList = new JList<>(modeloLista);
        add(new JScrollPane(produtoJList), BorderLayout.CENTER);

        // Painel direito (informações)
        infoArea = new JTextArea();
        infoArea.setEditable(false);
        add(new JScrollPane(infoArea), BorderLayout.EAST);
        infoArea.setPreferredSize(new Dimension(300, 0));

        // Configurar listeners
        configurarListeners();
    }

    private void configurarListeners() {
        tipoProdutoCombo.addActionListener(e -> gerenciadorCampos.atualizarCampos(painelCampos, (String) tipoProdutoCombo.getSelectedItem()));

        produtoJList.addListSelectionListener(e -> {
            Produto p = produtoJList.getSelectedValue();
            if (p != null) {
                infoArea.setText(p.exibirInformacao());
            }
        });
    }

    public void carregarLista() {
        ArrayList<Produto> produtos = gerenciador.getProdutos();
        modeloLista.clear();
        for (Produto p : produtos) {
            modeloLista.addElement(p);
        }
    }

    // Getters para os componentes que precisam ser acessados por outras classes

    public DefaultListModel<Produto> getModeloLista() {
        return modeloLista;
    }

    public JTextArea getInfoArea() {
        return infoArea;
    }

    public JComboBox<String> getTipoProdutoCombo() {
        return tipoProdutoCombo;
    }
}