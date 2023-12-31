package mini_supermarket.utils;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.Objects;

public class DateUserType implements UserType<Date> {
    @Override
    public int getSqlType() {
        return Types.DATE;
    }

    @Override
    public Class<Date> returnedClass() {
        return Date.class;
    }

    @Override
    public boolean equals(Date date, Date j1) {
        if (date == j1) return true;
        if (date == null || j1 == null) return false;
        return date.date.equals(j1.date);
    }

    @Override
    public int hashCode(Date date) {
        return Objects.hashCode(date);
    }

    @Override
    public Date nullSafeGet(ResultSet rs, int position, SharedSessionContractImplementor session, Object owner) throws SQLException {
        LocalDate date = rs.getDate(position).toLocalDate();
        return new Date(date);
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Date date, int index, SharedSessionContractImplementor session) throws SQLException {
        if (date == null) {
            st.setNull(index, Types.DATE);
        } else {
            LocalDate localDate = date.date;
            st.setDate(index, java.sql.Date.valueOf(localDate));
        }
    }

    @Override
    public Date deepCopy(Date date) {
        if (date == null) return null;
        return new Date(date.date);
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Date date) {
        if (date == null) return null;
        return date.date;
    }

    @Override
    public Date assemble(Serializable serializable, Object o) {
        if (serializable == null) return null;
        return new Date((LocalDate) serializable);
    }
}
