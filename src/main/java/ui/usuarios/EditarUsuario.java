package ui.usuarios;

import ui.controllers.UsuarioService;
import ui.entities.Usuario;

import javax.swing.*;
import java.awt.*;

public class EditarUsuario extends JFrame {

    private JButton editarUsuarioButton;
    private JButton voltarButton;
    private JTextField emailField;
    private JTextField nomeField;
    private JPasswordField senhaField;
    private JPanel rootPanel;

    Usuario usuario;

    public EditarUsuario(Usuario usuario) {
        this.usuario = usuario;

        nomeField.setText(this.usuario.getNome());
        emailField.setText(this.usuario.getEmail());
        senhaField.setText(this.usuario.getSenha());

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

        editarUsuarioButton.addActionListener(e -> {
            try {
                String email = emailField.getText();
                String senha = new String(senhaField.getPassword());
                String nome = nomeField.getText();

                Usuario novoUsuario = new Usuario(email, senha, nome);

                UsuarioService usuarioService = new UsuarioService();
                usuarioService.editarUsuario(this.usuario, novoUsuario);

                new ListagemUsuariosForm();
                dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
            }
        });
    }
}
