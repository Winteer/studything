package com.winter.studything.Entity;

/**
 * 预约信息
 * @author Winter
 * @title: BookInfo
 * @projectName studything
 * @description: TODO
 * @date 2019/9/819:24
 **/
public class BookInfo {
    private long id;    //自增id
    private String book_time;   //预定时间
    private String phone;   //预约手机号码
    private String room;    //预定主题
    private String number;  //预约人数
    private String start_time;  //游戏开始时间
    private String end_time;    //游戏结束时间
    private String create_time; //记录创建时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBook_time() {
        return book_time;
    }

    public void setBook_time(String book_time) {
        this.book_time = book_time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
