import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

public class UserCRUD {

    public void create(String surname, String email) {
        SessionFactory sessionFactory = buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        User user = new User();
        user.setSurname(surname);
        user.setEmail(email);

        session.save(user);
        session.getTransaction().commit();

        session.flush();
        session.close();
    }

    public void readAll() {
        SessionFactory sessionFactory = buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        String sql = "From " + User.class.getSimpleName();
        List<User> users = session.createQuery(sql).list();

        users.forEach(System.out::println);

        session.flush();
        session.close();
    }

    public void delete(int id){
        SessionFactory sessionFactory = buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        User user = session.load(User.class, id);

        session.delete(user);

        session.flush();
        session.close();

    }

    public void updateSurname(int id, String surname) {
        SessionFactory sessionFactory = buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        User user = session.load(User.class, id);
        user.setSurname(surname);

        session.update(user);

        session.flush();
        session.close();
    }

    public void updateEmail(int id, String email) {
        SessionFactory sessionFactory = buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();

        User user = session.load(User.class, id);
        user.setEmail(email);

        session.update(user);

        session.flush();
        session.close();
    }

    private static SessionFactory buildSessionFactory() {
        try {
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("persistance.cfg.xml").build();
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

}

