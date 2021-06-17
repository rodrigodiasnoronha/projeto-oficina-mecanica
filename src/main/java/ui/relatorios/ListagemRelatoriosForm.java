package ui.relatorios;

import ui.entities.Relatorio;
import ui.menu.MenuForm;
import ui.repositories.RelatorioRepository;
import ui.services.RelatorioService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListagemRelatoriosForm extends JFrame {

    private JPanel rootPanel;
    private JTable tabelaRelatorios;
    private JButton addRelatorioButton;
    private JButton excluirRelatorioButton;
    private JButton editarRelatorio;
    private JButton voltarButton;

    String relato = "";
    String valor = "";

    public ListagemRelatoriosForm() {
        setContentPane(rootPanel); // adiciona o painel a tela
        setSize(720, 500); // width e height do programa
        setVisible(true); // seta a tela como visible

        // posição -> centro da tela
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dimension.width / 2 - getSize().width / 2, dimension.height / 2 - getSize().height / 2);

        // encerra o programa ao clicar no x do windows
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.listeners();
        this.listarRelatorios();
    }

    public void listeners() {
        addRelatorioButton.addActionListener(e -> {
            new AddRelatorioForm();
            dispose();
        });

        tabelaRelatorios.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                if (tabelaRelatorios.getSelectedRow() != -1) {
                    relato = tabelaRelatorios.getValueAt(tabelaRelatorios.getSelectedRow(), 0).toString();
                    valor = tabelaRelatorios.getValueAt(tabelaRelatorios.getSelectedRow(), 1).toString();
                }
            }
        });

        excluirRelatorioButton.addActionListener(e -> {
            try {

                Relatorio relatorio = new Relatorio(relato, valor);
                RelatorioService relatorioService = new RelatorioService();

                relatorioService.deletarRelatorio(relatorio);
                this.listarRelatorios();

                relato = "";
                valor = "";
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
            }
        });


        editarRelatorio.addActionListener(e -> {
            try {
                if (relato.length() < 1 && valor.length() < 1) {
                    throw new Exception("Necessário selecionar uma peça");
                }

                Relatorio relatorio = new Relatorio(relato, valor);

                new EditarRelatorioForm(relatorio);
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

    public void listarRelatorios() {
        List<Relatorio> relatorioList = RelatorioRepository.Companion.getRelatorioList();

        String[] nomeColunas = {"Relato", "Valor"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(new Object[0][0], nomeColunas);

        relatorioList.forEach(relatorio -> {
            Object[] o = new Object[2];

            o[0] = relatorio.getRelato();
            o[1] = relatorio.getValor();

            defaultTableModel.addRow(o);
        });

        tabelaRelatorios.clearSelection();
        tabelaRelatorios.setModel(defaultTableModel);
    }
}
