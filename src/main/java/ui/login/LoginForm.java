package ui.login;

import ui.menu.MenuForm;
import ui.controllers.AuthService;
import ui.entities.Usuario;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    private JPanel rootPanel;
    private JButton loginButton;
    private JTextField emailField;
    private JTextField senhaField;

    public LoginForm() {
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
        loginButton.addActionListener(e -> {

            try {
                String email = emailField.getText();
                String senha = senhaField.getText();

                Usuario usuario = new Usuario(email, senha);

                AuthService authService = new AuthService();
                authService.login(usuario);

                new MenuForm();
                dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
            }
        });
    }
}
