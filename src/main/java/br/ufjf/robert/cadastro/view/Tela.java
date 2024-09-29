package br.ufjf.robert.cadastro.view;

import javax.swing.*;
import javax.swing.table.*;

import java.util.ArrayList;
import java.util.List;

import br.ufjf.robert.cadastro.controller.*;
import br.ufjf.robert.cadastro.exceptions.CPFException;
import br.ufjf.robert.cadastro.exceptions.DataException;
import br.ufjf.robert.cadastro.model.Cadastro;

import java.awt.*;

public class Tela extends JFrame {

    private final int WIDTH = 500;
    private final int HEIGHT = 200;
    private final int V_GAP = 10;
    private final int H_GAP = 5;
 
    private JTextField tfNome = new JTextField("Digite seu nome");
    private JTextField tfCPF = new JTextField("000.000.000-00");
    private JTextField tfDataNascimento = new JTextField("00/00/0000");

    //private JTextField tfNome = new JTextField("Robert");
    //private JTextField tfCPF = new JTextField("131.577.396-11");
    //private JTextField tfDataNascimento = new JTextField("03/05/2005");

    private JButton btAdicionar = new JButton("Adicionar");
    private JButton btRemover = new JButton("Remover");
    private JButton btEditar = new JButton("Editar");

    private JTable tabelaPessoas;
    private DefaultTableModel model;
    private List<Cadastro> cadastros = new ArrayList<>();

    public Tela() {
        super("Cadastro de Pessoas");

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());

        desenhaFormulario();
        desenhaTabela();

        pack();
    }

    private void desenhaFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Formulário"));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;

        c.insets = new Insets(5, 10, 5, 10);

        JPanel dadosPanel = new JPanel();
        dadosPanel.setLayout(new GridLayout(6, 1, H_GAP, V_GAP));

        dadosPanel.add(newLabel("Nome:"));
        tfNome.setForeground(Color.GRAY);
        tfNome.setPreferredSize(new Dimension(200, 25));
        dadosPanel.add(tfNome);

        dadosPanel.add(newLabel("CPF:"));
        tfCPF.setForeground(Color.GRAY);
        tfCPF.setPreferredSize(new Dimension(200, 25));
        dadosPanel.add(tfCPF);

        dadosPanel.add(newLabel("Data de Nascimento:"));
        tfDataNascimento.setForeground(Color.GRAY);
        tfDataNascimento.setPreferredSize(new Dimension(200, 25));
        dadosPanel.add(tfDataNascimento);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        btAdicionar.setPreferredSize(new Dimension(100, 25));
        btRemover.setPreferredSize(new Dimension(100, 25));
        btEditar.setPreferredSize(new Dimension(100, 25));

        btAdicionar.setBackground(Color.LIGHT_GRAY);
        btRemover.setBackground(Color.LIGHT_GRAY);
        btEditar.setBackground(Color.LIGHT_GRAY);

        btAdicionar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        btRemover.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));
        btEditar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        btAdicionar.addActionListener(new AdicionarCadastro(this));
        btRemover.addActionListener(new RemoverCadastro(this));
        btEditar.addActionListener(new EditarCadastro(this));

        buttonPanel.add(btAdicionar, c);
        buttonPanel.add(btRemover, c);
        buttonPanel.add(btEditar, c);

        c.insets = new Insets(25, 10, 25, 10);

        panel.add(dadosPanel, c);
        panel.add(buttonPanel, c);

        add(panel, BorderLayout.WEST);
    }

    private void desenhaTabela() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Cadastros de Pessoas"));
        
        this.model = new DefaultTableModel(new Object[]{"Nome", "CPF", "Data de Nascimento", "Idade"}, 0);

        this.tabelaPessoas = new JTable(model);
        this.tabelaPessoas.setFillsViewportHeight(true);
        this.tabelaPessoas.setDefaultEditor(Object.class, null);

        this.tabelaPessoas.getSelectionModel().addListSelectionListener(new SelecionarCadastro(this));

        JScrollPane scrollPane = new JScrollPane(tabelaPessoas);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel, BorderLayout.EAST);
    }


    private JLabel newLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setFont(new Font("Segoe UI Variable", Font.ITALIC, 12));
        return label;
    }

    public void addPessoa(){
        try {
            // Adiciona a pessoa na tabela
            cadastros.add(new Cadastro(tfNome.getText(), tfCPF.getText(), tfDataNascimento.getText()));

            atualizaTabela();
            
        } catch (DataException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro na Data", JOptionPane.ERROR_MESSAGE);
        } catch (CPFException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro no CPF", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removePessoa(){
        try {
            // Remove a pessoa da tabela
            cadastros.remove(tabelaPessoas.getSelectedRow());

            atualizaTabela();
            limparCampos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Selecione um cadastro para remover", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void editCadastro(){
        try {
            // Edita a pessoa na tabela
            Cadastro cadastro = cadastros.get(tabelaPessoas.getSelectedRow());
                cadastro.setNome(tfNome.getText());
                cadastro.setCPF(tfCPF.getText());
                cadastro.setDataNascimento(tfDataNascimento.getText());
                cadastro.setIdade(tfDataNascimento.getText());

            atualizaTabela();
            limparCampos();

        } catch (DataException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (CPFException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Selecione um cadastro para editar", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        tfNome.setText("");
        tfCPF.setText("");
        tfDataNascimento.setText("");
    }    

    private void atualizaTabela() {
        tabelaPessoas.clearSelection();
        model.setRowCount(0);

        for (Cadastro cadastro : cadastros) {
            model.addRow(new Object[]{cadastro.getNome(), cadastro.getCPF(), cadastro.getDataNascimento(), cadastro.getIdade()});
        }
    }

    public void atualizaFormulario(){
        if(this.tabelaPessoas.getSelectedRow() == -1)
            return;
        
        Cadastro cadastro = cadastros.get(tabelaPessoas.getSelectedRow());
        
        tfNome.setText(cadastro.getNome());
        tfCPF.setText(cadastro.getCPF());
        tfDataNascimento.setText(cadastro.getDataNascimento());
    }
}
