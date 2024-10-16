package exercicioBanco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Contato {
    private int idContato;
    private String nome;
    private String email;
    private String telefone;
    private Connection conexao;

    public Contato(Connection conexao){
        this.conexao = conexao;
    }

    public Contato(){};

    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void gravarContato(){
        try{
            String sql = "INSERT INTO tb_contato(nome, e_mail, telefone) VALUES(?, ?, ?)";
            PreparedStatement requisicao = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            Scanner ler = new Scanner(System.in);
            System.out.print("Digite o nome: ");
            String nome = ler.nextLine();
            System.out.print("Digite o e-mail: ");
            String mail = ler.nextLine();
            System.out.print("Qual o telefone: ");
            String fone = ler.nextLine();

            requisicao.setString(1, nome);
            requisicao.setString(2, mail);
            requisicao.setString(3, fone);

            requisicao.execute();

            ResultSet resultado = requisicao.getGeneratedKeys();

            if(resultado.next()){
                int idContato = resultado.getInt(1);
                System.out.println("Contato "+nome+" gravado, obteve o id: "+idContato);
            }
        } catch (SQLException e) {
            System.out.println("Erro de sql "+e.getMessage());
        }
    }

    public void atualizarContato(){
        try{
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

            String sqlAtuliza = "UPDATE tb_contato SET nome = ?, e_mail = ?, telefone = ? WHERE contato_id = ?";

            System.out.print("Qual o novo nome do contato: ");
            String nome = ler.next();
            System.out.print("Qual o novo email do contato: ");
            String email = ler.next();
            System.out.print("Qual o novo telefone do contato: ");
            String fone = ler.next();

            PreparedStatement reqAtualizar = conexao.prepareStatement(sqlAtuliza);

            reqAtualizar.setString(1, nome);
            reqAtualizar.setString(2, email);
            reqAtualizar.setString(3, fone);
            reqAtualizar.setInt(4, idContato);

            int cont = reqAtualizar.executeUpdate();
            if(cont>0){
                System.out.println("Registro atualizado!");
            }else{
                System.out.println("Registro não encontrado!");
            }
        } catch (SQLException e) {
            System.out.println("Erro de sql "+e.getMessage());
        }
    }

    public void deletarContato(int idContato){
        try{
            String sql = "DELETE FROM tb_contato WHERE contato_id=?;";
            PreparedStatement requisicao = conexao.prepareStatement(sql);

            requisicao.setInt(1, idContato);
            requisicao.execute();
            System.out.println("Usuário deletado.");
        } catch (SQLException e) {
            System.out.println("Erro de sql "+e.getMessage());
        }
    }

    public void deletarContato(){
        try{
            String sql = "DELETE FROM tb_contato WHERE contato_id=?;";
            PreparedStatement requisicao = conexao.prepareStatement(sql);

            requisicao.setInt(1, idContato);
            requisicao.execute();
            System.out.println("Usuário deletado.");
        } catch (SQLException e) {
            System.out.println("Erro de sql " + e.getMessage());
        }
    }

    public boolean obterContatoPeloId(int idContato) {
        try {
            String sql = "SELECT * FROM tb_contato where contato_id = ?";
            PreparedStatement requisicao = conexao.prepareStatement(sql);

            requisicao.setInt(1, idContato);
            ResultSet resultado = requisicao.executeQuery();
            while (resultado.next()) {
                String nome = resultado.getString("nome");
                String mail = resultado.getString("e_mail");
                String telefone = resultado.getString("telefone");
                System.out.println("\nId: " + idContato);
                System.out.println("Nome: " + nome);
                System.out.println("E-Mail: " + mail);
                System.out.println("Telefone: " + telefone);
                System.out.println("\n======================================");
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Erro de sql " + e.getMessage());
            return false;
        }
    }

    public ResultSet obterContatos() throws SQLException{
        String sql = "SELECT * FROM tb_contato";
        PreparedStatement requisicao = conexao.prepareStatement(sql);
        return requisicao.executeQuery();
    }

    public List obterListaContato(){
        List<Contato> contatos = new ArrayList<>();
        try{
            String sql = "SELECT * FROM tb_contato";
            PreparedStatement requisicao = conexao.prepareStatement(sql);
            ResultSet resultado = requisicao.executeQuery();
            while(resultado.next()){
                int idContato = resultado.getInt("contato_id");
                String nome = resultado.getString("nome");
                String mail = resultado.getString("e_mail");
                String telefone = resultado.getString("telefone");
                Contato c1 = new Contato();
                c1.idContato = idContato;
                c1.nome = nome;
                c1.email = mail;
                c1.telefone = telefone;
                contatos.add(c1);
                System.out.println("\nId: "+idContato);
                System.out.println("Nome: "+nome);
                System.out.println("E-Mail: "+mail);
                System.out.println("Telefone: "+telefone);
                System.out.println("\n======================================");
            }
            return contatos;
        } catch (SQLException e) {
            System.out.println("Erro de sql "+e.getMessage());
            return contatos;
        }
    }

    public List pesquisarContato(String termo){
        List<Contato> contatos = new ArrayList<>();
        try{
            String sql = "SELECT * FROM tb_contato where nome like ? or e_mail like ? or telefone like ?";
            PreparedStatement requisicao = conexao.prepareStatement(sql);

            requisicao.setString(1, "%"+termo+"%");
            requisicao.setString(2, "%"+termo+"%");
            requisicao.setString(3, "%"+termo+"%");
            ResultSet resultado = requisicao.executeQuery();
            while(resultado.next()){
                int idContato = resultado.getInt("contato_id");
                String nome = resultado.getString("nome");
                String mail = resultado.getString("e_mail");
                String telefone = resultado.getString("telefone");
                Contato c1 = new Contato();
                c1.idContato = idContato;
                c1.nome = nome;
                c1.email = mail;
                c1.telefone = telefone;
                contatos.add(c1);
            }
            return contatos;
        } catch (SQLException e) {
            System.out.println("Erro de sql "+e.getMessage());
            return contatos;
        }
    }

    @Override
    public String toString(){
        return "\nId: " + idContato + "\nNome: " + nome + "\nE-Mail: " + email + "\nTelefone: " + telefone;
    }
}
