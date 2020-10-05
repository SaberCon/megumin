package cn.sabercon.common.enums.intenum;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.DynamicParameterizedType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Objects;
import java.util.Properties;

/**
 * 处理 hibernate 中 {@link IntEnum} 和数据库字段的映射
 *
 * @author SaberCon
 * @since 1.0.0
 */
public class IntEnumType implements UserType, DynamicParameterizedType {

    public static final String CLASS_FULL_NAME = "cn.sabercon.common.enums.intenum.IntEnumType";

    private Class<? extends IntEnum> enumClass;

    private static final int[] SQL_TYPES = new int[]{Types.INTEGER};

    @Override
    public void setParameterValues(Properties parameters) {
        var reader = (ParameterType) parameters.get(PARAMETER_TYPE);
        enumClass = reader.getReturnedClass().asSubclass(IntEnum.class);
    }

    @Override
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    @Override
    public Class returnedClass() {
        return enumClass;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        return Objects.equals(x, y);
    }

    @Override
    public int hashCode(Object x) throws HibernateException {
        return x == null ? 0 : x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner) throws HibernateException, SQLException {
        return IntEnum.convert(enumClass, rs.getString(names[0]));
    }

    @Override
    public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            st.setNull(index, SQL_TYPES[0]);
        } else {
            st.setInt(index, enumClass.cast(value).val());
        }
    }

    @Override
    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    @Override
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }
}
