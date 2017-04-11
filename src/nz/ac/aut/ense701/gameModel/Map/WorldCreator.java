/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel.Map;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import nz.ac.aut.ense701.gameModel.Entity.Fauna;
import nz.ac.aut.ense701.gameModel.Entity.Food;
import nz.ac.aut.ense701.gameModel.Entity.Hazard;
import nz.ac.aut.ense701.gameModel.Entity.Kiwi;
import nz.ac.aut.ense701.gameModel.Entity.Occupant;
import nz.ac.aut.ense701.gameModel.Entity.Player;
import nz.ac.aut.ense701.gameModel.Entity.Predator;
import nz.ac.aut.ense701.gameModel.Entity.Tool;
import nz.ac.aut.ense701.gameModel.Tile.Tile;
import nz.ac.aut.ense701.main.Game;
import nz.ac.aut.ense701.main.Handler;

/**
 *
 * @author Everybody's
 */
public class WorldCreator {

    private int totalPredators;
    private int totalKiwis;
    private Island island;
    private Player player;
    private Handler handler;

    public WorldCreator(Handler handler,String path) {
        this.handler = handler;
        initialiseIslandFromFile(path);
    }

    /**
     * Loads terrain and occupant data from a file. At this stage this method
     * assumes that the data file is correct and just throws an exception or
     * ignores it if it is not.
     *
     * @param fileName file name of the data file
     */
    private void initialiseIslandFromFile(String fileName) {
        try {
            Scanner input = new Scanner(new File(fileName));
            // make sure decimal numbers are read in the form "123.23"
            input.useLocale(Locale.US);
            input.useDelimiter("\\s*,\\s*");

            // create the island
            int numRows = input.nextInt();
            int numColumns = input.nextInt();
            island = new Island(numRows, numColumns);

            // read and setup the terrain
            setUpTerrain(input);

            // read and setup the player
            setUpPlayer(input);

            // read and setup the occupants
            setUpOccupants(input);

            input.close();
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find data file '" + fileName + "'");
        } catch (IOException e) {
            System.err.println("Problem encountered processing file.");
        }
    }

    /**
     * Reads terrain data and creates the terrain.
     *
     * @param input data from the level file
     */
    private void setUpTerrain(Scanner input) {
        for (int row = 0; row < island.getNumRows(); row++) {
            String terrainRow = input.next();
            for (int col = 0; col < terrainRow.length(); col++) {
                Position pos = new Position(island, row, col);
                String terrainString = terrainRow.substring(col, col + 1);
                Terrain terrain = Terrain.getTerrainFromStringRepresentation(terrainString);
                island.setTerrain(pos, terrain);
            }
        }
    }

    /**
     * Reads player data and creates the player.
     *
     * @param input data from the level file
     */
    private void setUpPlayer(Scanner input) {
        String playerName = input.next();
        int playerPosRow = input.nextInt();
        int playerPosCol = input.nextInt();
        double playerMaxStamina = input.nextDouble();
        double playerMaxBackpackWeight = input.nextDouble();
        double playerMaxBackpackSize = input.nextDouble();
                
        Position pos = new Position(island, playerPosRow, playerPosCol);
        player = new Player(this.handler,pos, playerName,
                playerMaxStamina,
                playerMaxBackpackWeight, playerMaxBackpackSize);
        island.updatePlayerPosition(player);
       
    }

    /**
     * Creates occupants listed in the file and adds them to the island.
     *
     * @param input data from the level file
     */
    private void setUpOccupants(Scanner input) {
        int numItems = input.nextInt();
        for (int i = 0; i < numItems; i++) {
            String occType = input.next();
            String occName = input.next();
            String occDesc = input.next();
            int occRow = input.nextInt();
            int occCol = input.nextInt();
            Position occPos = new Position(island, occRow, occCol);
            Occupant occupant = null;

            if (occType.equals("T")) {
                double weight = input.nextDouble();
                double size = input.nextDouble();
                occupant = new Tool(this.handler,occPos, occName, occDesc, weight, size);
            } else if (occType.equals("E")) {
                double weight = input.nextDouble();
                double size = input.nextDouble();
                double energy = input.nextDouble();
                occupant = new Food(this.handler,occPos, occName, occDesc, weight, size, energy);
            } else if (occType.equals("H")) {
                double impact = input.nextDouble();
                occupant = new Hazard(this.handler,occPos, occName, occDesc, impact);
            } else if (occType.equals("K")) {
                occupant = new Kiwi(this.handler,occPos, occName, occDesc);
                totalKiwis++;
            } else if (occType.equals("P")) {
                occupant = new Predator(this.handler,occPos, occName, occDesc);
                totalPredators++;
            } else if (occType.equals("F")) {
                occupant = new Fauna(this.handler,occPos, occName, occDesc);
            }
            if (occupant != null) {
                island.addOccupant(occPos, occupant);
            }
        }
    }

    public int getTotalPredators() {
        return totalPredators;
    }

    public int getTotalKiwis() {
        return totalKiwis;
    }

    public Island getIsland() {
        return island;
    }

    public Player getPlayer() {
        return player;
    }

}
