package com.vxcompany.meetup.tripservice.user;

import com.vxcompany.meetup.tripservice.trip.Trip;

import java.util.Arrays;

public class UserBuilder {
    private User[] friends = new User[]{};
    private Trip[] trips = new Trip[]{};

    public static UserBuilder newUser() {
        return new UserBuilder();
    }

    public UserBuilder withFriends(User... friends) {
        this.friends = friends;
        return this;
    }

    public UserBuilder withTrips(Trip... trips) {
        this.trips = trips;
        return this;
    }

    public User build() {
        User user = new User();
        Arrays.asList(friends).stream().forEach(friend -> user.addFriend(friend));
        Arrays.asList(trips).stream().forEach(trip -> user.addTrip(trip));
        return user;
    }
}
