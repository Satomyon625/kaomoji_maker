package controllers.toppage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.Emoticon;
import utils.DBUtil;

/**
 * Servlet implementation class CopyServlet
 */
@WebServlet(urlPatterns={"/saveCopy"})
public class CopyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CopyServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        //顔文字のid(Integer）を取得（パラメータ）
       // int emoticon_id = Integer.parseInt(request.getParameter("emoticon_id"));
        Emoticon e = em.find(Emoticon.class, Integer.parseInt(request.getParameter("emoticon_id")));
        String message = null;

        try {

        long num = e.getCopy_number()+1;
        e.setCopy_number(num);//コピー数にカウント

        em.getTransaction().begin();
        em.getTransaction().commit();
        em.close();
        message = "コピー数にカウントされました。";


        //出力(レスポンスをmapに格納してJSON化)

        //JSONマップ
        Map<String,String> mapMsg = new HashMap<String, String>();//文字列出力でStringに

        //追加
        mapMsg.put("message", message);

        //マッパ(JSON <-> Map, List)
        ObjectMapper mapper = new ObjectMapper();

        //json文字列
        String jsonStr = mapper.writeValueAsString(mapMsg); //list, map

        //ヘッダ設定
        response.setContentType("application/json;charset=UTF-8"); //JSON形式, UTF-8

        //pwオブジェクト
        PrintWriter pw = response.getWriter();

        //出力
        pw.print(jsonStr);

        //クローズ
        pw.close();

    } catch (Exception er) {
        er.printStackTrace();
    }

}
}