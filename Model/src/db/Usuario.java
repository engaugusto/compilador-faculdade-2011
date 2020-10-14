/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author Augusto
 */
public class Usuario implements java.io.Serializable {

    private int id;
    private String login;
    private String pwd;
    private String nome;
    private boolean ativo;

    /**
     * @return the _id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the _id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the _login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the _login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the _pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd the _pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * @return the _nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the _nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the _ativo
     */
    public boolean isAtivo() {
        return ativo;
    }

    /**
     * @param ativo the _ativo to set
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Usuario() {
    }

    public Usuario(String nome, String login, String pwd, boolean ativo) {
        this.nome = nome;
        this.login = login;
        this.pwd = pwd;
        this.ativo = ativo;
    }

    public Usuario(int id, String nome, String login, String pwd, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.pwd = pwd;
        this.ativo = ativo;
    }
}
