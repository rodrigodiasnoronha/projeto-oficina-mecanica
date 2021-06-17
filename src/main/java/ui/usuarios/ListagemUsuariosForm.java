package ui.usuarios;

import ui.controllers.UsuarioService;
import ui.entities.Peca;
import ui.entities.Usuario;
import ui.menu.MenuForm;
import ui.repositories.PecaRepository;
import ui.repositories.UsuarioRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListagemUsuariosForm extends JFrame {
    private JButton excluirUsuarioButton;
    private JButton voltarButton;
    private JButton editarUsuario;
    private JButton criarUsuarioButton;
    private JPanel rootPanel;
    private JTable tabelaUsuarios;

    String usuarioNome = "";
    String usuarioEmail = "";

    public ListagemUsuariosForm() {
        setContentPane(rootPanel); // adiciona o painel a tela
        setSize(720, 500); // width e height do programa
        setVisible(true); // seta a tela como visible

        // posição -> centro da tela
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dimension.width / 2 - getSize().width / 2, dimension.height / 2 - getSize().height / 2);

        // encerra o programa ao clicar no x do windows
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.listeners();
        this.listarUsuarios();
    }

    public void listeners() {
        voltarButton.addActionListener(e -> {
            new MenuForm();
            dispose();
        });

        criarUsuarioButton.addActionListener(e -> {
            new CriarUsuario();
            dispose();
        });

        tabelaUsuarios.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                if (tabelaUsuarios.getSelectedRow() != -1) {
                    usuarioNome = tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 0).toString();
                    usuarioEmail = tabelaUsuarios.getValueAt(tabelaUsuarios.getSelectedRow(), 1).toString();
                }
            }
        });

        excluirUsuarioButton.addActionListener(e -> {
            try {
                Usuario usuario = new Usuario(usuarioEmail, "", usuarioNome);

                UsuarioService usuarioService = new UsuarioService();
                usuarioService.deletarUsuario(usuario);

                this.listarUsuarios();

                usuarioEmail = "";
                usuarioNome = "";
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
            }
        });

        editarUsuario.addActionListener(e -> {
            try {
                if (usuarioEmail == "" || usuarioNome == "") {
                    throw new Exception("Necessário selecionar um usuário");
                }

                Usuario usuario = new Usuario(usuarioEmail, "", usuarioNome);

                new EditarUsuario(usuario);
                dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());

            }
        });
    }

    public void listarUsuarios() {
        List<Usuario> usuarioList = UsuarioRepository.Companion.getListaUsuarios();

        String[] nomeColunas = {"Nome", "Email"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(new Object[0][0], nomeColunas);

        usuarioList.forEach(e -> {
            Object[] o = new Object[2];

            o[0] = e.getNome();
            o[1] = e.getEmail();

            defaultTableModel.addRow(o);
        });

        tabelaUsuarios.clearSelection();
        tabelaUsuarios.setModel(defaultTableModel);
    }
}
