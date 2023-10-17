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
    public DateTime nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner) throws SQLException {
        LocalDateTime dateTime = rs.getTimestamp(position).toLocalDateTime();
        return new DateTime(dateTime);
    }

    @Override
    public void nullSafeSet(PreparedStatement st, DateTime dateTime, int index, SharedSessionContractImplementor session) throws SQLException {
        if (dateTime == null) {
            st.setNull(index, Types.TIMESTAMP);
        } else {
            LocalDateTime localDateTime = dateTime.dateTime;
            st.setTimestamp(index, java.sql.Timestamp.valueOf(localDateTime));
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
