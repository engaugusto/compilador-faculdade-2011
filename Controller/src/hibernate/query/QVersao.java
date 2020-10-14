/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.query;

import db.Usuario;
import db.Versao;
import hibernate.util.HibernateUtil;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Queiroz
 */
public class QVersao {
    public Integer GravarVersao(String codigo, Usuario usr) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Integer ver_id = -1;
        try {
            transaction = session.beginTransaction();
            Date d = new Date();
             
            Versao v = new Versao(usr, d, codigo);
            ver_id = (Integer)session.save(v);
            transaction.commit();
        }catch(HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
        return ver_id;
    }
}
