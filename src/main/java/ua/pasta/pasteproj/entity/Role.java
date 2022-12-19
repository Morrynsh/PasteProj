package ua.pasta.pasteproj.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@Table(name = "roles", schema = "pasteproj")
public class Role {
    @Id
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    private Collection<Permission> permissions = new ArrayList<>();

    public void addPermission(Permission permission){
        permissions.add(permission);
        permission.setRole(this);
    }

    public void removePermission(Permission permission){
        permissions.remove(permission);
        permission.setRole(null);
    }
}
