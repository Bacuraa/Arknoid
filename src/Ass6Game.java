import java.util.ArrayList;
import java.util.List;

/**
 * Class description: Class "Ass3Game" this class will run the game, and use everything we have done so far.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class Ass6Game {
    /**
     * simple main method the run games.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        List<LevelInformation> levels = new ArrayList<>();
        // the next for loop handles the args received by the user
        for (String arg : args) {
            if (arg.equals("1")) {
                levels.add(new DirectHit());
            }
            if (arg.equals("2")) {
                levels.add(new WideEasy());
            }
            if (arg.equals("3")) {
                levels.add(new Green3());
            }
            if (arg.equals("4")) {
                levels.add(new FinalFour());
            }
        }
        // if there are no game levels in our level's list, we will initialize all the possible levels of the game
        if (levels.size() == 0) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
        }
        // game start
        GameFlow gameFlow = new GameFlow();
        gameFlow.runLevels(levels);
    }
}