package model;

public interface Emprestavel {
    void emprestar(Usuario usuario);
    void devolver(Usuario usuario);
}
