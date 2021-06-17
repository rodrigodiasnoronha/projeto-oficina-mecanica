package ui.vendas;


import ui.controllers.UsuarioService;
import ui.entities.Usuario;
import ui.entities.Venda;
import ui.menu.MenuForm;
import ui.repositories.UsuarioRepository;
import ui.repositories.VendaRepository;
import ui.services.VendaService;
import ui.usuarios.CriarUsuario;
import ui.usuarios.EditarUsuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListagemVendasForm extends JFrame {
    private JPanel rootPanel;
    private JTable tabelaVendas;
    private JButton criarVendaButton;
    private JButton editarVendaButton;
    private JButton excluirVendaButton;
    private JButton voltarButton;

    String produto = "";
    String valor = "";

    public ListagemVendasForm() {
        setContentPane(rootPanel); // adiciona o painel a tela
        setSize(720, 500); // width e height do programa
        setVisible(true); // seta a tela como visible

        // posição -> centro da tela
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dimension.width / 2 - getSize().width / 2, dimension.height / 2 - getSize().height / 2);

        // encerra o programa ao clicar no x do windows
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.listeners();
        this.listarVendas();
    }

    public void listeners() {
        voltarButton.addActionListener(e -> {
            new MenuForm();
            dispose();
        });

        criarVendaButton.addActionListener(e -> {
            new CriarVendaForm();
            dispose();
        });

        tabelaVendas.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                if (tabelaVendas.getSelectedRow() != -1) {
                    produto = tabelaVendas.getValueAt(tabelaVendas.getSelectedRow(), 0).toString();
                    valor = tabelaVendas.getValueAt(tabelaVendas.getSelectedRow(), 1).toString();
                }
            }
        });

        excluirVendaButton.addActionListener(e -> {
            try {
                Venda venda = new Venda(produto, valor);

                VendaService vendaService = new VendaService();
                vendaService.deletarVenda(venda);

                this.listarVendas();

                produto = "";
                valor = "";
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
            }
        });

        editarVendaButton.addActionListener(e -> {
            try {
                if (produto.length() < 1 || valor.length() < 1) {
                    throw new Exception("Necessário selecionar um usuário");
                }

                Venda venda = new Venda(produto, valor);

                new EditarVendaForm(venda);
                dispose();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(new JFrame(), exception.getMessage());
            }
        });
    }

    public void listarVendas() {
        List<Venda> vendaList = VendaRepository.Companion.getVendaList();

        String[] nomeColunas = {"Produto", "Valor"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(new Object[0][0], nomeColunas);

        vendaList.forEach(venda -> {
            Object[] o = new Object[2];

            o[0] = venda.getProduto();
            o[1] = venda.getValor();

            defaultTableModel.addRow(o);
        });

        tabelaVendas.clearSelection();
        tabelaVendas.setModel(defaultTableModel);
    }
}

