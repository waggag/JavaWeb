package cn.waggag.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: waggag
 * @time: 2019/7/8 22:54
 */
public class User {

    private String username;
    private String password;
    private Date date;
    public String getBitStr(){
        if(date != null){
            //1.格式化日期
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //2.返回字符串
            return simpleDateFormat.format(date);
        }else {
            return null;
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
