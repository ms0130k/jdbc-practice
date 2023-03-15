package org.shock;

public class UserDao {
    public int create(final User user) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO users VALUES (?, ?, ?, ?)";
        return jdbcTemplate.executeUpdate(user, sql, psmt -> {
            psmt.setString(1, user.getUserid());
            psmt.setString(2, user.getPassword());
            psmt.setString(3, user.getName());
            psmt.setString(4, user.getEmail());
        });
    }

    public User findById(final String userid) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        return jdbcTemplate.executeQuery(userid, "SELECT userId, password, name, email FROM users WHERE userId = ?", psmt -> psmt.setString(1, userid), rs -> new User(
                rs.getString(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4)
        ));
    }
}
