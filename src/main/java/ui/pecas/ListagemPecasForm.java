package ui.pecas;

import ui.menu.MenuForm;
import ui.controllers.PecaService;
import ui.entities.Peca;
import ui.repositories.PecaRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListagemPecasForm extends JFrame {

    private JPanel rootPanel;
    private JTable tabelaPecas;
    private JButton adicionarPecaButton;
    private JButton excluirPecaButton;
    private JButton editarPecaButton;
    private JButton voltarButton;

    String nome = "";
    String quantidade = "";

    public ListagemPecasForm() {
        setContentPane(rootPanel); // adiciona o painel a tela
        setSize(720, 500); // width e height do programa
        setVisible(true); // seta a tela como visible

        // posição -> centro da tela
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dimension.width / 2 - getSize().width / 2, dimension.height / 2 - getSize().height / 2);

        // encerra o programa ao clicar no x do windows
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.listeners();
        this.listarPecas();
    }

    public void listeners() {
        adicionarPecaButton.addActionListener(e -> {
            new AddPecaForm();
            dispose();
        });

        tabelaPecas.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                if (tabelaPecas.getSelectedRow() != -1) {
                    nome = tabelaPecas.getValueAt(tabelaPecas.getSelectedRow(), 0).toString();
                    quantidade = tabelaPecas.getValueAt(tabelaPecas.getSelectedRow(), 1).toString();
                }
            }
        });

        excluirPecaButton.addActionListener(e -> {
            try {
                PecaService pecaService = new PecaService();
                pecaService.deletarPeca(new Peca(nome, quantidade));

                this.listarPecas();
                nome = "";
                quantidade = "";
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
            }
        });


        editarPecaButton.addActionListener(e -> {
            try {
                if (nome.length() < 1 && quantidade == "") {
                    throw new Exception("Necessário selecionar uma peça");
                }

                Peca peca = new Peca(nome, quantidade);

                new EditarPecaForm(peca);
                dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
            }
        });

        voltarButton.addActionListener(e -> {
            new MenuForm();
            dispose();
        });

    }

    public void listarPecas() {
        List<Peca> pecaList = PecaRepository.Companion.getListaPecas();

        String[] nomeColunas = {"Nome", "Quantidade"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(new Object[0][0], nomeColunas);

        pecaList.forEach(e -> {
            Object[] o = new Object[2];

            o[0] = e.getNome();
            o[1] = e.getQuantidade();

            defaultTableModel.addRow(o);
        });

        tabelaPecas.clearSelection();
        tabelaPecas.setModel(defaultTableModel);
    }
}
