import biuoop.DrawSurface;
import biuoop.KeyboardSensor;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * Class description: Class "GameLevel" will hold all our Sprites and our Collidable objects and will be charged
 * on all the animations.
 *
 * @author Michael Alayev
 * ID: 207117045
 */

public class GameLevel implements Animation {
    // constant variables
    static final int BORDER_WIDTH = 800;
    static final int BORDER_HEIGHT = 600;
    static final int SIDE_BORDER_SIZE = 25;            // size of the left/right borders
    static final int TOP_BORDER_SIZE = 40;             // size of the top border
    static final int SCORE_RECTANGLE_SIZE = 20;        // size of the score rectangle
    static final int NUM_OF_SECONDS = 2;               // the countdown seconds before the games starts
    static final int COUNT_FROM = 3;                   // where the countdown starts from
    static final int FRAMES_PER_SECOND = 60;
    static final int NUM_OF_LIVES = 7;
    // the score the player receives each time he clears a level
    static final int SCORE_PER_LEVEL = 100;
    static final Color PADDLE_COLOR = new Color(255, 4, 111);
    static final Color BALLS_OUTER_COLOR = Color.BLACK;
    static final Color BALLS_INNER_COLOR = Color.WHITE;
    static final int BALL_SIZE = 6;
    static final Point BALL_STARTING_POINT = new Point(BORDER_WIDTH / 2,
            BORDER_HEIGHT - Paddle.HEIGHT - SIDE_BORDER_SIZE - 10);

    // Fields
    private GameEnvironment environment;
    private SpriteCollection sprites;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private Counter lives;
    private AnimationRunner animationRunner;
    private boolean running;
    private biuoop.KeyboardSensor keyboardSensor;
    private LevelInformation levelInfo;
    private Paddle paddle;

    /**
     * A constructor which receives collections of sprites and collidable objects.
     *
     * @param levelInfo holds the information about the current level.
     * @param ks        holds the keyboardSensor which will be used to move the paddle.
     * @param ar        is responsible to run the animations of the game.
     * @param score     holds the current score of the game.
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor ks, AnimationRunner ar, Counter score) {
        this.levelInfo = levelInfo;
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.score = score;
    }

    /**
     * method "removeCollidable" removes a certain collidable object from the list.
     *
     * @param c is the collidable we want to remove from the collidable list.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * method "removeSprite" removes a certain Sprite object from the list.
     *
     * @param s is the sprite we want to remove from the list.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * "createGameLimits" a method which inserts into the current list the screen borders as rectangles.
     */
    public void addGameLimits() {
        java.awt.Color gray = Color.GRAY;
        List<Rectangle> list = new ArrayList<>();
        //top limit
        list.add(new Rectangle(new Point(0, 0), BORDER_WIDTH, TOP_BORDER_SIZE));
        //left limit
        list.add(new Rectangle(new Point(0, 0), SIDE_BORDER_SIZE, BORDER_HEIGHT));
        //right limit
        list.add(new Rectangle(new Point(BORDER_WIDTH - SIDE_BORDER_SIZE, 0), SIDE_BORDER_SIZE, BORDER_HEIGHT));
        for (Rectangle rec : list) {
            Block block = new Block(rec, gray, gray);
            block.addToGame(this);
        }
        //bottom limit
        Block deathZone = new Block(new Rectangle(new Point(0, BORDER_HEIGHT), BORDER_WIDTH, 0), gray, gray);
        deathZone.setListener(new ArrayList<HitListener>());
        deathZone.addHitListener(new BallRemover(this, this.ballCounter));
        deathZone.addToGame(this);
    }

    /**
     * method "addScoreRectangle" initializes the rectangle in which the game score will be in.
     * @param levelName represents the name of the current level.
     */
    public void addScoreRectangle(String levelName) {
        Rectangle rectangle = new Rectangle(new Point(0, 0), BORDER_WIDTH, SCORE_RECTANGLE_SIZE);
        ScoreIndicator indicator = new ScoreIndicator(rectangle, this.score, this.lives, levelName);
        this.sprites.addSprite(indicator);
    }

    /**
     * @param c will be a new Collidable which will be added to our collection.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * @param paddle1 the paddle object will have a special place in the collection.
     */
    public void addCollidableVIP(Collidable paddle1) {
        this.environment.addCollidable(paddle1);
    }

    /**
     * @param s will be a new sprite which will be added to our collection.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * @param paddle1 the paddle object will have a special place in the collection.
     */
    public void addSpriteVIP(Sprite paddle1) {
        this.sprites.addSprite(paddle1);
    }

    /**
     * @return the method returns the frames per second of the current level animation.
     */
    public int framesPerSecond() {
        return GameLevel.FRAMES_PER_SECOND;
    }

    /**
     * method "ballsImplement" will implement the balls according level information and add them into the game.
     */
    public void ballsImplement() {
        // first, we will initialize the balls and update their velocities
        List<Ball> balls = new ArrayList<>();
        List<Velocity> velocities = this.levelInfo.initialBallVelocities();
        for (int i = 0; i < this.levelInfo.numberOfBalls(); i++) {
            balls.add(new Ball(BALL_STARTING_POINT, BALL_SIZE,
                    BALLS_OUTER_COLOR, BALLS_INNER_COLOR, velocities.get(i)));
        }
        // the next loop will insert the balls into the game
        for (Ball ball : balls) {
            this.ballCounter.increase(1);
            ball.setPaddle(paddle);
            ball.setGame(this.environment);
            ball.addToGame(this);
        }
    }

    /**
     * "initialize" is responsible to initialize all the objects needed to make the game function properly.
     */
    public void initialize() {
        // initializing our collections
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        // initializing the background of the game level
        this.addSprite(this.levelInfo.getBackground());
        // now we will initialize the sprite counters
        this.ballCounter = new Counter();
        this.blockCounter = new Counter();
        this.lives = new Counter(NUM_OF_LIVES);
        // initializing the game limits
        this.addGameLimits();
        this.addScoreRectangle(this.levelInfo.levelName());
        // now we will initialize the game's paddle
        Point upperLeft = new Point(((BORDER_WIDTH - this.levelInfo.paddleWidth()) / 2),
                BORDER_HEIGHT - Paddle.HEIGHT - SIDE_BORDER_SIZE);
        Rectangle rectangle = new Rectangle(upperLeft, this.levelInfo.paddleWidth(), Paddle.HEIGHT);
        Paddle paddle1 = new Paddle(keyboardSensor, rectangle, PADDLE_COLOR,
                this.levelInfo.paddleSpeed(), this.levelInfo.paddleWidth());
        paddle1.vipAdding(this);
        this.paddle = paddle1;
        // a specific method to initialize the balls of the game
        this.ballsImplement();
        // the next for loop will create and insert the blocks of the level into the game
        for (Block block : this.levelInfo.blocks()) {
            this.blockCounter.increase(1);
            block.setListener(new ArrayList<>());
            block.addHitListener(new BlockRemover(this, this.blockCounter));
            block.addHitListener(new ScoreTrackingListener(this.score));
            block.addToGame(this);
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // the next loop will run the animation
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, BORDER_WIDTH, BORDER_HEIGHT);
        // we will draw in our Surface all the sprites gathered in the collection
        SpriteCollection spritesCopy = new SpriteCollection();
        spritesCopy.setList(new ArrayList<>(this.sprites.getList()));
        spritesCopy.drawAllOn(d);
        spritesCopy.notifyAllTimePassed();
        // the game animation will be stopped if all the balls/lives are lost or the player killed all the blocks
        if (this.blockCounter.getValue() == 0 || this.ballCounter.getValue() == 0) {
            this.running = false;
            // level has been completed, we will move into the next level (if there are any more levels)
            if (this.blockCounter.getValue() == 0) {
                this.score.increase(SCORE_PER_LEVEL);
                return;
            }
            // if all the balls are lost and the player has lives available, we will re-initialize the balls
            if (this.ballCounter.getValue() == 0 && this.lives.getValue() != 1) {
                this.lives.decrease(1);
                this.ballsImplement();
                this.run();
            }
            // if all the balls are lost and the player doesn't have lives available, game is over
            if (this.ballCounter.getValue() == 0 && this.lives.getValue() == 1) {
                this.lives.decrease(1);
                return;
            }
        }
        // if the player presses "p" in the middle of the game, we will pause the game
        if (this.keyboardSensor.isPressed("p")) {
            String key = "paused -- press space to continue";
            Animation pauseScreen = new PauseScreen(this.keyboardSensor, key);
            KeyPressStoppableAnimation boost = new KeyPressStoppableAnimation(this.keyboardSensor, pauseScreen);
            this.animationRunner.run(boost);
        }
    }

    @Override
    public boolean shouldStop() {
        return !(this.running);
    }

    /**
     * Method "run" will be responsible to run everything we have just initialized in method "initialize", into
     * a moving animation.
     */
    public void run() {
        // countdown before the round starts
        this.animationRunner.run(new CountdownAnimation(NUM_OF_SECONDS, COUNT_FROM, this.sprites));
        this.running = true;
        this.animationRunner.run(this);
    }

    /**
     * @return a getter that returns the current number of blocks in the game.
     */
    public Counter getBlockCounter() {
        return this.blockCounter;
    }
    /**
     * @return a getter that returns the current number of balls in the game.
     */
    public Counter getBallsCounter() {
        return this.lives;
    }
    /**
     * @return a getter that returns the number of lives left for the player.
     */
    public Counter getLivesCounter() {
        return this.lives;
    }
}