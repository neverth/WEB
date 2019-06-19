package entity;



public class   GoodsClass {
    private int goodClassId;
    private String  classname;

    public GoodsClass(int goodClassId, String className ) {
        this.goodClassId = goodClassId;
        this.classname = className;
    }

    public GoodsClass(int goodClassId) {
        this.goodClassId = goodClassId;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getGoodClassId() {
        return goodClassId;
    }

    public void setGoodClassId(int goodClassId) {
        this.goodClassId = goodClassId;
    }
}