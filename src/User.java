import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class User extends Person{
    private boolean isMember;
    private boolean isLoggedIn;
    //storing information about the product user has purchased along with purchased date
    private HashMap<String, Date> purchasedPrd;
    String getUserName(){
        return this.userName;
    }

    String getPassWord(){
        return this.passWord;
    }
    public void purchaseProduct(int pid){
        int lastProductID = Product.getDeltaID();
        Product.setDeltaID(lastProductID+1);
        Date currDate = new Date();
        //original productID.lastProductID.date
        String finalProductID = String.valueOf(pid) +"."+ String.valueOf(lastProductID);
        purchasedPrd.put(finalProductID,currDate);
    }

    public void setPassword(String passWord){
        this.passWord = passWord;
    }
    public void getPassword(String passWord){
        this.passWord = passWord;
    }

    public User(String fullName,String userName,String userPass){
        purchasedPrd = new HashMap<String,Date>();
        this.fullName=fullName;
        this.userName=userName;
        this.passWord=userPass;
    }
    @Override
    public boolean login(String userName, String passWord) {
        return false;
    }

    void register(String userName,String password,String fullName){

    }
    @Override
    public void changePassword() {

    }
    public void setLoggedIn(boolean stat){this.isLoggedIn=stat;}
    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public void showProfile(){
        System.out.println("::: My Profile :::");
        System.out.println("Username : "+this.userName);
        System.out.println("FullName : "+this.fullName);
        System.out.println("My Purchased Products :: ");
    }

    public int getPurchasedProductsSize(){
        return purchasedPrd.size();
    }

    public HashMap<String, Date> getPurchasedProducts(){
        return purchasedPrd;
    }
}
