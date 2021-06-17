package ui.services

import ui.entities.Relatorio
import ui.repositories.RelatorioRepository
import java.lang.Exception

class RelatorioService {
    fun criarRelatorio(relatorio: Relatorio) {
        if (relatorio.relato.length < 1 || relatorio.valor.length < 1) {
            throw Exception("Necessário preencher todos os campos");
        }

        RelatorioRepository.criarRelatorio(relatorio);
    }

    fun deletarRelatorio(relatorio: Relatorio) {
        RelatorioRepository.deletarRelatorio(relatorio);
    }

    fun editarRelatorio(antigoRelatorio: Relatorio, novoRelatorio: Relatorio) {
        if (novoRelatorio.relato.length < 1 || novoRelatorio.valor.length < 1) {
            throw Exception("Necessário preencher todos os campos");
        }

        RelatorioRepository.editarRelatorio(antigoRelatorio, novoRelatorio);
    }

}