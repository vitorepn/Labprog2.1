package ControleAereo.src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
    private String servidor;
    private String porta;
    private String banco;
    private String login;
    private String senha;

    public Conexao(int status){
        this.servidor = "127.0.0.1";
        this.porta = "3306";
        this.banco = "aeroportos";
        this.login = "svcuser";
        this.senha = "12345678#";
    }

    public Connection estabelecerConexao() throws SQLException, ClassNotFoundException {
        String servidor = "jdbc:mysql://" + this.servidor + ":" +
                this.porta + "/" + this.banco;
        return (Connection) DriverManager.getConnection(servidor,
                this.login, this.senha);
    }
}
