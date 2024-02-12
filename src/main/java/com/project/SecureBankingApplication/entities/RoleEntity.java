package com.project.SecureBankingApplication.entities;

import com.project.SecureBankingApplication.utils.Role;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class RoleEntity {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Role title;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getTitle() {
        return title;
    }

    public void setTitle(Role title) {
        this.title = title;
    }
}
