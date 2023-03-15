package org.shock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {
    @BeforeEach
    void setUp() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db_schema.sql"));
        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
    }

    @Test
    void 사용자_등록() {
        UserDao userDao = new UserDao();
        int createResult = userDao.create(new User("userid", "password", "name", "email"));
        assertThat(createResult).isEqualTo(1);
        User user = userDao.findById("userid");
        assertThat(user).isEqualTo(new User("userid", "password", "name", "email"));

    }
}
