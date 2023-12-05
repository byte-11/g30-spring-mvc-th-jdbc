package uz.pdp;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import uz.pdp.domain.User;
import uz.pdp.repository.UserRepository;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:ioc-config.xml");

        UserRepository userRepository = (UserRepository) context.getBean("userRepository");
        User user = User.builder()
                .firstname("Qodirali 14 PRO MAX")
                .lastname("Kimsanali")
                .age(88)
                .build();
        userRepository.saveWithSimpleJdbc(user);
//        userRepository.delete(8);
//        System.out.println(userRepository.getAll(80));
    }
}