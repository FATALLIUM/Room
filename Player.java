public class Player {
    private String[] inventory;

    public Player() {
        this.inventory = new String[] {" ", " ", " ", " ", " ", " "};
    }

    public void addItem(String item) {
        if (!hasItem(item)) {
            item = item.substring(0,1).toUpperCase() + item.substring(1);
            System.out.println("\n+ " + item + " added to inventory.\n");
            for (int i = 0; i < inventory.length; i++) {
                if (inventory[i].equals(" ")) {
                    inventory[i] = item;
                    break;
                }
            }
        }
        else {
            System.out.println("\n+ You already have this item.\n");
        }
    }

    public void printInventory() {
        System.out.println("\nInventory: ");
        for (int i = 0; i < inventory.length; i++) {
            if (!inventory[i].equals(" ")) {
                System.out.println(inventory[i]);
            }
            else {
                break;
            }
        }
    }

    public boolean hasItem(String item) {
        for (int i = 0; i < inventory.length; i ++) {
            if (inventory[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAllArtifacts() {
        String invStr = "";
        for (int i = 0; i < inventory.length; i ++) {
            invStr += inventory[i];
        }

        if (invStr.contains("teacup") && invStr.contains("toothbrush") && invStr.contains("vase") && invStr.contains("EXTREME DELUXE-100 MEGA-WATT LOTION CREAM")) {
            return true;
        }
        return false;
    }
}
