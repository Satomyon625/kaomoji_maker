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

@Table(name = "ctransaction")//顔文字カテゴリトランザクション
@NamedQueries({
    @NamedQuery(
            name = "SearchCategoryByName",
            query = "SELECT t FROM Transaction AS t WHERE t.category_id = :category_id"//カテゴリ検索(カテゴリ名を取り出してそのidを取得して検索)
            ),
    @NamedQuery(
            name = "getCategoryByDefault",
            query = "SELECT t FROM Transaction AS t WHERE t.emoticon_id = :emoticon_id AND 8 >= :category_id"//デフォルトカテゴリでチェックされてるカテゴリを検索
            )
})
@Entity
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "emoticon_id")//顔文字id
    private Emoticon emoticon_id;

    @ManyToOne
    @JoinColumn(name = "category_id")//カテゴリid
    private Category category_id;

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

    public Emoticon getEmoticon_id() {
        return emoticon_id;
    }

    public void setEmoticon_id(Emoticon emoticon_id) {
        this.emoticon_id = emoticon_id;
    }

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
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
