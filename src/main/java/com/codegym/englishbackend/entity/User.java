package com.codegym.englishbackend.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User extends AuditModel {

    @Column(name = "user_name", nullable = false, unique = true)
    @NotNull
    @Size(max = 100)
    private String userName;

    @NotNull
    @Size(max = 100)
    @Column(name = "pass_word", nullable = false)
    private String passWord;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "user"
    )
    @Builder.Default
    private List<Vocabulary> books = new ArrayList<>();
}
