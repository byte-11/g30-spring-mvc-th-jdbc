package uz.pdp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import uz.pdp.config.ApplicationConfiguration;
import uz.pdp.repository.UserRepository;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        UserRepository userRepository = context.getBean(UserRepository.class);

//        userRepository.save(User.builder().firstName("John").lastName("Doe").age(20).build());
//        userRepository.saveWithParams(User.builder().firstName("Anna").lastName("Bell").age(20).build());
//        System.out.println(userRepository.get(3));
        System.out.println(userRepository.getByFirstName("John"));
    }
}