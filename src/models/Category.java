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

@Table(name = "categories")//顔文字カテゴリマスタ
@NamedQueries({
    @NamedQuery(
            name = "getCategoryName",
            query = "SELECT c FROM Category AS c WHERE c.category = :category"//カテゴリの重複をチェック、検索ワードに一致するカテゴリ名のid取得
            ),
    @NamedQuery(
            name = "getCategoryByInput",
            query = "SELECT c FROM Category c, Transaction t WHERE c.id = t.category_id.id AND t.emoticon_id = :emoticon_id AND 8 < t.category_id.id"//入力カテゴリ名を取得
            )
})
@Entity
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "category", length = 64, nullable = false, unique = true)//カテゴリ名、重複不可
    private String category;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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


}
