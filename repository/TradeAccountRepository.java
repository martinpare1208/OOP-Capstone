package repository;

import java.util.HashMap;
import java.util.Map;

import pojo.TradeAccount;

public class TradeAccountRepository {

    private Map<String, TradeAccount> datastore = new HashMap<>();


    /** 
     *
     * Repository will have CRUD Functionality
     * Create an account
     * Read their account details
     * Update their account details
     * Delete their account details 
     * 
    **/

    public void createTradeAccount(TradeAccount tradeAccount) {
        this.datastore.put(tradeAccount.clone().getId(), tradeAccount.clone());
    }

    public TradeAccount retrieveTradeAccount(String id) {
       TradeAccount retrieveSuccess = this.datastore.get(id) == null ? null : this.datastore.get(id).clone();
       if (retrieveSuccess == null) {
        throw new NullPointerException();
       }
       return retrieveSuccess;

    }

    public void updateTradeAccount(TradeAccount tradeAccount) {
        this.datastore.put(tradeAccount.clone().getId(), tradeAccount.clone());
    }

    public void deleteTradeAccount(String id) {
        this.datastore.remove(id);
    }




}
