package session7.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import session7.model.entity.Update;
import session7.model.entity.User;

import java.util.Date;
import java.util.List;

public class UserDao extends BaseDao {
//    public User findById(int id) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        User user = session.load(User.class, id);
//        transaction.commit();
//        session.close();
//        return user;
//    }
    public User findUserByNameAndFamily(String name, String family ,String nationalCode) {
        Session session = sessionFactory.openSession();
        List<User> result;

        Transaction transaction = session.beginTransaction();
        final Query<User> query = session.createQuery("FROM User e WHERE e.name=:name AND e.family=:family AND e.nationalCode=:nationalCode");
        query.setParameter("name", name);
        query.setParameter("family", family);
        query.setParameter("nationalCode", nationalCode);

        result = query.list();
//        transaction.commit();
//        session.close();
        assert result != null;
        try {
            return result.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }



    }


    public void add (User user){
        Session session =sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();

    }


        public List<Update> getUpdatesByUserId(int userId) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            String hql = "from Update updateInfo where updateInfo.user.id=:id";
            System.out.println(hql);
            Query<Update> query = session.createQuery(hql);
            query.setParameter("id", userId);

            return query.list();


        }
    public void updateFirstName(int id, String value) {
        Update update = new Update();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.load(User.class, id);
        update.setUser(user);
        update.setMessage(String.format("changing name to %s", value));
        update.setUpdateTime(new Date());
        user.setName(value);
        session.save(update);
        transaction.commit();
        session.close();
    }

    public void updateLastName(int id, String value) {
        Update update = new Update();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.load(User.class, id);
        update.setUser(user);
        update.setMessage(String.format("changing last name to %s", value));
        update.setUpdateTime(new Date());
        user.setFamily(value);
        session.save(update);
        transaction.commit();
        session.close();
    }

    public void updateNationalCode(int id, String value) {
        Update update = new Update();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.load(User.class, id);
        update.setUser(user);
        update.setMessage(String.format("changing national code to %s", value));
        update.setUpdateTime(new Date());
        user.setNationalCode(value);
        session.save(update);
        transaction.commit();
        session.close();
    }
    }

