package session7.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "update_info")
public class Update{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String message;
    @Column(name = "update_time")
    private Date updateTime;
    @ManyToOne
    private User user;
}
