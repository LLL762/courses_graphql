package com.delacasa.courses.auth.repo;

import com.delacasa.courses.auth.entity.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRoleRepository extends JpaRepository<AppUserRole, Integer> {
}