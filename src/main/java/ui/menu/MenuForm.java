package ui.menu;

import ui.controllers.AuthService;
import ui.login.LoginForm;
import ui.pecas.ListagemPecasForm;
import ui.relatorios.ListagemRelatoriosForm;
import ui.usuarios.ListagemUsuariosForm;
import ui.vendas.ListagemVendasForm;

import javax.swing.*;
import java.awt.*;

public class MenuForm extends JFrame {
    private JPanel rootPanel;
    private JButton pecasButton;
    private JButton usuariosButton;
    private JButton sairButton;
    private JButton vendasButton;
    private JButton relatoriosButton;

    public MenuForm() {
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
        pecasButton.addActionListener(e -> {
            new ListagemPecasForm();
            dispose();
        });

        usuariosButton.addActionListener(e -> {
            new ListagemUsuariosForm();
            dispose();
        });

        vendasButton.addActionListener(e -> {
            new ListagemVendasForm();
            dispose();
        });

        relatoriosButton.addActionListener(e -> {
            new ListagemRelatoriosForm();
            dispose();
        });

        sairButton.addActionListener(e -> {
            AuthService authService = new AuthService();
            authService.logOff();

            new LoginForm();
            dispose();
        });
    }
}
