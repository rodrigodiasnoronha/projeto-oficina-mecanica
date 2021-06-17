package ui.repositories

import ui.entities.Peca

class PecaRepository {
    companion object {
        var listaPecas = mutableListOf<Peca>(
            Peca("Pneu", "90.00"),
            Peca("Parabrisas", "120.00")
        );

        fun addPeca(peca: Peca): Peca {
            this.listaPecas.add(peca);
            return  peca;
        }

        fun deletarPeca(peca: Peca) {
            this.listaPecas = this.listaPecas.filter { it.nome != peca.nome } as MutableList<Peca>
        }

        fun editarPeca(novaPeca: Peca, antigaPeca: Peca): Peca {
           var novaListaPecas = this.listaPecas.filter { it.nome != antigaPeca.nome } as MutableList<Peca>;
            novaListaPecas.add(novaPeca);

            this.listaPecas = novaListaPecas;

            return novaPeca;
        }
    }
}