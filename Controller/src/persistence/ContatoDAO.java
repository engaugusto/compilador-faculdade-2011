/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author Andreia
 */
public class ContatoDAO {

    private static ContatoDAO instance;

    public static ContatoDAO getInstance() {
        if (instance == null) {
            instance = new ContatoDAO();
        }
        return instance;
    }

    private ContatoDAO() {
    }
    /*public int GravarContato(Contato cont) throws SQLException, ClassNotFoundException, Exception {
    PreparedStatement ps = null;
    Connection conn = null;
    //ResultSet rs = null;
    int id = 0;
    
    try{
    conn = Conexao.getInstance().getConnection();
    String sql = "insert into contato values ('"+cont.getCodigo()+"','"+cont.getNome()+"','"+
    cont.getEmail()+"')";
    
    
    ps = conn.prepareStatement(sql);
    ps.execute();
    
    /*while(rs.next()){
    int vId = rs.getInt(1);
    id = vId;
    }*//*
    return id;
    
    
    
    } catch(SQLException e){
    throw e;
    
    }
    catch(Exception se){
    throw se;
    
    }finally {
    try {
    if(ps!=null) ps.close();
    if(conn!=null) conn.close();
    } catch(SQLException e){
    }
    }
    }*/

}
