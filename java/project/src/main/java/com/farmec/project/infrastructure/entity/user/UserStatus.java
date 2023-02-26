package com.farmec.project.infrastructure.entity.user;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.farmec.project.domain.type.secure.auth.Status;

@Entity
@Table(name = "user_statuses")
public class UserStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "email")
    private User user;

    @Size(max = 30)
    @NotBlank
    @Column(name = "status", nullable = false)
    private String status;

    public UserStatus() {}

    public UserStatus(User user) {
        this.user = user;
        status = Status.ACTIVE.name();
    }

    public UserStatus(User user, String key) {
        this.user = user;
        status = Arrays.stream(Status.values())
            .filter(status -> status.getStatus().equals(key))
            .findFirst().orElse(Status.ACTIVE).name();
    }

    public String getStatus() {
        return status;
    }
}
