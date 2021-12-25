package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.User;
import utils.DBUtil;

public class UserValidator {
    public static List<String> validate(User u, Boolean u_nameDuplicateCheckFlag, Boolean passCheckFlag) {
        List<String> errors = new ArrayList<String>();

        String u_name_error = validateU_name(u.getU_name(), u_nameDuplicateCheckFlag);
        if(!u_name_error.equals("")) {
            errors.add(u_name_error);
        }

        String pass_error = validatePass(u.getPass(), u.getPass(), passCheckFlag);
        if(!pass_error.equals("")) {
            errors.add(pass_error);
        }

        return errors;
    }

    //ユーザー名の必須入力チェック
    private static String validateU_name(String u_name, Boolean u_nameDuplicateCheckFlag) {
        //必須入力
        if(u_name == null || u_name.equals("")) {
            return "ユーザー名を入力してください。";
        }

     // すでに登録されているユーザー名との重複チェック
        if(u_nameDuplicateCheckFlag) {
            EntityManager em = DBUtil.createEntityManager();
            long users_count = (long)em.createNamedQuery("checkRegisteredU_name", Long.class).setParameter("u_name", u_name).getSingleResult();
            em.close();
            if(users_count > 0) {
                return "入力されたユーザー名はすでに存在しています。";
            }
        }
        return "";
    }

    // パスワードの必須入力チェック
    private static String validatePass(String pass, String c_pass, Boolean passCheckFlag) {
        if (pass == null || pass.equals("") || c_pass == null || c_pass.equals("")) {
            return "パスワードを入力してください。";
        }

        if(pass != c_pass) {
            return "確認用のパスワードが一致しません。";
        }
        return "";
    }
}
