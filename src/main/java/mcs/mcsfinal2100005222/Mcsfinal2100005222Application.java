package mcs.mcsfinal2100005222;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Mcsfinal2100005222Application {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Mcsfinal2100005222Application.class);
        springApplication.setAdditionalProfiles("dev");
        springApplication.run(args);
    }

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
