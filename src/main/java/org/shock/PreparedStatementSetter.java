package org.shock;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface PreparedStatementSetter {
    void setValue(PreparedStatement psmt) throws SQLException;
}
