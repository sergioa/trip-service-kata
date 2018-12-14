package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

import java.util.Collections;
import java.util.List;

public class TripService {

    public List<Trip> getTripsByUser(User user) {
        User loggedUser = getLoggedUser();
        if (loggedUser != null) {
            return user.isFriendOf(loggedUser) ? findTripsByUser(user) : Collections.<Trip>emptyList();
        } else {
            throw new UserNotLoggedInException();
        }
    }

    protected User getLoggedUser() {
        return UserSession.getInstance().getLoggedUser();
    }

    protected List<Trip> findTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }

}
