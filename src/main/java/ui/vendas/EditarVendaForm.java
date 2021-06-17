package ui.vendas;

import ui.entities.Venda;
import ui.services.VendaService;

import javax.swing.*;
import java.awt.*;

public class EditarVendaForm extends JFrame {
    private JButton voltarButton;
    private JButton editarVendaButton;
    private JTextField valorField;
    private JTextField produtoField;
    private JPanel rootPanel;

    Venda venda;

    public EditarVendaForm(Venda venda) {
        this.venda = venda;

        produtoField.setText(this.venda.getProduto());
        valorField.setText(this.venda.getValor());

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
            new ListagemVendasForm();
            dispose();
        });

        editarVendaButton.addActionListener(e -> {
            try {
                String produto = produtoField.getText();
                String valor = valorField.getText();

                Venda venda = new Venda(produto, valor);

                VendaService vendaService = new VendaService();
                vendaService.editarVenda(this.venda, venda);

                new ListagemVendasForm();
                dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
            }
        });
    }
}
