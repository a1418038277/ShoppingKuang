package firsttest.test.shoppingkuang.realm;

import io.realm.RealmObject;

public class MyUser extends RealmObject {
    private int id;
    private String imgPath;
    private String title;
    private String desc;
    private String price;

    public MyUser() {
    }

    public MyUser(int id, String imgPath, String title, String desc, String price) {
        this.id = id;
        this.imgPath = imgPath;
        this.title = title;
        this.desc = desc;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}