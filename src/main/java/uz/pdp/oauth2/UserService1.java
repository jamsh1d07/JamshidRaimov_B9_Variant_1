package uz.pdp.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.User;
import uz.pdp.entity.enums.Provider;
import uz.pdp.repository.UserRepository;

@Service
public class UserService1 {

    @Autowired
    private UserRepository repo;

    public void processOAuthPostLogin(String username) {
        User existUser = repo.getUserByUsername(username);

        if (existUser == null) {
            User newUser = new User();
            newUser.setUserName(username);
            newUser.setProvider(Provider.GOOGLE);
            newUser.setEnabled(true);
            repo.save(newUser);
        }

    }
}