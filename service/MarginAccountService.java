package service;

import java.math.BigDecimal;

import pojo.MarginAccount;
import repository.TradeAccountRepository;

public class MarginAccountService implements TradeAccountService {

        TradeAccountRepository tradeAccountRepository;

        public MarginAccountService(TradeAccountRepository tradeAccountRepository) {
            this.tradeAccountRepository = tradeAccountRepository;
        }


        @Override
        public void deposit(String id, BigDecimal amount) {
            MarginAccount account =  retreiveTradeAccount(id);
            account.setMargin(account.getMargin().add(amount));
            updateTradeAccount(account);
        }

        @Override
        public void withdraw(String id, BigDecimal amount) {
            // TODO Auto-generated method stub
            MarginAccount account =  retreiveTradeAccount(id);
            account.setMargin(account.getMargin().subtract(amount));
            updateTradeAccount(account);
        }

        /*Insert TradeAccountRepository methods into here. */

        public void createTradeAccount(MarginAccount marginAccount) {
            this.tradeAccountRepository.createTradeAccount(marginAccount);
       }

        public MarginAccount retreiveTradeAccount(String id) {
            return (MarginAccount)this.tradeAccountRepository.retrieveTradeAccount(id).clone();
        }


        public void updateTradeAccount(MarginAccount marginAccount) {
            this.tradeAccountRepository.updateTradeAccount(marginAccount);
    }

        public void deleteTradeAccount(String id) {
            this.tradeAccountRepository.deleteTradeAccount(id);
    }

}