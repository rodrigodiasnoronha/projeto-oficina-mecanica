package ui.relatorios;

import sun.reflect.annotation.ExceptionProxy;
import ui.entities.Relatorio;
import ui.services.RelatorioService;

import javax.swing.*;
import java.awt.*;

public class EditarRelatorioForm extends JFrame {
    Relatorio relatorio;
    private JPanel rootPanel;
    private JButton voltarButton;
    private JButton editarButton;
    private JTextField relatoField;
    private JTextField valorField;

    public EditarRelatorioForm(Relatorio relatorio) {
        this.relatorio = relatorio;

        relatoField.setText(this.relatorio.getRelato());
        valorField.setText(this.relatorio.getValor());

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
        voltarButton.addActionListener(e -> {
            new ListagemRelatoriosForm();
            dispose();
        });

        editarButton.addActionListener(e -> {
            try {
                String relato = relatoField.getText();
                String valor = valorField.getText();

                Relatorio relatorio = new Relatorio(relato, valor);

                RelatorioService relatorioService = new RelatorioService();
                relatorioService.editarRelatorio(this.relatorio, relatorio);

                new ListagemRelatoriosForm();
                dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());

            }
        });
    }
}
