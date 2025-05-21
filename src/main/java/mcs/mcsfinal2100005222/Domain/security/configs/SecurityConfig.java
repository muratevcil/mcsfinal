package mcs.mcsfinal2100005222.Domain.security.configs;


import mcs.mcsfinal2100005222.Domain.security.concretes.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.beans.factory.annotation.Value;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


	private final JwtAuthFilter jwtAuthFilter;

	private final UserManager userManager;

	private final PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("delegatedAuthenticationEntryPoint")
    private final RestAuthenticationEntryPoint authenticationEntryPoint;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserManager userManager, PasswordEncoder passwordEncoder, RestAuthenticationEntryPoint authenticationEntryPoint) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userManager = userManager;
        this.passwordEncoder = passwordEncoder;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			return http.csrf(AbstractHttpConfigurer::disable)
					.authorizeHttpRequests(x ->
							x.requestMatchers("/auth/addNewUser/**").permitAll()
									.requestMatchers("/auth/welcome/**").permitAll()
									.requestMatchers("/auth/generateToken/**").permitAll()
					)

					.authorizeHttpRequests(x ->
							x.requestMatchers("/auth/user").authenticated()
									.requestMatchers("/auth/admin").hasRole("ADMIN")
									.requestMatchers("/auth/checkIfJwtValid").permitAll()
									.requestMatchers("/auth/getUserRole").permitAll()
									.requestMatchers("/auth/getUserDetails").authenticated()
					)
					.authorizeHttpRequests(x->
							x.requestMatchers("/test/**").hasRole("ADMIN")
					)
					.authorizeHttpRequests(x->
							x.requestMatchers("/product/getAllProducts").permitAll()
									.requestMatchers("/product/addNewProduct").hasRole("SELLER")
									.requestMatchers("/product/deleteProduct").hasRole("SELLER")
									.requestMatchers("/product/buyProduct").authenticated()
									.requestMatchers("/product/getProductByUUID/**").authenticated()
									.requestMatchers("/product/editProduct").authenticated()
									.requestMatchers("/product/getMainPageProducts").permitAll()
									.requestMatchers("/product/getSellerProducts").authenticated()
					)
					.authorizeHttpRequests(x->
							x.requestMatchers("/cart/getCartContent").permitAll()
									.requestMatchers("/cart/incrementOrDecrementItemCount").authenticated()
									.requestMatchers("/cart/getCartItemCount").authenticated()
					)
					.authorizeHttpRequests(x->
							x.requestMatchers("/favorites/addToFavorites").authenticated()
									.requestMatchers("/favorites/getFavoritesCount").authenticated()
									.requestMatchers("/favorites/getFavoritedProducts").authenticated()
									.requestMatchers("/favorites/removeFavoritedProduct/**").authenticated())
					.authorizeHttpRequests(x->
							x.requestMatchers("/system/ping").permitAll())
					.authorizeHttpRequests(x->x.requestMatchers("/category/getAll").permitAll())
					.authorizeHttpRequests(x->x.requestMatchers("/cart/**").authenticated())
					.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
					.build();


	}

	@Bean
	public AuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userManager);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		return authenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
