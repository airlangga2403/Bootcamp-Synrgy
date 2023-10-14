package com.example.synrgy6.repository;

import com.example.synrgy6.model.Users;
import com.example.synrgy6.utils.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class UsersRepositoryTest {

    @Autowired private UsersRepository usersRepository;

    @Test
    public void testCreateUser() {
        String randomString = Utils.generateRandomString();
        Users users = new Users("airlanggaxxxx" + randomString, "airlanggaemailamantaxp@gmail.com","12345");
        Users savedUser = usersRepository.save(users);
        assertThat(savedUser).isNotNull();
    }
}