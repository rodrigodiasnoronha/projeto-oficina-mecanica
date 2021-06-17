package ui.entities

class Usuario(var email: String, var senha: String) {
    var nome: String = "";

    constructor(email: String, senha: String, nome: String) : this(email, senha) {
        this.nome = nome;
    }
}