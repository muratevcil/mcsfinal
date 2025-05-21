package mcs.mcsfinal2100005222.Application.endpoints;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import mcs.mcsfinal2100005222.Domain.dto.user.CheckJwtIsValidRequest;
import mcs.mcsfinal2100005222.Domain.entities.GenericResponse;
import mcs.mcsfinal2100005222.Domain.dto.requests.AuthRequest;
import mcs.mcsfinal2100005222.Domain.dto.requests.CreateUserRequest;
import mcs.mcsfinal2100005222.Domain.security.concretes.JwtManager;
import mcs.mcsfinal2100005222.Domain.security.concretes.UserManager;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.user.Abstracts.Role;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.user.User;
import mcs.mcsfinal2100005222.Infrastructure.mysql.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.SignatureException;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@Slf4j
public class UserController {
    @Autowired
    private final UserManager service;

    @Autowired
    private UserRepository userRepository;
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

    @GetMapping("/getUserDetails")
    private GenericResponse<User> getUserDetails(@RequestHeader("Authorization") String jwt){
        User user = userRepository.findByUsername(jwtService.extractUser(jwt.substring(7)))
                .orElseThrow(EntityNotFoundException::new);
        return new GenericResponse<User>(200,user);
    }

    @PostMapping("/generateToken")
    public String generateToken(@RequestBody AuthRequest request){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );
            if (authentication.isAuthenticated()) {
                Set<Role> userType = userRepository.findByUsername(request.username()).get().getAuthorities();
                return jwtService.generateToken(request.username(),userType);
            }
        } catch (Exception e) {
            e.printStackTrace();  // Hatanın detayını görmek için
            throw new UsernameNotFoundException("Invalid username: " + request.username());
        }

        return null;
    }

    @PostMapping("/checkIfJwtValid")
    private boolean checkIfJwtValid(@RequestBody CheckJwtIsValidRequest checkJwtIsValidRequest) throws SignatureException {
        System.out.println("check jwt is valid tetiklendi");
        return service.checkIfJwtIsValid(checkJwtIsValidRequest.getJwt());
    }

    @GetMapping("/getUserRole")
    private GenericResponse<Set<Role>> handleGetUserRole(@RequestHeader("Authorization") String jwt){
        Set<Role> userType = jwtService.extractUserType(jwt.substring(7));
        return new GenericResponse<Set<Role>>(200,userType);
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
