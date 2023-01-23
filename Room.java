import java.util.Scanner;

public class Room {
    private int roomNumber; // keep track of what room you're in
    private String action; // user's action (global)

    private boolean dlcPlay;

    public Room() {
        // default roomNumber to 0
        roomNumber = 0;
        dlcPlay = false;
    }

    // informs the player know which room they are currently in using two parameters (int and String)
    private void roomPuzzle(int roomNumber, String name) {
        switch(roomNumber) {
            case 0:
                System.out.println("\nThis is the kitchen. The artifact is you need is a " + name + ".");
                break;
            case 1:
                System.out.println("\nThis is the living room. The artifact you need are " + name + ".");
                break;
            case 2:
                System.out.println("\nThis is the bathroom. The artifact you need is a " + name + ".");
                break;
            case 3:
                System.out.println("\nThis is the bedroom. The artifact you need is a " + name + ".");
                break;
            case 4:
                System.out.println("\nAnother plane of reality. You need to find " + name + ".");
                break;
            default:
                System.out.println("\nEmpty.");
                break;
        }
    }

    // where everything is set together
    public void playRoom() {
        // create new objects
        // Player obj
        Player player = new Player();
        Artifact artifact = new Artifact();
        Scanner scan = new Scanner(System.in);
        NPC npc = new NPC();
        Interaction interaction = new Interaction(player, artifact, npc);

        System.out.println("\nWelcome, player! This is a text-adventure where you need to escape the house." +
                "\nCollect all artifacts from four rooms in order to leave the premises!\n\nGood luck!");

        // while the player doesn't have all the artifacts, run the current room
        // print the 'main' menu
        executeActions(player, artifact, scan, interaction);

        System.out.println("\nYou've collected the last artifact! Huzzah!\nYou leave the premises unscathed.");
        System.out.println("\n\nWould you like to play the trial DLC?\n1. Yes\n2. No");
        action = scan.nextLine();

        switch (action) {
            // head to the "DLC"
            case "1":
                incrRmNum();
                System.out.println(artifact.getName(roomNumber));
                dlcPlay = true;
                executeActions(player, artifact, scan, interaction);
                break;
            // Leave the game
            case "2":
                System.exit(0);
                break;
            default:
                System.out.println("\nInvalid decision.");
                break;
        }
        System.out.println();
        System.exit(0);
    }

    // check surroundings depending on current
    // room (with Artifact obj)
    private void roomLook(int roomNumber, Interaction interaction) {
        switch(roomNumber) {
            // ----------------------------------------------------
            // ----------------------------------------------------
            // ----------------------------------------------------
            case 0: // search in kitchen
                interaction.interact("kitchen", roomNumber);
                break;
            // ----------------------------------------------------
            // ----------------------------------------------------
            // ----------------------------------------------------
            case 1: // search in living room
                interaction.interact("living room", roomNumber);
                break;
            // ----------------------------------------------------
            // ----------------------------------------------------
            // ----------------------------------------------------
            case 2: // search in bathroom
                interaction.interact("bathroom", roomNumber);
                break;
            // ----------------------------------------------------
            // ----------------------------------------------------
            // ----------------------------------------------------
            case 3: // search in bedroom
                interaction.interact("bedroom", roomNumber);
                break;
            // ----------------------------------------------------
            // ----------------------------------------------------
            // ----------------------------------------------------
            case 4: // "DLC"!!!
                interaction.interact("DLC", roomNumber);
                break;
            default:
                System.out.println("Invalid decision.");
                break;
        }
    }

    // add to roomNumber when the user has finished current room
    private void incrRmNum() {
        roomNumber++;
    }

    // print menu
    private void printActions(Artifact artifact) {
        if (artifact.isFound()) {
            System.out.println("\nACTIONS:\n1. Look around\n2. Inventory\n3. Exit game\n4. Move to the next room");
        }
        else {
            System.out.println("\nACTIONS:\n1. Look around\n2. Inventory\n3. Exit game");
        }
    }

    // when the player with the menu
    private void executeActions(Player player, Artifact artifact, Scanner scan, Interaction interaction) {
        while (!player.hasAllArtifacts()) {
            roomPuzzle(roomNumber, artifact.getName(roomNumber));
            printActions(artifact);
            action = scan.nextLine();

            switch (action) {
                case "1":   // check surroundings
                    roomLook(roomNumber, interaction);
                    break;
                case "2":   // check inventory
                    player.printInventory();
                    break;
                case "3":   // exit game
                    System.out.println("\n\nGood-bye!");
                    System.exit(0);
                case "4":   // option available only when player has found the current area's artifact
                    // if they did, they are allowed to leave the current area
                    if (artifact.isFound() && !dlcPlay) {
                        System.out.println("\nYou leave the area.");
                        incrRmNum();
                        artifact.setFound(false);
                        break;
                    }
                default:
                    System.out.println("Invalid decision.");
                    break;
            }
        }
        if (dlcPlay) {
            while (!player.hasFinalFinality()) {
                roomPuzzle(roomNumber, artifact.getName(roomNumber));
                printActions(artifact);
                action = scan.nextLine();

                switch (action) {
                    case "1":   // check surroundings
                        roomLook(roomNumber, interaction);
                        break;
                    case "2":   // check inventory
                        player.printInventory();
                        break;
                    case "3":   // exit game
                        System.out.println("\n\nGood-bye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid decision.");
                        break;
                }
            }
        }
    }
}
