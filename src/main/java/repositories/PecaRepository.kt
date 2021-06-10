package ui.repositories

import ui.entities.Peca

class PecaRepository {
    companion object {
        var listaPecas = mutableListOf<Peca>();

        fun addPeca(peca: Peca) {
            this.listaPecas.add(peca);
        }

        fun deletarPeca(peca: Peca) {
            this.listaPecas = this.listaPecas.filter { it.nome != peca.nome } as MutableList<Peca>
        }

        fun editarPeca(novaPeca: Peca, antigaPeca: Peca) {
           var novaListaPecas = this.listaPecas.filter { it.nome != antigaPeca.nome } as MutableList<Peca>;
            novaListaPecas.add(novaPeca);

            this.listaPecas = novaListaPecas;
        }
    }
}