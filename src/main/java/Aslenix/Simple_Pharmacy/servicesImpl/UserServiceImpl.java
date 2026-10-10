package Aslenix.Simple_Pharmacy.servicesImpl;

import Aslenix.Simple_Pharmacy.dataTransferObject.UserResponseDTO;
import Aslenix.Simple_Pharmacy.exceptions.UserNotFoundException;
import Aslenix.Simple_Pharmacy.model.User;
import Aslenix.Simple_Pharmacy.repository.UserRepository;
import Aslenix.Simple_Pharmacy.services.UserService;
import Aslenix.Simple_Pharmacy.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponseDTO userLogin(String username, String password) {

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException("Unable to login user "+username+" because the user was not found"));
        if (!PasswordUtil.verifyPassword(user.getPassword(), password)) {
            throw new UserNotFoundException("Unable to login user "+username+" because the password is incorrect");
        }

        return new UserResponseDTO(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }




}
