package DataAccess;

import Connection.ConnectionFactory;
import Model.client;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Abstract class provides common functionality for data access operations.
 *
 * @param <T> the type of the data object.
 */
public class Abstract<T> {
    /**
     * The logger for the Abstract class.
     */
    protected final Logger LOGGER = Logger.getLogger(Abstract.class.getName());
    /**
     * The class type parameter.
     */
    private final Class<T> type;
    /**
     * Constructs an Abstract object.
     * It sets the type parameter based on the actual type argument provided.
     */
    public Abstract() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    /**
     * Inserts a new object into the database.
     *
     * @param t the object to insert.
     * @return the inserted object.
     */
    public T insert(T t) {
        int lastIndex = getLastIndex();
        setId(t, lastIndex + 1);
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            int index = 1;
            for (Field field : type.getDeclaredFields()) {
                if (!field.getName().equals("id")) { // Exclude the "id" field
                    field.setAccessible(true);
                    Object value = field.get(t);
                    statement.setObject(index++, value);
                }
            }

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating object failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    setId(t, id); // Assuming the ID field is named "id" and has a setter method
                } else {
                    throw new SQLException("Creating object failed, no ID obtained.");
                }
            }

            return t;
        } catch (SQLException | IllegalAccessException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        }
        return null;
    }
    /**
     * Sets the ID of an object using reflection.
     *
     * @param t  the object.
     * @param id the ID value.
     */
    private void setId(T t, int id) {
        try {
            Method setIdMethod = type.getMethod("setId", int.class);
            setIdMethod.invoke(t, id);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.log(Level.WARNING, "Failed to set ID for object: " + e.getMessage());
        }
    }
    /**
     * Creates the insert SQL query for the object.
     *
     * @return the insert SQL query.
     */
    private String createInsertQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");

        Field[] fields = type.getDeclaredFields();
        List<String> fieldNames = new ArrayList<>();

        for (Field field : fields) {
            if (!field.getName().equals("id")) { // Exclude the "id" field
                fieldNames.add(field.getName());
            }
        }

        for (int i = 0; i < fieldNames.size(); i++) {
            sb.append(fieldNames.get(i));
            if (i < fieldNames.size() - 1) {
                sb.append(", ");
            }
        }

        sb.append(") VALUES (");

        for (int i = 0; i < fieldNames.size(); i++) {
            sb.append("?");
            if (i < fieldNames.size() - 1) {
                sb.append(", ");
            }
        }

        sb.append(")");

        return sb.toString();
    }

    /**
     * Creates the select SQL query for a specific field.
     *
     * @param field the field to query.
     * @return the select SQL query.
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }
    /**
     * Creates a list of objects from the ResultSet.
     *
     * @param resultSet the ResultSet containing the data.
     * @return the list of objects.
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * Finds an object by its ID.
     *
     * @param id the ID of the object to find.
     * @return the found object.
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        }
        return null;
    }
    /**
     * Finds an object by its name.
     *
     * @param name the name of the object to find.
     * @return the found object.
     */
    public T findByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("name");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        }
        return null;
    }
    /**
     * Updates an object in the database.
     *
     * @param t  the object to update.
     * @param id the ID of the object.
     * @return the updated object.
     */
    public T update(T t, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            setUpdateParameters(statement, t, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        }

        return t;
    }

    private void setUpdateParameters(PreparedStatement statement, T t, int id) throws SQLException {
        Field[] fields = type.getDeclaredFields();
        int parameterIndex = 1;
        for (Field field : fields) {
            if (!field.getName().equalsIgnoreCase("id")) {
                field.setAccessible(true);
                try {
                    Object value = field.get(t);
                    statement.setObject(parameterIndex, value);
                    parameterIndex++;
                } catch (IllegalAccessException e) {
                    LOGGER.log(Level.WARNING, "Error setting update parameters: " + e.getMessage());
                }
            }
        }
        // Set the ID parameter
        statement.setObject(parameterIndex, id);
    }
    /**
     * Updates the ID of an object in the database.
     *
     * @param t  the object to update.
     * @param id the new ID value.
     * @return the updated object.
     */
    public T updateId(T t, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQueryId();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            setUpdateParameters(statement, t, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        }

        return t;
    }
    /**
     * Creates the update SQL query for updating an object's ID.
     *
     * @return the update SQL query.
     */
    private String createUpdateQueryId() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        Field[] fields = type.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (!field.getName().equalsIgnoreCase("id")) {
                sb.append(field.getName());
                sb.append(" = ?");
                if (i < fields.length - 1) {
                    sb.append(", ");
                }
            }
        }

        sb.append(" WHERE id = ?");

        return sb.toString();
    }
    /**
     * Creates the update SQL query for updating an object.
     *
     * @return the update SQL query.
     */
    private String createUpdateQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        Field[] fields = type.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (!field.getName().equalsIgnoreCase("id")) {
                sb.append(field.getName());
                sb.append(" = ?");
                if (i < fields.length - 1) {
                    sb.append(", ");
                }
            }
        }

        sb.append(" WHERE id = ?");

        return sb.toString();
    }

    /**
     * Deletes an object from the database.
     *
     * @param t the object to delete.
     * @return the deleted object.
     */
    public T delete(T t){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            setDeleteParameters(statement, t);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        }

        return t;
    }
    /**
     * Creates the delete SQL query for deleting an object.
     *
     * @return the delete SQL query.
     */
    private String createDeleteQuery() {
        String sb = "DELETE FROM " +
                type.getSimpleName() +
                " WHERE name = ?";

        return sb;
    }
    /**
     * Sets the parameters for the delete SQL query.
     *
     * @param statement the PreparedStatement.
     * @param t         the object.
     * @throws SQLException if an SQL error occurs.
     */
    public void setDeleteParameters(PreparedStatement statement, T t) throws SQLException {
        Field[] fields = type.getDeclaredFields();
        int parameterIndex = 1;
        for (Field field : fields) {
            if (field.getName().equalsIgnoreCase("name")) {
                field.setAccessible(true);
                try {
                    Object value = field.get(t);
                    statement.setObject(parameterIndex, value);
                    parameterIndex++;
                } catch (IllegalAccessException e) {
                    LOGGER.log(Level.WARNING, "Error setting delete parameters: " + e.getMessage());
                }
            }
        }
    }
    /**
     * Retrieves all objects from the database.
     *
     * @return a list of all objects.
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        List<T> resultList = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return resultList=createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        }

        return new ArrayList<>();
    }
    /**
     * Creates the select all SQL query.
     *
     * @return the select all SQL query.
     */
    private String createSelectAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }
    /**
     * Gets the last index value from the database.
     *
     * @return the last index value.
     */
    public int getLastIndex() {
        int lastIndex = 0;
        String query = "SELECT MAX(id) FROM " + type.getSimpleName();

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                lastIndex = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error getting last index: " + e.getMessage());
        }

        return lastIndex;
    }

}