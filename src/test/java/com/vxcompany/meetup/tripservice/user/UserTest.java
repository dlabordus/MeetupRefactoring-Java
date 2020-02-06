package com.vxcompany.meetup.tripservice.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private static final User MARC = new User();
    private static final User PIET = new User();

    @Test
    public void isFriendsWith_WhenUserIsNotFriend_ShouldReturnFalse() {
        User user = UserBuilder.newUser()
                .withFriends(MARC)
                .build();

        assertFalse(user.isFriendsWith(PIET));
    }

    @Test
    public void isFriendsWith_WhenUserIsFriend_ShouldReturnTrue() {
        User user = UserBuilder.newUser()
                .withFriends(MARC, PIET)
                .build();

        assertTrue(user.isFriendsWith(PIET));
    }
}