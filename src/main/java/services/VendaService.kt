package ui.services

import ui.entities.Venda
import ui.repositories.VendaRepository
import java.lang.Exception

class VendaService {
    fun deletarVenda(venda: Venda) {
        VendaRepository.deletarVenda(venda);
    }

    fun criarVenda(venda: Venda) {
        if (venda.produto.length < 1 || venda.valor.length < 1) {
            throw Exception("Necessário preencher todos os campos");
        }

        VendaRepository.criarVenda(venda);
    }

    fun editarVenda(vendaAntiga: Venda, novaVenda: Venda) {
        if (novaVenda.produto.length < 1 || novaVenda.valor.length < 1) {
            throw Exception("Necessário preencher todos os campos");
        }

        VendaRepository.editarVenda(vendaAntiga, novaVenda);
    }

}