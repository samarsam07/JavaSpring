package com.samar.journalApp.Repository;

import com.samar.journalApp.model.User;
import com.samar.journalApp.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRepositoryImplUnitTests {

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private UserRepositoryImpl userRepositoryImpl;

    public UserRepositoryImplUnitTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Disabled
    @Test
    void testGetUserForSA() {
        // given
        User mockUser = new User();
        mockUser.setUserName("samar");

        when(mongoTemplate.find(any(Query.class), eq(User.class)))
                .thenReturn(List.of(mockUser));

        // when
        List<User> users = userRepositoryImpl.getUserForSA();

        // then
        assertNotNull(users);
//        assertEquals(1, users.size());
//        assertEquals("samar", users.get(0).getUserName());
    }
}