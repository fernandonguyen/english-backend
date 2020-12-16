package com.codegym.englishbackend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "vocabularys")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vocabulary extends AuditModel {

    @Column(nullable = false)
    private String word;

    @Column(nullable = false)
    private String mean;

    @Column(nullable = false)
    private String lang;
    private String des;

    @ManyToOne(
            fetch = FetchType.EAGER,
            optional = false
    )
    @JoinColumn(
            name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_vocabularys_users_id"
            )
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
}
