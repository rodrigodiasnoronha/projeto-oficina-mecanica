package ui.usuarios;

import ui.controllers.UsuarioService;
import ui.entities.Usuario;

import javax.swing.*;
import java.awt.*;

public class CriarUsuario extends JFrame {
    private JButton criarUsuarioButton;
    private JButton voltarButton;
    private JTextField emailField;
    private JTextField nomeField;
    private JPasswordField senhaField;
    private JPasswordField confirmarSenhaField;
    private JPanel rootPanel;

    public CriarUsuario() {
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
            new ListagemUsuariosForm();
            dispose();
        });

        criarUsuarioButton.addActionListener(e -> {
            try {
                String nome = nomeField.getText();
                String email = emailField.getText();
                String senha = new String(senhaField.getPassword());
                String confirmacaoSenha = new String(confirmarSenhaField.getPassword());

                Usuario usuario = new Usuario(email, senha, nome);
                UsuarioService usuarioService = new UsuarioService();

                usuarioService.criarUsuario(usuario, confirmacaoSenha);

                new ListagemUsuariosForm();
                dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
            }
        });
    }
}
