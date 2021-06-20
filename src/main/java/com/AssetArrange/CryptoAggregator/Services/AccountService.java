package com.AssetArrange.CryptoAggregator.Services;

import com.AssetArrange.CryptoAggregator.Constants.Endpoints;
import com.AssetArrange.CryptoAggregator.Core.dto.Account;
import com.AssetArrange.CryptoAggregator.Core.dto.AccountHistory;
import com.AssetArrange.CryptoAggregator.Core.dto.Hold;
import com.AssetArrange.CryptoAggregator.Proxy.ICoinbaseProxy;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class AccountService {

    private final ICoinbaseProxy exchange;

    public AccountService(final ICoinbaseProxy exchange) {
        this.exchange = exchange;
    }

    public Account getAccount(String accountId) {
        final String uri = Endpoints.ACCOUNTS_FS + accountId;
        return exchange.get(uri, new ParameterizedTypeReference<>() {});
    }

    public List<Account> getAccounts() {
        return exchange.getAsList(Endpoints.ACCOUNTS, new ParameterizedTypeReference<>() {});
    }

    public List<AccountHistory> getAccountHistory(String accountId) {
        final String uri = Endpoints.ACCOUNTS_FS + accountId + Endpoints.LEDGER;
        return exchange.getAsList(uri, new ParameterizedTypeReference<>() {});
    }

    public List<AccountHistory> getPageAccountHistory(String accountId,
                                                      String beforeOrAfter,
                                                      Integer pageNumber,
                                                      Integer limit) {
        final String uri = Endpoints.ACCOUNTS_FS + accountId + Endpoints.LEDGER;
        return exchange.pagedGetAsList(uri, new ParameterizedTypeReference<>() {},
                beforeOrAfter, pageNumber, limit);
    }

    public List<Hold> getHolds(String accountId) {
        final String uri = Endpoints.ACCOUNTS_FS + accountId + Endpoints.HOLDS;
        return exchange.getAsList(uri, new ParameterizedTypeReference<>() {});
    }

    public List<Hold> getPagedHolds(String accountId,
                                    String beforeOrAfter,
                                    Integer pageNumber,
                                    Integer limit) {
        final String uri = Endpoints.ACCOUNTS_FS + accountId + Endpoints.HOLDS;
        return exchange.pagedGetAsList(uri, new ParameterizedTypeReference<>() {},
                beforeOrAfter, pageNumber, limit);
    }
}
