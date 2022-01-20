package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "emoticons")
@NamedQueries({
    @NamedQuery(
            name = "getAllEmoticons",
            query = "SELECT e FROM Emoticon AS e WHERE e.delete_flag = false ORDER BY e.id DESC"//デフォルト、新着順
            ),
    @NamedQuery(
            name = "getAllEmoticonsByOld",
            query = "SELECT e FROM Emoticon AS e WHERE e.delete_flag = false ORDER BY e.id ASC"//ソート、古い順
            ),
    @NamedQuery(
            name = "getAllEmoticonsByCopy",
            query = "SELECT e FROM Emoticon AS e WHERE e.delete_flag = false ORDER BY e.copy_number DESC"//ソート、コピー順
            ),
    @NamedQuery(
            name = "getAllEmoticonsByLike",
            query = "SELECT e FROM Emoticon AS e WHERE e.delete_flag = false ORDER BY e.like_number DESC"//ソート、いいね順
            ),
    @NamedQuery(
            name = "getEmoticonsCount",
            query = "SELECT COUNT(e) FROM Emoticon AS e WHERE e.delete_flag = false"
        ),
    @NamedQuery(
            name = "getMyAllEmoticons",
            query = "SELECT e FROM Emoticon AS e WHERE e.create_user = :create_user AND e.delete_flag = false ORDER BY e.id DESC"
        ),
    @NamedQuery(
            name = "getCopy_numberCount",
            query = "SELECT COUNT(e) FROM Emoticon AS e WHERE e.id = :id AND e.delete_flag = false"
        ),
        @NamedQuery(
            name = "getMyEmoticonsCount",
            query = "SELECT COUNT(e) FROM Emoticon AS e WHERE e.create_user = :create_user AND e.delete_flag = false"
        ),
        @NamedQuery(
                name = "getEmoticonsByCategoryId",
                query = "SELECT e FROM Emoticon e, Transaction t WHERE e.delete_flag = false AND e = t.emoticon_id AND t.category_id = :category_id ORDER BY e.id DESC"//検索したカテゴリidを条件にトランザクションテーブルとidを結合し顔文字を取得（新着順デフォルト）
            ),
        @NamedQuery(
                name = "getEmoticonsByCategoryIdAndOld",
                query = "SELECT e FROM Emoticon e, Transaction t WHERE e.delete_flag = false AND e = t.emoticon_id AND t.category_id = :category_id ORDER BY e.id ASC"//検索したカテゴリidを条件にトランザクションテーブルとidを結合し顔文字を取得（古い順）
            ),
        @NamedQuery(
                name = "getEmoticonsByCategoryIdAndCopy",
                query = "SELECT e FROM Emoticon e, Transaction t WHERE e.delete_flag = false AND e = t.emoticon_id AND t.category_id = :category_id ORDER BY e.copy_number DESC"//検索したカテゴリidを条件にトランザクションテーブルとidを結合し顔文字を取得（コピー順）
            ),
        @NamedQuery(
                name = "getEmoticonsByCategoryIdAndLike",
                query = "SELECT e FROM Emoticon e, Transaction t WHERE e.delete_flag = false AND e = t.emoticon_id AND t.category_id = :category_id ORDER BY e.like_number DESC"//検索したカテゴリidを条件にトランザクションテーブルとidを結合し顔文字を取得（いいね順）
            ),
        @NamedQuery(
                name = "getEmoticonsByCategoryIdCount",
                query = "SELECT COUNT(e) FROM Emoticon e, Transaction t WHERE e.delete_flag = false AND e = t.emoticon_id AND t.category_id = :category_id"//検索したカテゴリidを条件にトランザクションテーブルとidを結合し顔文字を取得
            )
})
@Entity
public class Emoticon {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "emoticon", length = 128, nullable = false)
    private String emoticon;

    @Column(name = "copy_number", nullable = false)
    private long copy_number;

    @Column(name = "delete_flag", nullable = false)
    private Boolean delete_flag;

    @ManyToOne
    @JoinColumn(name = "create_user", nullable = false)
    private User create_user;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @ManyToOne
    @JoinColumn(name = "updated_user", nullable = false)
    private User updated_user;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @Column(name = "like_number", nullable = false)
    private long like_number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmoticon() {
        return emoticon;
    }

    public void setEmoticon(String emoticon) {
        this.emoticon = emoticon;
    }

    public long getCopy_number() {
        return copy_number;
    }

    public void setCopy_number(long copy_number) {
        this.copy_number = copy_number;
    }

    public Boolean getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Boolean delete_flag) {
        this.delete_flag = delete_flag;
    }

    public User getCreate_user() {
        return create_user;
    }

    public void setCreate_user(User create_user) {
        this.create_user = create_user;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public User getUpdated_user() {
        return updated_user;
    }

    public void setUpdated_user(User updated_user) {
        this.updated_user = updated_user;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public long getLike_number() {
        return like_number;
    }

    public void setLike_number(long like_number) {
        this.like_number = like_number;
    }

}
