package com.kursova.demo.web;

import com.kursova.demo.dto.UserProfileInfoDTO;
import com.kursova.demo.dto.UserRegisterDto;
import com.kursova.demo.enums.UserRoleEnum;
import com.kursova.demo.service.UserActivationService;
import com.kursova.demo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    private final UserActivationService userActivationService;

//      private final CurrentUser currentUser;

    public UserController(UserService userService, UserActivationService userActivationService) {
        this.userService = userService;


        this.userActivationService = userActivationService;
    }
    @GetMapping("/login")
    public ModelAndView userLogin() {

        return new ModelAndView("login");
    }

    @GetMapping("/register")
    public ModelAndView userRegister(@ModelAttribute("user") UserRegisterDto userRegisterDto) {
        return new ModelAndView("sign-up");
    }

    @PostMapping("/register")
    public ModelAndView userRegister(@Valid @ModelAttribute("user") UserRegisterDto userRegisterDto,
                                     BindingResult bindingResult) {
        ModelAndView model = new ModelAndView("sign-up");
        if (bindingResult.hasErrors()) {
            return model;
        }
        if (!userService.passwordMatch(userRegisterDto)) {
            model.addObject("passwordNotMatching", true);
            return model;
        }

        boolean registered = userService.registerUser(userRegisterDto);
        if (!registered) {
            model.addObject("already_exists", true);
            return model;
        } else {
            return new ModelAndView("redirect:/login");
        }
    }


    @PostMapping("/failure")
    public String onFailure(@ModelAttribute("email") String email, Model model) {
        model.addAttribute("email", email);
        model.addAttribute("bad_credentials", true);
        return "login";
    }


    @GetMapping("/edit")
    public String editUser (@AuthenticationPrincipal UserDetails userDetails, Model model){

            String username = userDetails.getUsername();
            UserProfileInfoDTO user = userService.getUser(username);
            model.addAttribute("userDetails", user);
            return "user_profile";
    }
    @GetMapping("/{userId}/edit")
    public String editUser(@PathVariable("userId") Long id, Model model) {
        UserProfileInfoDTO userDto = userService.findById(id);
        model.addAttribute("user_info", userDto);
        return "user_edit";
    }
    @ModelAttribute("roles")
    public UserRoleEnum[] engines(){
        return UserRoleEnum.values();

    }
    @PostMapping("/{userId}/edit")
    public String editUserInfo(@PathVariable("userId") Long id, @ModelAttribute("user_info") UserProfileInfoDTO userProfileInfoDTO, Model model) {
        userService.updateUser(id,userProfileInfoDTO);
        return "redirect:/user/edit";
    }
    @GetMapping("/activate/{activation_code}")
       public String activateU(@PathVariable("activation_code") String activationCode,Model model){
        boolean state =  userActivationService.setActivationStatus(activationCode);

        if(state){
            model.addAttribute("successfullActivation",true);
        }
            return"redirect:/";
        }

    }



//    @PostMapping("/login")@ModelAttribute("user") UserLoginDto userLoginDto
//    public ModelAndView login(@Valid @ModelAttribute("user")UserLoginDto user,
//                              BindingResult bindingResult){
//
//        if(bindingResult.hasErrors()){
//            return new ModelAndView("login");
//        }
//
//        boolean isLoginSuccessful = userService.loginUser(user);
//
//        if(!isLoginSuccessful){
//            ModelAndView model  = new ModelAndView("login");
//            model.addObject("hasLoginError",true );
//                return model;        }
//
//        return new ModelAndView("redirect:/");

//    }
//    ModelAndView model  = new ModelAndView();
//    model.addObject("bad_credentials","true" );
    //  return model;
//
//    @GetMapping("/logout")
//    public String userLogout(){
//         currentUser.logOut();
//        return "redirect:/";
//    }