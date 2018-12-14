package org.craftedsw.tripservicekata.user;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserShould {

    @Test
    public void return_false_when_users_are_not_friends() {
        User user1 = new User();
        User user2 = new User();

        assertFalse(user2.isFriendOf(user1));
    }

    @Test
    public void return_true_when_users_are_not_friends() {
        User user1 = new User();
        User user2 = new User();
        user2.addFriend(user1);

        assertTrue(user2.isFriendOf(user1));
    }

}
