package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(
        name = "getAllUsers",
        query = "SELECT u FROM User AS u ORDER BY u.u_name DESC"
    )
})
@Table(name = "users")
public class User {
    @Id
    @Column(name = "u_name", length = 16, nullable = false)
    private String u_name;

    @Column(name = "pass", length = 20, nullable = false)
    private String pass;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    private Integer admin_flag; //ログインフィルターのためにたちまち追記

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getAdmin_flag() {
        return admin_flag;
    }
    public void setAdmin_flag(Integer admin_flag) {
        this.admin_flag = admin_flag;
    }

}
