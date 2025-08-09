package olis.codingexercise.mapper;

import olis.codingexercise.dto.UserResponse;
import olis.codingexercise.dto.UserUpdateRequest;
import olis.codingexercise.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public void updateUserFromRequest(UserUpdateRequest updateRequest, User userToUpdate) {
        if (isBlank(updateRequest.getName())) {
            userToUpdate.setFirstName(updateRequest.getName().trim());
        }
        if (updateRequest.getAge() != null) {
            userToUpdate.setAge(updateRequest.getAge());
        }
        if (updateRequest.getTitle() != null && !updateRequest.getTitle().trim().isEmpty()) {
            userToUpdate.setTitle(updateRequest.getTitle().trim());
        }
        if (isBlank(updateRequest.getHometown())) {
            userToUpdate.setHometown(updateRequest.getHometown().trim());
        }
    }

    public static UserResponse mapResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());

        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());

        userResponse.setAge(user.getAge());
        userResponse.setTitle(user.getTitle());
        userResponse.setHometown(user.getHometown());

        return userResponse;
    }

    public static boolean isBlank(String str) {
        // Utility class to check if the incoming string is empty or null!
        // Learning note:
            // Trims it first so empty spaces aren't treated as characters
            // which would cause isEmpty() to fail!
        return str == null || str.trim().isEmpty();
    }

}