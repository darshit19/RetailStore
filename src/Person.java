public abstract class Person {
    public String userName;
    public String passWord;
    public String fullName;

    public abstract boolean login(String userName,String passWord);
    public abstract void changePassword();
}
