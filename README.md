# CryptoAggregator
**STATUS:** This application has enabled access of Coinbase pro endpoints via the CoinbaseProxy 
which makes use of DTOs to obtain the responses of the desired endpoint.

## How to run:
Create an untracked resource such as src/main/resources/application-main.yml with credential content:
```
coinbasePro:
    publicKey: ""
    secretKey: """
    passphrase: ""
```
and run application with CLI arguments: --spring.profiles.active=main

### Useful Services:
```AccountService```
- ```List<Account> getAccounts()``` : This method retrieves all accounts available under the authorized profile.
- ```List<AccountHistory> getAccountHistory(String accountId)``` : This method retrieves the AccountHistory for a given accountID, this accountID can be obtained from the response object: Account.

```OrderService```
- ```Order getOrder(String orderId)``` : This method retrieves an Order response from a given orderID, this orderID can be obtained from the Detail object which resides in the response object: AccountHistory.
- ```List<Order> getOpenOrders()``` : This method retrieves all orders available under the authorized profile.
- ```List<Hold> createOrder(NewOrderSingle order)``` : This method needs to be tested.

### TODO core:
- [x] Fix GSON dependency to obtain stringified classes for logging (i.e HTML fallback in console)
- [x] Create Beans and Autowire Proxy class
- [x] Move credentials to a safe location and ignore in GIT
- [] Add unit tests
- [] Modifiy application.yml to support changing HTTP port.

### TODO features: 
- [] Investigate HTML/CSS/JS frameworks that best work with springboot.
- [] Create VIEW handlers that will service HTML content.
- [x] Create an application.yml to move Proxy strings except for passPhrase which should be moved to a file NOT to be uploaded.
- [] Create a DataStore that reads CSV files to upload key historical coin data (all that are available).
- [] Create API calls for Coin Market Cap data (free version + store retrieved data). 
- [] Create STICH handlers that will join database data with runtime data.
- [x] Create a transaction executer (in Models) that will accept a registration of handlers to execute a chain of methods.
