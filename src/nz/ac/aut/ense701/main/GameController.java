package nz.ac.aut.ense701.main;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.HashSet;
import java.util.Set;
import nz.ac.aut.ense701.gameModel.Entity.Food;
import nz.ac.aut.ense701.gameModel.Utils.GameEventListener;
import nz.ac.aut.ense701.gameModel.Utils.GameState;
import nz.ac.aut.ense701.gameModel.Entity.Hazard;
import nz.ac.aut.ense701.gameModel.Map.Island;
import nz.ac.aut.ense701.gameModel.Entity.Item;
import nz.ac.aut.ense701.gameModel.Entity.Kiwi;
import nz.ac.aut.ense701.gameModel.Utils.MoveDirection;
import nz.ac.aut.ense701.gameModel.Entity.Occupant;
import nz.ac.aut.ense701.gameModel.Entity.Player;
import nz.ac.aut.ense701.gameModel.Map.Position;
import nz.ac.aut.ense701.gameModel.Map.Terrain;
import nz.ac.aut.ense701.gameModel.Entity.Tool;
import nz.ac.aut.ense701.gameModel.Entity.UserInfo;
import nz.ac.aut.ense701.gameModel.Map.WorldCreator;
import nz.ac.aut.ense701.gameModel.Tile.Tile;

/**
 * This is the class that knows the Kiwi Island game rules and state and
 * enforces those rules.
 *
 * @author AS
 * @version 1.0 - created Maintenance History August 2011 Extended for stage 2.
 *          AS
 */
public class GameController {

	// Constants shared with UI to provide player data
	public static final int STAMINA_INDEX = 0;
	public static final int MAXSTAMINA_INDEX = 1;
	public static final int MAXWEIGHT_INDEX = 2;
	public static final int WEIGHT_INDEX = 3;
	public static final int MAXSIZE_INDEX = 4;
	public static final int SIZE_INDEX = 5;

	private Island island;
	private Player player;
	private GameState state;
	private int kiwiCount;
	private int totalPredators;
	private int totalKiwis;
	private int predatorsTrapped;
	private int level;
	private int difficulty;
	private Set<GameEventListener> eventListeners;
	public UserInfo user;

	private Handler handler;

	private final double MIN_REQUIRED_CATCH = 0.8;

	private String winMessage = "";
	private String loseMessage = "";
	private String playerMessage = "";

	public long startTime;
	public long finishTime;

	// for test purpose
	public GameController(Handler handler, WorldCreator world) {
		this.handler = handler;

		eventListeners = new HashSet<GameEventListener>();

		this.handler.setIsland(world.getIsland());

		totalPredators = world.getTotalPredators();
		totalKiwis = world.getTotalKiwis();
		predatorsTrapped = 0;
		kiwiCount = 0;

		player = world.getPlayer();
		island = world.getIsland();
		drawIsland();

		state = GameState.PLAYING;
		winMessage = "";
		loseMessage = "";
		playerMessage = "";
		notifyGameEventListeners();
	}

	/**
	 * A new instance of Kiwi island that reads data from "someMap.txt".
	 */
	public GameController(Handler handler) {
		this.handler = handler;
		eventListeners = new HashSet<GameEventListener>();
		difficulty = 0;
		level = 1;
		user = new UserInfo();
		// createNewGame();
	}

	public void setDifficulty(int diff) {
		difficulty = diff;
	}

	/**
	 * Starts a new game. At this stage data is being read from a text file
	 */
	public void createNewGame() {
		startTime = System.currentTimeMillis();
		WorldCreator world = null;
		switch (difficulty) {
		case (0):
			switch (level) {
			case (1):
				world = new WorldCreator(handler, "maps/1E.txt");
				break;
			case (2):
				world = new WorldCreator(handler, "maps/2E.txt");
				break;
			case (3):
				world = new WorldCreator(handler, "maps/3E.txt");
				break;
			}
			break;
		case (1):
			switch (level) {
			case (1):
				world = new WorldCreator(handler, "maps/1M.txt");
				break;
			case (2):
				world = new WorldCreator(handler, "maps/2M.txt");
				break;
			case (3):
				world = new WorldCreator(handler, "maps/3M.txt");
				break;
			}
			break;
		case (2):
			switch (level) {
			case (1):
				world = new WorldCreator(handler, "maps/1H.txt");
				break;
			case (2):
				world = new WorldCreator(handler, "maps/2H.txt");
				break;
			case (3):
				world = new WorldCreator(handler, "maps/3H.txt");
				break;
			}
			break;
		}

		handler.setIsland(world.getIsland());

		totalPredators = world.getTotalPredators();
		totalKiwis = world.getTotalKiwis();
		predatorsTrapped = 0;
		kiwiCount = 0;

		player = world.getPlayer();
		island = world.getIsland();
		drawIsland();

		state = GameState.PLAYING;
		winMessage = "";
		loseMessage = "";
		playerMessage = "";
		notifyGameEventListeners();
	}

	public void tick() {

	}

	public void render(Graphics g) {

		island.render(g);
		player.render(g);

	}

	/**
	 * *************************************************************************************************************
	 * Mutator Methods
	 * **************************************************************************************************************
	 */
	/**
	 * Picks up an item at the current position of the player Ignores any
	 * objects that are not items as they cannot be picked up
	 *
	 * @param item
	 *            the item to pick up
	 * @return true if item was picked up, false if not
	 */
	public boolean collectItem(Object item) {
		boolean success = (item instanceof Item) && (player.collect((Item) item));
		if (success) {
			// player has picked up an item: remove from grid square
			island.removeOccupant(player.getPosition(), (Item) item);

			// everybody has to know about the change
			notifyGameEventListeners();
		}
		return success;
	}

	/**
	 * Drops what from the player's backpack.
	 *
	 * @param what
	 *            to drop
	 * @return true if what was dropped, false if not
	 */
	public boolean dropItem(Object what) {
		boolean success = player.drop((Item) what);
		if (success) {
			// player has dropped an what: try to add to grid square
			Item item = (Item) what;
			success = island.addOccupant(player.getPosition(), item);
			if (success) {
				// drop successful: everybody has to know that
				notifyGameEventListeners();
			} else {
				// grid square is full: player has to take what back
				player.collect(item);
			}
		}
		return success;
	}

	/**
	 * Uses an item in the player's inventory. This can be food or tool items.
	 *
	 * @param item
	 *            to use
	 * @return true if the item has been used, false if not
	 */
	public boolean useItem(Object item) {
		boolean success = false;
		if (item instanceof Food && player.hasItem((Food) item)) // Player east
																	// food to
																	// increase
																	// stamina
		{
			Food food = (Food) item;
			// player gets energy boost from food
			player.increaseStamina(food.getEnergy());
			// player has consumed the food: remove from inventory
			player.drop(food);
			// use successful: everybody has to know that
			notifyGameEventListeners();
		} else if (item instanceof Tool) {
			Tool tool = (Tool) item;
			if (tool.isTrap() && !tool.isBroken()) {
				success = trapPredator();
			} else if (tool.isScrewdriver())// Use screwdriver (to fix trap)
			{
				if (player.hasTrap()) {
					Tool trap = player.getTrap();
					trap.fix();
				}
			}
		}
		updateGameState();
		return success;
	}

	/**
	 * Count any kiwis in this position
	 */
	public void countKiwi() {
		// check if there are any kiwis here
		for (Occupant occupant : island.getOccupants(player.getPosition())) {
			if (occupant instanceof Kiwi) {
				Kiwi kiwi = (Kiwi) occupant;
				if (!kiwi.counted()) {
					kiwi.count();
					kiwiCount++;
				}
			}
		}
		updateGameState();
	}

	/**
	 * Attempts to move the player in the specified direction.
	 *
	 * @param direction
	 *            the direction to move
	 * @return true if the move was successful, false if it was an invalid move
	 */
	public boolean playerMove(MoveDirection direction) {
		// what terrain is the player moving on currently
		boolean successfulMove = false;
		if (isPlayerMovePossible(direction)) {
			Position newPosition = player.getPosition().getNewPosition(direction);
			Terrain terrain = island.getTerrain(newPosition);

			// move the player to new position
			player.moveToPosition(newPosition, terrain);
			island.updatePlayerPosition(player);
			successfulMove = true;

			// Is there a hazard?
			checkForHazard();

			updateGameState();
		}
		return successfulMove;
	}

	/**
	 * Adds a game event listener.
	 *
	 * @param listener
	 *            the listener to add
	 */
	public void addGameEventListener(GameEventListener listener) {
		eventListeners.add(listener);
	}

	/**
	 * Removes a game event listener.
	 *
	 * @param listener
	 *            the listener to remove
	 */
	public void removeGameEventListener(GameEventListener listener) {
		eventListeners.remove(listener);
	}

	public int getMove() {
		return handler.getGameController().getPlayer().getMove();
	}

	/**
	 * *******************************************************************************************************************************
	 * Private methods
	 * *******************************************************************************************************************************
	 */
	/**
	 * Used after player actions to update game state. Applies the Win/Lose
	 * rules.
	 */
	private void updateGameState() {
		  String message = "";
	        if (!player.isAlive()) {
	            state = GameState.LOST;
	            message = "Sorry, you have lost the game. " + this.getLoseMessage() + "\n"
	            		+"You have used " + getMove() +" moves";
	            this.setLoseMessage(message);
	        } else if (!playerCanMove()) {
	            state = GameState.LOST;
	            message = "Sorry, you have lost the game. You do not have sufficient stamina to move.\n"
	            		+"You have used " + getMove() +"moves";
	            this.setLoseMessage(message);
	        } else if (predatorsTrapped == totalPredators) {
	            state = GameState.WON;
	            String rank = getRank(finishTime);
	            message = "You win! You have done an excellent job and trapped all the predators.\n" 
	            +"You achieved Rank "+rank + "\n"
	            +"You have used " + getMove() +" moves";
	            this.setWinMessage(message);
	            level++;
	        } else if (kiwiCount == totalKiwis) {
	            if (predatorsTrapped >= totalPredators * MIN_REQUIRED_CATCH) {
	                state = GameState.WON;
	                String rank = getRank(finishTime);
	                message = "You win! You have counted all the kiwi and trapped at least 80% of the predators.\n"
	                +"You achieved Rank "+rank +"\n"
	                +"You have used " + getMove() +" moves";
	                this.setWinMessage(message);
	                level++;
	            }
	        }
	        // notify listeners about changes
	        notifyGameEventListeners();
	}

	public String getRank(long finishTime) {
		double rankTime = finishTime / 1000 / 60;
		String rank;
		if (rankTime < 1) {
			rank = "S";
		} else if (rankTime < 2) {
			rank = "A";
		} else if (rankTime < 3) {
			rank = "B";
		} else if (rankTime < 4) {
			rank = "C";
		} else {
			rank = "D";
		}
		saveScore(user.getName(), rank);
		return rank;
	}

	public void saveScore(String name, String rank) {

		String userName = user.getName();
		String rankLevel = rank;

		try {
			PrintWriter record = new PrintWriter("record.txt", "UTF-8");

			record.println("username: " + userName);
			record.println("rankLevel: " + rankLevel);
			record.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets details about players win
	 *
	 * @param message
	 */
	private void setWinMessage(String message) {
		winMessage = message;
	}

	/**
	 * Sets details of why player lost
	 *
	 * @param message
	 */
	private void setLoseMessage(String message) {
		loseMessage = message;
	}

	/**
	 * Set a message for the player
	 *
	 * @param message
	 */
	private void setPlayerMessage(String message) {
		playerMessage = message;

	}

	/**
	 * Check if player able to move
	 *
	 * @return true if player can move
	 */
	private boolean playerCanMove() {
		return (isPlayerMovePossible(MoveDirection.NORTH) || isPlayerMovePossible(MoveDirection.SOUTH)
				|| isPlayerMovePossible(MoveDirection.EAST) || isPlayerMovePossible(MoveDirection.WEST));

	}

	/**
	 * Trap a predator in this position
	 *
	 * @return true if predator trapped
	 */
	private boolean trapPredator() {
		Position current = player.getPosition();
		boolean hadPredator = island.hasPredator(current);
		if (hadPredator) // can trap it
		{
			Occupant occupant = island.getPredator(current);
			// Predator has been trapped so remove
			island.removeOccupant(current, occupant);
			predatorsTrapped++;
		}

		return hadPredator;
	}

	/**
	 * Checks if the player has met a hazard and applies hazard impact. Fatal
	 * hazards kill player and end game.
	 */
	private void checkForHazard() {
		// check if there are hazards
		for (Occupant occupant : island.getOccupants(player.getPosition())) {
			if (occupant instanceof Hazard) {
				handleHazard((Hazard) occupant);
			}
		}
	}

	/**
	 * Apply impact of hazard
	 *
	 * @param hazard
	 *            to handle
	 */
	private void handleHazard(Hazard hazard) {
		if (hazard.isFatal()) {
			player.kill();
			this.setLoseMessage(hazard.getDescription() + " has killed you.");
		} else if (hazard.isBreakTrap()) {
			Tool trap = player.getTrap();
			if (trap != null) {
				trap.setBroken();
				this.setPlayerMessage(
						"Sorry your predator trap is broken. You will need to find tools to fix it before you can use it again.");
			}
		} else // hazard reduces player's stamina
		{
			double impact = hazard.getImpact();
			// Impact is a reduction in players energy by this % of Max Stamina
			double reduction = player.getMaximumStaminaLevel() * impact;
			player.reduceStamina(reduction);
			// if stamina drops to zero: player is dead
			if (player.getStaminaLevel() <= 0.0) {
				player.kill();
				this.setLoseMessage(" You have run out of stamina");
			} else // Let player know what happened
			{
				this.setPlayerMessage(hazard.getDescription() + " has reduced your stamina.");
			}
		}
	}

	/**
	 * Notifies all game event listeners about a change.
	 */
	private void notifyGameEventListeners() {
		for (GameEventListener listener : eventListeners) {
			listener.gameStateChanged();
		}
	}

	/**
	 * *********************************************************************************************************************
	 * Accessor methods for game data
	 * **********************************************************************************************************************
	 */
	/**
	 * Gets the current state of the game.
	 *
	 * @return the current state of the game
	 */
	public GameState getState() {
		return state;
	}

	/**
	 * Provide a description of occupant
	 *
	 * @param whichOccupant
	 * @return description if whichOccuoant is an instance of occupant, empty
	 *         string otherwise
	 */
	public String getOccupantDescription(Object whichOccupant) {
		String description = "";
		if (whichOccupant != null && whichOccupant instanceof Occupant) {
			Occupant occupant = (Occupant) whichOccupant;
			description = occupant.getDescription();
		}
		return description;
	}

	/**
	 * Gets the player object.
	 *
	 * @return the player object
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Checks if possible to move the player in the specified direction.
	 *
	 * @param direction
	 *            the direction to move
	 * @return true if the move was successful, false if it was an invalid move
	 */
	public boolean isPlayerMovePossible(MoveDirection direction) {
		boolean isMovePossible = false;
		// what position is the player moving to?
		Position newPosition = player.getPosition().getNewPosition(direction);
		// is that a valid position?
		if ((newPosition != null) && newPosition.isOnIsland()) {
			// what is the terrain at that new position?
			Terrain newTerrain = island.getTerrain(newPosition);
			Tile t = Tile.tiles[newTerrain.getCode()];
			if (t.isSolid()) {
				isMovePossible = false;
			} else {
				// can the playuer do it?
				isMovePossible = player.hasStaminaToMove(newTerrain) && player.isAlive();
			}

		}
		return isMovePossible;
	}

	/**
	 * Get terrain for position
	 *
	 * @param row
	 * @param column
	 * @return Terrain at position row, column
	 */
	public Terrain getTerrain(int row, int column) {
		return island.getTerrain(new Position(island, row, column));
	}

	/**
	 * Is this position visible?
	 *
	 * @param row
	 * @param column
	 * @return true if position row, column is visible
	 */
	public boolean isVisible(int row, int column) {
		return island.isVisible(new Position(island, row, column));

	}

	/**
	 * Is this position explored?
	 *
	 * @param row
	 * @param column
	 * @return true if position row, column is explored.
	 */
	public boolean isExplored(int row, int column) {
		return island.isExplored(new Position(island, row, column));
	}

	/**
	 * Get occupants for player's position
	 *
	 * @return occupants at player's position
	 */
	public Occupant[] getOccupantsPlayerPosition() {
		return island.getOccupants(player.getPosition());
	}

	/**
	 * Get string for occupants of this position
	 *
	 * @param row
	 * @param column
	 * @return occupant string for this position row, column
	 */
	public String getOccupantStringRepresentation(int row, int column) {
		return island.getOccupantStringRepresentation(new Position(island, row, column));
	}

	/**
	 * Get values from player for GUI display
	 *
	 * @return player values related to stamina and backpack.
	 */
	public int[] getPlayerValues() {
		int[] playerValues = new int[6];
		playerValues[STAMINA_INDEX] = (int) player.getStaminaLevel();
		playerValues[MAXSTAMINA_INDEX] = (int) player.getMaximumStaminaLevel();
		playerValues[MAXWEIGHT_INDEX] = (int) player.getMaximumBackpackWeight();
		playerValues[WEIGHT_INDEX] = (int) player.getCurrentBackpackWeight();
		playerValues[MAXSIZE_INDEX] = (int) player.getMaximumBackpackSize();
		playerValues[SIZE_INDEX] = (int) player.getCurrentBackpackSize();

		return playerValues;

	}

	/**
	 * How many kiwis have been counted?
	 *
	 * @return count
	 */
	public int getKiwiCount() {
		return kiwiCount;
	}

	/**
	 * How many predators are left?
	 *
	 * @return number remaining
	 */
	public int getPredatorsRemaining() {
		return totalPredators - predatorsTrapped;
	}

	/**
	 * Get contents of player backpack
	 *
	 * @return objects in backpack
	 */
	public Object[] getPlayerInventory() {
		return player.getInventory().toArray();
	}

	/**
	 * Get player name
	 *
	 * @return player name
	 */
	public String getPlayerName() {
		return player.getName();
	}

	/**
	 * Is player in this position?
	 *
	 * @param row
	 * @param column
	 * @return true if player is at row, column
	 */
	public boolean hasPlayer(int row, int column) {
		return island.hasPlayer(new Position(island, row, column));
	}

	/**
	 * Only exists for use of unit tests
	 *
	 * @return island
	 */
	public Island getIsland() {
		return island;
	}

	/**
	 * Draws the island grid to standard output.
	 */
	public void drawIsland() {
		island.draw();
	}

	/**
	 * Is this object collectable
	 *
	 * @param itemToCollect
	 * @return true if is an item that can be collected.
	 */
	public boolean canCollect(Object itemToCollect) {
		boolean result = (itemToCollect != null) && (itemToCollect instanceof Item);
		if (result) {
			Item item = (Item) itemToCollect;
			result = item.isOkToCarry();
		}
		return result;
	}

	/**
	 * Is this object a countable kiwi
	 *
	 * @param itemToCount
	 * @return true if is an item is a kiwi.
	 */
	public boolean canCount(Object itemToCount) {
		boolean result = (itemToCount != null) && (itemToCount instanceof Kiwi);
		if (result) {
			Kiwi kiwi = (Kiwi) itemToCount;
			result = !kiwi.counted();
		}
		return result;
	}

	/**
	 * Is this object usable
	 *
	 * @param itemToUse
	 * @return true if is an item that can be collected.
	 */
	public boolean canUse(Object itemToUse) {
		boolean result = (itemToUse != null) && (itemToUse instanceof Item);
		if (result) {
			// Food can always be used (though may be wasted)
			// so no need to change result

			if (itemToUse instanceof Tool) {
				Tool tool = (Tool) itemToUse;
				// Traps can only be used if there is a predator to catch
				if (tool.isTrap()) {
					result = island.hasPredator(player.getPosition());
				} // Screwdriver can only be used if player has a broken trap
				else if (tool.isScrewdriver() && player.hasTrap()) {
					result = player.getTrap().isBroken();
				} else {
					result = false;
				}
			}
		}
		return result;
	}

	/**
	 * Details of why player won
	 *
	 * @return winMessage
	 */
	public String getWinMessage() {
		return winMessage;
	}

	/**
	 * Details of why player lost
	 *
	 * @return loseMessage
	 */
	public String getLoseMessage() {
		return loseMessage;
	}

	/**
	 * Details of information for player
	 *
	 * @return playerMessage
	 */
	public String getPlayerMessage() {
		String message = playerMessage;
		playerMessage = ""; // Already told player.
		return message;
	}

	/**
	 * Is there a message for player?
	 *
	 * @return true if player message available
	 */
	public boolean messageForPlayer() {
		return !("".equals(playerMessage));
	}

}
