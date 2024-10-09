package exemploMySQL;

import java.sql.*;
import java.util.Scanner;

public class Inserir {
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

            //exemploMySQL.Inserir informações no banco
            String sql = "INSERT INTO tb_contato(nome, e_mail, telefone) VALUES(?, ?, ?)";

            // Criando uma requisição para inserir os dados
            PreparedStatement requisicao = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Obter os dados para o contato...
            Scanner ler = new Scanner(System.in);
            System.out.print("Digite o nome: ");
            String nome = ler.nextLine();
            System.out.print("Digite o e-mail: ");
            String mail = ler.nextLine();
            System.out.print("Qual o telefone: ");
            String fone = ler.nextLine();
            //
            requisicao.setString(1, nome);
            requisicao.setString(2, mail);
            requisicao.setString(3, fone);
            // Executando a requisição
            requisicao.execute();
            // Obtendo o id
            ResultSet resultado = requisicao.getGeneratedKeys();
            // next() -> próximo registro
            // previous() -> registro anterior
            // first() -> aponta para o primeiro registro
            // last() -> aponta para o último registro
            if(resultado.next()){
                int idContato = resultado.getInt(1);
                System.out.println("Contato "+nome+" gravado, obteve o id: "+idContato);
            }
            conexao.close();
        } catch (ClassNotFoundException e){
            System.out.println("Erro ao carregar o driver...\n"+e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erro de sql "+e.getMessage());
        }
    }
}
