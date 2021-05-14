package cn.xiaoxiaoge.api;

import cn.xiaoxiaoge.pojo.Comment;
import cn.xiaoxiaoge.uilts.CommentUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletInputStream;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "comment", value = "/comment")
public class ApiComment extends HttpServlet {

    Logger logger = Logger.getLogger(ApiPost.class);

    public void init() {

    }


    //获取帖子分页评论列表
    //获取帖子评论总数
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        JSONObject json = null;
        PrintWriter writer = null;
        try {
            json = new JSONObject();
            writer = response.getWriter();
            //type 1 获取帖子分页评论列表
            //type 2 保留
            //type 3 获取帖子评论总数量
            String typeStr = request.getParameter("type");
            if (typeStr == null)
                throw new Exception("参数有误");

            int type = Integer.parseInt(typeStr);
            //获取分页评论列表
            if (type == 1) {
                String pageIndexStr = request.getParameter("page_index");
                String pageSizeStr = request.getParameter("page_size");
                String pIdStr = request.getParameter("p_id");

                if (pageIndexStr == null || pageSizeStr == null || pIdStr == null)
                    throw new Exception("参数有误");

                int pageIndex = Integer.parseInt(pageIndexStr);
                int pageSize = Integer.parseInt(pageSizeStr);
                int pId = Integer.parseInt(pIdStr);

                List<Comment> comments = CommentUtils.getComments(pId, pageIndex, pageSize);
                if (comments == null)
                    throw new Exception("没有查询到结果");

                JSONArray jsonArray = new JSONArray();
                for (Comment comment : comments) {
                    JSONObject jsonObject = new JSONObject();

                    int id = comment.getId();
                    String name = comment.getName();
                    Date createTime = comment.getCreateTime();
                    String content = comment.getContent();

                    long createTimeLong = createTime == null ? 0 : createTime.getTime();

                    jsonObject.put("id", id);
                    jsonObject.put("p_id", pId);
                    jsonObject.put("name", name);
                    jsonObject.put("create_time", createTimeLong);
                    jsonObject.put("content", content);

                    jsonArray.add(jsonObject);
                }

                json.put("comments", jsonArray);

                //获取帖子评论总数量
            } else if (type == 3) {
                String PidStr = request.getParameter("p_id");
                if (PidStr == null)
                    throw new Exception("参数有误");
                int pId = Integer.parseInt(PidStr);

                int count = CommentUtils.getCount(pId);
                json.put("count", count);
            } else {
                throw new Exception("参数有误");
            }

            json.put("code", 1);
            json.put("message", "查询成功");
        } catch (Exception ex) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            String message = ex.getMessage();
            if (message.isEmpty())
                message = ex.toString();

            jsonObject.put("message", message);
            json = jsonObject;
        } finally {

            if (json == null) {
                logger.error(request.toString() + "\r\njson is null");
                return;
            }

            if (writer == null) {
                logger.error(request.toString() + "\r\nwriter is null");
                return;
            }

            String res = json.toJSONString();
            writer.write(res);

        }
    }

    //发布评论
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        JSONObject json = null;
        PrintWriter writer = null;
        try {
            json = new JSONObject();
            writer = response.getWriter();

            BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer("");
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            if (sb.length() == 0)
                throw new Exception("参数有误");

            JSONObject jsonObject = JSONObject.parseObject(sb.toString());
            if (jsonObject == null || jsonObject.isEmpty())
                throw new Exception("参数有误");

            int id = 0;
            String name = jsonObject.getString("name");
            String pIdStr = jsonObject.getString("p_id");
            String content = jsonObject.getString("content");

            if (name == null || pIdStr == null || content == null)
                throw new Exception("参数有误");

            if (name.length() > 6)
                throw new Exception("名称不得超过6个字符");

            if (content.length() > 160)
                throw new Exception("内容不得超过160个字符");

            int pId = Integer.parseInt(pIdStr);


            Comment comment = new Comment(id, pId, name, new Date(), content);
            int updateLine = CommentUtils.addComment(comment);

            if (updateLine != 1) {
                throw new Exception("添加失败，数据库添加行数为" + updateLine);
            }

            json.put("code", 1);
            json.put("message", "发布成功");
        } catch (Exception ex) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", -1);
            String message = ex.getMessage();
            if (message.isEmpty())
                message = ex.toString();

            jsonObject.put("message", message);
            json = jsonObject;
        } finally {

            if (json == null) {
                logger.error(request.toString() + "\r\njson is null");
                return;
            }

            if (writer == null) {
                logger.error(request.toString() + "\r\nwriter is null");
                return;
            }
            String res = json.toJSONString();
            writer.write(res);
        }
    }

    public void destroy() {
    }
}