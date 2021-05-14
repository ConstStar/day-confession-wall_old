package cn.xiaoxiaoge.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private int id;
    private int pId;

    private String name;
    private Date createTime;
    private String content;
}
