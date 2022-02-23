public class State {
    private int[] p1Houses;
    private int[] p2Houses;
    private int p1Store;
    private int p2Store;

    public State(int[] p1H, int[] p2H, int p1S, int p2S){
        p1Houses = p1H;
        p2Houses = p2H;
        p1Store = p1S;
        p2Store = p2S;
    }

    public int[] getP1Houses(){
        return p1Houses;
    }
    public int[] getP2Houses(){
        return p2Houses;
    }
    public int getP1Store(){
        return p1Store;
    }

    public int getP2Store() {
        return p2Store;
    }
}
