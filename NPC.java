public class NPC {
    private String name;

    public NPC (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void interact(String name, int interaction) {
        switch (name) {
            case "God":
                switch (interaction) {
                    default:
                        System.out.println("Error.");
                        break;
                }
                break;
            case "Jolly Man":
                break;
            default:
                System.out.println("Error.");
        }
    }
}
