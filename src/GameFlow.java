import biuoop.KeyboardSensor;

import java.util.List;
/**
 * Class description: Class "GameFlow" is responsible to connect all the levels together into a one single game,
 * keeping the flow of the game without stopping it. Moreover it is responsible to connect all the different animations
 * to a single game.
 *
 * @author Michael Alayev
 * ID: 207117045
 */
public class GameFlow {
    // Fields
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private Counter score;

    /**
     * the "GameFlow" constructor which initializes it's fields without receiving arguments.
     */
    public GameFlow() {
        this.animationRunner = new AnimationRunner();
        this.keyboardSensor = this.animationRunner.getGui().getKeyboardSensor();
        this.score = new Counter();
    }

    /**
     * method "runLevels" will connect all the levels into a one game.
     * @param levels represents all the levels which will be run on a single game.
     */
    public void runLevels(List<LevelInformation> levels) {
        // the next for loop will start a new level once the previous one has ended
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score);
            level.initialize();
            while (level.getBlockCounter().getValue() != 0 && level.getBallsCounter().getValue() != 0) {
                level.run();
            }
            // if the player lost all it's lives, game is over, we will close the game and show the player's score
            if (level.getLivesCounter().getValue() == 0) {
                String loserKey = "Game Over. Your score is " + this.score.getValue();
                Animation pauseScreen = new PauseScreen(this.keyboardSensor, loserKey);
                KeyPressStoppableAnimation boost = new KeyPressStoppableAnimation(this.keyboardSensor, pauseScreen);
                this.animationRunner.run(boost);
                this.animationRunner.getGui().close();
                return;
            }
        }
        // if the method reached this stage, the player has won the game, we will show the score and exit the game
        String winnerKey = "You Win! Your score is " + this.score.getValue();
        Animation pauseScreen = new PauseScreen(this.keyboardSensor, winnerKey);
        KeyPressStoppableAnimation boost = new KeyPressStoppableAnimation(this.keyboardSensor, pauseScreen);
        this.animationRunner.run(boost);
        this.animationRunner.getGui().close();
    }
}