package exemploMySQL;

import java.sql.*;
import java.util.Scanner;

public class Alterar {
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

            Scanner ler = new Scanner(System.in);
            System.out.print("Qual dos registros acima deseja alterar: ");
            int idContato = ler.nextInt();

            // Alterando registro
            String sqlAtuliza = "UPDATE tb_contato SET nome = ?, e_mail = ?, telefone = ? WHERE contato_id = ?";

            // Obter as novas informações
            System.out.print("Qual o novo nome do contato: ");
            String nome = ler.next();
            System.out.print("Qual o novo email do contato: ");
            String email = ler.next();
            System.out.print("Qual o novo telefone do contato: ");
            String fone = ler.next();

            // Criando a requisição
            PreparedStatement reqAtualizar = conexao.prepareStatement(sqlAtuliza);

            // Definindo os parametros
            reqAtualizar.setString(1, nome);
            reqAtualizar.setString(2, email);
            reqAtualizar.setString(3, fone);
            reqAtualizar.setInt(4, idContato);

            // Executar a requisição
            int cont = reqAtualizar.executeUpdate();
            if(cont>0){
                System.out.println("Registro atualizado!");
            }else{
                System.out.println("Registro não encontrado!");
            }

            conexao.close();
        } catch (ClassNotFoundException e){
            System.out.println("Erro ao carregar o driver...\n"+e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro de sql "+e.getMessage());
        }
    }
}
