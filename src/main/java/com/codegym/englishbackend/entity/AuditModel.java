package com.codegym.englishbackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(
//        value = {"createdAt", "updateAt"},
//        allowGetters = true
//)
public abstract class AuditModel implements Serializable {

    private static final long serialVersionUID = -3952398590854367799L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "create_at", nullable = true, updatable = true)
   // @CreatedDate
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate createAt;

    @Column(name = "updated_at", nullable = true)
   // @LastModifiedDate
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate  updateAt;
}
