package exemploMySQL;

import java.sql.*;

public class Listar {
    public static void main(String[] args) {
        try{
            // Carregando o driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Tentando realizar uma conexão
            // protocolo:sgbd://servidor:porta/nome_banco
            String url = "jdbc:mysql://localhost:3306/prog_63_1b";
            String usuario = "julia";
            String senha = "1234";
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Estou conectado");
            //Listando registros
            String sql = "SELECT * FROM tb_contato";
            PreparedStatement requisicao = conexao.prepareStatement(sql);
            ResultSet resultado = requisicao.executeQuery();
            while(resultado.next()){
                int idContato = resultado.getInt("contato_id");
                String nome = resultado.getString("nome");
                String mail = resultado.getString("e_mail");
                String telefone = resultado.getString("telefone");
                System.out.println("\nId: "+idContato);
                System.out.println("Nome: "+nome);
                System.out.println("E-Mail: "+mail);
                System.out.println("Telefone: "+telefone);
                System.out.println("\n======================================");
            }
            conexao.close();
        } catch (ClassNotFoundException e){
            System.out.println("Erro ao carregar o driver...\n"+e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro de sql "+e.getMessage());
        }
    }
}
