package model;

public class Usuario extends Pessoa {
    private String tipo;

    public Usuario(String nome, String id, String tipo) {
        super(nome, id);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
