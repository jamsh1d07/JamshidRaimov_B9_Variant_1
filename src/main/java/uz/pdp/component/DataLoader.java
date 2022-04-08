package uz.pdp.component;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.entity.Role;
import uz.pdp.entity.User;
import uz.pdp.entity.enums.RoleEnum;
import uz.pdp.repository.RoleRepository;
import uz.pdp.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {
    @Value("${spring.sql.init.mode}")
    private String mode; //always

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl; //create

    final RoleRepository roleRepository;
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always") && ddl.equals("create")) {
            Role admin = new Role();
            admin.setName(RoleEnum.ADMIN);
            Role superAdmin = new Role();
            superAdmin.setName(RoleEnum.SUPER_ADMIN);


            roleRepository.save(admin);
            roleRepository.save(superAdmin);


            Set<Role> roles = new HashSet<>();
            roles.add(superAdmin);

            Set<Role> roles1 = new HashSet<>();
            roles1.add(admin);

            User user = new User("Jamshid", roles,
                    "admin", passwordEncoder.encode("admin"), true);
            userRepository.save(user);
            User user1 = new User("Jasur",roles1, "user",
                    passwordEncoder.encode("user"), true);
            userRepository.save(user1);

        }
    }
}
