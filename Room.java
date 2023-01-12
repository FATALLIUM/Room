import java.util.Scanner;

public class Room {
    private Artifact artifact;
    private int roomNumber;
    private String action;
    private Player player;
    public Room() {
        roomNumber = 0;
    }
    private void roomPuzzle(int roomNumber, String name) {
        Scanner scan = new Scanner(System.in);
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

        printActions();
        action = scan.nextLine();
        switch (action) {
            case "1":
                roomLook(roomNumber);
                break;
            case "2":
                System.out.println("\n");
                player.printInventory();
                break;
            case "3":
                System.out.println("\n\nGood-bye!");
                System.exit(0);

            case "4":
                System.out.println("\nYou leave the room.");
                incrRmNum();
                artifact.setFound(false);
        }

    }

    public void playRoom() {
        player = new Player();
        Artifact artifact = new Artifact();
        System.out.println("\nWelcome, player! This is a text-adventure where you need to escape the house." +
                "\nCollect all artifacts from four rooms in order to leave the premises!\n\nGood luck!");
        while (!player.hasAllArtifacts()) {
            roomPuzzle(roomNumber, artifact.getName(roomNumber));
        }

    }

    private void roomLook(int roomNumber) {
        Scanner scan = new Scanner(System.in);
        Artifact artifact = new Artifact();
        boolean cabinetLocked = true;

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
                        if (cabinetLocked) {
                            System.out.println("\nIt's a locked cabinet. Rusty and musty.");
                            if (player.hasItem("Key")) {
                                cabinetLocked = false;
                                System.out.println("Luckily for you, you use the key you had found.");
                                System.out.println("Inside the cabinet is a vase.\n" +
                                        "1. Take the vase\n2. Leave the vase");
                                action = scan.nextLine();
                                switch (action) {
                                    case "1":
                                        player.addItem("vase");
                                        System.out.println("\nYou have found this room's artifact!\nYou may now head to the next room.\n...\n");
                                        artifact.setFound(true);
                                        break;
                                    case "2":
                                        System.out.println("\nYou leave the vase.");
                                        break;
                                    default:
                                        System.out.println("Invalid decision.");
                                        break;
                                }
                            }
                        }
                        System.out.println("\nAn empty cabinet.");
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
                break;
            case 3: // search in bedroom
                System.out.println("\nOne left.");
                System.out.println("\n1. Painting\n2. Bed\n3. Desk\n4. Closet");
                break;
        }
    }

    private void incrRmNum() {
        roomNumber++;
    }

    public void printActions() {
        if (artifact.isFound()) {
            System.out.println("\nACTIONS:\n1. Look around\n2. Inventory\n3. Exit game\n4. Move to the next room");
        }
        System.out.println("\nACTIONS:\n1. Look around\n2. Inventory\n3. Exit game");
    }
}
