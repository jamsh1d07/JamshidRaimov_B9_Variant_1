package uz.pdp.config;


import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.entity.User;

import java.util.Optional;


public class SecurityAuditor implements AuditorAware {
    @Override
    public Optional getCurrentAuditor() {
        //tizimga kim kirib turgan bo'lsa kim qo'shsa audit
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (!(authentication == null || !authentication.isAuthenticated() ||
                "anonymousUser".equals("" + authentication.getPrincipal()))) return Optional.of(user.getId());
        return Optional.empty();
    }
}
