package com.learn.library.domain;

import com.learn.library.data.DataTestUtil;
import com.learn.library.data.UserRepository;
import com.learn.library.domain.ErrorMessages.UserErrorMessage;
import com.learn.library.model.Member;
import com.learn.library.model.Staff;
import com.learn.library.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserServiceTest {
    @MockBean(name = "userJpaRepository")
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Test
    void shouldFindAll() {
        Mockito.when(userRepository.findAll()).thenReturn(DataTestUtil.users);

        List<User> actual = userService.findAll();

        assertEquals(1, actual.size());
        assertEquals(DataTestUtil.users, actual);
    }

    @Test
    void shouldAddMember() {
        Member newUser = (Member) generateNewMember();
        newUser.setUserId(5);

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(newUser);

        Result<User> actual = userService.add(generateNewMember());

        assertTrue(actual.isSuccess());
        assertEquals(newUser, actual.getPayload());
    }

    @Test
    void shouldAddStaff() {
        Staff newUser = (Staff) generateNewStaff();
        newUser.setUserId(5);

        Mockito.when(userRepository.save(Mockito.any())).thenReturn(newUser);

        Result<User> actual = userService.add(generateNewMember());

        assertTrue(actual.isSuccess());
        assertEquals(newUser, actual.getPayload());
    }

    @Test
    void shouldNotAddNullFirstName() {
        User newUser = generateNewMember();
        newUser.setFirstName(null);

        Result<User> actual = userService.add(newUser);

        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(UserErrorMessage.FIRST_NAME_EMPTY));
    }

    @Test
    void shouldNotAddEmptyFirstName(){
        User newUser = generateNewMember();
        newUser.setFirstName("");

        Result<User> actual = userService.add(newUser);

        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(UserErrorMessage.FIRST_NAME_EMPTY));
    }

    @Test
    void shouldNotAddNullLastName() {
        User newUser = generateNewMember();
        newUser.setLastName(null);

        Result<User> actual = userService.add(newUser);

        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(UserErrorMessage.LAST_NAME_EMPTY));
    }

    @Test
    void shouldNotAddEmptyName() {
        User newUser = generateNewMember();
        newUser.setLastName("");

        Result<User> actual = userService.add(newUser);

        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(UserErrorMessage.LAST_NAME_EMPTY));
    }


    private User generateNewMember() {
        return new Member("Charlie", "CuteGirl", LocalDate.now());
    }

    private User generateNewStaff() {
        return new Staff("Charlie", "CuteGirl", LocalDate.now());
    }
}