package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripServiceShould {

    @Test(expected = UserNotLoggedInException.class) public void
    fail_when_user_is_not_logged_in() {
        TripService tripService = mock(TestableTripService.class);
        when(tripService.getLoggedUser()).thenReturn(null);

        when(tripService.getTripsByUser(any(User.class))).thenCallRealMethod();

        User user = mock(User.class);

        tripService.getTripsByUser(user);
    }


    @Test public void
    return_empty_trip_list_when_users_are_not_friends() {
        TripService tripService = mock(TestableTripService.class);
        when(tripService.getLoggedUser()).thenReturn(new User());

        when(tripService.getTripsByUser(any(User.class))).thenCallRealMethod();
        User user = mock(User.class);

        List<Trip> tripList = tripService.getTripsByUser(user);
        assertTrue(tripList.isEmpty());
    }


    @Test public void
    return_empty_trip_list_when_users_are_friends() {
        User user1 = new User();
        User user2 = new User();
        user2.addFriend(user1);

        List<Trip> trips = Collections.singletonList(new Trip());
        TripService tripService = mock(TestableTripService.class);
        when(tripService.getLoggedUser()).thenReturn(user1);
        when(tripService.findTripsByUser(any(User.class))).thenReturn(trips);
        when(tripService.getTripsByUser(any(User.class))).thenCallRealMethod();

        assertEquals(tripService.getTripsByUser(user2), trips);
    }


    private class TestableTripService extends TripService {
        @Override
        protected User getLoggedUser() {
            return null;
        }
        @Override
        protected List<Trip> findTripsByUser(User user) {
            return null;
        }
    }
}
