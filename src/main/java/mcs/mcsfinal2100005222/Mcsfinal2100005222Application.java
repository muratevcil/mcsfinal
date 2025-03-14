package mcs.mcsfinal2100005222;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Mcsfinal2100005222Application {

    public static void main(String[] args) {
        SpringApplication.run(Mcsfinal2100005222Application.class, args);
    }

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

}
