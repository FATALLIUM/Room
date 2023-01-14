public class Artifact {
    private boolean found;

    public Artifact() {
        // variable to keep track if artifact of current room is found
        found = false;
    }

    // get artifact depending on current room
    public String getName(int roomNumber) {
        switch(roomNumber) {
            case 0:
                return "teacup";
            case 1:
                return "matches";
            case 2:
                return "toothbrush";
            case 3:
                return "EXTREME DELUXE-100 MEGA-WATT LOTION CREAM";
        }
        return ";";
    }

    // if the artifact of current room is found
    public boolean isFound() {
        return found;
    }

    // setting artifact to newFound
    public void setFound(boolean newFound) {
        found = newFound;
    }
}
