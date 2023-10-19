package mini_supermarket.DAL;

import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import mini_supermarket.DTO.BaseDTO;
import mini_supermarket.utils.HibernateUtil;
import mini_supermarket.utils.Log;
import mini_supermarket.utils.VNString;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.SelectionQuery;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;

import java.io.Serializable;
import java.util.List;

public class BaseDAL<DTO extends BaseDTO, ID extends Serializable> {
    protected final Class<DTO> type;
    protected List<String> columnNames;
    protected Session session;
    protected HibernateCriteriaBuilder builder;
    protected JpaCriteriaQuery<DTO> criteriaQuery;

    public BaseDAL(Class<DTO> type, List<String> columnNames) {
        this.type = type;
        this.columnNames = columnNames;
        this.session = null;
        this.builder = HibernateUtil.getSessionFactory().getCriteriaBuilder();
    }

    public Class<DTO> getType() {
        return type;
    }

    public HibernateCriteriaBuilder getBuilder() {
        return builder;
    }

    public JpaCriteriaQuery<DTO> getCriteriaQuery() {
        return criteriaQuery;
    }

    public void setCriteriaQuery(JpaCriteriaQuery<DTO> criteriaQuery) {
        this.criteriaQuery = criteriaQuery;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void openSession() {
        if (session == null)
            session = HibernateUtil.getSessionFactory().openSession();
    }

    public void closeSession() {
        if (session != null)
            session.close();
        session = null;
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
            Log.error(BaseDAL.class, "Error while persisting object: " + object + " - " + e);
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
            Log.error(BaseDAL.class, "Error while merging object: " + object + " - " + e);
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
            Log.error(BaseDAL.class, "Error while removing object: " + object + " - " + e);
            return false;
        } finally {
            closeSession();
        }
    }

    public long count() {
        openSession();
        long count;
        try {
            count = session.createQuery("SELECT COUNT(e) FROM " + type.getSimpleName() + " e", Long.class).getSingleResult();
        } catch (Exception e) {
            Log.error(BaseDAL.class, "Error while counting records: " + e);
            count = -1;
        } finally {
            closeSession();
        }
        return count;
    }

    public DTO getById(ID id) {
        openSession();
        return session.get(type, id);
    }

    public List<DTO> getByAttributes(int pageNumber, int pageSize, Object... attributes) {
        openSession();
        try {
            if (attributes.length % 2 != 0) {
                throw new IllegalArgumentException("Attribute-value pairs must be provided.");
            }

            StringBuilder hql = new StringBuilder("FROM ").append(type.getSimpleName());
            if (attributes.length > 0) {
                hql.append(" WHERE ");
                for (int i = 0; i < attributes.length; i += 2) {
                    hql.append(VNString.snakeCaseToCamelCase((String) attributes[i])).append(" = :param").append(i / 2).append(" AND ");
                }
                hql.setLength(hql.length() - 5);
            }

            SelectionQuery<DTO> query = session.createSelectionQuery(hql.toString(), type);
            for (int i = 0; i < attributes.length; i += 2) {
                query.setParameter("param" + (i / 2), attributes[i + 1]);
            }

            if (pageNumber > 0 || pageSize > 0) {
                query.setFirstResult((pageNumber - 1) * pageSize);
                query.setMaxResults(pageSize);
            }
            return query.getResultList();
        } catch (Exception e) {
            Log.error(BaseDAL.class, "Error while retrieving records by attributes: " + e);
            return List.of();
        } finally {
            closeSession();
        }
    }

    public List<DTO> getByCriteria(int pageNumber, int pageSize, Object... predicatesAndOrderings) {
        openSession();
        try {
            criteriaQuery.select(criteriaQuery.getSelection());
            Predicate predicates = null;
            for (Object criteria : predicatesAndOrderings) {
                if (criteria instanceof Predicate) {
                    if (predicates == null)
                        predicates = (Predicate) criteria;
                    else
                        predicates = builder.and(predicates, (Predicate) criteria);
                } else if (criteria instanceof Order) {
                    criteriaQuery.orderBy((Order) criteria);
                }
            }
            criteriaQuery.where(predicates);
            SelectionQuery<DTO> query = session.createSelectionQuery(criteriaQuery);
            if (pageNumber > 0 || pageSize > 0) {
                query.setFirstResult((pageNumber - 1) * pageSize);
                query.setMaxResults(pageSize);
            }
            return query.getResultList();
        } catch (Exception e) {
            Log.error(BaseDAL.class, "Error while retrieving records by criteria: " + e);
            return List.of();
        } finally {
            criteriaQuery = null;
            closeSession();
        }
    }

    public List<DTO> getByPage(int pageNumber, int pageSize) {
        openSession();
        try {
            SelectionQuery<DTO> query = session.createSelectionQuery("FROM " + type.getSimpleName(), type);
            if (pageNumber > 0 || pageSize > 0) {
                query.setFirstResult((pageNumber - 1) * pageSize);
                query.setMaxResults(pageSize);
            }
            return query.getResultList();
        } catch (Exception e) {
            Log.error(BaseDAL.class, "Error while retrieving records by page: " + e);
            return List.of();
        } finally {
            closeSession();
        }
    }

    public List<DTO> getAll() {
        return getByPage(0, 0);
    }

    public List<DTO> executeQuery(String query) {
        openSession();
        try {
            Query<DTO> customQuery = session.createQuery(query, type);
            return customQuery.list();
        } catch (Exception e) {
            Log.error(BaseDAL.class, "Error while executing custom query: " + e);
            return List.of();
        } finally {
            closeSession();
        }
    }
}
