package com.example.project_hotel.security;

import com.example.project_hotel.models.RegistrationDto;
import com.example.project_hotel.models.Role;
import com.example.project_hotel.models.UserEntity;
import com.example.project_hotel.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;

@SessionAttributes("user")
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private UserDetailsService userService;


    @Autowired
    public CustomAuthenticationSuccessHandler(UserDetailsService userService) {
        this.userService = userService;
    }

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication!=null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            System.out.println("Authenticated User: " + userDetails.getUsername());

            request.getSession().setAttribute("user", userDetails.getRegistrationDto());
            RegistrationDto registrationDto = userDetails.getRegistrationDto();

            if (registrationDto != null && registrationDto.getUsername() != null) {
                UserEntity userEntity=userService.findUserByUsername(registrationDto.getUsername());
                if(userEntity.getRoles().get(0).getName().equals("ADMIN"))
                {
                    setDefaultTargetUrl("/admin");
                }
                else
                {
                    setDefaultTargetUrl("/hotel");
                }
                //request.getSession().setAttribute("user", registrationDto);
            }

        }
        super.handle(request, response, authentication);
    }
}
