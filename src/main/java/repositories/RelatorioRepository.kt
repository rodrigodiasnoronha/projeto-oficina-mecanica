package ui.repositories

import ui.entities.Relatorio
import ui.entities.Usuario

class RelatorioRepository {

    companion object {
        var relatorioList = mutableListOf<Relatorio>(
            Relatorio("Lucro total da empresa no ano", "122.000.00")
        )

        fun criarRelatorio(relatorio: Relatorio) {
            this.relatorioList.add(relatorio);
        }

        fun deletarRelatorio(relatorio: Relatorio) {
            this.relatorioList =
                this.relatorioList.filter { it.relato != relatorio.relato && it.valor != relatorio.valor } as MutableList<Relatorio>;
        }

        fun editarRelatorio(antigoRelatorio: Relatorio, novoRelatorio: Relatorio) {
            var novaListaRelatorios =
                this.relatorioList.filter { it.relato != antigoRelatorio.relato && it.valor != antigoRelatorio.valor } as MutableList<Relatorio>;

            novaListaRelatorios.add(novoRelatorio)
            this.relatorioList = novaListaRelatorios;
        }
    }
}