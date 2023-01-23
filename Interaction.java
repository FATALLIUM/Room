import javax.annotation.processing.SupportedSourceVersion;
import java.util.Scanner;

// transcription of the entire script/interactions the player can do
public class Interaction {
    private Player player;
    private Artifact artifact;

    private String action;

    private NPC npc;

    public Interaction(Player player, Artifact artifact, NPC npc) {
        this.player = player;
        this.artifact = artifact;
        this.npc = npc;
    }

    public void interact(String name, int roomNumber) {
        // misc
        Scanner scan = new Scanner(System.in);

        // ----------------------------------------------------
        // ----------------------------------------------------
        // ----------------------------------------------------
        switch (name) {
            // AREAS

            // KITCHEN
            case "kitchen":
                System.out.println("\n1. Cupboards\n2. Sink");
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


            // LIVING ROOM
            case "living room":
                System.out.println("\nIt smells like sea salt in here. There's a sofa, a cabinet, and a window." +
                        "\n1. Sofa\n2. Cabinet\n3. Window");
                action = scan.nextLine();
                switch (action) {
                    case "1":
                        // check sofa
                        if (player.hasItem("matches") || player.hasItem("key")) {
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


            // BATHROOM
            case "bathroom":
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


            // BEDROOM
            case "bedroom":
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


            // DLC
            case "DLC":
                if (!player.hasItem("eggs")) {
                    int eggRand = (int) (Math.random() * 3) + 1;
                    if (eggRand == 1) {
                        System.out.println("\nThere's a carton of eggs on the floor.");
                        System.out.println("\n1. Pick up\n2. Leave");
                        action = scan.nextLine();

                        switch (action) {
                            case "1":
                                player.addItem("eggs");
                                break;
                            case "2":
                                System.out.println("\nYou leave the eggs.");
                                break;
                            default:
                                System.out.println("Invalid decision.");
                                break;
                        }
                    }
                }

                System.out.println("\nYou don't belong here.");

                if (player.hasItem("hay")) {
                    System.out.println("\n1. God\n2. Jolly Good Man That Looks Extremely Suspicious\n3. Bed of stars\n4. Desk\n5. The Sink\n6. Cow");
                }
                else {
                    System.out.println("\n1. God\n2. Jolly Good Man That Looks Extremely Suspicious\n3. Bed of stars\n4. Desk\n5. The Sink");
                }
                action = scan.nextLine();

                switch (action) {
                    case "1":   // God
                        System.out.println("\nIt's God. They are currently playing chess with a cow.");
                        System.out.println("\nInterrupt them?\n1. Yes\n2. No");
                        action = scan.nextLine();

                        // interact with God [fin]
                        switch (action) {
                            case "1":   // talk
                                System.out.println("\nGod: Oooh, don't bother me right now! Can't you see I'm busy?");
                                if (npc.getTalk("god") == 0) {
                                    System.out.println("\n1. Ask it about hamsters\n2. Ask it on leaving this plane of existence");
                                }
                                else {
                                    System.out.println("\n1. Ask it about hamsters\n2. Ask it on leaving this plane of existence\n" +
                                            "3. Ask it about the [Cake Recipe]");
                                }
                                action = scan.nextLine();

                                switch (action) {
                                    case "1":
                                        System.out.println("\nGod: Hamsters? Oh boy, I fostered ten of them this morning!\nGod:" +
                                                "Let's see... There's Gerold, Gerome, Gorm, Grelda, Grimes...");
                                        System.out.println("\nYou lose interest.");
                                        break;

                                    case "2":
                                        System.out.println("\nGod: Leaving? Uh. About that... You see that man over there?\nGod: " +
                                                "Dude kind of threatened me from dissolving this domain. Dunno why.\nGod: Good thing I got this" +
                                                " crazy [Cake Recipe], huh?");
                                        if (npc.getTalk("god") == 0) {npc.incrTalk("god");}
                                        break;

                                    case "3":
                                        if (npc.getTalk("god") == 1 && !player.hasItem("cake recipe")) {
                                            System.out.println("\nGod: It's a recipe for the most delicious" +
                                                    " red-velvet-chocolate-coated-strawberry cake in existence! My son really outdid himself!");
                                            System.out.println("\nGod: And, well, I guess it's also a panacea for crazy people.");
                                        }
                                        else if (npc.getTalk("god") == 1 && player.hasItem("cake recipe")) {
                                            System.out.println("\nGod: Oh boy. It's been too long since I've had cake." +
                                                    " The ingredients are probably scattered somewhere. I get rather forgetful.\n" +
                                                    "God: Would you like me to list the ingredients?\n1. Yes\n2. No");

                                            action = scan.nextLine();

                                            switch (action) {
                                                case "1":
                                                    System.out.println("\nGod: You need milk, eggs, strawberries, and chocolate.");
                                                    break;
                                                case "2":
                                                    System.out.println("\nGod: Okay.\nIt returns to their chess game.");
                                                    break;
                                                default:
                                                    System.out.println("Invalid decision.");
                                                    break;
                                            }
                                        }
                                        else {
                                            System.out.println("Invalid decision.");
                                        }
                                        break;

                                    default:
                                        System.out.println("Invalid decision.");
                                        break;
                                }
                                break;

                            case "2":   // stare
                                int random = (int) (Math.random() * 3) + 1;
                                switch (random) {
                                    case 1:
                                        System.out.println("\nYou stare at them playing. You have never seen anything quite like this.");
                                        break;
                                    case 2:
                                        System.out.println("\nThe cow stares back.");
                                        break;
                                    case 3:
                                        System.out.println("\nGod sneezes.");
                                        break;
                                    default:
                                        System.out.println("Error 0.");
                                        break;
                                }
                                break;
                            default:
                                System.out.println("Invalid decision.");
                                break;
                        }
                        break;

                    case "2":   // jolly man [fin]
                        System.out.println("\nA middle-aged man. He seems to be mumbling to himself.");
                        if (player.hasItem("hay")) {
                            System.out.println("\nJolly Man: Hm-hmm~ Ahoy! [laughs [un-suspiciously]]");
                            System.out.println("\n1. Tell him to leave");
                            action = scan.nextLine();

                            switch (action) {
                                case "2":
                                    int npcJolly = npc.getTalk("jolly man");
                                    switch (npcJolly) {
                                        case 0:
                                            npc.incrTalk("jolly man");
                                            System.out.println("\nJolly Man: Oh! But I have a doctor's appointment... I can't leave yet unfortunately!");
                                            break;
                                        case 1:
                                            npc.incrTalk("jolly man");
                                            System.out.println("\nJolly Man: I love apple pie! I gotta bake some tonight.");
                                            break;
                                        case 2:
                                            System.out.println("\nJolly Man: Hmm... That cow is looking at me funny.");
                                            break;
                                        default:
                                            System.out.println("NPC talk error.");
                                            break;
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid decision.");
                                    break;
                            }
                        }
                        else {
                            System.out.println("\nJolly Man: Halloo! Do ye need something?");
                            System.out.println("\n1. Ask for hay");
                            action = scan.nextLine();

                            switch (action) {
                                case "1":
                                    System.out.println("\nJolly Man: Hey! Hey!\n...\nJolly Man: Oh, you meant [hay]!");
                                    System.out.println("\nJolly Man: If you can open this box, I'll give you some hay! Hay!");
                                    System.out.println("\nEnter passcode [in lowercase]:");
                                    action = scan.nextLine();

                                    if (action.equals("rgb")) {
                                        System.out.println("\nYou hear a click.");
                                        player.addItem("hay");
                                        System.out.println("\nJolly Man: Oh no! My hay! Oh well.");
                                    }
                                    else {
                                        System.out.println("\nJolly Man: Incorrect!");
                                    }
                                    break;
                                default:
                                    System.out.println("Invalid decision.");
                                    break;
                            }
                        }
                        break;

                    case "3":   // bed of stars [fin]
                        System.out.println("\nShiny. They're assembled in a triangle-like formation.");
                        System.out.println("\n1. Talk to the stars\n2. Read note");
                        action = scan.nextLine();

                        switch (action) {
                            case "1":
                                System.out.println("\nStars: OH. HI. WHAT DO YOU WANT?");
                                System.out.println("\n1. Ask about chocolate\n2. Ask for hay.");
                                action = scan.nextLine();

                                switch (action) {
                                    case "1":
                                        if (!player.hasItem("chocolate")) {
                                            System.out.println("\nStars: CHOCOLATE? WHY WOULD WE HAVE CHOCOLATE?\n" +
                                                    "Stars: OH, ACTUALLY, WE MIGHT HAVE SOME OF THOSE IN OUR 100-KARAT CHOCOLATE STASH." +
                                                    "\nStars: WE'LL GIVE THE CHOCOLATE IF AND ONLY IF YOU ANSWER THIS RIDDLE CORRECTLY...");
                                            System.out.println("\nListen to riddle?\n1. Yes\n2. No");
                                            action = scan.nextLine();

                                            switch (action) {
                                                case "1":
                                                    System.out.println("\nStars: WHAT GOES UP BUT NEVER COMES DOWN?");
                                                    System.out.println("\nAnswer in [lowercase]:");
                                                    action = scan.nextLine();

                                                    if (action.equals("age")) {
                                                        System.out.println("\nStars: AW, YOU GOT IT! HERE'S OUR 100-KARAT CHOCOLATE.");
                                                        player.addItem("chocolate");
                                                    }
                                                    else {
                                                        System.out.println("\nStars: YOU GOT IT WRONG! TOO BAD.");
                                                    }
                                                    break;
                                                case "2":
                                                    System.out.println("\nYou shake your head.");
                                                    break;
                                                default:
                                                    System.out.println("Invalid decision.");
                                                    break;
                                            }
                                        }
                                        else {
                                            System.out.println("\nStars: NO MORE CHOCOLATE STASH...");
                                        }
                                        break;
                                    case "2":
                                        if (!player.hasItem("hay")) {
                                            System.out.println("\nStars: WHY WOULD WE HAVE HAY> GO ASK THAT WEIRD JOLLY GUY OVER THERE." +
                                                    "\nSars: HE LOOOVES HAY.");
                                        }
                                        else {
                                            System.out.println("\nStars: WOW, YOU ACTUALLY LISTENED TO US.");
                                        }
                                        break;
                                    default:
                                        System.out.println("Invalid decision.");
                                }
                                break;
                            case "2":
                                System.out.println("\nThere's a note that reads: 1\n 1 1\n 1 2 1\n 1 3 3 1\n... The last line is erased.");
                                break;
                            default:
                                System.out.println("Invalid decision.");
                                break;
                        }
                        break;

                    case "4":   // a desk [fin]
                        System.out.println("\nAn immaculate desk. Looks like the one in the house.");
                        System.out.println("\n1. Search\n2. Look under");
                        action = scan.nextLine();

                        switch (action) {
                            case "1":
                                System.out.println("\n1. Drawers\n2. Cardboard box\n3. Lamp");
                                action = scan.nextLine();

                                switch (action) {
                                    // check drawers
                                    case "1":
                                        if (player.hasItem("knife")) {
                                            System.out.println("\nUseless knick-knacks.");
                                        }
                                        else {
                                            System.out.println("\nThere's a knife.");
                                            System.out.println("\n1. Take knife\n2. Leave knife");
                                            action = scan.nextLine();

                                            switch (action) {
                                                case "1":
                                                    System.out.println("\nShould be useful.");
                                                    player.addItem("knife");
                                                    break;
                                                case "2":
                                                    System.out.println("\nYou leave the knife.");
                                                    break;
                                                default:
                                                    System.out.println("Invalid decision.");
                                                    break;
                                            }
                                        }
                                        break;
                                    // check cardboard box
                                    case "2":
                                        if (player.hasItem("knife") && !player.hasItem("cake recipe")) {
                                            System.out.println("\nUse the knife to open the box?\n1. Yes\n2. No");
                                            action = scan.nextLine();

                                            switch (action) {
                                                case "1":
                                                    System.out.println("\nYou use the knife to open the box.");
                                                    System.out.println("\nThere's a piece of yellowed parchment paper.\n" +
                                                            "1. Take paper\n2. Leave paper");
                                                    action = scan.nextLine();

                                                    switch (action) {
                                                        case "1":
                                                            System.out.println("\nIt's a cake recipe. You don't know the language... Perhaps someone else can.");
                                                            player.addItem("cake recipe");
                                                            break;
                                                        case "2":
                                                            System.out.println("\nYou leave the desk.");
                                                            break;
                                                        default:
                                                            System.out.println("Invalid decision.");
                                                    }
                                                    break;
                                                case "2":
                                                    System.out.println("\nYou leave the box.");
                                                    break;
                                                default:
                                                    System.out.println("Invalid decision.");
                                            }
                                        }
                                        else if (player.hasItem("knife") && player.hasItem("cake recipe")) {
                                            System.out.println("\nAn empty cardboard box.");
                                        }
                                        else {
                                            System.out.println("\nA cardboard box taped up. You need a sharp object for this.");
                                        }
                                        break;
                                    // check lamp
                                    case "3":
                                        System.out.println("\nA lamp. It's broken.");
                                        System.out.println("\nThere's a stamp that says RGB. Or is it KGB... ?");
                                        break;
                                    default:
                                        System.out.println("Invalid decision.");
                                        break;
                                }
                                break;

                            case "2":   // check under the desk
                                if (player.hasItem("strawberries")) {
                                    System.out.println("\nYou look under. There's an empty chest.");
                                }
                                else {
                                    System.out.println("\nYou look under. There's a locked chest.");
                                    System.out.println("\nEnter pin\n1. Yes\n2. No");
                                    action = scan.nextLine();

                                    switch (action) {
                                        case "1":
                                            System.out.println("\nEnter the pin (in digits): " );
                                            action = scan.nextLine();

                                            if (action.equals("14641")) {
                                                System.out.println("\nYou hear a click.");
                                                System.out.println("\nThere's a box of perfect, ripe strawberries.\n1. Take strawberries\n2. Leave strawberries");

                                                action = scan.nextLine();

                                                switch (action) {
                                                    case "1":
                                                        player.addItem("strawberries");
                                                        break;
                                                    case "2":
                                                        System.out.println("\nYou leave the strawberries.");
                                                        break;
                                                    default:
                                                        System.out.println("Invalid decision.");
                                                        break;
                                                }
                                            }
                                            break;
                                        case "2":
                                            System.out.println("\nYou leave the box.");
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

                    case "5":   // look at The Sink + get The Finality [fin]
                        System.out.println("\nIt's not [[[A]]] sink, it's [[[THE SINK]]].");
                        System.out.println("\nThis is also where you can assemble the cake once you have [all the ingredients].");
                        System.out.println("\nAssemble?\n1. Yes\n2. No");
                        action = scan.nextLine();

                        switch (action) {
                            case "1":
                                if (player.hasAllIngredients() && !player.hasFinalFinality()) {
                                    System.out.println("\nYou mush the ingredients together...\n\n");
                                    player.removeItem("chocolate");
                                    player.removeItem("strawberries");
                                    player.removeItem("milk");
                                    player.removeItem("eggs");
                                    player.addItem(artifact.getName(roomNumber));
                                    System.out.println("\nWoo! You have collected the divine caketh of the gods!");
                                    System.out.println("\nIn the distance, you can hear a jolly man wailing and a cow laughing...");
                                    System.out.println("\n\nThe END!");
                                    System.exit(0);
                                }
                                else {
                                    System.out.println("\nYou don't have all the ingredients.");
                                }
                                break;
                            case "2":
                                System.out.println("\nYou leave The Sink.");
                                break;
                            default:
                                System.out.println("Invalid decision.");
                                break;
                        }
                        break;

                    case "6":   // cow [fin]
                        if (player.hasItem("hay") && !player.hasItem("milk")) {
                            System.out.println("\n");
                        System.out.println("\nCow: Moo?");
                        System.out.println("\nOffer hay?\n1. Offer\n2. Don't offer");
                        action = scan.nextLine();

                        switch (action) {
                            case "1":
                                player.removeItem("hay");
                                System.out.println("\nThe cow spits out a perfectly preserved jag of milk.");
                                player.addItem("milk");
                                break;
                            case "2":
                                System.out.println("\nYou have offended the cow.");
                                break;
                            default:
                                System.out.println("Invalid decision.");
                                break;
                            }
                        }
                        break;
                    default:
                        System.out.println("\nInvalid action.");
                        break;
                }
                break;

            default:
                System.out.println("Error.");
        }
    }
}