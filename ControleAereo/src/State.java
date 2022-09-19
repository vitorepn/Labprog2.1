package ControleAereo.src;

import java.sql.PreparedStatement;
import java.util.HashSet;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.DriverManager;
public class State {
    private Cache _cache = new Cache();
    public State() throws SQLException, ClassNotFoundException {
        _cache.InitialLoad();
    }
    public HashSet<Aeroporto> get() {
        return _cache.get();
    };
}

class Cache{
    HashSet<Aeroporto> _aeroportos = new HashSet<Aeroporto>();
    DatabaseAccess db = new DatabaseAccess();

    Cache() throws SQLException, ClassNotFoundException {
    }

    public void InitialLoad() throws SQLException {
        _aeroportos = db.PegarAeroportosDoBanco();
    }
    public HashSet<Aeroporto> get(){
        return _aeroportos;
    }
}

class DatabaseAccess{
    private Connection banco;
    HashSet<Aeroporto> aeroportos;
    Aeroporto aeroporto;
    public DatabaseAccess() throws SQLException, ClassNotFoundException {
        this.banco = (new Conexao(1)).estabelecerConexao();
        aeroportos = new HashSet<Aeroporto>();
    }
    public HashSet<Aeroporto> PegarAeroportosDoBanco() throws SQLException {
        String SQL = "SELECT * FROM aeroportosinternacionais";
        PreparedStatement query = this.banco.prepareStatement(SQL);
        ResultSet selecao = query.executeQuery();

        while(selecao.next()){
            aeroporto = new Aeroporto(selecao.getString("nome"),
                                        selecao.getDouble("latitude"),
                                        selecao.getDouble("longitude"),
                                        selecao.getInt("altitude"),
                                        selecao.getString("uf"),
                                        selecao.getString("cidade"),
                                        selecao.getString("iata"));
            this.aeroportos.add(aeroporto);
        }
        return aeroportos;
    }
}