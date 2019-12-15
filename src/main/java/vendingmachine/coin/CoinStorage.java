package vendingmachine.coin;

import lombok.Data;
import vendingmachine.coin.Coin;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
@Data
public class CoinStorage {
    private static CoinStorage instance= null;
    private static Queue<Coin> quarterStash = new ArrayDeque<>();
    private static Queue<Coin> dimeStash= new ArrayDeque<>();
    private static Queue<Coin> nickelStash = new ArrayDeque<>();
    private static Queue<Coin> pennyStash = new ArrayDeque<>();
    public void addQuarter(int nr){
        for (int i = 0; i < nr; i++){
            this.quarterStash.add(Coin.QUARTER_25);
     }
    }
    public void addDime(int nr) {
        for (int i = 0; i < nr; i++) {
            this.dimeStash.add(Coin.DIME_10);
        }
    }
    public void addNickel(int nr) {
        for (int i = 0; i < nr; i++) {
            this.nickelStash.add(Coin.NICKEL_5);
        }
    }
    public void addPenny(int nr) {
        for (int i = 0; i < nr; i++) {
            this.pennyStash.add(Coin.PENNY_1);
        }
    }

    public static Queue<Coin> getQuarterStash() {
        return quarterStash;
    }

    public static Queue<Coin> getDimeStash() {
        return dimeStash;
    }

    public static Queue<Coin> getNickelStash() {
        return nickelStash;
    }

    public static Queue<Coin> getPennyStash() {
        return pennyStash;
    }

    public  int getCoinTotal(){
    return quarterStash.size() * 25
            + dimeStash.size() * 10 + nickelStash.size()*5
            + pennyStash.size();
}

 }
