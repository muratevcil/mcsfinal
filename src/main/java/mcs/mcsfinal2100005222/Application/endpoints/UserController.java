package mcs.mcsfinal2100005222.Application.endpoints;

import lombok.extern.slf4j.Slf4j;
import mcs.mcsfinal2100005222.Domain.requests.AuthRequest;
import mcs.mcsfinal2100005222.Domain.requests.CreateUserRequest;
import mcs.mcsfinal2100005222.Domain.security.concretes.JwtManager;
import mcs.mcsfinal2100005222.Domain.security.concretes.UserManager;
import mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {
    @Autowired
    private final UserManager service;

    private final JwtManager jwtService;

    private final AuthenticationManager authenticationManager;


    public UserController(UserManager service, JwtManager jwtService, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/welcome")
    private String welcome(){
        return "Hello World";
    }

    @PostMapping("/addNewUser")
    private User addNewUser(@RequestBody CreateUserRequest createUserRequest){
        return service.createUser(createUserRequest);
    }

    @PostMapping("/generateToken")
    public String generateToken(@RequestBody AuthRequest request){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );
            System.out.println("authenticated");
            if (authentication.isAuthenticated()) {
                return jwtService.generateToken(request.username());
            }
        } catch (Exception e) {
            e.printStackTrace();  // Hatanın detayını görmek için
            throw new UsernameNotFoundException("Invalid username: " + request.username());
        }

        return null;
    }

    @GetMapping("/user")
    private String user(){
        return "Hello from user endpoint";
    }

    @GetMapping("/admin")
    private String admin(){
        return "Hello from admin endpoint";
    }
}
