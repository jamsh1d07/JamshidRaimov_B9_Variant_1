package uz.pdp.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Role;
import uz.pdp.entity.enums.RoleEnum;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}
