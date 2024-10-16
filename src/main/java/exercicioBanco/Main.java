package exercicioBanco;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner ler = new Scanner(System.in);
        System.out.println("1 - gravarContato");
        System.out.println("2 - atualizarContato");
        System.out.println("3 - deletarContato(int idContato");
        System.out.println("4 - deletarContato()");
        System.out.println("5 - obterContatoPeloId(int idContato");
        System.out.println("6 - obterContatos()");
        System.out.println("7 - obterListaContato");
        System.out.println("8 - pesquisarContato(String termo");
        System.out.println("9 - toString");
        System.out.print("Selecione: ");
        int op = ler.nextInt();
        Banco b1 = new Banco("julia", "1234");
        Contato c1 = new Contato(b1.obterConexao());
        switch (op){
            case 1 -> {
                c1.gravarContato();
            }
            case 2 -> {
                c1.atualizarContato();
            }
            case 3 -> {
                System.out.print("Digite o id do contato: ");
                int idContato = ler.nextInt();
                c1.deletarContato(idContato);
            }
            case 4 -> {
                c1.deletarContato();
            }
            case 5 -> {
                System.out.print("Digite o id do contato: ");
                int idContato = ler.nextInt();
                c1.obterContatoPeloId(idContato);
            }
            case 6 -> {
                c1.obterContatos();
            }
            case 7 -> {
                c1.obterListaContato();
            }
            case 8 -> {
                System.out.print("Digite o termo da pesquisa: ");
                String termo = ler.next();
                c1.pesquisarContato(termo);
            }
            case 9 -> {
                c1.toString();
            }
        }
    }
}
