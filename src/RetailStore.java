import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RetailStore {
    //no of admins are fixe
    // d so normal fixed size array is ok
    private Admin[] admins;
    private ArrayList<Product> products;
    //users can be added dynamically so array size is not fixed so arraylist
    private ArrayList<User> users;
    private int currAdminIndex;
    private User currentuser ;

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

    private void displayProducts(){
        printDashLine();
        System.out.printf("%-11s %-20s %-11s %-6s %-12s %-12s %-10s %-18s", "PRODUCT ID", "PRODUCTNAME", "ISAVAILABLE", "STOCK", "BASEPRICE","SELLPRICE","DISCOUNT","VALID RETURN DAYS");
        System.out.println();
        printDashLine();
        //iterates over the list
        for(Product prd: products)
        {
            String temp;
            if(prd.getStock()>0){
                temp="In-store";
            }else{
                temp="OutOfStock";
            }
            System.out.format("%-11s %-20s %-11s %-6s %-12s %-12s %-10s %-18s",prd.getId(),prd.getPrdName(),temp,prd.getStock(),prd.getbPrice(),prd.getsPrice(),prd.getDiscount(),prd.getValidRetDays() );
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------------------");
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
    private void displayProductsToUser(BufferedReader br){
        printDashLine();
        System.out.printf("%-11s %-20s %-11s %-6s %-12s %-20s", "PRODUCT ID", "PRODUCTNAME", "ISAVAILABLE","PRICE","DISCOUNT","VALID RETURN DAYS");
        System.out.println();
        printDashLine();
        //iterates over the list
        for(Product prd: products)
        {
            String temp;
            if(prd.getStock()>0){
                temp="In-store";
            }else{
                temp="OutOfStock";
            }
            System.out.format("%-11s %-20s %-11s %-6s %-12s %-20s",prd.getId(),prd.getPrdName(),temp,prd.getsPrice(),prd.getDiscount(),prd.getValidRetDays() );
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------------------");
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
                           displayProducts();
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
                    case 2:
                        handleUser(br);
                        break;
                    case 3:
                        return;
                    default:
                        printInvalidChoice();
                        break;
                }
            }catch (Exception e ){
                printInvalidInput();
            }
        }
    }
    public void profilePart(BufferedReader br){
        currentuser.showProfile();

        printPurchasedProducts();

        printDashLine();
        System.out.printf("%-8s %-20s %-15s", "BOOK ID", "BOOKNAME", "PRICEPOINTS");
        System.out.println();
        printDashLine();
        if(currentuser.getPurchasedProductsSize()==0){
            System.out.println("--------------------------You haven't purchased any books--------------------------------\n");
        }else{
            for(int i=currentuser.getPurchasedProductsSize()-1;i>=0;i--){
                //int id = currentuser.getPurchasedProducts().get(i);
//                Book tBook = books.get(id);
//                System.out.format("%-8s %-20s %-15s", tBook.getBookId(), tBook.getBookName(), tBook.getPricePoints());
                System.out.println();
            }
        }

        printLine();
        backToCatalogOpt();

        int catalogChoice=takeChoice(br);
        switch(catalogChoice){
            case 1:
                return;
            case 2:
                currentuser.setLoggedIn(false);
                System.out.println("\nLogged out Successfully...\n");
                return;
            default:
                printInvalidChoice();
        }
    }
    public void DisplayCatalog(BufferedReader br){
        //Display options
        displayProfileOptions();

        int profileCh=takeChoice(br);

        switch (profileCh){
            case 1:
                //purchase product
                System.out.println("Products Catalog");
                //Displaying books
                displayProductsToUser(br);

                System.out.print("Enter the ProductID which you want ot purchase : ");
                int purchasePid;
                try{
                    purchasePid=Integer.parseInt(br.readLine());
                    if(products.get(purchasePid).getStock()>0){
                        currentuser.purchaseProduct(purchasePid);
                        System.out.println("Product Purchased successfully...\n");
                        products.get(purchasePid).setStock(products.get(purchasePid).getStock()-1);
                        profilePart(br);
                        return;
                    }else{
                        System.out.println("This Product is Out Of stock...\n");
                    }
                    break;
                }catch (Exception e){
                    printInvalidInput();
                }
                return;
            case 2:
                //cancel purchase product

            case 3:
                //display profile
                profilePart(br);
                break;
            case 4:
                //take membership
                break;
            case 5:
                //cancel membership
                break;
            default:
                printInvalidChoice();
                break;
        }
    }
    public void getSignInInformation(BufferedReader br) {
        printSignIn();
        int choice;

        while (true) {
            //displaying sign in options
            displaySignInOptions();
            try
            {
                choice = takeChoice(br);
                switch (choice) {
                    case 1:
                        String uName, uPass;
                        System.out.print("Enter Username : ");
                        uName = br.readLine();
                        System.out.print("Enter Password : ");
                        uPass = readPassword();
                        if (users.size()==0) {
                            System.out.println("Please Register first...!!!\n");
                            return;
                        }

                        //validate credentials
                        for(User tempUser:users){
                            if (uName.equals(tempUser.getUserName()) && uPass.equals(tempUser.getPassWord())) {
                                //credentials are valid
                                System.out.println("Signed in Successfully...\n");

                                // then set the user to current user as he is the active user
                                currentuser=tempUser;
                                currentuser.setLoggedIn(true);

                                while(true) {
                                    printAvailableProducts();
                                    displayProductsToUser(br);
                                    //Display Catalog
                                    DisplayCatalog(br);

                                    if (!currentuser.getIsLoggedIn()) {
                                        currentuser=null;
                                        break;
                                    }
                                }
                                return;
                            }
                        }
                        System.out.println("Invalid credentials ! Try Again...!!!\n");
                        break;

                    case 2:
                        printForgotPassword();
                        String uname;
                        String pass;
                        System.out.print("Enter your username :");
                        uname = br.readLine();

                        for(User tempUser:users){
                            if (tempUser.getUserName().equals(uname)) {
                                System.out.print("Enter new password : ");
                                pass = readPassword();
                                tempUser.setPassword(pass);
                                System.out.println("Password changed Successfully...\n");
                                return;
                            }
                        }
                        System.out.println("User with given username doesn't exists...!!!\n");
                        break;
                    case 3:
                        return;
                    default:
                        printInvalidChoice();

                }
            }catch(Exception e){
                printInvalidInput();
            }
        }
    }

    public void registerUser(BufferedReader br) throws IOException {
        String fullName,userName,userPass;

        while(true){

            System.out.print("Enter FullName : ");
            fullName = br.readLine();
            System.out.print("Enter Username : ");
            userName = br.readLine();
            System.out.print("Enter Password : ");
            userPass = readPassword();

            boolean flag=true;
            //set all values to user object
            for(User tempmUser:users){
                if(tempmUser.getUserName().equals(userName)){
                    System.out.println("------------------------------------------Sorry , This username is already taken by someone.Choose another one.------------------------------------------\n");
                    flag=false;
                    break;
                }
            }
            if(flag){
                User newUser=new User(fullName,userName,userPass);
                users.add(newUser);
                System.out.println("Registered in Successfully...\n");
                break;
            }
        }
    }
    private void handleUser(BufferedReader br) {
        int choice;
        while(true){
            displayInitialOptions();
            System.out.print("Enter your choice : ");
            try{
                choice = Integer.parseInt(br.readLine());
                switch (choice){
                    case 1:
                        //for sign in option
                        getSignInInformation(br);
                        break;
                    case 2:
                        //for register option
                        //1.1 will print register details
                        printRegister();
                        registerUser(br);
                        break;
                    case 3:
                        return;
                    default:
                        printInvalidChoice();
                }
            }catch (Exception e){
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

    public static void printAvailableProducts() {
        System.out.println("\n*************************| Available Products |**************************");
    }

    public static void printPurchasedProducts() {
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
        System.out.println("1.Purchase Product");
        System.out.println("2.Cancel Purchased Product");
        System.out.println("3.Display Profile");
        System.out.println("4.Take membership to get extra discounts");
        System.out.println("5.Cancel membership if you already have");
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
