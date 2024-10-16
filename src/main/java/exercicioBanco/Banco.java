package exercicioBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Banco {
    private Connection conexao;
    private boolean estaConectado = false;
    private String mensagemErro;

    public Banco(String servidor, String porta, String usuario, String senha){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            // protocolo:sgbd://servidor:porta/nome_banco
            String url = "jdbc:mysql://"+servidor+":"+porta+"/prog_63_1b";
            conexao = DriverManager.getConnection(url, usuario, senha);
            estaConectado = true;
        } catch (SQLException | ClassNotFoundException e){
            mensagemErro = "Erro: "+e;
        }
    }

    public Banco(String usuario, String senha) throws SQLException {
        try{
            // protocolo:sgbd://servidor:porta/nome_banco
            String url = "jdbc:mysql://localhost:3306/prog_63_1b";
            conexao = DriverManager.getConnection(url, usuario, senha);
            estaConectado = true;
        } catch (SQLException e){
            mensagemErro = "Erro: "+e;
        }
    }

    public Connection obterConexao(){
        return conexao;
    }

    public boolean conectado(){
        return estaConectado;
    }

    public String obterMensagemErro(){
        return mensagemErro;
    }

    public void encerraConexao() {
        try{
            conexao.close();
        } catch (SQLException e){
            mensagemErro = "Erro: "+e;
        }
    }
}
