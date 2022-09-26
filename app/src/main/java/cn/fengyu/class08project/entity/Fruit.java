package cn.fengyu.class08project.entity;

public class Fruit {
    private int imageId;
    private String name;
    private String price;

    public Fruit(int imageId, String name, String price) {
        this.imageId = imageId;
        this.name = name;
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    private Fruit(Builder builder) {
        imageId = builder.imageId;
        name = builder.name;
        price = builder.price;
    }

    public static final class Builder {
        private int imageId;
        private String name;
        private String price;

        public Builder() {
        }

        public Builder imageId(int val) {
            imageId = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder price(String val) {
            price = val;
            return this;
        }

        public Fruit build() {
            return new Fruit(this);
        }
    }
}
