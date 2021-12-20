package session7.model.entity;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import session7.model.enumaration.UserType;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String family;
    private String nationalCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Account> account = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @UpdateTimestamp
    private Date updateDate;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Update> update = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", family='" + family + '\'' +
                ", nationalCode=" + nationalCode +
                ", account=" + account +
                ", userType=" + userType +
                ", creationDate=" + creationDate +
                ", updateDate=" + updateDate +
                ", update=" + update +
                '}';
    }
}

