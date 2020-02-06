package com.vxcompany.meetup.tripservice.trip;

import com.vxcompany.meetup.tripservice.exception.UserNotLoggedInException;
import com.vxcompany.meetup.tripservice.user.User;
import com.vxcompany.meetup.tripservice.user.UserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class TripServiceTest {
    private User USER_A = new User();
    private User GUEST = null;
    private User REGISTERED_USER = new User();

    private Trip BAARN = new Trip();

    @Mock
    private TripDAO tripDAO;

    @InjectMocks
    private TripService service;

    @Test
    public void getTripsByUser_WhenWithoutLoggedinUser_ShouldThrowUserNotLoggedInException() {
        assertThrows(UserNotLoggedInException.class,
                () -> service.getTripsByUser(USER_A, GUEST));
    }

    @Test
    public void getTripsByUser_WhenUserWithoutFriends_ShouldReturnEmptyList() {
        List<Trip> trips = service.getTripsByUser(USER_A, REGISTERED_USER);

        assertNotNull(trips);
        assertEquals(0, trips.size());
    }

    @Test
    public void getTripsByUser_WhenUserWithFriends_ShouldReturnListOfTrips() {
        User userWithFriends = UserBuilder.newUser()
                .withFriends(REGISTERED_USER)
                .withTrips(BAARN)
                .build();
        when(tripDAO.tripsBy(eq(userWithFriends))).thenReturn(userWithFriends.trips());

        //        when(tripDAO.tripsBy(any())).thenAnswer(
//                (invocation) -> invocation.getArgument(0, User.class).trips()
//        );

        List<Trip> trips = service.getTripsByUser(userWithFriends, REGISTERED_USER);

        assertNotNull(trips);
        assertEquals(1, trips.size());
    }
}