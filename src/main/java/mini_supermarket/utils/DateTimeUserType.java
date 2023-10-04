package mini_supermarket.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Objects;

public class DateTimeUserType implements UserType<DateTime> {
    @Override
    public int getSqlType() {
        return Types.TIMESTAMP;
    }

    @Override
    public Class<DateTime> returnedClass() {
        return DateTime.class;
    }

    @Override
    public boolean equals(DateTime dateTime, DateTime j1) {
        if (dateTime == j1) return true;
        if (dateTime == null || j1 == null) return false;
        return dateTime.dateTime.equals(j1.dateTime);
    }

    @Override
    public int hashCode(DateTime dateTime) {
        return Objects.hashCode(dateTime);
    }

    @Override
    public DateTime nullSafeGet(ResultSet resultSet, int i, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws SQLException {
        LocalDateTime dateTime = resultSet.getTimestamp(i).toLocalDateTime();
        return new DateTime(dateTime);
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, DateTime dateTime, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws SQLException {
        if (dateTime == null) {
            preparedStatement.setNull(i, Types.TIMESTAMP);
        } else {
            LocalDateTime localDateTime = dateTime.dateTime;
            preparedStatement.setTimestamp(i, java.sql.Timestamp.valueOf(localDateTime));
        }
    }

    @Override
    public DateTime deepCopy(DateTime dateTime) {
        if (dateTime == null) return null;
        return new DateTime(dateTime.dateTime);
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(DateTime dateTime) {
        if (dateTime == null) return null;
        return dateTime.dateTime;
    }

    @Override
    public DateTime assemble(Serializable serializable, Object o) {
        if (serializable == null) return null;
        return new DateTime((LocalDateTime) serializable);
    }
}
