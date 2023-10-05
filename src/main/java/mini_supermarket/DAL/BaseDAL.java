package mini_supermarket.DAL;

import mini_supermarket.DTO.BaseDTO;
import mini_supermarket.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class BaseDAL<DTO extends BaseDTO, ID extends Serializable> {
    protected final Class<DTO> type;
    protected List<String> columnNames;
    protected Session session;

    public BaseDAL(Class<DTO> type, List<String> columnNames) {
        this.type = type;
        this.columnNames = columnNames;
        this.session = null;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void openSession() {
        if (session == null)
            session = HibernateUtil.getSession();
    }

    public void closeSession() {
        HibernateUtil.closeSession(session);
        session = null;
    }

    public DTO getById(ID id) {
        openSession();
        return session.get(type, id);
    }

    public List<DTO> getBy(Object... attributes) {
        openSession();
        if (attributes.length % 2 != 0) {
            throw new IllegalArgumentException("Attribute-value pairs must be provided.");
        }

        StringBuilder hql = new StringBuilder("FROM ").append(type.getSimpleName());

        if (attributes.length > 0) {
            hql.append(" WHERE ");
            for (int i = 0; i < attributes.length; i += 2) {
                hql.append(attributes[i]).append(" = :param").append(i / 2).append(" AND ");
            }
            hql.setLength(hql.length() - 5);
        }

        Query<DTO> query = session.createQuery(hql.toString(), type);

        for (int i = 0; i < attributes.length; i += 2) {
            query.setParameter("param" + (i / 2), attributes[i + 1]);
        }

        List<DTO> result = query.list();
        closeSession();
        return result;
    }

    public List<DTO> getAll() {
        return getBy();
    }

    public boolean persist(DTO object) {
        openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(object);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            System.out.println("Error occurred in BaseDAL.persist(): " + e.getMessage());
            return false;
        } finally {
            closeSession();
        }
    }

    public boolean merge(DTO object) {
        openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(object);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            System.out.println("Error occurred in BaseDAL.merge(): " + e.getMessage());
            return false;
        } finally {
            closeSession();
        }
    }

    public boolean remove(DTO object) {
        openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.remove(object);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null)
                transaction.rollback();
            System.out.println("Error occurred in BaseDAL.remove(): " + e.getMessage());
            return false;
        } finally {
            closeSession();
        }
    }
}
