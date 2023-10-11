package com.delacasa.courses.auth.service;

import com.delacasa.courses.auth.entity.AppUserRole;
import com.delacasa.courses.auth.model.CustomAuthToken;
import com.delacasa.courses.auth.repo.AppUserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Service("userRoleServ")
@RequiredArgsConstructor
public class UserRoleService {

    private final AppUserRoleRepository repo;
    private Map<String, AppUserRole> roles;


    public void setRoles() {
        roles = repo.findAll().stream()
                .collect(toMap(AppUserRole::getName, role -> role));
    }

    public Map<String, AppUserRole> getRoles() {
        if (roles == null || roles.isEmpty()) {
            setRoles();
        }
        return Map.copyOf(roles);
    }

    public boolean compare(String role1Name, String role2Name) {
        final Map<String, AppUserRole> rolesCopy = getRoles();
        final AppUserRole role1 = rolesCopy.get(role1Name);
        final AppUserRole role2 = rolesCopy.get(role2Name);

        if (role1 == null || role2 == null) return false;

        return role1.getAccessLevel() - role2.getAccessLevel() >= 0;
    }

    public boolean hasAccessLevel(Authentication authToken, String roleName) {

        return ((CustomAuthToken) authToken).getPrincipal().roles().stream()
                .anyMatch(role -> compare(role, roleName));
    }


}
