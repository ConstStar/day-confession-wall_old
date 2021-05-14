package cn.xiaoxiaoge.api;


import cn.xiaoxiaoge.dao.PostMapper;
import cn.xiaoxiaoge.pojo.Post;
import cn.xiaoxiaoge.uilts.MybatisUtils;
import cn.xiaoxiaoge.uilts.PostUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletInputStream;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "post", value = "/post")
public class ApiPost extends HttpServlet {

    Logger logger = Logger.getLogger(ApiPost.class);

    public void init() {
    }


    //type 1 分页帖子列表
    //type 2 单个帖子内容
    //type 3 帖子总数量
    //type 4 搜索帖子
    //type 5 搜索总数量
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        JSONObject json = null;
        PrintWriter writer = null;
        try {
            json = new JSONObject();
            writer = response.getWriter();

            String typeStr = request.getParameter("type");
            if (typeStr == null)
                throw new Exception("参数有误");

            int type = Integer.parseInt(typeStr);

            //分页帖子列表
            if (type == 1) {
                String pageIndexStr = request.getParameter("page_index");
                String pageSizeStr = request.getParameter("page_size");
                if (pageIndexStr == null || pageSizeStr == null)
                    throw new Exception("参数有误");

                int pageIndex = Integer.parseInt(pageIndexStr);
                int pageSize = Integer.parseInt(pageSizeStr);

                if (pageIndex < 1 || pageSize < 1)
                    throw new Exception("页数或帖数不得小于1");

                //获取表白列表
                List<Post> posts = PostUtils.getPosts(pageIndex, pageSize);
                if (posts == null)
                    throw new Exception("没有查询到结果");

                JSONArray jsonArray = new JSONArray();
                for (Post post : posts) {
                    JSONObject jsonObject = new JSONObject();

                    int id = post.getId();
                    String sender = post.getSender();
                    int senderSex = post.getSenderSex();
                    String recipient = post.getRecipient();
                    int recipientSex = post.getRecipientSex();
                    Date createTime = post.getCreateTime();
                    String content = post.getContent();
                    int thumbsUp = post.getThumbsUp();
                    int commentCount = post.getCommentCount();

                    long createTimeLong = createTime == null ? 0 : createTime.getTime();

                    jsonObject.put("id", id);
                    jsonObject.put("sender", sender);
                    jsonObject.put("sender_sex", senderSex);
                    jsonObject.put("recipient", recipient);
                    jsonObject.put("recipient_sex", recipientSex);
                    jsonObject.put("create_time", createTimeLong);
                    jsonObject.put("content", content);
                    jsonObject.put("thumbs_up", thumbsUp);
                    jsonObject.put("comment_count", commentCount);

                    jsonArray.add(jsonObject);
                }
                json.put("posts", jsonArray.toArray());


                //单个帖子内容
            } else if (type == 2) {
                String idStr = request.getParameter("id");
                if (idStr == null)
                    throw new Exception("参数有误");

                int id = Integer.parseInt(idStr);

                //获取表白
                Post post = PostUtils.getPost(id);
                if (post == null)
                    throw new Exception("没有此表白");

                JSONObject jsonObject = new JSONObject();
                String sender = post.getSender();
                int senderSex = post.getSenderSex();
                String recipient = post.getRecipient();
                int recipientSex = post.getRecipientSex();
                Date createTime = post.getCreateTime();
                String content = post.getContent();
                int thumbsUp = post.getThumbsUp();
                int commentCount = post.getCommentCount();

                long createTimeLong = createTime == null ? 0 : createTime.getTime();

                jsonObject.put("id", id);
                jsonObject.put("sender", sender);
                jsonObject.put("sender_sex", senderSex);
                jsonObject.put("recipient", recipient);
                jsonObject.put("recipient_sex", recipientSex);
                jsonObject.put("create_time", createTimeLong);
                jsonObject.put("content", content);
                jsonObject.put("thumbs_up", thumbsUp);
                jsonObject.put("comment_count", commentCount);

                json = jsonObject;

            } else if (type == 3) {
                int count = PostUtils.getCount();
                json.put("count", count);
            } else if (type == 4) {
                String keyword = request.getParameter("keyword");
                String pageIndexStr = request.getParameter("page_index");
                String pageSizeStr = request.getParameter("page_size");
                if (keyword == null || pageIndexStr == null || pageSizeStr == null)
                    throw new Exception("参数有误");

                int pageIndex = Integer.parseInt(pageIndexStr);
                int pageSize = Integer.parseInt(pageSizeStr);
                if (pageIndex < 1 || pageSize < 1)
                    throw new Exception("页数或帖数不得小于1");

                //获取表白列表
                List<Post> posts = PostUtils.getSearchPosts(keyword, pageIndex, pageSize);
                if (posts == null)
                    throw new Exception("没有查询到结果");

                JSONArray jsonArray = new JSONArray();
                for (Post post : posts) {
                    JSONObject jsonObject = new JSONObject();

                    int id = post.getId();
                    String sender = post.getSender();
                    int senderSex = post.getSenderSex();
                    String recipient = post.getRecipient();
                    int recipientSex = post.getRecipientSex();
                    Date createTime = post.getCreateTime();
                    String content = post.getContent();
                    int thumbsUp = post.getThumbsUp();
                    int commentCount = post.getCommentCount();

                    long createTimeLong = createTime == null ? 0 : createTime.getTime();

                    jsonObject.put("id", id);
                    jsonObject.put("sender", sender);
                    jsonObject.put("sender_sex", senderSex);
                    jsonObject.put("recipient", recipient);
                    jsonObject.put("recipient_sex", recipientSex);
                    jsonObject.put("create_time", createTimeLong);
                    jsonObject.put("content", content);
                    jsonObject.put("thumbs_up", thumbsUp);
                    jsonObject.put("comment_count", commentCount);

                    jsonArray.add(jsonObject);
                }
                json.put("posts", jsonArray.toArray());

            } else if (type == 5) {
                String keyword = request.getParameter("keyword");
                if (keyword == null)
                    throw new Exception("参数有误");
                int count = PostUtils.getSearchCount(keyword);
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

    //发布表白
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

            String sender = jsonObject.getString("sender");
            Integer sender_sex = jsonObject.getInteger("sender_sex");
            String recipient = jsonObject.getString("recipient");
            Integer recipient_sex = jsonObject.getInteger("recipient_sex");
            String content = jsonObject.getString("content");

            if (sender == null || sender_sex == null || recipient == null || recipient_sex == null || content == null) {
                throw new Exception("参数有误");
            }

            if (sender.length() > 6 || recipient.length() > 6)
                throw new Exception("名称不得超过6个字符");

            if (content.length() > 160)
                throw new Exception("内容不得超过160个字符");

            Post post = new Post(0, sender, sender_sex, recipient, recipient_sex, new Date(), content, 0, 0);
            int updateLine = PostUtils.addPost(post);

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

    //点赞
    public void doPut(HttpServletRequest request, HttpServletResponse response) {

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


            Integer type = jsonObject.getInteger("type");
            //点赞
            if (type == 1) {
                Boolean thumbs_up = jsonObject.getBoolean("thumbs_up");
                Integer id = jsonObject.getInteger("id");
                if (thumbs_up == null || id == null)
                    throw new Exception("参数有误");

                PostUtils.incrementThumbsUp(id, thumbs_up);

            } else {
                throw new Exception("参数有误");
            }

            json.put("code", 1);
            json.put("message", "成功");
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