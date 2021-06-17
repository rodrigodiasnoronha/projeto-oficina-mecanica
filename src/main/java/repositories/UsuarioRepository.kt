package ui.repositories

import ui.entities.Usuario
import java.lang.Exception

class UsuarioRepository {
    companion object {
        var listaUsuarios = mutableListOf<Usuario>(
            Usuario("admin@email.com", "123", "Administrador")
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

        fun deslogarUsuario() {
            this.usuarioLogado = null;
        }

        fun criarUsuario(usuario: Usuario): Usuario {
            this.listaUsuarios.add(usuario);
            return usuario;
        }

        fun deletarUsuario(usuario: Usuario) {
            this.listaUsuarios = this.listaUsuarios.filter { it.email != usuario.email } as MutableList<Usuario>;
        }

        fun editarUsuario(usuarioAntigo: Usuario, novoUsuario: Usuario) {
            if (usuarioAntigo.email != novoUsuario.email) {
                var isEmailEmUso = this.listaUsuarios.find { it.email == novoUsuario.email }

                if (isEmailEmUso != null) {
                    throw Exception("Email já está em uso");
                }
            }

            var novaListaUsuarios =
                this.listaUsuarios.filter { it.email != usuarioAntigo.email } as MutableList<Usuario>;
            novaListaUsuarios.add(novoUsuario);

            this.listaUsuarios = novaListaUsuarios;
        }
    }
}