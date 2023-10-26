package com.delacasa.courses.auth.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Immutable;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@Immutable
public class AppUser {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @ManyToMany(fetch = EAGER)
    @JoinTable(
            name = "app_user_has_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<AppUserRole> roles = new ArrayList<>();


}
