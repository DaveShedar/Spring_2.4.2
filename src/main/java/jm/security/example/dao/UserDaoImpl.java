package jm.security.example.dao;

import jm.security.example.model.Role;
import jm.security.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    public Session getSession() {
        if(session == null){
            session = sessionFactory.openSession();
        }
        return session;
    }

    @Override
    public User getUserByName(String name) {
        Transaction transaction = getSession().beginTransaction();
        Query query = getSession().createQuery("FROM User u WHERE u.username =:name");
        query.setParameter("name", name);
        List< User > results = query.getResultList();
        User user = results.get(0);
        transaction.commit();
        return user;
    }


    @Override
    public List< User > getUsers() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery< User > cq = cb.createQuery(User.class);
        Root< User > root = cq.from(User.class);
        cq.select(root);
        Query query = session.createQuery(cq);
        List<User> list = query.getResultList();
        return list;
    }

    @Override
    public void deleteUser(Long id) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User u WHERE u.id =:id");
        query.setParameter("id", id);
        List< User > results = query.getResultList();
        User user = results.get(0);
        session.delete(user);
        transaction.commit();
    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        if(user.getRoles() == null){
            user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        }
        session.saveOrUpdate(user);
        session.getTransaction().commit();
    }

    @Override
    public User getUser(Long id) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User u WHERE u.id =:id");
        query.setParameter("id", id);
        List< User > results = query.getResultList();
        User user = results.get(0);
        transaction.commit();
        return user;
    }
}