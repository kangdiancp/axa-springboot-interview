package com.axa.axatest;

import static org.assertj.core.api.Assertions.assertThat;

import com.axa.axatest.entity.Roles;
import com.axa.axatest.entity.User;
import com.axa.axatest.repository.RolesRepository;
import com.axa.axatest.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserUnitTest {


    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RolesRepository rolesRepository;


    //1. gunakan method test yg pertama
    @Test
    public void should_find_noUser_if_repository_is_empty() {
        Iterable<User> users = repository.findAll();

        assertThat(users).isEmpty();
    }



    // 2.
    @Test
    public void should_find_user_by_id() {
        User user1 = new User(null, "Rini", "rini");
        entityManager.persist(user1);

        User user2 = new User(null, "Yuli", "yuli");
        entityManager.persist(user2);

        Optional<User> foundUser = repository.findById(user1.getUserId());

        assertThat(foundUser.get()).isEqualTo(user1);
    }

    //3
    @Test
    public void should_store_user_and_role() {
        // save data user with roles
        User user1 = new User(null, "Rini", "rini",
            Set.of(new Roles(null,"ADMIN"),
                    new Roles(null,"GUEST"))
        );

        entityManager.persist(user1);


        Optional<User> foundUser = repository.findById(user1.getUserId());
        //assertThat(foundUser.get()).isEqualTo(user1);

        assertThat(foundUser.get().getRoles()).hasSize(2);
    }

    @Test
    public void should_update_user_by_id() {
        User user1 = new User(null, "Rini", "rini");
        entityManager.persist(user1);



        User updateUser = repository.findById(user1.getUserId()).get();
        updateUser.setUserName("Rini Marlina");
        repository.save(updateUser);

        User checkUpdateUser = repository.findById(updateUser.getUserId()).get();

        assertThat(checkUpdateUser.getUserId()).isEqualTo(updateUser.getUserId());
        assertThat(checkUpdateUser.getUserName()).isEqualTo(updateUser.getUserName());

    }


    @Test
    public void should_delete_user_by_id() {
        User user1 = new User(null, "Rini", "rini");
        entityManager.persist(user1);

        User user2 = new User(null, "Yuli", "yuli");
        entityManager.persist(user2);

        User user3 = new User(null, "Widi", "widi");
        entityManager.persist(user3);



        repository.deleteById(user2.getUserId());

        Iterable<User> users = repository.findAll();

        assertThat(users).hasSize(2).contains(user1, user3);
    }

    @Test
    public void should_store_a_user() {
        User user = repository.save(new User(null,"kang dian","secret"));

        //assertThat(user).hasFieldOrPropertyWithValue("userId", 1l);
        assertThat(user).hasFieldOrPropertyWithValue("userName", "kang dian");
        assertThat(user).hasFieldOrPropertyWithValue("password", "secret");
    }

}
