package ui.repositories

import ui.entities.Venda

class VendaRepository {
    companion object {
        var vendaList = mutableListOf<Venda>(
            Venda("Pneu", "120.00"),
            Venda("Motor", "340.00")
        );

        fun criarVenda(venda: Venda) {
            this.vendaList.add(venda);
        }

        fun deletarVenda(venda: Venda) {
            this.vendaList =
                this.vendaList.filter { it.produto != venda.produto && it.valor != venda.valor } as MutableList<Venda>;
        }

        fun editarVenda(vendaAntiga: Venda, novaVenda: Venda) {
            var novaListaVendas = this.vendaList.filter {
                it.produto != vendaAntiga.produto && it.valor != vendaAntiga.valor
            } as MutableList<Venda>

            novaListaVendas.add(novaVenda);

            this.vendaList = novaListaVendas;
        }
    }
}