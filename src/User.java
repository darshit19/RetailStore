import java.util.Date;
import java.util.HashMap;

public class User extends Person{
    private boolean isMember;
    //storing information about the product user has purchased along with purchased date
    private HashMap<String, Date> purchasedPrd;

    public User(){
        purchasedPrd = new HashMap<String,Date>();
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
}
