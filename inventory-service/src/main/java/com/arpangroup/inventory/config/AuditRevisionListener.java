package com.arpangroup.inventory.config;

import org.hibernate.envers.RevisionListener;

import java.util.Optional;

public class AuditRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        String currentUser = "arpan";

//        String currentUser = Optional.ofNullable(SecurityContextHolder.getContext())
//                .map(SecurityContext::getAuthentication)
//                .filter(Authentication::isAuthenticated)
//                .map(Authentication::getPrincipal)
//                .map(User.class::cast)
//                .map(User::getUsername)
//                .orElse("Unknown-User");

        AuditRevisionEntity audit = (AuditRevisionEntity) revisionEntity;
        audit.setUser(currentUser);
    }
}
