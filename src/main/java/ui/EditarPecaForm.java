package ui;

import sun.reflect.annotation.ExceptionProxy;
import ui.controllers.PecaController;
import ui.entities.Peca;

import javax.swing.*;
import java.awt.*;

public class EditarPecaForm extends JFrame {
    Peca pecaSelecionada;
    private JPanel rootPanel;
    private JButton editarButton;
    private JButton voltarButton;
    private JTextField quantidadeField;
    private JTextField nomeField;

    public EditarPecaForm(Peca peca) {
        this.pecaSelecionada = peca;

        nomeField.setText(this.pecaSelecionada.getNome());
        quantidadeField.setText(this.pecaSelecionada.getQuantidade());

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

        editarButton.addActionListener(e -> {
            try {
                String novoNome = nomeField.getText();
                String novaQuantidade = quantidadeField.getText();

                Peca novaPeca = new Peca(novoNome, novaQuantidade);

                PecaController pecaController = new PecaController();
                pecaController.editarPeca(novaPeca, pecaSelecionada);

                new ListagemForm();
                dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
            }
        });
    }
}
