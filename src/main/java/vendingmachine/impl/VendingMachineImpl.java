package vendingmachine.impl;

import vendingmachine.coin.Coin;
import vendingmachine.coin.CoinStorage;
import vendingmachine.exceptions.NoSufficientChangeException;
import vendingmachine.exceptions.NotFullPaidException;
import vendingmachine.exceptions.SoldOutException;
import vendingmachine.item.Item;
import vendingmachine.item.VendingStorage;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class VendingMachineImpl implements IVendingMachine {
    private HashMap<Integer, Queue<Item>> allItems;
    private VendingStorage storage;
    private CoinStorage coinStorage;

    public void setStorage(VendingStorage storage) {
        this.storage = storage;
    }

    public void setCoinStorage(CoinStorage coinStorage) {
        this.coinStorage = coinStorage;
    }

    public VendingMachineImpl() {
        super();
    }

    @Override
    public void selectItem(int shelfCode, Queue<Coin> amountPayed) {
        Map<Integer, Queue<Item>> storageMap = storage.getStorageMap();
        Queue<Item> items = storageMap.get(shelfCode);
        if (items.isEmpty()) {
            throw new SoldOutException("Sold out!");
        } else {
            Item selectedItem = items.peek();
            int change = payItem(selectedItem.getPrice(), amountPayed);
            getChange(change);
            //update coin storage with the amount payed
            updateBankWithAmountPayed(amountPayed);
            //update storage with product
            items.remove();
        }
    }

    @Override
    public Item returnItem() {
        return null;
    }

    @Override
    public int payItem(int productPrice, Queue<Coin> amountIntroduced) {
        int amountIntroducedAsInt = getAmountIntroducedAsInt(amountIntroduced);
        if (amountIntroducedAsInt >= productPrice) {
            System.out.println("Payment successful");
            return amountIntroducedAsInt - productPrice;
        } else {
            throw new NotFullPaidException("Item not fully paid!");
        }
    }

    private int getAmountIntroducedAsInt(Queue<Coin> amountIntroduced) {
        int sum = 0;
        for (Coin c : amountIntroduced) {
            switch (c) {
                case QUARTER_25:
                    sum += 25;
                    break;
                case DIME_10:
                    sum += 10;
                    break;
                case NICKEL_5:
                    sum += 5;
                    break;
                case PENNY_1:
                    sum += 1;
                    break;
            }
        }
        return sum;
    }

    @Override
    public void getItem() {

    }

    @Override
    public Queue<Coin> getChange(int change) {
        int nrOfCoinsNeeded = change / 25;
        int remainingChange = nrOfCoinsNeeded > 0 ? change % 25 : change;
        Queue<Coin> changeReturn = new ArrayDeque<>();

        if (coinStorage.getCoinTotal() < change) {
            throw new NoSufficientChangeException();
        }
        Queue<Coin> quaters = coinStorage.getQuarterStash();
        nrOfCoinsNeeded = getNrOfCoinsNeeded(nrOfCoinsNeeded, quaters);
        Queue<Coin> changeInQuarters = addCoinsToChangeStash(nrOfCoinsNeeded, Coin.QUARTER_25);
        changeReturn.addAll(changeInQuarters);
        quaters.removeAll(changeInQuarters);

        nrOfCoinsNeeded = remainingChange / 10;
        remainingChange = nrOfCoinsNeeded > 0 ? remainingChange % 10 : remainingChange;
        Queue<Coin> dimes = coinStorage.getDimeStash();
        nrOfCoinsNeeded = getNrOfCoinsNeeded(nrOfCoinsNeeded, dimes);
        dimes = removeCoinsFromStash(nrOfCoinsNeeded, dimes);
        Queue<Coin> changeInDimes = addCoinsToChangeStash(nrOfCoinsNeeded, Coin.DIME_10);
        changeReturn.addAll(changeInDimes);
        dimes.removeAll(changeInDimes);

        nrOfCoinsNeeded = remainingChange / 5;
        remainingChange = nrOfCoinsNeeded > 0 ? remainingChange % 5 : remainingChange;
        Queue<Coin> nickels = coinStorage.getNickelStash();
        nrOfCoinsNeeded = getNrOfCoinsNeeded(nrOfCoinsNeeded, nickels);
        nickels = removeCoinsFromStash(nrOfCoinsNeeded, nickels);
        Queue<Coin> changeInNickels = addCoinsToChangeStash(nrOfCoinsNeeded, Coin.NICKEL_5);
        changeReturn.addAll(changeInNickels);
        nickels.removeAll(changeInNickels);

        nrOfCoinsNeeded = remainingChange / 1;
        remainingChange = nrOfCoinsNeeded > 0 ? remainingChange % 1 : remainingChange;
        Queue<Coin> pennies = coinStorage.getPennyStash();
        nrOfCoinsNeeded = getNrOfCoinsNeeded(nrOfCoinsNeeded, pennies);
        pennies = removeCoinsFromStash(nrOfCoinsNeeded, pennies);
        Queue<Coin> changeInPennies = addCoinsToChangeStash(nrOfCoinsNeeded, Coin.PENNY_1);
        changeReturn.addAll(changeInPennies);
        pennies.removeAll(changeInPennies);

        return changeReturn;

    }

    private void updateBankWithAmountPayed(Queue<Coin> amountPayed) {
        for (Coin c : amountPayed) {
            switch (c) {
                case QUARTER_25:
                    coinStorage.addQuarter(25);
                    break;
                case DIME_10:
                    coinStorage.addDime(10);
                    break;
                case NICKEL_5:
                    coinStorage.addNickel(5);
                    break;
                case PENNY_1:
                    coinStorage.addPenny(1);
                    break;
            }
        }
    }

    private int getNrOfCoinsNeeded(int nrOfCoinsNeeded, Queue<Coin> stash) {
        return Math.min(nrOfCoinsNeeded, stash.size());
    }

    //v1
    private Queue<Coin> removeCoinsFromStash(int nrOfCoins, Queue<Coin> stash) {
        for (int i = 0; i < nrOfCoins; i++) {
            stash.poll();
        }
        return stash;
    }

    //v2
    public Queue<Coin> addCoinsToChangeStash(int nrOfCoins, Coin coin) {
        Queue<Coin> stash = new ArrayDeque<>();
        for (int i = 0; i < nrOfCoins; i++) {
            stash.add(coin);
        }
        return stash;
    }

}
