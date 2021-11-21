package Lock;

public class Counter {
    int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    int increase(){
        return ++count;
    }
}
