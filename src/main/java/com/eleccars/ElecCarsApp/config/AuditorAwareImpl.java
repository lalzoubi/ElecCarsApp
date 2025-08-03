package com.eleccars.ElecCarsApp.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware {

    @Override
    public Optional<String> getCurrentAuditor() {
        // مثال: من SecurityContext (لو عندك JWT)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return Optional.of("SYSTEM");
        }
        return Optional.of(auth.getName()); // بيرجع اسم المستخدم الحالي
    }
}
