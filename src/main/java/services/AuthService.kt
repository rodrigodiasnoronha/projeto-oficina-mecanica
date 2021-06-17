package ui.controllers

import ui.entities.Usuario
import ui.repositories.UsuarioRepository
import java.lang.Exception

class AuthService {
    fun login(usuario: Usuario) {
        var usuarioEncontrado = UsuarioRepository.getUsuarioByEmail(usuario.email);

        if (usuarioEncontrado == null) {
            throw Exception("Usuário não encontrado");
        }

        if (usuarioEncontrado.senha != usuario.senha) {
            throw Exception("Senha incorreta");
        }

        UsuarioRepository.logarUsuario(usuario);
    }

    fun logOff() {
        UsuarioRepository.deslogarUsuario();
    }
}