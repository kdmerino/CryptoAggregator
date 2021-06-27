package com.AssetArrange.CryptoAggregator.Handlers;

import com.AssetArrange.CryptoAggregator.Core.context.Context;
import com.AssetArrange.CryptoAggregator.Core.context.IContext;
import com.AssetArrange.CryptoAggregator.Core.exceptions.AggregatorException;
import com.AssetArrange.CryptoAggregator.Services.AccountService;

public class AccountsHandler implements IHandler {

    public boolean execute(IContext iContext) {
        Context context = (Context) iContext;
        AccountService accountService = new AccountService(context.getProxy());
        try {
            context.setAccountList(accountService.getAccounts());
        } catch (Exception e) {
            throw new AggregatorException("AccountHandler failed because AccountService failed to get Accounts");
        }

        return !context.getAccountList().isEmpty();
    }
}
