package exemploMySQL;

import java.sql.*;
import java.util.Scanner;

public class Pesquisar {
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
            String sql = "SELECT * FROM tb_contato where nome like ? or e_mail like ? or telefone like ?";
            PreparedStatement requisicao = conexao.prepareStatement(sql);
            // Ler o termo de busca
            System.out.print("Qual o termo de busca: ");
            String termo = new Scanner(System.in).nextLine();
            // Definindo o termo de busca
            requisicao.setString(1, "%"+termo+"%");
            requisicao.setString(2, "%"+termo+"%");
            requisicao.setString(3, "%"+termo+"%");
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
