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

    protected void addProduct(){

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
}
