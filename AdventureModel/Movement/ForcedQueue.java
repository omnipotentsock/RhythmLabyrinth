package AdventureModel.Movement;

import AdventureModel.Interactions.Interaction;

import java.io.Serializable;
import java.util.ArrayList;

public class ForcedQueue implements Serializable {

    private ArrayList<Interaction> list = new ArrayList<>();

    private ArrayList<Interaction> cache = new ArrayList<>();

    public ForcedQueue(){
    }

    public void enqueue(Interaction i){
        this.list.add(i);
        this.cache.add(i);
    }
    public Interaction dequeue() {
        Interaction i;
        try {
            i = this.list.remove(0);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exception:" + e);
            return null;
        }
        return i;
    }

    public void refresh(){

        while(!this.list.isEmpty()){
            this.dequeue();
        }

        boolean temp;
        for (Interaction i : this.cache){
            temp = i.getRefreshing();
            if (temp){
                this.list.add(i);
            }
        }
    }
}
