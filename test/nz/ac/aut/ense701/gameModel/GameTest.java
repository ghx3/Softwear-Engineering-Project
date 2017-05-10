/**
 * in order to perform this test the Test.txt text file should be used as a ma
 */

package nz.ac.aut.ense701.gameModel;

import nz.ac.aut.ense701.gameModel.Entity.Food;
import nz.ac.aut.ense701.gameModel.Entity.Hazard;
import nz.ac.aut.ense701.gameModel.Entity.Item;
import nz.ac.aut.ense701.gameModel.Entity.Player;
import nz.ac.aut.ense701.gameModel.Entity.Predator;
import nz.ac.aut.ense701.gameModel.Entity.Tool;
import nz.ac.aut.ense701.gameModel.Map.GridSquare;
import nz.ac.aut.ense701.gameModel.Utils.MoveDirection;
import nz.ac.aut.ense701.gameModel.Utils.GameState;
import nz.ac.aut.ense701.gameModel.Map.Island;
import nz.ac.aut.ense701.gameModel.Map.Position;
import nz.ac.aut.ense701.gameModel.Map.WorldCreator;
import nz.ac.aut.ense701.main.Game;
import nz.ac.aut.ense701.main.GameController;
import nz.ac.aut.ense701.main.Handler;
import org.junit.Test;

/**
 * The test class GameTest.
 *
 * @author  AS
 * @version S2 2011
 */
public class GameTest extends junit.framework.TestCase
{
    GameController       gameController;
    Player     player;
    Position   playerPosition;
    Island island ;
    Game game;
    Handler handler;
    
    
    /**
     * Default constructor for test class GameTest
     */
    public GameTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Override
    protected void setUp()
    {   
        game = new Game("Title",800,640);
        handler = new Handler(game);
        WorldCreator world = new WorldCreator(handler, "test.txt");
        // Create a new game from the data file.
        // Player is in position 2,0 & has 100 units of stamina
        gameController = new GameController(handler,world);
        playerPosition = gameController.getPlayer().getPosition();
        player         = gameController.getPlayer();
        island = gameController.getIsland();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @Override
    protected void tearDown()
    {
        game = null;
        player = null;
        playerPosition = null;
    }

    /*********************************************************************************************************
     * Game under test
      
---------------------------------------------------
|    |    |@   | F  | T  |    |    | PK |    |    |
|~~~~|~~~~|....|....|....|~~~~|^^^^|^^^^|^^^^|^^^^|
---------------------------------------------------
|    |    |    |    | H  |    |    |    |    |    |
|~~~~|####|^^^^|^^^^|^^^^|^^^^|^^^^|^^^^|^^^^|^^^^|
---------------------------------------------------
|    |    | H  |    | E  |    | P  |    | K  |    |
|####|####|####|####|^^^^|^^^^|^^^^|^^^^|^^^^|~~~~|
---------------------------------------------------
| T  |    |    |    | P  | H  |    |    |    |    |
|....|####|####|####|****|****|^^^^|....|~~~~|~~~~|
---------------------------------------------------
| F  | P  |    |    |    |    | F  |    |    |    |
|....|^^^^|^^^^|^^^^|****|****|^^^^|....|~~~~|~~~~|
---------------------------------------------------
| H  |    | P  | T  | E  |    |    |    |    |    |
|....|****|****|****|****|****|####|####|####|~~~~|
---------------------------------------------------
|    |    | K  |    | P  | H  | K  | E  | F  |    |
|....|****|****|****|****|****|****|####|####|####|
---------------------------------------------------
| K  |    |    | F  | H  |    | H  | K  | T  |    |
|****|****|****|****|****|~~~~|****|****|~~~~|~~~~|
---------------------------------------------------
|    |    | E  | K  |    |    |    |    | F  |    |
|....|....|****|****|~~~~|~~~~|~~~~|****|****|....|
---------------------------------------------------
|    |    |    | K  | K  |    | K  | P  |    |    |
|~~~~|....|****|****|****|~~~~|****|****|****|....|
---------------------------------------------------
 *********************************************************************************************************/
    /**
     * Tests for Accessor methods of Game, excluding those which are wrappers for accessors in other classes.
     * Other class accessors are tested in their test classes.
     */
    
    @Test
    public void testGetNumRows(){
        assertEquals("Check row number", island.getNumRows(), 10);
    }
    
    @Test
    public void testGetNumColumns(){
        assertEquals("Check column number", island.getNumRows(), 10);
    }
    
    @Test
    public void testGetPlayer(){
        String name = player.getName();
        String checkName = "River Song";
        assertTrue("Check player name", name.equals(checkName) );
    } 

    @Test
    public void testGetInitialState(){
        assertEquals("Wrong initial state", gameController.getState(), GameState.PLAYING);
    }
    
    @Test
    public void testGetPlayerValues(){
        int[] values = gameController.getPlayerValues();
        assertEquals("Check Max backpack size.", values[GameController.MAXSIZE_INDEX], 5);    
        assertEquals("Check max stamina.", values[GameController.MAXSTAMINA_INDEX], 100);
        assertEquals("Check max backpack weight.", values[GameController.MAXWEIGHT_INDEX], 10);
        assertEquals("Check initialstamina", values[GameController.STAMINA_INDEX], 100);
        assertEquals("Check initial backpack weight.", values[GameController.WEIGHT_INDEX], 0);
        assertEquals("Check initial backp[ack size.", values[GameController.SIZE_INDEX], 0);
    }
    
    @Test
    public void testIsPlayerMovePossibleValidMove(){
        //At start of game player has valid moves EAST, West & South
        assertTrue("Move should be valid", gameController.isPlayerMovePossible(MoveDirection.SOUTH));
    }
    
    @Test
    public void testIsPlayerMovePossibleInvalidMove(){
        
        gameController.getPlayer().setPosition(new Position(island,0,0));
        //At start of game player has valid moves EAST, West & South
        assertFalse("Move should not be valid", gameController.isPlayerMovePossible(MoveDirection.NORTH));
    }
    
    @Test
    public void testCanCollectCollectable(){
        //Items that are collectable and fit in backpack
        Item valid = new Food(playerPosition,"Sandwich", "Yummy",1.0, 1.0,1.0);
        assertTrue("Should be able to collect", gameController.canCollect(valid));
    }
    
    @Test    
    public void testCanCollectNotCollectable(){
        //Items with size of '0' cannot be carried
        Item notCollectable = new Food(playerPosition,"Sandwich", "Very Heavy Sandwich",10.0, 0.0,1.0);
        assertFalse("Should not be able to collect", gameController.canCollect(notCollectable));
    }
    
    @Test
    public void testCanUseFoodValid(){
        //Food can always be used
        Item valid = new Food(playerPosition,"Sandwich", "Yummy",1.0, 1.0,1.0);
        assertTrue("Should be able to use", gameController.canUse(valid));
    }
    
    @Test
    public void testCanUseTrapValid(){
        //Trap can be used if there is a predator here
        Item valid = new Tool(playerPosition,"Trap", "A predator trap",1.0, 1.0);
        //Add predator
        Predator rat = new Predator(playerPosition,"Rat", "A norway rat");
        island.addOccupant(playerPosition, rat);
        assertTrue("Should be able to use", gameController.canUse(valid));
    }
    
    @Test
    public void testCanUseTrapNoPredator(){
        //Trap can be used if there is a predator here
        Item tool = new Tool(playerPosition,"Trap", "A predator trap",1.0, 1.0);

        assertFalse("Should not be able to use", gameController.canUse(tool));
    }
    
    @Test
    public void testCanUseTool(){
        //Screwdriver can be used if player has a broken trap
        Item tool = new Tool(playerPosition,"Screwdriver", "A good tool to fix a trap",1.0, 1.0);
        Tool trap = new Tool(playerPosition,"Trap", "A predator trap",1.0, 1.0);
        trap.setBroken();
        player.collect(trap);

        assertTrue("Should be able to use", gameController.canUse(tool));
    }
    
    @Test
    public void testCanUseToolNoTrap(){
        //Screwdriver can be used if player has a broken trap
        Item tool = new Tool(playerPosition,"Screwdriver", "A good tool to fix a trap",1.0, 1.0);
        Tool trap = new Tool(playerPosition,"Trap", "A predator trap",1.0, 1.0);
        trap.setBroken();

        assertFalse("Should not be able to use", gameController.canUse(tool));
    }
    
    @Test
    public void testCanUseToolTrapNotBroken(){
        //Screwdriver can be used if player has a broken trap
        Item tool = new Tool(playerPosition,"Screwdriver", "A good tool to fix a trap",1.0, 1.0);
        Tool trap = new Tool(playerPosition,"Trap", "A predator trap",1.0, 1.0);
        player.collect(trap);

        assertFalse("Should not be able to use", gameController.canUse(tool));
    }
    
    @Test
    public void testGetKiwiCountInitial()
    {
       assertEquals("Shouldn't have counted any kiwis yet",gameController.getKiwiCount(),0); 
    }
    /**
     * Test for mutator methods
     */
    
    @Test
    public void testCollectValid(){
        Item food = new Food(playerPosition,"Sandwich", "Yummy",1.0, 1.0,1.0);
        island.addOccupant(playerPosition, food);
        assertTrue("Food now on island", island.hasOccupant(playerPosition, food));
        gameController.collectItem(food);
        
        assertTrue("Player should have food",player.hasItem(food));
        assertFalse("Food should no longer be on island", island.hasOccupant(playerPosition, food));
    }
    
    @Test
    public void testCollectNotCollectable(){
        Item notCollectable = new Food(playerPosition,"House", "Can't collect",1.0, 0.0,1.0);
        island.addOccupant(playerPosition, notCollectable);
        assertTrue("House now on island", island.hasOccupant(playerPosition, notCollectable));
        gameController.collectItem(notCollectable);
        
        assertFalse("Player should not have house",player.hasItem(notCollectable));
        assertTrue("House should be on island", island.hasOccupant(playerPosition, notCollectable));
    }
    
    @Test    
    public void testDropValid(){
        Item food = new Food(playerPosition,"Sandwich", "Yummy",1.0, 1.0,1.0);
        island.addOccupant(playerPosition, food);
        gameController.collectItem(food);
        assertTrue("Player should have food",player.hasItem(food));
        
        gameController.dropItem(food);
        assertFalse("Player should no longer have food",player.hasItem(food));
        assertTrue("Food should be on island", island.hasOccupant(playerPosition, food));
    }
    
    @Test
    public void testDropNotValidPositionFull(){
        Item food = new Food(playerPosition,"Sandwich", "Yummy",1.0, 1.0,1.0);
        island.addOccupant(playerPosition, food);
        gameController.collectItem(food);
        assertTrue("Player should have food",player.hasItem(food));
        
        //Positions can have at most three occupants
        Item dummy = new Tool(playerPosition,"Trap", "An extra occupant", 1.0, 1.0);
        Item dummy2 = new Tool(playerPosition,"Trap", "An extra occupant", 1.0, 1.0);
        Item dummy3 = new Tool(playerPosition,"Trap", "An extra occupant", 1.0, 1.0);
        island.addOccupant(playerPosition, dummy);
        island.addOccupant(playerPosition, dummy2);
        island.addOccupant(playerPosition, dummy3);
        
        gameController.dropItem(food);
        assertTrue("Player should have food",player.hasItem(food));
        assertFalse("Food should not be on island", island.hasOccupant(playerPosition, food));
    }
    
    @Test
    public void testUseItemFoodCausesIncrease(){
        Item food = new Food(playerPosition,"Sandwich", "Yummy",1.0, 1.0,1.3);
        player.collect(food);
        assertTrue("Player should have food",player.hasItem(food));
        
        // Will only get a stamina increase if player has less than max stamina
        player.reduceStamina(5.0);
        gameController.useItem(food);
        assertFalse("Player should no longer have food",player.hasItem(food));
        assertEquals("Wrong stamina level", player.getStaminaLevel(), 96.3);
    }
 
    public void testUseItemFoodNoIncrease(){
        Item food = new Food(playerPosition,"Sandwich", "Yummy",1.0, 1.0,1.3);
        player.collect(food);
        assertTrue("Player should have food",player.hasItem(food));
        
        // Will only get a stamina increase if player has less than max stamina
        gameController.useItem(food);
        assertFalse("Player should no longer have food",player.hasItem(food));
        assertEquals("Wrong stamina level", player.getStaminaLevel(), 100.0);
    }  
    
    @Test
    public void testUseItemTrap(){
        Item trap = new Tool(playerPosition,"Trap", "Rat trap",1.0, 1.0);
        player.collect(trap);
        assertTrue("Player should have trap",player.hasItem(trap));
        
        // Can only use trap if there is a predator.
        Predator predator = new Predator(playerPosition,"Rat", "Norway rat");
        island.addOccupant(playerPosition, predator);
        gameController.useItem(trap);
        assertTrue("Player should still have trap",player.hasItem(trap));
        assertFalse("Predator should be gone.", island.hasPredator(playerPosition));
    }
    
    @Test
    public void testUseItemBrokenTrap(){
        Tool trap = new Tool(playerPosition,"Trap", "Rat trap",1.0, 1.0);
        player.collect(trap);
        assertTrue("Player should have trap",player.hasItem(trap));
        
        // Can only use trap if there is a predator.
        Predator predator = new Predator(playerPosition,"Rat", "Norway rat");
        island.addOccupant(playerPosition, predator);
        trap.setBroken();
        gameController.useItem(trap);
        assertTrue("Player should still have trap",player.hasItem(trap));
        assertTrue("Predator should still be there as trap broken.", island.hasPredator(playerPosition));
    }
    
    @Test
    public void testUseItemToolFixTrap(){
        Tool trap = new Tool(playerPosition,"Trap", "Rat trap",1.0, 1.0);
        trap.setBroken();
        player.collect(trap);
        assertTrue("Player should have trap",player.hasItem(trap));
        Tool screwdriver = new Tool(playerPosition,"Screwdriver", "Useful screwdriver",1.0, 1.0);
        player.collect(screwdriver);
        assertTrue("Player should have screwdriver",player.hasItem(screwdriver));
        
        gameController.useItem(screwdriver);
        assertFalse("Trap should be fixed", trap.isBroken());
    }
   
    @Test
    public void testPlayerMoveToInvalidPosition(){
        Position prevPosition= player.getPosition();
        gameController.getPlayer().setPosition(new Position(island,0,0));
        //A move NORTH would be invalid from player's start position
        assertFalse("Move not valid", gameController.playerMove(MoveDirection.NORTH));
        gameController.getPlayer().setPosition(prevPosition);
    }
 
    @Test
    public void testPlayerMoveValidNoHazards(){
        double stamina = player.getStaminaLevel(); 
        
        Position prevPosition= player.getPosition();
                
        assertTrue("Move valid", gameController.playerMove(MoveDirection.SOUTH));
       
        GridSquare square = island.getGridSquare( player.getPosition());
        
        //Stamina reduced by move
        assertEquals("Wrong stamina", stamina - square.getTerrain().getDifficulty(), player.getStaminaLevel());
        Position newPos = gameController.getPlayer().getPosition();
        assertEquals("Wrong position", newPos.getColumn(), prevPosition.getColumn()+1);
        assertFalse("Player should not be here", gameController.hasPlayer(playerPosition.getRow(), playerPosition.getColumn()));
    }
    
    @Test
    public void testPlayerMoveFatalHazard(){ 
        Position hazardPosition = new Position(island, playerPosition.getRow()+1, playerPosition.getColumn());
        Hazard fatal = new Hazard(hazardPosition, "Cliff", "Steep cliff", 1.0);
        island.addOccupant(hazardPosition, fatal);
        
        assertTrue("Move valid", gameController.playerMove(MoveDirection.EAST));
        //Fatal Hazard should kill player
        assertTrue("Player should be dead.", !player.isAlive());
        assertTrue("Game should be over", gameController.getState()== GameState.LOST);
    }
    
    @Test
    public void testPlayerMoveDeadPlayer(){
        player.kill();
        assertFalse(gameController.playerMove(MoveDirection.SOUTH));
    }
    
    @Test
    public void testPlayerMoveNonFatalHazardNotDead(){
        double stamina = player.getStaminaLevel(); 
        Position hazardPosition = new Position(island, playerPosition.getRow()+1, playerPosition.getColumn());
        Hazard fatal = new Hazard(hazardPosition, "Cliff", "Not so steep cliff", 0.5);
        island.addOccupant(hazardPosition, fatal);
        
        assertTrue("Move valid", gameController.playerMove(MoveDirection.EAST));
        //Non-fatal Hazard should reduce player stamina
        assertTrue("Player should be alive.", player.isAlive());
        assertTrue("Game should not be over", gameController.getState()== GameState.PLAYING);
        assertEquals("Wrong stamina", (stamina-53), player.getStaminaLevel());
    }
    
    @Test
    public void testPlayerMoveNonFatalHazardDead(){
        Position hazardPosition = new Position(island, playerPosition.getRow()+1, playerPosition.getColumn());
        Hazard fatal = new Hazard(hazardPosition, "Cliff", "Not so steep cliff", 0.5);
        island.addOccupant(hazardPosition, fatal);
        player.reduceStamina(47.0);
        
        assertTrue("Move valid", gameController.playerMove(MoveDirection.EAST));
        //Non-fatal Hazard should reduce player stamina to less than zero
        assertFalse("Player should not be alive.", player.isAlive());
        assertTrue("Game should be over", gameController.getState()== GameState.LOST);
        assertEquals("Wrong stamina", 0.0, player.getStaminaLevel());
    }
    
    @Test
    public void testPlayerMoveNotEnoughStamina(){
        // Reduce player's stamina to less than is needed for the most challenging move
        //Most challenging move is WEST as Terrain is water
        player.reduceStamina(97.0);
        assertFalse("Player should not have required stamina", gameController.playerMove(MoveDirection.WEST));
        //Game not over as there other moves player has enough stamina for
        assertTrue("Game should not be over", gameController.getState()== GameState.PLAYING);
    }
    
    @Test
    public void testCountKiwi()
    {   
        Position prevPosition= player.getPosition();
        gameController.getPlayer().setPosition(new Position(island,0,0));
        //Need to move to a place where there is a kiwi
        assertTrue (" This move valid", playerMoveSouth(7));
        gameController.countKiwi();
        assertEquals("Wrong count", gameController.getKiwiCount(), 1);
        gameController.getPlayer().setPosition(prevPosition);
    }

/**
 * Private helper methods
 */
    
  
    
    private boolean playerMoveNorth(int numberOfMoves)
    {
        boolean success = false;
        for (int i = 0; i < numberOfMoves; i++) {
            success = gameController.playerMove(MoveDirection.NORTH);
            if(!success)break;
            
        }
        return success;
    }
    
    private boolean playerMoveSouth(int numberOfMoves)
    {
        boolean success = false;
        for (int i = 0; i < numberOfMoves; i++) {
            success = gameController.playerMove(MoveDirection.SOUTH);
            if(!success)break;
            
        }
        return success;
    }
    
    private boolean playerMoveEast(int numberOfMoves)
    {
        boolean success = false;
        for (int i = 0; i < numberOfMoves; i++) {
            success = gameController.playerMove(MoveDirection.EAST);
            if(!success)break;
            
        }
        return success;
    }
    
    private boolean playerMoveWest(int numberOfMoves)
    {
        boolean success = false;
        for (int i = 0; i < numberOfMoves; i++) {
            success = gameController.playerMove(MoveDirection.WEST);
            if(!success)break;
            
        }
        return success;
    }
}
