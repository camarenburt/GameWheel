import java.util.ArrayList;

import java.util.Random;
public class GameWheel {
    Random rand = new Random();
    private ArrayList <Slice> slices = new ArrayList<>();
    private int currentPos;
    private ArrayList <Slice> red = new ArrayList<Slice>();
    private ArrayList <Slice> blue = new ArrayList<Slice>();
    private ArrayList <Slice> black = new ArrayList<Slice>();

    public GameWheel(){
        for(int i = 0; i <= 19; i++){
            if(i % 5 == 0){
                slices.add(new Slice("black", i * 1000));
            } else if (i % 2 == 0){
                slices.add(new Slice("blue", i * 100));
            } else{
                slices.add(new Slice("red", i * 200));
            }
        }
    }
    public String toString(){
        String out = "";
        for (int i = 0; i <= 19; i++) {
            out += i + " - " + slices.get(i).toString() + "\n";
        }
        return out;
    }
    public void split(){
        for (int i = 0; i <=19; i++){

            if(i % 5 == 0){
                black.add(slices.get(i));
            } else if(i % 2 == 0){
                blue.add(slices.get(i));
            } else{
                red.add(slices.get(i));
            }
        }
    }
    public void scramble(){
        split();
        for(int i = 0; i <= 19; i++){
            if (i % 5 == 0){
                int temp =rand.nextInt(black.size());
                slices.set(i, black.get(temp));
                black.remove(temp);
            } else if(i % 2 == 0){
                int temp =rand.nextInt(blue.size());
                slices.set(i,blue.get(temp));
                blue.remove(temp);
            } else{
                int temp =rand.nextInt(red.size());
                slices.set(i, red.get(temp));
                red.remove(temp);
            }
        }
    }
    public void sort() {
        split();
        for(int i = 1; i <black.size(); i ++){
            int current = black.get(i).getPrizeAmount();
            int place = i;

            while(place > 0 && current < black.get(place-1).getPrizeAmount()){
                place--;
            }
            black.add(place,black.get(i));
            black.remove(i+1);
        }
        for(int i = 1; i <red.size(); i ++){
            int current = red.get(i).getPrizeAmount();
            int place = i;

            while(place > 0 && current < red.get(place-1).getPrizeAmount()){
                place--;
            }
            red.add(place,red.get(i));
            red.remove(i+1);
        }
        for(int i = 1; i <blue.size(); i ++){
            int current = blue.get(i).getPrizeAmount();
            int place = i;

            while(place > 0 && current < blue.get(place-1).getPrizeAmount()){
                place--;
            }
            blue.add(place,blue.get(i));
            blue.remove(i+1);
        }
        for (int i = 0; i <= 19; i++){
            if (i % 5 == 0){
                slices.set(i,black.get(0));
                black.remove(0);
            } else if(i % 2 == 0){
                slices.set(i,blue.get(0));
                blue.remove(0);
            } else{
                slices.set(i,red.get(0));
                red.remove(0);
            }
        }
    }
    public Slice spinWheel(){
        currentPos = rand.nextInt(19);
        return slices.get(currentPos);
    }
    public Slice getSlice(int i){
        if (i < 20){
            return slices.get(i);
        }
        return slices.get(0);
    }


}