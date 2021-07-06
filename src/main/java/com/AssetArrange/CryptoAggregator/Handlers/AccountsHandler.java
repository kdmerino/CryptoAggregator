package com.AssetArrange.CryptoAggregator.Handlers;

import com.AssetArrange.CryptoAggregator.Core.context.Context;
import com.AssetArrange.CryptoAggregator.Core.context.IContext;
import com.AssetArrange.CryptoAggregator.Core.dto.Account;
import com.AssetArrange.CryptoAggregator.Core.exceptions.AggregatorException;
import com.AssetArrange.CryptoAggregator.Model.Asset;
import com.AssetArrange.CryptoAggregator.Services.AccountService;

import java.util.stream.Collectors;

public class AccountsHandler implements IHandler {

    public boolean execute(IContext iContext) {
        Context context = (Context) iContext;
        AccountService accountService = new AccountService(context.getProxy());
        try {
            context.setAssetMap(accountService.getAccounts().stream()
                .collect(Collectors.toMap(Account::getCurrency,
                        asset -> new Asset.Builder()
                                .fromAccount(asset)
                                .build())));
        } catch (Exception e) {
            throw new AggregatorException("AccountHandler failed with error = " + e.getMessage());
        }

        return !context.getAssetMap().isEmpty();
    }
}
