package ui.relatorios;

import ui.entities.Relatorio;
import ui.services.RelatorioService;

import javax.swing.*;
import java.awt.*;

public class AddRelatorioForm extends JFrame {
    private JButton voltarButton;
    private JButton criarButton;
    private JTextField relatoField;
    private JTextField valorField;
    private JPanel rootPanel;

    public AddRelatorioForm() {
        setContentPane(rootPanel); // adiciona o painel a tela
        setSize(720, 500); // width e height do programa
        setVisible(true); // seta a tela como visible

        // posição -> centro da tela
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dimension.width / 2 - getSize().width / 2, dimension.height / 2 - getSize().height / 2);

        // encerra o programa ao clicar no x do windows
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.listeners();
    }

    public void listeners() {
        criarButton.addActionListener(e -> {

            try {
                String relato = relatoField.getText();
                String valor = valorField.getText();

                Relatorio relatorio = new Relatorio(relato, valor);

                RelatorioService relatorioService = new RelatorioService();
                relatorioService.criarRelatorio(relatorio);

                new ListagemRelatoriosForm();
                dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
            }
        });

        voltarButton.addActionListener(e -> {
            new ListagemRelatoriosForm();
            dispose();
        });
    }
}
