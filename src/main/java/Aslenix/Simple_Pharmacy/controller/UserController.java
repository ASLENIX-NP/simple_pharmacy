package Aslenix.Simple_Pharmacy.controller;


import Aslenix.Simple_Pharmacy.dataTransferObject.UserResponseDTO;
import Aslenix.Simple_Pharmacy.enums.UserStatus;
import Aslenix.Simple_Pharmacy.exceptions.UserNotFoundException;
import Aslenix.Simple_Pharmacy.model.User;
import Aslenix.Simple_Pharmacy.servicesImpl.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class UserController {


    @Autowired
    private UserServiceImpl userService;

    @GetMapping({"/","login"})
    public String login() {
        return "user/loginForm";
    }

    @PostMapping("/login")
    public String postLogin(
            @ModelAttribute User u ,
            HttpSession session, Model model) {

        try {
            UserResponseDTO userResponseDTO = userService.userLogin(u.getUsername(), u.getPassword());


            if (userResponseDTO != null) {
                session.setAttribute("activeUser", userResponseDTO);
                // if userResponseDTO is inactive for more than 10 min session expires
                session.setMaxInactiveInterval(600);

            }else {
                model.addAttribute("error", "Id or password incorrect ");
                return "user/loginForm";
            }

            if(userResponseDTO.status().equals(UserStatus.SUSPENDED)){
                model.addAttribute("error", "Your account has been suspended");
                return "user/loginForm";
            }

            return "redirect:/dashboard";
        }catch (UserNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            return "user/loginForm";
        }
    }


    @GetMapping("/dashboard")
    public String roleBasedDashboardRedirect(HttpSession session,Model model){
        User activeUser = (User) session.getAttribute("activeUser");
        if (activeUser == null) {
            return "redirect:/login";
        }

        switch (activeUser.getRole()){
            case ADMIN -> {
                return "redirect:/admin/dashboard";
            }

            case CASHIER -> {
                model.addAttribute("currentPage", "overview");
                return "redirect:/cashier/dashboard";
            }

            default -> {
                model.addAttribute("error", "role not defined ");
                return "user/loginForm";
            }
        }

    }

    @GetMapping("/logout")
    public String postLogout(HttpSession session){
        session.invalidate();

        return "redirect:/login";
    }




}
