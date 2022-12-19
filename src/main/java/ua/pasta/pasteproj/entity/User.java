package ua.pasta.pasteproj.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users", schema = "pasteproj")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_role")
    private Role role;
}
