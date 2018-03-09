package kr.yearnning.hamburger.model;

/**
 * Created by yearnning on 14. 10. 24..
 */
public class Food {

    private String img_url = null;
    private String name = null;
    private int price = 0;
    private int kcal = 0;

    /**
     * Getter and Setter
     *
     * @return
     */

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }


}
