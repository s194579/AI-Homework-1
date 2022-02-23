public class State {
    private int[] humanHouses;
    private int[] agentHouses;
    private int humanStore;
    private int agentStore;

    public State(int[] p1H, int[] p2H, int p1S, int p2S){
        humanHouses = p1H;
        agentHouses = p2H;
        humanStore = p1S;
        agentStore = p2S;
    }

    public int[] getHumanHouses(){
        return humanHouses;
    }
    public int[] getAgentHouses(){
        return agentHouses;
    }
    public int getHumanStore(){
        return humanStore;
    }

    public int getAgentStore() {
        return agentStore;
    }
}
