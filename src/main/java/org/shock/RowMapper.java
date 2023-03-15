package org.shock;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper {
    User setRow(ResultSet resultSet) throws SQLException;
}
