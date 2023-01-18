public class NPC {
    private int godTalk;
    private int jollyTalk;

    public void incrTalk(String name) {
        switch (name) {
            case "god":
                godTalk++;
                break;
            case "jolly":
                jollyTalk++;
                break;
            default:
                System.out.println("Error talk 0.");
        }
    }

    public int getTalk(String name) {
        switch (name) {
            case "god":
                return godTalk;
            case "jolly":
                return jollyTalk;
            default:
                System.out.println("Error retrieving talk 0.");
        }
    }
}
