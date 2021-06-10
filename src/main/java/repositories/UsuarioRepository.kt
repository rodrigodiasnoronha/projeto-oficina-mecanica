package ui.repositories

import ui.entities.Usuario

class UsuarioRepository {
    companion object {
        var listaUsuarios = mutableListOf<Usuario>(
            Usuario("a@a.com", "123")
        );
        var usuarioLogado: Usuario? = null;

        fun getUsuarioByEmail(email: String): Usuario? {
            var usuarioEncontrado: Usuario? = null;

            this.listaUsuarios.forEach {
                if (email == it.email) {
                    usuarioEncontrado = it;
                }
            }

            return usuarioEncontrado;
        }

        fun logarUsuario(usuario: Usuario) {
            this.usuarioLogado = usuario;
        }
    }
}