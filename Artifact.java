public class Artifact {
    private boolean found;

    public Artifact() {
        found = false;
    }

    public String getName(int roomNumber) {
        switch(roomNumber) {
            case 0:
                return "teacup";
            case 1:
                return "vase";
            case 2:
                return "toothbrush";
            case 3:
                return "EXTREME DELUXE-100 MEGA-WATT LOTION CREAM";
        }
        return ";";
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean newFound) {
        found = newFound;
    }
}
