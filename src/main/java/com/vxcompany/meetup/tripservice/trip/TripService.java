package com.vxcompany.meetup.tripservice.trip;

import com.vxcompany.meetup.tripservice.exception.UserNotLoggedInException;
import com.vxcompany.meetup.tripservice.user.User;
import com.vxcompany.meetup.tripservice.user.UserSession;

import java.util.ArrayList;
import java.util.List;

public class TripService {
    private TripDAO tripDAO;

    public TripService(TripDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

    public List<Trip> getTripsByUser(User user, User loggedUser) throws UserNotLoggedInException {
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }

        return user.isFriendsWith(loggedUser)
                ? getTripsByUserFromDB(user)
                : new ArrayList<>();
    }

    protected List<Trip> getTripsByUserFromDB(User user) {
        return tripDAO.tripsBy(user);
    }
}
