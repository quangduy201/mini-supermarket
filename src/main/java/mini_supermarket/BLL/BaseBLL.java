package mini_supermarket.BLL;

import mini_supermarket.DAL.BaseDAL;
import mini_supermarket.DTO.BaseDTO;
import mini_supermarket.utils.Pair;
import mini_supermarket.utils.VNString;
import org.hibernate.HibernateException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

public abstract class BaseBLL<DTO extends BaseDTO, ID extends Serializable> {
    private final BaseDAL<DTO, ID> DAL;

    public BaseBLL(BaseDAL<DTO, ID> DAL) {
        this.DAL = DAL;
    }

    public BaseDAL<DTO, ID> getDAL() {
        return DAL;
    }

    public boolean add(DTO object) {
        return DAL.persist(object);
    }

    public boolean update(DTO object) {
        return DAL.merge(object);
    }

    public boolean delete(DTO object) {
        return DAL.remove(object);
    }

    public long count() {
        return DAL.count();
    }

    /**
     * Searches for DTO objects in the database based on the specified attributes and their corresponding values.
     * The method allows for flexible filtering of DTO objects by providing zero or more attribute-value pairs.
     *
     * @param attributes A variable number of attribute-value pairs to filter the DTO objects.
     *                   Attributes should be defined as static fields in the __ class.
     * @return A list of DTO objects that match the specified attributes and their values.
     * @example Here's an example of how to use the `findBy` method to retrieve a list of customers are members and are female:
     * <pre>
     * CustomerBLL customerBLL = new CustomerBLL();
     * List&lt;Customer&gt; customers = customerBLL.findBy(
     *     __.CUSTOMER.MEMBERSHIP, true,
     *     __.CUSTOMER.GENDER, false);
     * </pre>
     * In this example, the method filters customers based on the provided attributes and their values,
     * returning a list of matching DTO objects.
     */
    public List<DTO> findBy(Object... attributes) {
        return findIn(0, 0, attributes);
    }

    public List<DTO> findIn(int pageNumber, int pageSize, Object... attributes) {
        return DAL.getByAttributes(pageNumber, pageSize, attributes);
    }

    /**
     * Searches for DTO objects in the database based on the specified criteria.
     * The method allows for flexible filtering of DTO objects by providing zero or more criteria.
     *
     * @param criteria A variable number of criteria to filter the DTO objects.
     *                 Attributes should be defined as static fields in the DTO class.
     * @return A list of DTO objects that match the specified criteria.
     * @example Here's an example of how to use the `findByCriteria` method to retrieve a list of customers whose signed up date is between 01/01/2020 and today,
     * also their name contains "NGUYỄN". Then order them ascending by their birthdate:
     * <pre>
     * CustomerBLL customerBLL = new CustomerBLL();
     * Criteria&lt;Customer&gt; c = new Criteria(customerBLL);
     * List&lt;Customer&gt; customers = customerBLL.findByCriteria(
     *     c.between(__.CUSTOMER.SIGNED_UP_DATE, Date.of(2020, 1, 1), Date.now()),
     *     c.like(__.CUSTOMER.NAME, "%NGUYỄN%"),
     *     c.asc(__.CUSTOMER.BIRTHDATE));
     * </pre>
     */
    public List<DTO> findByCriteria(Object... criteria) {
        return findByCriteriaIn(0, 0, criteria);
    }

    public List<DTO> findByCriteriaIn(int pageNumber, int pageSize, Object... criteria) {
        return DAL.getByCriteria(pageNumber, pageSize, criteria);
    }

    /**
     * Searches for DTO objects in the database which are in the specific page of the pagination.
     *
     * @param pageNumber A specific page index.
     * @param pageSize   A number of DTO objects per page.
     * @return A list of DTO objects that are in a specific page of the pagination.
     * @example Here's an example of how to use the `findByPage` method to retrieve a list of customers in the third page with the page size of 50 DTO objects:
     * <pre>
     * CustomerBLL customerBLL = new CustomerBLL();
     * List&lt;Customer&gt; customers = customerBLL.findByPage(3, 50);
     * </pre>
     */
    public List<DTO> findByPage(int pageNumber, int pageSize) {
        return DAL.getByPage(pageNumber, pageSize);
    }

    /**
     * Get all the DTO objects in the database
     *
     * @return A list of DTO objects
     */
    public List<DTO> findAll() {
        return DAL.getAll();
    }

    /**
     * This method allows you to specify a subset of columns to retrieve from the DTO objects,
     * creating a table with the selected data. The table is represented as a two-dimensional array
     * with rows corresponding to DTO objects and columns corresponding to the selected columns.
     * The order of the columns can be altered.
     *
     * @param objectList          A list of DTO objects from which to retrieve data.
     * @param addNumberingColumn  A boolean parameter indicating whether to add a numbering column
     *                           to the resulting table.
     * @param columns             A variable number of column names to select from each DTO object.
     *                           If not provided, all available columns are selected.
     * @return A two-dimensional array representing the selected data from the DTO objects.
     * @example Here's an example of how to use the `getData` method to retrieve specific columns
     * from a list of accounts:
     * <pre>
     * AccountBLL accountBLL = new AccountBLL();
     * List&lt;Account&gt; accounts = accountBLL.findAll();
     * Object[][] objects = accountBLL.getData(accounts, true,
     *     __.ACCOUNT.COLUMN.ID,
     *     __.ACCOUNT.COLUMN.USERNAME,
     *     __.ROLE.COLUMN.NAME,
     *     __.STAFF.COLUMN.NAME);
     * System.out.println(Arrays.deepToString(objects));
     * </pre>
     * In this example, the method retrieves the "id" and "username" columns from the Account objects,
     * as well as the "name" column from associated Role objects and the "name" column from associated Staff objects.
     * The resulting two-dimensional array contains the selected data for further processing or display.
     */
    public Object[][] getData(List<DTO> objectList, boolean addNumberingColumn, String... columns) {
        List<String> fullColumns = DAL.getColumnNames();
        if (columns == null || columns.length == 0) {
            columns = fullColumns.toArray(new String[0]);
        }

        List<Integer> indices = new ArrayList<>();
        for (String column : columns) {
            int index = fullColumns.indexOf(column);
            if (index != -1)
                indices.add(index); // Add the index of matching element in fullColumns
        }

        int numberOfColumns = addNumberingColumn ? indices.size() + 1 : indices.size();
        Object[][] table = new Object[objectList.size()][numberOfColumns];
        for (int i = 0; i < table.length; i++) {
            Object[] data = objectList.get(i).toString().split(Pattern.quote(VNString.NULL));
            for (int j = 0; j < indices.size(); j++) {
                table[i][j + (addNumberingColumn ? 1 : 0)] = data[indices.get(j)];
            }
            if (addNumberingColumn)
                table[i][0] = i + 1;
        }
        return table;
    }

    /**
     * Retrieves data from a list of DTO objects and creates a table with the selected data. The table
     * is represented as a two-dimensional array with rows corresponding to DTO objects and columns
     * corresponding to the selected data or columns obtained from converters. A numbering column can
     * be added as the first column if specified.
     *
     * @param objectList          A list of DTO objects from which to retrieve data.
     * @param addNumberingColumn  A boolean parameter indicating whether to add a numbering column
     *                           to the resulting table.
     * @param converters          A list of pairs containing column names and conversion functions
     *                           to transform the data from DTO objects.
     * @return A two-dimensional array representing the selected or converted data from the DTO objects.
     * @example Here's an example of how to use the `getData` method to retrieve and convert specific
     * columns from a list of DTO objects with or without a numbering column:
     * <pre>
     * List<DTO> accounts = // Your list of DTO objects (e.g., Account objects)
     * AccountBLL accountBLL = new AccountBLL();
     *
     * // Define a list of converters to specify how to transform the data.
     * List<Pair<String, Function<String, ?>>> converters = List.of(
     *     new Pair<>(__.ACCOUNT.COLUMN.USERNAME, String::toString),
     *     new Pair<>(__.STAFF.COLUMN.NAME, String::toString),
     *     new Pair<>(__.STAFF.COLUMN.GENDER, s -> Boolean.parseBoolean(s) ? "Nam" : "Nữ"),
     *     new Pair<>(__.STAFF.COLUMN.BIRTHDATE, Date::parse),
     *     new Pair<>(__.ROLE.COLUMN.NAME, String::toString),
     *     new Pair<>(__.STAFF.COLUMN.EMAIL, String::toString)
     * );
     *
     * // Retrieve and convert the data, including a numbering column.
     * Object[][] data = accountBLL.getData(accounts, true, converters);
     *
     * // The resulting 'data' array contains the transformed data with a numbering column.
     * // You can now use this data for further processing or display.
     * System.out.println(Arrays.deepToString(data));
     * </pre>
     * In this example, the method retrieves and converts specific data from the list of accounts using
     * the provided converters. It includes a numbering column in the resulting table for easy reference.
     */
    public Object[][] getData(List<DTO> objectList, boolean addNumberingColumn,
                              List<Pair<String, Function<String, ?>>> converters) {
        List<String> fullColumns = DAL.getColumnNames();
        if (converters == null || converters.isEmpty()) {
            converters = new ArrayList<>();
            for (String fullColumn : fullColumns) {
                converters.add(new Pair<>(fullColumn, s -> s));
            }
        }

        List<Integer> indices = new ArrayList<>();
        for (Pair<String, Function<String, ?>> converter : converters) {
            int index = fullColumns.indexOf(converter.getFirst());
            if (index != -1)
                indices.add(index); // Add the index of matching element in fullColumns
        }

        int numberOfColumns = addNumberingColumn ? indices.size() + 1 : indices.size();
        Object[][] table = new Object[objectList.size()][numberOfColumns];
        for (int i = 0; i < table.length; i++) {
            Object[] data = objectList.get(i).toString().split(Pattern.quote(VNString.NULL));
            for (int j = 0; j < indices.size(); j++) {
                Function<String, ?> converter = converters.get(j).getSecond();
                table[i][j + (addNumberingColumn ? 1 : 0)] = converter.apply((String) data[indices.get(j)]);
            }
            if (addNumberingColumn)
                table[i][0] = i + 1;
        }
        return table;
    }

    /**
     * Executes a function on a DTO object identified by its unique ID and returns the result.
     * This method opens a session, retrieves a DTO object by its unique ID, applies the provided
     * function to it, and then closes the session.
     *
     * @param id   The unique identifier of the DTO object.
     * @param code The function to be executed on the DTO object.
     * @param <R>  The type of the result returned by the function.
     * @return The result of executing the provided function on the DTO object.
     * @throws HibernateException If there is an issue with the Hibernate session.
     * @example Here's an example of how to use the `doSomethingOn` method
     * to get all the products of Coca-Cola brand:
     * <pre>
     * BrandBLL brandBLL = new BrandBLL();
     * Brand brand = brandBLL.findBy(__.BRAND.NAME, "Coca-Cola").get(0);
     *
     * brandBLL.doSomethingOn(brand.getId(), b -> {
     *     for (Product product : b.getProducts()) {
     *         System.out.println(product);
     *     }
     *     return null;
     * });
     * </pre>
     */
    public <R> R doSomethingOn(ID id, Function<DTO, R> code) {
        try {
            DTO data = DAL.getById(id);
            return code.apply(data);
        } finally {
            DAL.closeSession();
        }
    }

    public abstract Pair<Boolean, String> exists(DTO object);
}
