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

@Table(name = "reports")
@NamedQueries({
    @NamedQuery(
            name = "getReportsCountByEmoticon_id",
            query = "SELECT COUNT(r) FROM Report AS r WHERE r.emoticon_id = :emoticon_id"//顔文字(id)別の通報件数を取得
            ),
    @NamedQuery(
            name = "getReasonsOfEmoticon_id",
            query = "SELECT r FROM Report AS r WHERE r.emoticon_id = :emoticon_id ORDER BY r.id DESC"//顔文字(id)別の通報理由を取得
            ),
    @NamedQuery(
            name = "getAllReportsByNotDeal_flag",
            query = "SELECT r FROM Report AS r WHERE r.deal_flag = :deal_flag ORDER BY r.id DESC"//未対応の分だけ取得
            ),
    @NamedQuery(
            name = "getAllReportsCountByNotDeal_flag",
            query = "SELECT COUNT(r) FROM Report AS r WHERE r.deal_flag = false ORDER BY r.id DESC"//未対応の件数を取得
            )
})
@Entity
public class Report {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "emoticon_id",nullable = false)
    private Integer emoticon_id;

    @Column(name = "report_user",nullable = false)//通報した人のユーザー名
    private String report_user;

    @Column(name = "create_user",nullable = false)
    private String create_user;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "update_user",nullable = false)
    private String update_user;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @Column(name = "deal_flag", nullable = false)//対応済み
    private Boolean deal_flag;

    @Column(name = "reason", nullable = false)//通報理由
    private String reason;

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

    public String getReport_user() {
        return report_user;
    }

    public void setReport_user(String report_user) {
        this.report_user = report_user;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getUpdate_user() {
        return update_user;
    }

    public void setUpdate_user(String update_user) {
        this.update_user = update_user;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Boolean getDeal_flag() {
        return deal_flag;
    }

    public void setDeal_flag(Boolean deal_flag) {
        this.deal_flag = deal_flag;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
