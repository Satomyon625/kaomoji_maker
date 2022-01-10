package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "likes")
@NamedQueries({
    @NamedQuery(
            name = "getMyAllLikes",
            query = "SELECT l FROM Like AS l WHERE l.like_user = :like_user ORDER BY l.id DESC"
            ),
    @NamedQuery(
            name = "getLikeCountByEmoticon_idAndLike_user",
            query = "SELECT COUNT(l) FROM Like AS l WHERE l.emoticon_id = :emoticon_id AND l.like_user = :like_user"
            )
})
@Entity
public class Like {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "emoticon_id",nullable = false)
    private Integer emoticon_id;

    @Column(name = "like_user",nullable = false)
    private String like_user;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmoticon_id() {
        return emoticon_id;
    }

    public void setEmoticon_id(Integer emoticon_id) {
        this.emoticon_id = emoticon_id;
    }

    public String getLike_user() {
        return like_user;
    }

    public void setLike_user(String like_user) {
        this.like_user = like_user;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

}
