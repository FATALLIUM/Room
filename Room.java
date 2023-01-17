import java.util.Scanner;

public class Room {
    private int roomNumber; // keep track of what room you're in
    private String action; // user's action (global)
    private Player player; // Player obj

    public Room() {
        // default roomNumber to 0
        roomNumber = 0;
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
        player = new Player();
        Artifact artifact = new Artifact();
        Scanner scan = new Scanner(System.in);

        System.out.println("\nWelcome, player! This is a text-adventure where you need to escape the house." +
                "\nCollect all artifacts from four rooms in order to leave the premises!\n\nGood luck!");

        // while the player doesn't have all the artifacts, run the current room
        // print the 'main' menu
        executeActions(player, artifact, scan);

        System.out.println("\nYou've collected the last artifact! Huzzah!\nYou leave the premises unscathed.");
        System.out.println("\n\nWould you like to play the trial DLC?\n1. Yes\n2. No");
        action = scan.next();
        switch (action) {
            // head to the "DLC"
            case "1":
                incrRmNum();
                executeActions(player, artifact, scan);
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
    // ugly as heck
    // h u g e switch statements (massive, ginormous, ungodly and satanic [endearment])
    // was deciding between different methods for each room but decided to converge them all
    private void roomLook(int roomNumber, Artifact artifact) {
        Scanner scan = new Scanner(System.in);

        switch(roomNumber) {
            // ----------------------------------------------------
            // ----------------------------------------------------
            // ----------------------------------------------------
            case 0: // search in kitchen
                System.out.println("\nThe cupboards have scratch marks. The sink has hair in it. The hair is choppy." +
                        "\n1. Cupboards\n2. Sink\n");
                action = scan.nextLine();
                switch (action) {
                    case "1":
                        // check cupboards
                        if (artifact.isFound()) {
                            System.out.println("\nCobwebs and dust.");
                            break;
                        }
                        else {
                            System.out.println("\nYou open the cupboards. A cockroach crawls out and tap-dances away.\nThere's a porcelain item in the back of the cupboard." +
                                    "\nGrab it?\n1. Grab\n2. Don't grab\n");
                            action = scan.nextLine();
                            switch (action) {
                                case "1":
                                    player.addItem(artifact.getName(roomNumber));
                                    System.out.println("\nYou have found this room's artifact!\nYou may now head to the next room.\n...\n");
                                    artifact.setFound(true);
                                    break;
                                case "2":
                                    System.out.println("\nYou leave the item.");
                                    break;
                                default:
                                    System.out.println("Invalid decision.");
                                    break;
                            }
                        }
                        break;
                    case "2":
                        // check sink
                        System.out.println("\nYou look inside the sink. There's bits of leftover cheese and what looks like shredded meat." +
                                "\nThere's nothing else here.");
                        break;
                    default:
                        System.out.println("\nInvalid decision.");
                        break;
                }
                break;
            // ----------------------------------------------------
            // ----------------------------------------------------
            // ----------------------------------------------------
            case 1: // search in living room
                System.out.println("\nIt smells like sea salt in here. There's a sofa, a cabinet, and a window." +
                        "\n1. Sofa\n2. Cabinet\n3. Window");
                action = scan.nextLine();
                switch (action) {
                    case "1":
                        // check sofa
                        if (player.hasItem("key")) {
                            System.out.println("\nTattered and battered. Nothing useful.");
                            break;
                        }
                        else {
                            System.out.println("\nA beat-up sofa. There's something shiny beneath a cushion.\n" +
                                    "1. Grab it\n2. Don't grab\n");
                            action = scan.nextLine();
                            switch (action) {
                                case "1":
                                    System.out.println("\nYou grab the shiny thing. It's a key.");
                                    player.addItem("key");
                                    break;
                                case "2":
                                    System.out.println("\nYou leave the sofa.");
                                    break;
                                default:
                                    System.out.println("\nInvalid decision.");
                                    break;
                            }
                        }
                        break;
                    case "2":
                        // check cabinet
                        if (!artifact.isFound()) {
                            System.out.println("\nIt's a locked cabinet. Rusty and musty.");
                            if (player.hasItem("key")) {
                                System.out.println("Luckily for you, you use the key you had found.");
                                player.removeItem("key");
                                System.out.println("\nYou also lost your key.");
                                System.out.println("\n+ key removed from inventory.");
                                System.out.println("Inside the cabinet is a box of matches.\n" +
                                        "1. Take the matches\n2. Leave the matches");
                                action = scan.nextLine();
                                switch (action) {
                                    case "1":
                                        player.addItem(artifact.getName(roomNumber));
                                        System.out.println("\nYou have found this room's artifact!\nYou may now head to the next room.\n...\n");
                                        artifact.setFound(true);

                                        break;
                                    case "2":
                                        System.out.println("\nYou leave the matches.");
                                        player.addItem("key");
                                        break;
                                    default:
                                        System.out.println("Invalid decision.");
                                        break;
                                }
                            }
                        }
                        else {
                            System.out.println("\nAn empty cabinet.");
                            break;
                        }
                        break;
                    case "3":
                        // check window
                        System.out.println("\nYou look outside the window. It's dark out.\nYou can barely make out a figure in the background.");
                        break;
                    default:
                        System.out.println("\nInvalid decision.");
                }
                break;
            // ----------------------------------------------------
            // ----------------------------------------------------
            // ----------------------------------------------------
            case 2: // search in bathroom
                System.out.println("\nThis is the most disgusting and utterly pungent bathroom you have ever set foot in.");
                System.out.println("\n1. Photo\n2. Sink\n3. Toilet\n4. Shower");
                action = scan.nextLine();

                switch (action) {
                    case "1":
                        // check photo
                        System.out.println("\nYou look at the photo of a lake. You don't like it.");
                        break;

                    case "2":
                        // check sink: mirror or drawer
                        System.out.println("\nThere's black, sludge-like water in the sink.");
                        System.out.println("\n1. Mirror\n2. Drawer");
                        action = scan.nextLine();

                        switch (action) {
                            case "1":
                                System.out.println("\nThee mirror has a note that says: \n" +
                                        "Why was eight afraid of seven?");
                                break;
                            case "2":
                                if (!artifact.isFound()) {
                                    System.out.println("\nThe drawer is locked with a pin.");
                                    System.out.println("\n1. Enter pin\n2. Leave drawer");
                                    action = scan.nextLine();
                                    switch (action) {
                                        case "1":
                                            System.out.println("\nEnter the pin (in digits): ");
                                            action = scan.nextLine();
                                            if (action.equals("789")) {
                                                System.out.println("\nYou unlocked the drawer. Inside is a toothbrush.");
                                                System.out.println("\n1. Take toothbrush\n2. Leave toothbrush");
                                                action = scan.nextLine();
                                                switch (action) {
                                                    case "1":
                                                        player.addItem(artifact.getName(roomNumber));
                                                        System.out.println("\nYou have found this room's artifact!\nYou may now head to the next room.\n...\n");
                                                        artifact.setFound(true);
                                                        break;
                                                    case "2":
                                                        System.out.println("\nYou leave the toothbrush.");
                                                        break;
                                                    default:
                                                        System.out.println("\nInvalid decision.");
                                                        break;
                                                }
                                            }
                                            break;
                                        case "2":
                                            System.out.println("\nYou leave the drawer.");
                                            break;
                                        default:
                                            System.out.println("Invalid decision.");
                                            break;
                                    }
                                }
                                else {
                                    System.out.println("\nIt's empty.");
                                }
                                break;
                            default:
                                System.out.println("Invalid decision.");
                                break;
                        }
                        break;

                    case "3":
                        // check toilet
                        System.out.println("\nIt smells great.");
                        break;

                    case "4":
                        // check shower
                        System.out.println("\nDo you really want to look?\n1. Yes\n2. No");
                        action = scan.nextLine();
                        switch (action) {
                            case "1":
                                System.out.println("\nYou move the shower curtain to find... nothing.");
                                break;
                            case "2":
                                System.out.println("\nGood.");
                                break;
                            default:
                                System.out.println("Invalid decision.");
                                break;
                        }
                        break;
                    default:
                        System.out.println("Invalid decision.");
                        break;
                }

                break;
            // ----------------------------------------------------
            // ----------------------------------------------------
            // ----------------------------------------------------
            case 3: // search in bedroom
                System.out.println("\nOne left.");
                if (player.hasItem("jug of gasoline") && player.hasItem("tattered bedsheets")) {
                    System.out.println("\nMattress.");
                }

                System.out.println("\n1. Painting\n2. Bed\n3. Desk\n4. Closet");
                action = scan.nextLine();

                switch (action) {
                    case "1":
                        // check painting
                        System.out.println("\nIt's a painted portrait of you.");
                        break;

                    case "2":
                        // part two of mattress
                        System.out.println("\nThe mattress seems to have gone through a few beatings.");

                        // setting stuff on fire part one
                        if (player.hasItem("jug of gasoline") && player.hasItem("tattered bedsheets")) {
                            System.out.println("\n1. Commit arson\n");
                            action = scan.nextLine();
                            switch (action) {
                                case "1":
                                        System.out.println("\n1. Douse items in gasoline");
                                        action = scan.nextLine();
                                        switch (action) {
                                            case "1":
                                                System.out.println("\nYou douse all your acquired items in gasoline.");
                                                player.removeItem("tattered blankets");
                                                player.removeItem("jug of gasoline");
                                                player.removeItem("teacup");
                                                player.removeItem("toothbrush");
                                                break;
                                            default:
                                                System.out.println("Invalid decision.");
                                                break;
                                        }
                                        break;
                                default:
                                    System.out.println("Invalid decision.");
                                    break;
                            }
                        }

                        // if player doesn't have gasoline yet but has all other items
                        if (player.hasItem("toothbrush") && player.hasItem("tattered blankets")) {
                            System.out.println("\n1. Look under\n2. Sleep");
                            action = scan.nextLine();
                            switch (action) {
                                case "1":
                                    System.out.println("\nBut you refused.");
                                    break;
                                case "2":
                                    System.out.println("\nGood idea.\n\nYou go to bed");
                                    System.out.println("\n\nBut you can't fall asleep.");
                                    break;
                                default:
                                    System.out.println("Invalid decision.");
                                    break;
                            }
                        }

                        // setting stuff on fire part two
                        if (!player.hasItem("toothbrush") && player.hasItem("matches")) {
                            System.out.println("\n1. Use matches");
                            action = scan.nextLine();
                            switch (action) {
                                case "1":
                                    System.out.println("\nYou use the matches.");
                                    player.removeItem("matches");
                                    System.out.println("\nYou set the mattress on fire. Woo, arson!");
                                    break;
                                default:
                                    System.out.println("Invalid decision.");
                                    break;
                            }
                            break;
                        }

                        // final part of mattress
                        if (!player.hasItem("matches") && !player.hasItem("toothbrush")) {
                            System.out.println("\nYou look under the charred bed.");
                            System.out.println("You have found the " + artifact.getName(roomNumber) + "!!!");
                            player.addItem(artifact.getName(roomNumber));
                            break;
                        }

                        // part one of mattress (normal)
                        else {
                            System.out.println("\n1. Look under\n2. Take bedsheets\n3. Sleep");
                            action = scan.nextLine();
                            switch (action) {
                                case "1":
                                    System.out.println("\nBut you refused.");
                                    break;
                                case "2":
                                    System.out.println("You remove the sheets.");
                                    player.addItem("tattered bedsheets");
                                    break;
                                case "3":
                                    System.out.println("\nGood idea.\n\nYou go to bed");
                                    System.out.println("\n\nBut you can't fall asleep.");
                                    break;
                                default:
                                    System.out.println("Invalid decision.");
                                    break;
                            }
                        }
                        break;

                    // check desk
                    case  "3":
                        System.out.println("\nThe desk is immaculate.");
                        System.out.println("\n1. Yellowed paper\n2. Leave desk");
                        action = scan.nextLine();
                        switch (action) {
                            case "1":
                                System.out.println("\nIt's an old piece of paper. It seems to be a letter addressed to you.");
                                System.out.println("\n1. Read\n2. Don't read");
                                action = scan.nextLine();
                                switch (action) {
                                    case "1":
                                        System.out.println("\nIt's from an old friend. The Mushroom King.\nYou feel conflicted.");
                                        break;
                                    case "2":
                                        System.out.println("\nYou couldn't bring yourself to read it.");
                                        break;
                                }
                                break;
                            case "2":
                                System.out.println("\nYou leave the desk.");
                                break;
                            default:
                                System.out.println("Invalid decision.");
                                break;
                        }
                        break;

                    case "4":
                        // check closet
                        System.out.println("\nMaple wood.");
                        System.out.println("\n1. Open");
                        action = scan.nextLine();
                        switch (action) {
                            case "1":
                                if (player.hasItem("jug of gasoline")) {
                                    System.out.println("\nAn empty closet.");
                                }
                                else {
                                    System.out.println("\nThere's an old container of gasoline.");
                                    System.out.println("\n1. Take it\n2. Leave it");
                                    action = scan.nextLine();
                                    switch (action) {
                                        case "1":
                                            System.out.println("\nYou take the container of gasoline.");
                                            player.addItem("jug of gasoline");
                                            break;
                                        case "2":
                                            System.out.println("\nYou leave the jug of gasoline.");
                                            break;
                                        default:
                                            System.out.println("Invalid decision.");
                                            break;
                                    }
                                }
                                break;
                            default:
                                System.out.println("Invalid decision.");
                                break;
                        }
                        break;
                    default:
                        System.out.println("Invalid decision.");
                        break;
                }
                break;
            // ----------------------------------------------------
            // ----------------------------------------------------
            // ----------------------------------------------------
            case 4: // "DLC"!!!
                System.out.println("\nYou don't belong here.");
                System.out.println("\n1. God\n2. Jolly Good Man That Looks Suspicious\n3. Bed of stars\n4. Desk\n5. The Sink");
                action = scan.nextLine();

                switch (action) {
                    case "1":   // God
                        System.out.println("\nIt's God. It's currently playing chess with cows.");
                        System.out.println("\nInterrupt them?\n1. Yes\n2. No");
                        action = scan.nextLine();

                        switch (action) {

                        }
                        break;

                    case "2":   // NPC
                        break;
                    case "3":   // bed of stars
                        break;
                    case "4":   // a desk
                        System.out.println("\nAn immaculate desk. Looks like the one in the house.");
                        System.out.println("\n");
                        break;
                    case "5":   // look at The Sink
                        System.out.println("\nIt's not [[[A]]] sink, it's [[[THE SINK]]].");

                        break;
                    default:
                        System.out.println("\nInvalid action.");
                        break;
                }
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
    private void executeActions(Player player, Artifact artifact, Scanner scan) {
        while (!player.hasAllArtifacts()) {
            roomPuzzle(roomNumber, artifact.getName(roomNumber));
            printActions(artifact);
            action = scan.nextLine();
            switch (action) {
                case "1":   // check surroundings
                    roomLook(roomNumber, artifact);
                    break;
                case "2":   // check inventory
                    player.printInventory();
                    break;
                case "3":   // exit game
                    System.out.println("\n\nGood-bye!");
                    System.exit(0);
                case "4":   // option available only when player has found the current area's artifact
                    // if they did, they are allowed to leave the current area
                    if (artifact.isFound()) {
                        System.out.println("\nYou leave the area.");
                        incrRmNum();
                        artifact.setFound(false);
                        break;
                    }
                    else {
                        System.out.println("Invalid decision.");
                    }
                    break;
            }
        }
    }
}
