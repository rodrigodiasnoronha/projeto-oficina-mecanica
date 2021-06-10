package ui.controllers

import ui.entities.Peca
import ui.repositories.PecaRepository
import java.lang.Exception


class PecaController {
    fun deletarPeca(peca: Peca) {
        PecaRepository.deletarPeca(peca);
    }

    fun addPeca(peca: Peca) {
        if (peca.nome.length < 1 || peca.quantidade.length < 1) {
            throw Exception("Necessário preencher todos os campos");
        }

        PecaRepository.addPeca(peca);
    }

    fun editarPeca(novaPeca: Peca, antigaPeca: Peca) {
        if (novaPeca.nome.length < 1 || novaPeca.quantidade.length < 1) {
            throw Exception("Necessário preencher todos os campos");
        }
        PecaRepository.editarPeca(novaPeca, antigaPeca);
    }
}