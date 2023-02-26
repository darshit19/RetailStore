import java.io.BufferedReader;
import java.util.ArrayList;

public class Admin extends Person implements Fine{

    String getUserName(){
        return this.userName;
    }

    String getPassWord(){
        return this.passWord;
    }


    @Override

    public boolean login(String userName,String passWord) {
        if(this.userName.equals(userName) && this.passWord.equals(passWord))return true;
        return false;
    }

    @Override
    public void changePassword() {

    }

    void addProduct(ArrayList<Product> products, BufferedReader br){

        try{
            while(true){
                System.out.print("Enter ProductName : ");
                String pName = br.readLine();
                System.out.print("Enter  Stock : ");
                int pStock = Integer.parseInt(br.readLine());
                System.out.print("Enter Base Price : ");
                double bPrice = Double.parseDouble(br.readLine());
                System.out.print("Enter Selling Price : ");
                double sellPrice = Double.parseDouble(br.readLine());
                System.out.print("Enter Discount percentage : ");
                double disPer = Double.parseDouble(br.readLine());
                System.out.print("Enter expected return days :");
                int retDays=Integer.parseInt(br.readLine());

                if(pStock<0 && bPrice<=0.0){
                    System.out.println("Product Stock and Product Price can not be Negative or zero ...!!!\n");
                    continue;
                }
                if(pStock < 0){
                    System.out.println("Product Stock can not be Negative ...!!!\n");
                    continue;
                }
                if(bPrice <=0.0){
                    System.out.println("Product Price can not be less than or equal to 0...!!!\n");
                    continue;
                }
                products.add(new Product(String.valueOf(products.size()),pName,bPrice,sellPrice,pStock,disPer,retDays));
                System.out.println("New Product Added Successfully...\n");
                break;
            }
        }catch (Exception e){
            printInvalidInput();
        }
    }

    public Admin(String fullName,String uName,String password){
        this.fullName=fullName;
        this.userName=uName;
        this.passWord=password;
    }
    @Override
    public double calculateFine() {
        return 0;
    }

    public static void printInvalidInput() {
        System.out.println("Invalid Input...!!!\n");
    }
}
