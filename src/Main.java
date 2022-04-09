import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        InputTestCase InputTestCase = createInput();
        LegoLand legoLand = new LegoLand();
        legoLand.getTotalProfit(InputTestCase);
    }


    public static InputTestCase createInput(){
     InputTestCase inputTestCase = new InputTestCase();
     inputTestCase.setNoOfSawMills(1);
     SawMill sawMill = new SawMill();
     sawMill.setNoOfTrunks(3);
     sawMill.setTrunks(new ArrayList<>(Arrays.asList(1, 8)));
     inputTestCase.setSawMills(List.of(sawMill));
     return inputTestCase;
    }
}
