package exemploMySQL;

import java.sql.*;
import java.util.Scanner;

public class Deletar {
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
            String sql = "DELETE FROM tb_contato WHERE contato_id=?;";
            PreparedStatement requisicao = conexao.prepareStatement(sql);
            // Ler o termo de busca
            System.out.print("Digite o id: ");
            int termo = new Scanner(System.in).nextInt();
            // Definindo o termo de busca
            requisicao.setInt(1, termo);
            requisicao.execute();
            System.out.println("Usuário deletado.");
            conexao.close();
        } catch (ClassNotFoundException e){
            System.out.println("Erro ao carregar o driver...\n"+e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro de sql "+e.getMessage());
        }
    }
}
