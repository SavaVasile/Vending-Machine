package vendingmachine.item;

import lombok.Data;
import vendingmachine.coin.Coin;
import vendingmachine.item.Item;

import java.util.Map;
import java.util.Queue;
@Data
public class VendingStorage {
    private Map<Integer, Queue<Item>> storageMap;

}
