public class Product {
    private String id;
    private String prdName;
    private double bPrice;
    private double sPrice;
    private int stock;
    private  double discount;
    private int validRetDays;
    private static int deltaID;

    //make setter and getter

    public Product(String id,String prdName,double bPrice,double sPrice,int stock,double discount,int validRetDays){
        this.id=id;
        this.prdName=prdName;
        this.bPrice=bPrice;
        this.sPrice=sPrice;
        this.stock=stock;
        this.discount=discount;
        this.validRetDays=validRetDays;
        deltaID=1;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrdName(String prdName) {
        this.prdName = prdName;
    }

    public void setbPrice(double bPrice) {
        this.bPrice = bPrice;
    }

    public void setsPrice(double sPrice) {
        this.sPrice = sPrice;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setValidRetDays(int validRetDays) {
        this.validRetDays = validRetDays;
    }

    public static void setDeltaID(int deltaID) {
        Product.deltaID = deltaID;
    }

    public String getId() {
        return id;
    }

    public String getPrdName() {
        return prdName;
    }

    public double getsPrice() {
        return sPrice;
    }

    public double getbPrice() {
        return bPrice;
    }

    public int getStock() {
        return stock;
    }

    public double getDiscount() {
        return discount;
    }

    public static int getDeltaID() {
        return deltaID;
    }

    public int getValidRetDays() {
        return validRetDays;
    }
}
