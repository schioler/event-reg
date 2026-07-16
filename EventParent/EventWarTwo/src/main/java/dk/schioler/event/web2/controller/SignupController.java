package dk.schioler.event.web2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dk.schioler.event.web2.form.model.SignupForm;

/**
 * SignUpController class for User sign up form processing
 * 
 * @author Ramesh Fadatare
 */
@Controller
public class SignupController {

    /**
     * Create new signUpForm object for empty from
     * 
     * @return
     */
    @ModelAttribute("signUpForm")
    public SignupForm setSignupForm() {
        return new SignupForm();
    }

    /**
     * Method to show the initial HTML form
     * 
     * @return
     */
    @GetMapping("/showSignUpForm")
    public String showForm() {
        return "signup-form";
    }

    /**
     * Save User sign up form
     * 
     * @param signUpForm
     * @param model
     * @return
     */
    @PostMapping("/saveSignUpForm")
    public String saveUser(@ModelAttribute("signUpForm") SignupForm signUpForm, Model model) {

        // Implement business logic to save user details into a database
        // .....

        System.out.println("FirstName : " + signUpForm.getFirstName());
        System.out.println("LastName : " + signUpForm.getLastName());
        System.out.println("Username : " + signUpForm.getUserName());
        System.out.println("Password : " + signUpForm.getPassword());
        System.out.println("Email : " + signUpForm.getEmail());

        model.addAttribute("message", "User SignUp successfully.");
        model.addAttribute("user", signUpForm);

        return "signup-success";
    }
}