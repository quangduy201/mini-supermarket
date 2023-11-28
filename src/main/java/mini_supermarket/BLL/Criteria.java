package mini_supermarket.BLL;

import jakarta.persistence.criteria.*;
import mini_supermarket.DTO.BaseDTO;
import mini_supermarket.utils.Date;
import mini_supermarket.utils.DateTime;
import mini_supermarket.utils.VNString;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

public class Criteria<DTO extends BaseDTO> {
    protected final Class<DTO> type;
    protected final HibernateCriteriaBuilder builder;
    protected final CriteriaQuery<DTO> criteriaQuery;
    protected final Root<DTO> root;

    public Criteria(BaseBLL<DTO, ?> baseBLL) {
        this.type = baseBLL.getDAL().getType();
        this.builder = baseBLL.getDAL().getBuilder();
        baseBLL.getDAL().setCriteriaQuery(builder.createQuery(type));
        this.criteriaQuery = baseBLL.getDAL().getCriteriaQuery();
        this.root = criteriaQuery.from(type);
    }

    public <Y> Join<DTO, Y> join(String attributeName) {
        return root.join(attributeName);
    }

    public Criteria<DTO> where(Predicate... predicates) {
        criteriaQuery.where(predicates);
        return this;
    }

    public Predicate not(Expression<Boolean> expression) {
        return builder.not(expression);
    }

    public Predicate and(Predicate... predicates) {
        return builder.and(predicates);
    }

    public Predicate or(Predicate... predicates) {
        return builder.or(predicates);
    }

    public Predicate equal(Expression<?> attribute, Object value) {
        return builder.equal(attribute, value);
    }

    public Predicate equal(String attribute, Object value) {
        attribute = VNString.snakeCaseToCamelCase(attribute);
        return builder.equal(root.get(attribute), value);
    }

    public Predicate notEqual(Expression<?> attribute, Object value) {
        return builder.notEqual(attribute, value);
    }

    public Predicate notEqual(String attribute, Object value) {
        attribute = VNString.snakeCaseToCamelCase(attribute);
        return builder.notEqual(root.get(attribute), value);
    }

    public Predicate lt(Expression<Number> attribute, Number value) {
        return builder.lt(attribute, value);
    }

    public Predicate lt(String attribute, Number value) {
        attribute = VNString.snakeCaseToCamelCase(attribute);
        return builder.lt(root.get(attribute), value);
    }

    public Predicate le(Expression<Number> attribute, Number value) {
        return builder.le(attribute, value);
    }

    public Predicate le(String attribute, Number value) {
        attribute = VNString.snakeCaseToCamelCase(attribute);
        return builder.le(root.get(attribute), value);
    }

    public Predicate gt(Expression<Number> attribute, Number value) {
        return builder.gt(attribute, value);
    }

    public Predicate gt(String attribute, Number value) {
        attribute = VNString.snakeCaseToCamelCase(attribute);
        return builder.gt(root.get(attribute), value);
    }

    public Predicate ge(Expression<Number> attribute, Number value) {
        return builder.ge(attribute, value);
    }

    public Predicate ge(String attribute, Number value) {
        attribute = VNString.snakeCaseToCamelCase(attribute);
        return builder.ge(root.get(attribute), value);
    }

    public Predicate like(Expression<String> attribute, String value) {
        return builder.like(attribute, value);
    }

    public Predicate like(String attribute, String value) {
        attribute = VNString.snakeCaseToCamelCase(attribute);
        return builder.like(root.get(attribute), value);
    }

    public Predicate between(Expression<Integer> attribute, int start, int end) {
        return builder.between(attribute, start, end);
    }

    public Predicate between(Expression<String> attribute, String start, String end) {
        return builder.between(attribute, start, end);
    }

    public Predicate between(String attribute, int start, int end) {
        attribute = VNString.snakeCaseToCamelCase(attribute);
        return builder.between(root.get(attribute), start, end);
    }

    public Predicate between(String attribute, String start, String end) {
        attribute = VNString.snakeCaseToCamelCase(attribute);
        return builder.between(root.get(attribute), start, end);
    }

    public Predicate between(String attribute, Date start, Date end) {
        attribute = VNString.snakeCaseToCamelCase(attribute);
        return builder.between(root.get(attribute), start.date, end.date);
    }

    public Predicate between(String attribute, DateTime start, DateTime end) {
        attribute = VNString.snakeCaseToCamelCase(attribute);
        return builder.between(root.get(attribute), start.dateTime, end.dateTime);
    }

    public Expression<Long> count(Expression<?> expression) {
        return builder.count(expression);
    }

    public Expression<Double> avg(Expression<Number> expression) {
        return builder.avg(expression);
    }

    public Expression<Number> sum(Expression<Number> expression) {
        return builder.sum(expression);
    }

    public Order asc(String attribute) {
        return builder.asc(root.get(attribute));
    }

    public Order desc(String attribute) {
        return builder.desc(root.get(attribute));
    }
}
