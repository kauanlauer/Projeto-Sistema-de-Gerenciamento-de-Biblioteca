package main;

import model.Livro;
import model.Usuario;
import service.BibliotecaService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BibliotecaService biblioteca = new BibliotecaService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nSistema de Gerenciamento de Biblioteca");
            System.out.println("1. Gerenciar Livros");
            System.out.println("2. Gerenciar Usuários");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    gerenciarLivros(biblioteca, scanner);
                    break;
                case 2:
                    gerenciarUsuarios(biblioteca, scanner);
                    break;
                case 3:
                    System.out.println("Saindo do sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void gerenciarLivros(BibliotecaService biblioteca, Scanner scanner) {
        while (true) {
            System.out.println("\nGerenciamento de Livros");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Remover Livro");
            System.out.println("3. Atualizar Livro");
            System.out.println("4. Listar Livros");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = scanner.nextLine();
                    Livro livro = new Livro(titulo, autor, isbn);
                    biblioteca.adicionarLivro(livro);
                    System.out.println("Livro adicionado com sucesso!");
                    break;
                case 2:
                    System.out.print("ISBN do livro a ser removido: ");
                    isbn = scanner.nextLine();
                    livro = biblioteca.buscarLivroPorIsbn(isbn);
                    if (livro != null) {
                        biblioteca.removerLivro(livro);
                        System.out.println("Livro removido com sucesso!");
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("ISBN do livro a ser atualizado: ");
                    isbn = scanner.nextLine();
                    livro = biblioteca.buscarLivroPorIsbn(isbn);
                    if (livro != null) {
                        System.out.print("Novo Título: ");
                        titulo = scanner.nextLine();
                        System.out.print("Novo Autor: ");
                        autor = scanner.nextLine();
                        Livro livroAtualizado = new Livro(titulo, autor, isbn);
                        biblioteca.atualizarLivro(isbn, livroAtualizado);
                        System.out.println("Livro atualizado com sucesso!");
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("Livros na biblioteca:");
                    for (Livro l : biblioteca.listarLivros()) {
                        System.out.println("Título: " + l.getTitulo() + ", Autor: " + l.getAutor() + ", ISBN: " + l.getIsbn() + ", Disponível: " + l.isDisponivel());
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void gerenciarUsuarios(BibliotecaService biblioteca, Scanner scanner) {
        while (true) {
            System.out.println("\nGerenciamento de Usuários");
            System.out.println("1. Registrar Usuário");
            System.out.println("2. Remover Usuário");
            System.out.println("3. Atualizar Usuário");
            System.out.println("4. Listar Usuários");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Tipo (Aluno, Professor, etc.): ");
                    String tipo = scanner.nextLine();
                    Usuario usuario = new Usuario(nome, id, tipo);
                    biblioteca.registrarUsuario(usuario);
                    System.out.println("Usuário registrado com sucesso!");
                    break;
                case 2:
                    System.out.print("ID do usuário a ser removido: ");
                    id = scanner.nextLine();
                    usuario = biblioteca.listarUsuarios().stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
                    if (usuario != null) {
                        biblioteca.removerUsuario(usuario);
                        System.out.println("Usuário removido com sucesso!");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("ID do usuário a ser atualizado: ");
                    id = scanner.nextLine();
                    usuario = biblioteca.listarUsuarios().stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
                    if (usuario != null) {
                        System.out.print("Novo Nome: ");
                        nome = scanner.nextLine();
                        System.out.print("Novo Tipo (Aluno, Professor, etc.): ");
                        tipo = scanner.nextLine();
                        Usuario usuarioAtualizado = new Usuario(nome, id, tipo);
                        biblioteca.atualizarUsuario(id, usuarioAtualizado);
                        System.out.println("Usuário atualizado com sucesso!");
                    } else {
                        System.out.println("Usuário não encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("Usuários registrados:");
                    for (Usuario u : biblioteca.listarUsuarios()) {
                        System.out.println("Nome: " + u.getNome() + ", ID: " + u.getId() + ", Tipo: " + u.getTipo());
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}
