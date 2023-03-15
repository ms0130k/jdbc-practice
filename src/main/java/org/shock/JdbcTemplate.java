package org.shock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {
    public int executeUpdate(final User user, final String sql, final PreparedStatementSetter pss) {
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement psmt = con.prepareStatement(sql)) {
            pss.setValue(psmt);
            return psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User executeQuery(final String userid, final String sql, final PreparedStatementSetter pss, final RowMapper rowMapper) {
        ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement psmt = con.prepareStatement(sql)) {
            pss.setValue(psmt);
            rs = psmt.executeQuery();
            if (rs.next()) {
                return rowMapper.setRow(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
