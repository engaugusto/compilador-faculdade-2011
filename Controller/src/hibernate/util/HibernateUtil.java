/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Singleton usado para o acesso ao banco
 * @author Augusto
 */
public class HibernateUtil {
    /**
     * Session Factory
     */
    private static final SessionFactory sessionFactory;
    
    /**
     * Propriedade estatica usada quando e chamado a classe
     */
    static {
        try {
            sessionFactory = new Configuration().configure()
                    .buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    /**
     * Retorna a Session do Banco
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
