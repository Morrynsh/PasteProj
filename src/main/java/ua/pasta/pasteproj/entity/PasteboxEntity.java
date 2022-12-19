package ua.pasta.pasteproj.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pastes", schema = "pasteproj")
public class PasteboxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "data")
    private String data;
    @Column(name = "hash")
    private String hash;
    @Column(name = "lifetime")
    private LocalDateTime lifeTime;
    @Column(name = "ispublic")
    private boolean isPublic;

    public PasteboxEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(LocalDateTime lifeTime) {
        this.lifeTime = lifeTime;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    @Override
    public String toString() {
        return "PasteboxEntity{" +
                "id=" + id +
                ", hash='" + hash + '\'' +
                ", lifeTime=" + lifeTime +
                ", isPublic=" + isPublic +
                '}';
    }
}
