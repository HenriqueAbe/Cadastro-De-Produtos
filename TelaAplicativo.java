import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TelaAplicativo extends JFrame {
    private JComboBox<String> tipoProdutoCombo;
    private JPanel painelCampos;
    private DefaultListModel<Produto> modeloLista;
    private JList<Produto> produtoJList;
    private JTextArea infoArea;
    private GerenciadorProduto gerenciador; //9. Relação de associação
    private GerenciadorCampos gerenciadorCampos; //9. Relação de associação
    private GerenciadorBotoes gerenciadorBotoes; //9. Relação de associação

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
        //Atualiza campos de acordo com o tipo selecionado
        tipoProdutoCombo.addActionListener(e -> gerenciadorCampos.atualizarCampos(painelCampos, (String) tipoProdutoCombo.getSelectedItem()));

        //Quando selecionado aparece suas informações na direita
        produtoJList.addListSelectionListener(e -> {
            Produto p = produtoJList.getSelectedValue();
            if (p != null) {
                infoArea.setText(p.exibirInformacao()); //8. Chamada Polimórfica
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

    //Para poder adicionar ou remover items
    public JList<Produto> getProdutoJList() {
        return produtoJList;
    }

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