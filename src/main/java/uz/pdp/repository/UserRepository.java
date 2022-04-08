package uz.pdp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String name);
//    @Query("SELECT u FROM User u WHERE u.userName = :userName")
//    public User getUserByUsername(@Param("userName") String userName);
}
