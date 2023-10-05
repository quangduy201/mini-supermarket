package mini_supermarket.BLL;

import mini_supermarket.DAL.BaseDAL;
import mini_supermarket.DTO.BaseDTO;
import mini_supermarket.utils.VNString;
import org.hibernate.HibernateException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BaseBLL<DTO extends BaseDTO, ID extends Serializable> {
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

    public List<DTO> findBy(Object... attributes) {
        return DAL.getBy(attributes);
    }

    public List<DTO> findAll() {
        return DAL.getAll();
    }

    /**
     * Retrieves data from a list of DTO objects, selecting specific columns for each object,
     * and returns the data as a two-dimensional array.
     *
     * <p>This method allows you to specify a subset of columns to retrieve from the DTO objects,
     * creating a table with the selected data. The table is represented as a two-dimensional array
     * with rows corresponding to DTO objects and columns corresponding to the selected columns.
     * The order of the columns can be altered.
     *
     * @param objectList A list of DTO objects from which to retrieve data.
     * @param columns    A variable number of column names to select from each DTO object.
     *                   If not provided, all available columns are selected.
     * @return A two-dimensional array representing the selected data from the DTO objects.
     *
     * @example
     * Here's an example of how to use the `getData` method to retrieve specific columns
     * from a list of accounts:
     * <pre>
     * AccountBLL accountBLL = new AccountBLL();
     * List<Account> accounts = accountBLL.findAll();
     * Object[][] objects = accountBLL.getData(accounts, __.ACCOUNT.ID, __.ACCOUNT.USERNAME, __.ROLE.NAME, __.STAFF.NAME);
     * System.out.println(Arrays.deepToString(objects));
     * </pre>
     * In this example, the method retrieves the "ID" and "USERNAME" columns from the Account objects,
     * as well as the "NAME" column from associated Role objects and the "NAME" column from associated Staff objects.
     * The resulting two-dimensional array contains the selected data for further processing or display.
     */
    public Object[][] getData(List<DTO> objectList, String... columns) {
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

        String[][] table = new String[objectList.size()][indices.size()];
        for (int i = 0; i < table.length; i++) {
            String[] data = objectList.get(i).toString().split(VNString.NULL);
            for (int j = 0; j < table[0].length; j++) {
                table[i][j] = data[indices.get(j)];
            }
        }
        return table;
    }

    /**
     * Executes a function on a DTO object identified by its unique ID and returns the result.
     * <p>
     * This method opens a session, retrieves a DTO object by its unique ID, applies the provided
     * function to it, and then closes the session.
     *
     * @param id   The unique identifier of the DTO object.
     * @param code The function to be executed on the DTO object.
     * @param <R>  The type of the result returned by the function.
     * @return The result of executing the provided function on the DTO object.
     * @throws HibernateException If there is an issue with the Hibernate session.
     * @throws IllegalArgumentException If the provided ID is null.
     *
     * @example
     * Here's an example of how to use the `doSomethingOn` method
     * to get all the products of Coca-Cola brand:
     * <pre>
     * BrandBLL brandBLL = new BrandBLL();
     * Brand brand = brandBLL.findBy(Brand.NAME, "Coca-Cola").get(0);
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
}
