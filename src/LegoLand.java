import com.sun.source.doctree.SeeTree;

import java.util.*;
import java.util.stream.Collectors;

public class LegoLand {

    public void getTotalProfit(InputTestCase inputTestCase) {

        var noOfSawMills = inputTestCase.getNoOfSawMills();
        var sawMills = inputTestCase.getSawMills();
        int totalProfit = 0;
        List<List<List<Integer>>> order = new ArrayList<>();
        for (int i = 0; i < noOfSawMills; i++) {
            var profitWithOrder = getMaximumProfitFromSawMill(sawMills.get(i).getTrunks(), 0);
            totalProfit += profitWithOrder.getProfit();
            var list = profitWithOrder.getReversedOrder();
            list.forEach(Collections::reverse);
            order.add(list.stream().distinct().collect(Collectors.toList()));
        }
        System.out.println("Max profit: " + totalProfit);
        System.out.println("Order: " + order);
    }

    private ProfitWithOrder getMaximumProfitFromSawMill(List<Integer> trunks, int filled) {
        ProfitWithOrder profitWithOrder = new ProfitWithOrder();
        if (trunks.size() == 0) {
            return profitWithOrder;
        }
        profitWithOrder.setProfit(Integer.MIN_VALUE);

        for (int i = 0; i < trunks.size(); i++) {
            List<Integer> remainingTrunks = new ArrayList<>(trunks);
            Integer currentTrunk = remainingTrunks.remove(i);
            int profitOfCurrentTrunk;
            if (filled + currentTrunk <= 3) {
                profitOfCurrentTrunk = getProfitOfWood(currentTrunk);
            } else {
                int noOfThreeSizedWoods = (currentTrunk + filled) / 3 - 1;
                int sizeOfFirstWood = 3 - filled;
                int sizeOfLastWood = (currentTrunk + filled) % 3;
                profitOfCurrentTrunk = getProfitOfWood(sizeOfFirstWood) + getProfitOfWood(3) * noOfThreeSizedWoods
                        + getProfitOfWood(sizeOfLastWood);
            }
            var tempProfitWithOrder = getMaximumProfitFromSawMill(remainingTrunks, (filled + currentTrunk) % 3);
            var profitOfChoosingI = profitOfCurrentTrunk + tempProfitWithOrder.getProfit();
            if (profitWithOrder.getProfit() < profitOfChoosingI) {
                tempProfitWithOrder.setProfit(profitOfChoosingI);
                tempProfitWithOrder.append(currentTrunk);
                profitWithOrder = tempProfitWithOrder;
            } else if (profitWithOrder.getProfit() == profitOfChoosingI) {
                tempProfitWithOrder.append(currentTrunk);
                var list = profitWithOrder.getReversedOrder();
                list.addAll(tempProfitWithOrder.getReversedOrder());
                profitWithOrder.setReversedOrder(list);
            }

        }

        return profitWithOrder;
    }

    private int getProfitOfWood(Integer wood) {
        switch (wood) {
            case 1:
                return -1;
            case 2:
                return 3;
            case 3:
                return 1;
            case 0:
                return 0;
            default:
                throw new RuntimeException("invalid size for wood " + wood);
        }
    }
}
