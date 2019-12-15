package vendingmachine.impl;

import vendingmachine.coin.Coin;
import vendingmachine.item.Item;

import java.util.Queue;

public interface IVendingMachine {
    void selectItem(int shelfCode, Queue<Coin> amountPayed);
    Item returnItem();
    int payItem(int productPrice, Queue<Coin> amountIntroduced);
    void getItem();
    Queue<Coin> getChange(int change);
}
