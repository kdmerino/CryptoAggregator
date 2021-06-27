package com.AssetArrange.CryptoAggregator.Handlers;

import com.AssetArrange.CryptoAggregator.Core.context.Context;
import com.AssetArrange.CryptoAggregator.Core.context.IContext;
import com.AssetArrange.CryptoAggregator.Core.dto.Account;
import com.AssetArrange.CryptoAggregator.Services.AccountService;

import java.util.Collection;
import java.util.stream.Collectors;

public class AccountsHistoryHandler implements IHandler {

    public boolean execute(IContext iContext) {
        Context context = (Context) iContext;
        AccountService accountService = new AccountService(context.getProxy());
        // List<Account> -> List<id> -> List<List<AccountHistory>> -> List<AccountHistory>
        context.setAccountHistories(context.getAccountList().stream()
                .map(Account::getId).map(accountService::getAccountHistory)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));

        return true;
    }
}
