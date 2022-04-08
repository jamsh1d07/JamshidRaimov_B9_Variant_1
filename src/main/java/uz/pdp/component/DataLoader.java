package uz.pdp.component;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.entity.User;
import uz.pdp.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {
    @Value("${spring.sql.init.mode}")
    private String mode; //always

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl; //create


    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always") && ddl.equals("create")) {

            User user = new User("Jamshid",
                    "admin", passwordEncoder.encode("admin"), true);
            userRepository.save(user);
            User ketmon = new User("Jasur", "user",
                    passwordEncoder.encode("user"), true);
            userRepository.save(ketmon);
        }
    }
}
