package Aslenix.Simple_Pharmacy.dataTransferObject;

import Aslenix.Simple_Pharmacy.enums.UserRole;
import Aslenix.Simple_Pharmacy.enums.UserStatus;
import Aslenix.Simple_Pharmacy.model.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record UserResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String username,
        String initials,
        String email,
        UserRole role,
        UserStatus status,
        LocalDate createdAt) {

    public UserResponseDTO(User user) {
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getInitials(), user.getEmail(), user.getRole(), user.getStatus(), user.getCreatedAt());
    }
}
