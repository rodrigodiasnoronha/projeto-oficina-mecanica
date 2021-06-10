package ui;

import sun.reflect.annotation.ExceptionProxy;
import ui.controllers.PecaController;
import ui.entities.Peca;

import javax.swing.*;
import java.awt.*;

public class AddPecaForm extends JFrame {
    private JButton adicionarButton;
    private JButton voltarButton;
    private JTextField nomeField;
    private JTextField quantidadeField;
    private JPanel rootPanel;


    public AddPecaForm() {
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
            new ListagemForm();
            dispose();
        });

        adicionarButton.addActionListener(e -> {
            try  {
                String nome = nomeField.getText();
                String quantidade = quantidadeField.getText();

                Peca peca = new Peca(nome, quantidade);

                PecaController pecaController = new PecaController();
                pecaController.addPeca(peca);

                new ListagemForm();
                dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
            }
        });
    }
}
