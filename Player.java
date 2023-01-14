public class Player {
    private String[] inventory;

    public Player() {
        // initialize inventory
        this.inventory = new String[] {" ", " ", " ", " ", " ", " "};
    }

    // adding item to inventory
    public void addItem(String item) {
        if (!hasItem(item)) {
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

    // remove item if hasItem()
    public void removeItem(String item) {
        if (hasItem(item)) {
            for (int i = 0; i < inventory.length; i++) {
                if (inventory[i].equals(item)) {
                    inventory[i] = " ";
                    break;
                }
            }
        }
    }

    // print the inventory
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

    // check if inventory contains item
    public boolean hasItem(String item) {
        for (int i = 0; i < inventory.length; i ++) {
            if (inventory[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    // checking if inventory has all needed artifacts
    public boolean hasAllArtifacts() {
        String invStr = "";
        for (int i = 0; i < inventory.length; i ++) {
            invStr += inventory[i];
        }

        if (invStr.contains("EXTREME DELUXE-100 MEGA-WATT LOTION CREAM")) {
            return true;
        }
        return false;
    }
}
