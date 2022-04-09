import java.util.ArrayList;
import java.util.List;

public class ProfitWithOrder {
    private int profit;
    private List<List<Integer>> reversedOrder;

    public ProfitWithOrder() {
        reversedOrder = new ArrayList<>(new ArrayList<>());
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public List<List<Integer>> getReversedOrder() {
        return reversedOrder;
    }

    public void setReversedOrder(List<List<Integer>> reversedOrder) {
        this.reversedOrder = reversedOrder;
    }

    public void append(Integer i) {
        if (reversedOrder.size() == 0){
            reversedOrder.add(new ArrayList<>(List.of(i)));
            return;
        }
        reversedOrder.forEach(l -> l.add(i));
    }
}
