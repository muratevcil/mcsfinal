package mcs.mcsfinal2100005222.Domain.security.concretes;


import io.jsonwebtoken.JwtException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import mcs.mcsfinal2100005222.Domain.dto.requests.CreateUserRequest;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.cart.Cart;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.user.User;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.wallet.Wallet;
import mcs.mcsfinal2100005222.Infrastructure.mysql.repositories.CartRepository;
import mcs.mcsfinal2100005222.Infrastructure.mysql.repositories.UserRepository;
import mcs.mcsfinal2100005222.Infrastructure.mysql.repositories.WalletRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserManager implements UserDetailsService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final WalletRepository walletRepository;
    private final JwtManager jwtService;
    private final EntityManager entityManager;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserManager(UserRepository userRepository, CartRepository cartRepository, WalletRepository walletRepository, EntityManager entityManager, BCryptPasswordEncoder passwordEncoder, JwtManager jwtManager) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.walletRepository = walletRepository;
        this.entityManager = entityManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtManager;
    }

    public Optional<User> getByUserName(String username){
        return userRepository.findUserByUsername(username);
    }


    @Transactional
    public User createUser(CreateUserRequest createUserRequest) {
        if(userRepository.existsByEmail(createUserRequest.getEmail())){
            throw new RuntimeException("An account with this email already exists");
        }
        User newUser = new User(
                UUID.randomUUID().toString(),
                createUserRequest.getName(),
                createUserRequest.getUsername(),
                passwordEncoder.encode(createUserRequest.getPassword()),
                true,  // accountNonExpired
                true,  // isEnabled
                true,  // accountNonLocked
                true,  // credentialNonExpired
                createUserRequest.getAuthorities(),
                createUserRequest.getPhoneNumber(),
                createUserRequest.getEmail(),
                new ArrayList<>()
        );
        //cartRepository.save(new Cart(newUser));
        //walletRepository.save(new Wallet(newUser));
        // Kullanıcıyı veritabanına kaydediyoruz
        //User user = userRepository.save(newUser);

        Cart cart = new Cart(newUser);
        Wallet wallet = new Wallet(newUser);
        newUser.setWallet(wallet);
        newUser.setCart(cart);
        cartRepository.save(cart);
        walletRepository.save(wallet);
        return userRepository.save(newUser);

    }

    public boolean checkIfJwtIsValid(String jwt) throws SignatureException {
        try {
            Date expirationDate = jwtService.extractExpiration(jwt);
            return !expirationDate.before(new Date());
        } catch (JwtException e) {
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(RuntimeException::new);
    }
}
