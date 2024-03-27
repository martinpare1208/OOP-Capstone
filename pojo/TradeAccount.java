package pojo;


//Abstract class for CashAccount and MarginAccount
public abstract class TradeAccount {

    private String id;

    //Create a constructor
    public TradeAccount(String id) {
        setId(id);
    }



    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }


    //Create deep copies for us
    public abstract TradeAccount clone();


}