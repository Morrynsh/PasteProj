package ua.pasta.pasteproj.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "permissions", schema = "pasteproj")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "fk_role")
    private Role role;
}
