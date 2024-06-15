package service;

import model.Livro;
import model.Usuario;
import model.Emprestimo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BibliotecaService {
    private List<Livro> livros;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;

    public BibliotecaService() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    // Métodos para gerenciar livros
    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void removerLivro(Livro livro) {
        livros.remove(livro);
    }

    public void atualizarLivro(String isbn, Livro livroAtualizado) {
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getIsbn().equals(isbn)) {
                livros.set(i, livroAtualizado);
                break;
            }
        }
    }

    public List<Livro> listarLivros() {
        return livros;
    }

    // Métodos para gerenciar usuários
    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void removerUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }

    public void atualizarUsuario(String id, Usuario usuarioAtualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().equals(id)) {
                usuarios.set(i, usuarioAtualizado);
                break;
            }
        }
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    // Métodos para gerenciar empréstimos
    public void emprestarLivro(Livro livro, Usuario usuario) {
        if (livro.isDisponivel()) {
            Emprestimo emprestimo = new Emprestimo(livro, usuario, new Date());
            emprestimos.add(emprestimo);
            livro.setDisponivel(false);
        } else {
            System.out.println("Livro não está disponível para empréstimo.");
        }
    }

    public void devolverLivro(Livro livro, Usuario usuario) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().equals(livro) && emprestimo.getUsuario().equals(usuario) && emprestimo.getDataDevolucao() == null) {
                emprestimo.setDataDevolucao(new Date());
                livro.setDisponivel(true);
                break;
            }
        }
    }

    public List<Emprestimo> listarEmprestimos() {
        return emprestimos;
    }

    // Métodos extras
    public List<Livro> buscarLivrosPorTitulo(String titulo) {
        return livros.stream()
                .filter(livro -> livro.getTitulo().equalsIgnoreCase(titulo))
                .collect(Collectors.toList());
    }

    public List<Livro> buscarLivrosPorAutor(String autor) {
        return livros.stream()
                .filter(livro -> livro.getAutor().equalsIgnoreCase(autor))
                .collect(Collectors.toList());
    }

    public Livro buscarLivroPorIsbn(String isbn) {
        return livros.stream()
                .filter(livro -> livro.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    // Métodos de relatórios
    public void gerarRelatorioEmprestimos() {
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println("Livro: " + emprestimo.getLivro().getTitulo() +
                    ", Usuário: " + emprestimo.getUsuario().getNome() +
                    ", Data de Empréstimo: " + emprestimo.getDataEmprestimo() +
                    ", Data de Devolução: " + (emprestimo.getDataDevolucao() == null ? "Ainda não devolvido" : emprestimo.getDataDevolucao()));
        }
    }

    public void gerarRelatorioUsuariosComMultas() {
        // Implementação para gestão de multas
    }
}
