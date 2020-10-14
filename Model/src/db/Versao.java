/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.Date;

/**
 *
 * @author Queiroz
 */
public class Versao implements java.io.Serializable {
    private int id;
    private Date data_versao;
    private String codigo;
    private Usuario user;

    public Versao()
    {
    }
    
    public Versao(Usuario user, Date data, String codigo){
        this.user = user;
        this.data_versao = data;
        this.codigo = codigo;
    }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the data_versao
     */
    public Date getData_versao() {
        return data_versao;
    }

    /**
     * @param data_versao the data_versao to set
     */
    public void setData_versao(Date data_versao) {
        this.data_versao = data_versao;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the user
     */
    public Usuario getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Usuario user) {
        this.user = user;
    }
    
}
