package nz.ac.aut.ense701.gameModel.Entity;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import nz.ac.aut.ense701.gameModel.Map.Position;
import nz.ac.aut.ense701.gameModel.Map.Terrain;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import static nz.ac.aut.ense701.gameModel.Entity.Occupant.DEFAULT_OCCUPANT_HEIGHT;
import static nz.ac.aut.ense701.gameModel.Entity.Occupant.DEFAULT_OCCUPANT_WIDTH;
import nz.ac.aut.ense701.gameModel.Tile.Tile;
import nz.ac.aut.ense701.gameModel.Utils.MoveDirection;

import nz.ac.aut.ense701.gameModel.gfx.Assets;
import nz.ac.aut.ense701.main.Game;
import nz.ac.aut.ense701.main.Handler;

/**
 * Player represents the player in the KiwiIsland game.
 *
 * @author AS
 * @version July 2011
 */
public class Player extends Occupant {

    public static final int DEFAULT_PLAYER_WIDTH = 32;
    public static final int DEFAULT_PLAYER_HEIGHT = 32;

    public static final double MOVE_STAMINA = 1.0;

    private final double maxStamina;
    private double stamina;
    private boolean alive;
    private Set<Item> backpack;
    private final double maxBackpackWeight;
    private final double maxBackpackSize;

    private MoveDirection direction;
    private float speed = 5.0f;
    private float xMove, yMove;

    public void setDirection(MoveDirection direction) {
        this.direction = direction;
    }

    /**
     * Constructs a new player object.
     *
     * @param handler
     * @param position the initial position of the player
     * @param name the name of the player
     * @param maxStamina the maximum stamina level of the player
     * @param maxBackpackWeight the most weight that can be in a backpack
     * @param maxBackpackSize the maximum size items that will fit in the
     * backpack
     */
    public Player(Handler handler, Position position, String name, double maxStamina,
            double maxBackpackWeight, double maxBackpackSize) {

        super(handler, position, name, "Player");
        this.maxStamina = maxStamina;
        this.stamina = maxStamina;
        this.maxBackpackWeight = maxBackpackWeight;
        this.maxBackpackSize = maxBackpackSize;
        this.alive = true;
        this.backpack = new HashSet<Item>();

    }
     public Player( Position position, String name, double maxStamina,
            double maxBackpackWeight, double maxBackpackSize) {

        super( position, name, "Player");
        this.maxStamina = maxStamina;
        this.stamina = maxStamina;
        this.maxBackpackWeight = maxBackpackWeight;
        this.maxBackpackSize = maxBackpackSize;
        this.alive = true;
        this.backpack = new HashSet<Item>();

    }
     
     //for test purposes
    public void setPosition(Position pos){
        this.position = pos;
    }

    public void setXY() {
        xMove = position.getRow() * Game.TILE_WIDTH;
        yMove = position.getColumn() * Game.TILE_HEIGTH;
    }

    public void move(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {

            setDirection(MoveDirection.NORTH);

            handler.getGameController().playerMove(MoveDirection.NORTH);

        }
        if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {

            setDirection(MoveDirection.SOUTH);

            handler.getGameController().playerMove(MoveDirection.SOUTH);

        }
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            setDirection(MoveDirection.WEST);
            handler.getGameController().playerMove(MoveDirection.WEST);

        }
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            setDirection(MoveDirection.EAST);
            handler.getGameController().playerMove(MoveDirection.EAST);

        }

        /*
        boolean movedTiles = false;
        float currentX =  (xMove / Game.TILE_WIDTH);
        float currentY =  (yMove / Game.TILE_HEIGTH);

        int cX = Integer.parseInt(Double.toString(currentX).substring(0, 1));
        int cY = Integer.parseInt(Double.toString(currentY).substring(0, 1));
        
        if ((cX != position.getRow()) || (cY != position.getColumn())) {
            System.out.println("Current POSX = " + position.getRow() + "  Current POSY = " + position.getColumn());
            System.out.println("Current X = " +currentX + "  Current Y = " +currentY);
            System.out.println("cx = " + cX + "  cY = " + cY);
            System.out.println("xMove = " + xMove + "  yMove = " + yMove);
            
            movedTiles = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_W) {
            yMove -= speed;
            setDirection(MoveDirection.NORTH);
            if (movedTiles) {
                handler.getGameController().playerMove(MoveDirection.NORTH);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            yMove += speed;
            setDirection(MoveDirection.SOUTH);
            if (movedTiles) {
                handler.getGameController().playerMove(MoveDirection.SOUTH);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            xMove -= speed;
            setDirection(MoveDirection.WEST);
            if (movedTiles) {
                handler.getGameController().playerMove(MoveDirection.WEST);
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            xMove += speed;
            setDirection(MoveDirection.EAST);
            if (movedTiles) {
                handler.getGameController().playerMove(MoveDirection.EAST);
            }
        }
        System.out.println("Updated POSX = " + position.getRow() + "  Current POSY = " + position.getColumn());
        System.out.println();
         */
    }

    /**
     * ***************************************************************************************************
     * Accessor methods
     * **************************************************************************************************
     */
    /**
     * Checks if Player is still alive
     *
     * @return true if Player is alive, false if not
     */
    public boolean isAlive() {
        return this.alive;
    }

    /**
     * Get the maximum stamina for the player.
     *
     * @return maximum stamina
     */
    public double getMaximumStaminaLevel() {
        return this.maxStamina;
    }

    /**
     * Get the current stamina for the player.
     *
     * @return current stamina of the player
     */
    public double getStaminaLevel() {
        return this.stamina;
    }

    /**
     * Returns the amount of stamina needed to move.
     *
     * @param terrain the terrain to move in
     * @return the amount of stamina needed for the next move
     */
    public double getStaminaNeededToMove(Terrain terrain) {
        double staminaNeeded = MOVE_STAMINA;
        double load = getCurrentBackpackWeight() / maxBackpackWeight;
        // Twice as much is needed when the backpack is full
        staminaNeeded *= (1.0 + load);
        // and even more when the terrain is difficult
        staminaNeeded *= terrain.getDifficulty();
        return staminaNeeded;
    }

    /**
     * Checks to see if the player has enough stamina to move.
     *
     * @param terrain the terrain to move in
     * @return true if player can move, false if not
     */
    public boolean hasStaminaToMove(Terrain terrain) {
        return (this.stamina >= getStaminaNeededToMove(terrain));
    }

    /**
     * Get current size of backpack.
     *
     * @return currentBackpackSize
     */
    public double getCurrentBackpackSize() {
        double totalSize = 0.0;
        for (Item item : backpack) {
            totalSize += item.getSize();
        }
        return totalSize;
    }

    /**
     * Gets the maximum Backpack size.
     *
     * @return the maximum backpack size
     */
    public double getMaximumBackpackSize() {
        return maxBackpackSize;
    }

    /**
     * Get current weight of backpack.
     *
     * @return currentBackpackWeight
     */
    public double getCurrentBackpackWeight() {
        double totalWeight = 0.0;
        for (Item item : backpack) {
            totalWeight += item.getWeight();
        }
        return totalWeight;
    }

    /**
     * Gets the maximum Backpack weight.
     *
     * @return the maximum backpack weight
     */
    public double getMaximumBackpackWeight() {
        return maxBackpackWeight;
    }

    /**
     * Checks if the player has a specific item.
     *
     * @param item to check
     * @return true if item in backpack, false if not
     */
    public boolean hasItem(Item item) {
        return backpack.contains(item);
    }

    /**
     * Checks if the player has a tool.
     *
     * @return true if tool in backpack, false if not
     */
    public boolean hasTrap() {
        boolean found = false;
        for (Item item : backpack) {
            if (item instanceof Tool) {
                Tool tool = (Tool) item;
                if (tool.isTrap()) {
                    found = true;
                }
            }
        }
        return found;
    }

    /**
     * get a trap from player's backpack
     *
     * @return trap or null if player has no trap
     */
    public Tool getTrap() {
        Tool tool = null;
        Tool trap = null;
        for (Item item : backpack) {
            if (item instanceof Tool) {
                tool = (Tool) item;
                if (tool.isTrap()) {
                    trap = tool;
                }
            }
        }
        return trap;
    }

    /**
     * Returns a collection of all items in the player's backpack.
     *
     * @return the items in the player's backpack
     */
    public Collection<Item> getInventory() {
        return Collections.unmodifiableCollection(backpack);
    }

    /**
     * ***********************************************************************************************************
     * Mutator methods
     * **************************************************************************************************************
     */
    /**
     * Kills the Player
     */
    public void kill() {
        this.alive = false;
    }

    /**
     * Decrease the stamina level by reduction.
     *
     * @param reduction the amount of stamina to reduce
     */
    public void reduceStamina(double reduction) {
        if (reduction > 0) {
            this.stamina -= reduction;
            if (this.stamina < 0.0) {
                this.stamina = 0.0;
            }
        }
    }

    /**
     * Increase the stamina level of the player.
     *
     * @param increase the amount of stamina increase
     */
    public void increaseStamina(double increase) {
        if (increase > 0 && isAlive()) {
            this.stamina += increase;
        }
        if (stamina > maxStamina) {
            stamina = maxStamina;
        }
    }

    /**
     * Collect an item if it will fit in player's backpack.
     *
     * @param item to collect
     * @return true if item is placed in backpack.
     */
    public boolean collect(Item item) {
        boolean success = false;
        if (item != null && item.isOkToCarry()) {
            double addedSize = getCurrentBackpackSize() + item.getSize();
            boolean enoughRoom = (addedSize <= this.maxBackpackSize);
            double addedWeight = getCurrentBackpackWeight() + item.getWeight();
            //Will weight fit in backpack?
            boolean notTooHeavy = (addedWeight <= this.maxBackpackWeight);
            //Player can only carry one trap at a time.
            //Is this an addtional trap?
            boolean additionalTrap = false;
            if (item instanceof Tool) {
                Tool tool = (Tool) item;
                additionalTrap = (tool.isTrap() && this.hasTrap());
            }

            if (enoughRoom && notTooHeavy && !additionalTrap) {
                success = backpack.add(item);
                // when item is collected, it is no longer "on the island"
                if (success) {
                    // assign it the "not on island" position
                    item.setPosition(Position.NOT_ON_ISLAND);
                }
            }
        }
        return success;
    }

    /**
     * Drops an item if player is carrying it.
     *
     * @param item to drop
     * @return true if item dropped, false if not
     */
    public boolean drop(Item item) {
        return backpack.remove(item);
    }

    /**
     * Moves the player over terrain to a new position.
     *
     * @param position the new position of the player
     * @param terrain the terrain to move over
     */
    public void moveToPosition(Position newPosition, Terrain terrain) {
        if ((this.position == null) || (terrain == null)) {
            throw new IllegalArgumentException("Null parameters");
        }

        if (hasStaminaToMove(terrain)) {
            this.position = newPosition;
            reduceStamina(getStaminaNeededToMove(terrain));
        }
    }

    @Override
    public String getStringRepresentation() {
        return "Player";
    }

    public void render(Graphics g) {
        int xOffset = (Game.TILE_WIDTH - DEFAULT_PLAYER_WIDTH) / 2;
        int yOffset = (Game.TILE_HEIGTH - DEFAULT_PLAYER_HEIGHT) / 2;

        g.drawImage(getCurrentAnimation(), (int) getPosition().getRow() * Game.TILE_WIDTH + xOffset, (int) getPosition().getColumn() * Game.TILE_HEIGTH + yOffset,
                DEFAULT_PLAYER_WIDTH, DEFAULT_PLAYER_HEIGHT, null);
        
        //g.drawImage(getCurrentAnimation(), (int) xMove, (int) yMove, DEFAULT_PLAYER_WIDTH, DEFAULT_PLAYER_HEIGHT, null);
    }

    //GEts the picture corresponding to the direction player is headig to. This method will be used for animation in the future
    private BufferedImage getCurrentAnimation() {

        if (direction == MoveDirection.WEST) {

            return Assets.player_left[1];
        } else if (direction == MoveDirection.EAST) {

            return Assets.player_right[1];
        }
        if (direction == MoveDirection.NORTH) {
//            
            return Assets.player_up[1];
        } else {
//            
            return Assets.player_down[1];

        }

    }
}
