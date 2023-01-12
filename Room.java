import java.util.Scanner;

public class Room {
    private int roomNumber;
    private String action;
    private Player player;

    public Room() {
        roomNumber = 0;
    }
    private void roomPuzzle(int roomNumber, String name) {
        switch(roomNumber) {
            case 0:
                System.out.println("\nThis is the kitchen. The artifact is you need is a " + name + ".");
                break;
            case 1:
                System.out.println("\nThis is the living room. The artifact you need is a " + name + ".");
                break;
            case 2:
                System.out.println("\nThis is the bathroom. The artifact you need is a " + name + ".");
                break;
            case 3:
                System.out.println("\nThis is the bedroom. The artifact you need is a " + name + ".");
                break;
        }
    }

    public void playRoom() {
        // create new opjects
        player = new Player();
        Artifact artifact = new Artifact();
        Scanner scan = new Scanner(System.in);

        System.out.println("\nWelcome, player! This is a text-adventure where you need to escape the house." +
                "\nCollect all artifacts from four rooms in order to leave the premises!\n\nGood luck!");

        while (!player.hasAllArtifacts()) {
            roomPuzzle(roomNumber, artifact.getName(roomNumber));
            printActions(artifact);
            action = scan.nextLine();
            switch (action) {
                case "1":
                    roomLook(roomNumber, artifact);
                    break;
                case "2":
                    player.printInventory();
                    break;
                case "3":
                    System.out.println("\n\nGood-bye!");
                    System.exit(0);
                case "4":
                    if (artifact.isFound()) {
                        System.out.println("\nYou leave the room.");
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

    private void roomLook(int roomNumber, Artifact artifact) {
        Scanner scan = new Scanner(System.in);
        switch(roomNumber) {
            case 0: // search in kitchen
                System.out.println("\nThe cupboards have scratch marks. The sink has hair in it. The hair is choppy." +
                        "\n1. Cupboards\n2. Sink\n");
                action = scan.nextLine();
                switch (action) {
                    case "1":
                        if (artifact.isFound()) {
                            System.out.println("\nCobwebs and dust.");
                            break;
                        }
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
                        break;
                    case "2":
                        System.out.println("\nYou look inside the sink. There's bits of leftover cheese and what looks like shredded meat." +
                                "\nThere's nothing else here.");
                        break;
                    default:
                        System.out.println("\nInvalid decision.");
                        break;
                }
                break;
            case 1: // search in living room
                System.out.println("\nIt smells like sea salt in here. There's a sofa, a cabinet, and a window." +
                        "\n1. Sofa\n2. Cabinet\n3. Window");
                action = scan.nextLine();
                switch (action) {
                    case "1":
                        if (player.hasItem("Key")) {
                            System.out.println("\nTattered and battered. Nothing useful.");
                            break;
                        }
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
                        break;
                    case "2":
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
                                        break;
                                    default:
                                        System.out.println("Invalid decision.");
                                        break;
                                }
                            }
                        }
                        else {
                            System.out.println("\nAn empty cabinet.");
                        }
                        break;
                    case "3":
                        System.out.println("\nYou look outside the window. It's too dark out.\nYou can barely make out a figure in the background.");
                        break;
                    default:
                        System.out.println("\nInvalid decision.");
                }
                break;
            case 2: // search in bathroom
                System.out.println("\nThis is the most disgusting and utterly pungent bathroom you have ever set foot in.");
                System.out.println("\n1. Photo\n2. Sink\n3. Toilet\n4. Shower");
                action = scan.nextLine();
                switch (action) {
                    case "1":
                        System.out.println("\nYou look at the photo of a lake. You don't like it.");
                        break;
                    case "2":
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
                        System.out.println("\nIt smells great.");
                        break;
                    case "4":
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
            case 3: // search in bedroom
                System.out.println("\nOne left.");
                if (player.hasItem("jug of gasoline") && player.hasItem("tattered bedsheets")) {
                    System.out.println("\nMattress.");
                }
                System.out.println("\n1. Painting\n2. Bed\n3. Desk\n4. Closet");
                action = scan.nextLine();
                switch (action) {
                    case "1":
                        System.out.println("\nIt's a painted portrait of you.");
                        break;
                    case "2":
                        System.out.println("\nThe mattress seems to have gone through a few beatings.");
                        if (player.hasItem("jug of gasoline") && player.hasItem("tattered bedsheets")) {
                            System.out.println("\n1. Commit arson\n");
                            action = scan.nextLine();
                            switch (action) {
                                case "1":
                                    System.out.println("\n1. Douse items in gasoline");
                                    action = scan.nextLine();
                                    action = scan.nextLine();
                                    System.out.println("\n1. Use matches");
                                    System.out.println("You have found the " + artifact.getName(roomNumber) + "!!!");
                                    break;
                                default:
                                    System.out.println("Invalid decision.");
                                    break;
                            }
                        }
                        else if (player.hasItem("tattered bedsheets")) {
                            System.out.println("\n1. Look under\n2. Sleep");
                            action = scan.nextLine();
                            switch (action) {
                                case "1":
                                   // bed
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
                        else {
                            System.out.println("\n1. Look under\n2. Take bedsheets\3. Sleep");
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
        }
    }

    private void incrRmNum() {
        roomNumber++;
    }

    public void printActions(Artifact artifact) {
        if (artifact.isFound()) {
            System.out.println("\nACTIONS:\n1. Look around\n2. Inventory\n3. Exit game\n4. Move to the next room");
        }
        else {
            System.out.println("\nACTIONS:\n1. Look around\n2. Inventory\n3. Exit game");
        }
    }
}
