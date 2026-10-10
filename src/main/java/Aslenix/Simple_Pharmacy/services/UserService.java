package Aslenix.Simple_Pharmacy.services;

import Aslenix.Simple_Pharmacy.dataTransferObject.UserResponseDTO;
import Aslenix.Simple_Pharmacy.model.User;

import java.util.Optional;

public interface UserService {

    UserResponseDTO userLogin(String username , String password);

    Optional<User> findUserByEmail(String email);


}
