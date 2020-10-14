/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.query;

import db.Usuario;
import hibernate.util.HibernateUtil;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Augusto
 */
public class QUsuario {

    public QUsuario(){
        
    }

    /**
     * Insere no banco o usuario
     * @param nome Nome do Usuario
     * @param login Login do Usuario
     * @param pwd Senha do Usuario
     * @return 
     */
    public Integer insert(String nome, String login, String pwd) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer usu_id = -1;
        try {
            transaction = session.beginTransaction();
            Usuario u = new Usuario(nome, login, pwd, true);
            usu_id = (Integer)session.save(u);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return usu_id;
    }

    /**
     * Lista todos os usuarios no banco
     */
    public void list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List usuarios = session.createQuery("from Usuario").list();
            for (Iterator iterator = usuarios.iterator(); iterator.hasNext();) {
                Usuario usuario = (Usuario) iterator.next();
                System.out.println(usuario.getNome());
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Usuario BuscaUsuario(Integer id_usuario){
        Usuario user = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        user = (Usuario)session.get(Usuario.class, id_usuario);
        session.getTransaction().commit();

        return user;
    }

    public boolean DeletaUsuario(Integer id_usuario){
       Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Usuario user = BuscaUsuario(id_usuario);
            if(user == null)
            {
                return false;
            }
            session.delete(user);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return true;
    }
}
