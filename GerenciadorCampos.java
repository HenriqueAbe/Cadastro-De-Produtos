import javax.swing.*;
import java.awt.*;

public class GerenciadorCampos {
    // Campos comuns
    private JTextField nomeField, validadeField, precoField;

    // Campos Alimenticio
    private JTextField ingredienteField;
    private JTextField quantidadeField;

    // Campos Comida
    private JTextField pesoField;
    private JTextField caloriasField;

    // Campos Bebida
    private JTextField volumeField;
    private JTextField tipoBebidaField;

    // Campos Limpeza
    private JTextField instrucoesField;

    // Limpeza pessoal
    private JTextField sensibilidadeField;
    private JTextField areaAplicacaoField;

    // Limpeza domestico
    private JTextField precaucaoField;
    private JTextField superficieField;

    public void atualizarCampos(JPanel painelCampos, String tipo) {
        painelCampos.removeAll();

        // Campos comuns
        nomeField = new JTextField(20);
        validadeField = new JTextField(10);
        precoField = new JTextField(10);

        painelCampos.add(new JLabel("Nome:"));
        painelCampos.add(nomeField);

        painelCampos.add(new JLabel("Validade:"));
        painelCampos.add(validadeField);

        painelCampos.add(new JLabel("Preço:"));
        painelCampos.add(precoField);

        switch (tipo) {
            case "Comida":
                configurarCamposComida(painelCampos);
                break;
            case "Bebida":
                configurarCamposBebida(painelCampos);
                break;
            case "Limpeza Pessoal":
                configurarCamposLimpezaPessoal(painelCampos);
                break;
            case "Limpeza Doméstica":
                configurarCamposLimpezaDomestica(painelCampos);
                break;
        }

        painelCampos.revalidate();
        painelCampos.repaint();
    }

    private void configurarCamposComida(JPanel painelCampos) {
        ingredienteField = new JTextField(20);
        quantidadeField = new JTextField(5);
        pesoField = new JTextField(5);
        caloriasField = new JTextField(5);

        painelCampos.add(new JLabel("Ingrediente:"));
        painelCampos.add(ingredienteField);

        painelCampos.add(new JLabel("Quantidade:"));
        painelCampos.add(quantidadeField);

        painelCampos.add(new JLabel("Peso (g):"));
        painelCampos.add(pesoField);

        painelCampos.add(new JLabel("Calorias:"));
        painelCampos.add(caloriasField);
    }

    private void configurarCamposBebida(JPanel painelCampos) {
        ingredienteField = new JTextField(20);
        quantidadeField = new JTextField(5);
        volumeField = new JTextField(5);
        tipoBebidaField = new JTextField(10);

        painelCampos.add(new JLabel("Ingrediente:"));
        painelCampos.add(ingredienteField);

        painelCampos.add(new JLabel("Quantidade:"));
        painelCampos.add(quantidadeField);

        painelCampos.add(new JLabel("Volume (L):"));
        painelCampos.add(volumeField);

        painelCampos.add(new JLabel("Tipo Bebida:"));
        painelCampos.add(tipoBebidaField);
    }

    private void configurarCamposLimpezaPessoal(JPanel painelCampos) {
        instrucoesField = new JTextField(20);
        sensibilidadeField = new JTextField(10);
        areaAplicacaoField = new JTextField(15);
        quantidadeField = new JTextField(5);

        painelCampos.add(new JLabel("Quantidade:"));
        painelCampos.add(quantidadeField);

        painelCampos.add(new JLabel("Instruções de Uso:"));
        painelCampos.add(instrucoesField);

        painelCampos.add(new JLabel("Nível de Sensibilidade:"));
        painelCampos.add(sensibilidadeField);

        painelCampos.add(new JLabel("Área de Aplicação:"));
        painelCampos.add(areaAplicacaoField);
    }

    private void configurarCamposLimpezaDomestica(JPanel painelCampos) {
        instrucoesField = new JTextField(20);
        precaucaoField = new JTextField(20);
        superficieField = new JTextField(20);
        quantidadeField = new JTextField(5);

        painelCampos.add(new JLabel("Quantidade:"));
        painelCampos.add(quantidadeField);

        painelCampos.add(new JLabel("Instruções de Uso:"));
        painelCampos.add(instrucoesField);

        painelCampos.add(new JLabel("Precaução de Uso:"));
        painelCampos.add(precaucaoField);

        painelCampos.add(new JLabel("Superfície Recomendada:"));
        painelCampos.add(superficieField);
    }

    public Produto criarProdutoAPartirCampos(String tipo) {
        try {
            String nome = nomeField.getText();
            String validade = validadeField.getText();
            float preco = Float.parseFloat(precoField.getText());

            switch (tipo) {
                case "Comida":
                    return criarComida(nome, validade, preco);
                case "Bebida":
                    return criarBebida(nome, validade, preco);
                case "Limpeza Pessoal":
                    return criarLimpezaPessoal(nome, validade, preco);
                case "Limpeza Doméstica":
                    return criarLimpezaDomestica(nome, validade, preco);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar produto: " + e.getMessage());
        }
        return null;
    }

    private Comida criarComida(String nome, String validade, float preco) {
        String ingrediente = ingredienteField.getText();
        int quantidade = Integer.parseInt(quantidadeField.getText());
        float peso = Float.parseFloat(pesoField.getText());
        int calorias = Integer.parseInt(caloriasField.getText());
        return new Comida(nome, validade, preco, ingrediente, quantidade, peso, calorias);
    }

    private Bebida criarBebida(String nome, String validade, float preco) {
        String ingrediente = ingredienteField.getText();
        int quantidade = Integer.parseInt(quantidadeField.getText());
        float volume = Float.parseFloat(volumeField.getText());
        String tipoBebida = tipoBebidaField.getText();
        return new Bebida(nome, validade, preco, ingrediente, quantidade, volume, tipoBebida);
    }

    private LimpezaPessoal criarLimpezaPessoal(String nome, String validade, float preco) {
        String instrucoes = instrucoesField.getText();
        String sensibilidade = sensibilidadeField.getText();
        String area = areaAplicacaoField.getText();
        int quantidade = Integer.parseInt(quantidadeField.getText());
        return new LimpezaPessoal(nome, validade, preco, instrucoes, sensibilidade, area, quantidade);
    }

    private LimpezaDomestico criarLimpezaDomestica(String nome, String validade, float preco) {
        String instrucoes = instrucoesField.getText();
        String precaucao = precaucaoField.getText();
        String superficie = superficieField.getText();
        int quantidade = Integer.parseInt(quantidadeField.getText());
        return new LimpezaDomestico(nome, validade, preco, instrucoes, precaucao, superficie, quantidade);
    }

    public void limparCampos() {
        nomeField.setText("");
        validadeField.setText("");
        precoField.setText("");

        if (ingredienteField != null) ingredienteField.setText("");
        if (quantidadeField != null) quantidadeField.setText("");
        if (pesoField != null) pesoField.setText("");
        if (caloriasField != null) caloriasField.setText("");
        if (volumeField != null) volumeField.setText("");
        if (tipoBebidaField != null) tipoBebidaField.setText("");
        if (instrucoesField != null) instrucoesField.setText("");
        if (sensibilidadeField != null) sensibilidadeField.setText("");
        if (areaAplicacaoField != null) areaAplicacaoField.setText("");
        if (precaucaoField != null) precaucaoField.setText("");
        if (superficieField != null) superficieField.setText("");
    }
}