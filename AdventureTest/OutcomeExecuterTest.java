package AdventureTest;

import AdventureModel.*;
import AdventureModel.Endings.EndingExecuter;
import AdventureModel.Minigames.Battle.Battle;
import AdventureModel.Minigames.Minigame;
import AdventureModel.Outcomes.OutcomeExecuter;
import AdventureModel.Outcomes.Progression;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class OutcomeExecuterTest {
    @Test
    void ProgressionTest() {
        Progression progression = new Progression();
        HashMap<String, Minigame> minigames = new HashMap<>();

        Battle b1 = new Battle("M002", new String[]{"1150","10","50"} , new String[]{"55","85"});
        b1.setPlayerHP(Double.valueOf(98/99.0));
        minigames.put("M002", b1);


        EndingExecuter endingExecuter = new EndingExecuter();
        OutcomeExecuter outcomeExecuter = new OutcomeExecuter(progression, minigames, endingExecuter);
        System.out.println(outcomeExecuter.execute().getID());
    }
}
