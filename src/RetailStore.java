import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;

public class RetailStore {
    //no of admins are fixed so normal fixed size array is ok
    private Admin[] admins;
    private ArrayList<Product> products;
    //users can be added dynamically so array size is not fixed so arraylist
    private ArrayList<User> users;
    private int currAdminIndex;

    RetailStore(){
        currAdminIndex = -1;
        admins=new Admin[2];
        admins[0] = new Admin("darshit","darshit","darshit");
        admins[1]= new Admin("chirag","chirag","chirag");
        users = new ArrayList<User>();
        products = new ArrayList<Product>();
        products.add(new Product("1","Biscuit",50.0,70.0,55,5,0));
        products.add(new Product("2","Rin Powder",200.0,300.0,12,20,5));
        products.add(new Product("3","Patanjali Shampoo",450.0,600.0,15,10,6));
        products.add(new Product("4","Dove Soap",70.0,100.0,15,12,1));
        products.add(new Product("5","Slice Cold-drink",20.0,25.0,20,0,0));
    }

    //function to take the input from the user
    public int takeChoice(BufferedReader br){
        int choice;
        System.out.print("Enter your choice : ");
        try{
            choice = Integer.parseInt(br.readLine());
            return choice;
        }catch (Exception e){
            return -1;
        }
    }

    //function to change the character array into the string
    public static String toString(char[] a) {
        String string = new String(a);
        return string;
    }

    //function to hide the password while reading
    private static String readPassword() {
        Console console;
        if ((console = System.console()) != null) {
            char[] password = console.readPassword();
            return toString(password);
        }
        return null;
    }

    //function to check is the admin with given admin details is there or not
    private int findAdmin(String uName,String password){
        for(int i=0;i<admins.length;i++){
            if(admins[i].getUserName().equals(uName) && admins[i].getPassWord().equals(password)){
                return i;
            }
        }
        return -1;
    }

    //function to add the product which can be only used by admin

    private void displayProducts(BufferedReader br){
        printDashLine();
        System.out.println("hello display");
        //System.out.printf("%-11s %-20s %-11s %-6s %-12s %-12s %-8 %-18", "PRODUCT ID", "PRODUCTNAME", "ISAVAILABLE", "STOCK", "BASEPRICE","SELLPRICE","DISCOUNT","VALID RETURN DAYS");
        System.out.println();
        printDashLine();
//iterates over the list
//        for(Product prd: products)
//        {
//            String temp;
//            if(prd.getStock()>0){
//                temp="In-store";
//            }else{
//                temp="OutOfStock";
//            }
//            System.out.format("%-11s %-20s %-11s %-6s %-12s %-12s %-8 %-18",prd.getId(),prd.getPrdName(),temp,prd.getStock(),prd.getbPrice(),prd.getsPrice(),prd.getDiscount(),prd.getValidRetDays() );
//            System.out.println();
//        }
//        System.out.println("----------------------------------------------------------------------------------------------");
    }

    private void countTotalProducts(){
        System.out.println("Total number of products availabe in Store : " + products.size());
    }

    private void calculateMaxProfit(){
        double totalProfit=0;
        for(Product prd:products){
            double totalSellPrice=prd.getsPrice()*prd.getStock();
            double totalDiscount=totalSellPrice*prd.getDiscount()/100;
            double totalBuyingPrice=prd.getbPrice()*prd.getStock();
            totalProfit+= totalSellPrice - totalDiscount - totalBuyingPrice ;
        }
        System.out.println("Maximum Profit can be achieved is : "+totalProfit+" Rs.");
    }
    public void handleAdmin(BufferedReader br) throws IOException {
        printAdminWelcomeMsg();
        String aName;
        String aPass;

        while(true){
            System.out.print("Enter username : ");
            aName = br.readLine();
            System.out.print("Enter password : ");
            aPass = readPassword();
            int res = findAdmin(aName,aPass);
            currAdminIndex = res;
           if(res!=-1){
               System.out.println("Logged In Successfully...");
               int choice;

               while(true){
                   displayAdminOptions();
                   choice = takeChoice(br);
                   switch(choice){
                       case 1:
                           admins[currAdminIndex].addProduct(products,br);
                           break;
                       case 2:
                           displayProducts(br);
                           break;
                       case 3:
                           countTotalProducts();
                           break;
                       case 4:
                           calculateMaxProfit();
                           break;
                       case 5:
                           return;
                       default:
                           printInvalidChoice();
                           break;
                   }
               }
           }else{
               System.out.println("Invalid Admin Credentials");
           }

        }
    }
    public void start(){
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        // for printing welcome message
        printWelcomeMsg();
        printLine();

        int choice;

        while(true){
            displayTypeOfUserOptions();
            System.out.print("Enter your choice : ");
            try{
                choice = Integer.parseInt(br.readLine());
                switch (choice){
                    case 1:
                        handleAdmin(br);
                        break;
                    //case 2:
                        //handleUser(br);
                      //  break;
                    //case 3:
                      //  return;
                    default:
                        printInvalidChoice();
                        break;
                }
            }catch (Exception e ){
                printInvalidInput();
            }
        }
    }



    //Input print message functions are written seperated so the code will be clean
    public static void printDashLine() {
        System.out.println("---------------------------------------------------------------------------------------------");
    }

    public static void printWelcomeMsg() {
        System.out.println("\n*************************| Welcome To The Stubborn RetailStore |**************************");
    }

    public static void printAvailableBooks() {
        System.out.println("\n*************************| Available Products |**************************");
    }

    public static void printPurchasedBooks() {
        System.out.println("\n*************************| Purchased Products |**************************");
    }

    public static void printLine() {
        System.out.println("=======================================================================================");
    }

    public static void printSignIn() {
        System.out.println("\n*************************| Sign In |**************************");
    }

    public static void printRegister() {
        System.out.println("\n*************************| Register |**************************");
    }

    public static void printForgotPassword() {
        System.out.println("\n*********| Forgot Password |**********");
    }

    public static void printAdminWelcomeMsg() {
        System.out.println("\n*********| Welcome To The Admin panel of The Stubborn Retail Store |**********");
    }

    public static void printInvalidInput() {
        System.out.println("Invalid Input...!!!\n");
    }

    public static void printInvalidChoice() {
        System.out.println("Invalid Choice...!!!\n");
    }

    //designing functions
    public static void displayInitialOptions() {
        System.out.println("1. Sign In");
        System.out.println("2. Register");
        System.out.println("3. Exit");
    }

    public static void displaySignInOptions() {
        System.out.println("1. Enter credentials");
        System.out.println("2. Forgot Password");
        System.out.println("3. Exit");
    }

    public static void displayProfileOptions() {
        System.out.println("1.Show Profile");
        System.out.println("2.Purchase");
    }

    public static void backToCatalogOpt() {
        System.out.println("1.Back to Catalog");
        System.out.println("2.Logout");
    }

    public static void displayTypeOfUserOptions() {
        System.out.println("1. Admin");
        System.out.println("2. User");
        System.out.println("3. Exit");
    }

    public static void displayAdminOptions() {
        System.out.println("1. Add Product");
        System.out.println("2. Display Products");
        System.out.println("3. Get total No of products");
        System.out.println("4. Calculate Maximum Profit");
        System.out.println("5. Exit");
    }
}
