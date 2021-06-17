package ui.controllers

import sun.reflect.annotation.ExceptionProxy
import ui.entities.Usuario
import ui.repositories.UsuarioRepository
import java.lang.Exception

class UsuarioService {
    fun criarUsuario(usuario: Usuario, confirmacaoSenha: String) {
        if (usuario.nome.length < 1 || usuario.senha.length < 1 || usuario.email.length < 1 || confirmacaoSenha.length < 1) {
            throw Exception("Necessário preencher todos os campos");
        }

        if (confirmacaoSenha != usuario.senha) {
            throw Exception("As senhas não coincidem");
        }

        var isEmailEmUso = UsuarioRepository.listaUsuarios.find { it.email.lowercase() == usuario.email.lowercase() }

        if (isEmailEmUso != null) {
            throw Exception("Este e-mail já está em uso");
        }

        UsuarioRepository.criarUsuario(usuario);
    }

    fun deletarUsuario(usuario: Usuario) {
        if (usuario.email == UsuarioRepository.usuarioLogado?.email) {
            throw Exception("Não é possível excluir o usuário logado")
        }

        UsuarioRepository.deletarUsuario(usuario);
    }

    fun editarUsuario(usuarioAntigo: Usuario, novoUsuario: Usuario) {
        if (novoUsuario.nome == "" || novoUsuario.email == "" || novoUsuario.senha == "") {
            throw Exception("Necessário preencher todos os campos");
        }

        UsuarioRepository.editarUsuario(usuarioAntigo, novoUsuario);
    }
}