public class State {
    private int[] data;

    public State(int[] array){
        //0: player store
        //1-6: player houses
        //7: agent store
        //8-14: agent houses
        data = array;
    }
    public int[] getData(){
        return data;
    }
}
