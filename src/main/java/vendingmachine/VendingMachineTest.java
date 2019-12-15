package vendingmachine;

import vendingmachine.coin.Coin;
import vendingmachine.coin.CoinStorage;
import vendingmachine.impl.VendingMachineImpl;
import vendingmachine.item.Item;
import vendingmachine.item.VendingStorage;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

public class VendingMachineTest {
    public static void main(String[] args) {
        VendingStorage storage = new VendingStorage();
        HashMap<Integer,Queue<Item>> storageMap = new HashMap<>();
        Queue<Item> cokeQueue = new ArrayDeque<>();
        Item coke = new Item("Coca-Cola", 20);
        cokeQueue.add(coke);
        cokeQueue.add(coke);
        cokeQueue.add(coke);
        cokeQueue.add(coke);

        Item chocolate = new Item("Chocolate",50);
        Queue<Item>chocolateQueue = new ArrayDeque<>();
        chocolateQueue.add(chocolate);
        chocolateQueue.add(chocolate);
        chocolateQueue.add(chocolate);

        Item water = new Item("Water", 30);
        Queue<Item>waterQueue = new ArrayDeque<>();
        waterQueue.add(water);

        storageMap.put(10,cokeQueue);
        storageMap.put(11,chocolateQueue);
        storageMap.put(12,waterQueue);
        storageMap.put(13,null);

        VendingMachineImpl vendingMachine = new VendingMachineImpl();
        storage.setStorageMap(storageMap);

        CoinStorage coinStorage = new CoinStorage();
        vendingMachine.setCoinStorage(coinStorage);

        vendingMachine.setStorage(storage);
     //   vendingMachine.selectItem(11, );

        Queue<Coin> payment = new ArrayDeque<>();
        payment.add(Coin.DIME_10);
        payment.add(Coin.NICKEL_5);
        payment.add(Coin.QUARTER_25);
        vendingMachine.payItem(25,payment);

        coinStorage.addQuarter(5);
        coinStorage.addDime(4);
        coinStorage.addNickel(6);
        coinStorage.addPenny(4);


        coinStorage.getQuarterStash();
        coinStorage.getDimeStash();
        coinStorage.getNickelStash();
        coinStorage.getPennyStash();

        System.out.println(vendingMachine.getChange(47));



    }
}
