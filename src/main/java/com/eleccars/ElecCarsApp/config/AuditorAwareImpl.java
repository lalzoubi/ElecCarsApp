package com.eleccars.ElecCarsApp.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware {
    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("Laith Alzoubi");
    }
}
