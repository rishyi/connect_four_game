package lk.ijse.dep.service;

public class Holder implements Comparable<Holder> {

    private int col;
    private Integer mark;

    public Holder(){}

    public Holder(int col,int mark){
        this.col = col;
        this.mark = mark;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setMark(int mark){
        this.mark = mark;
    }

    public int getMark(){
        return mark;
    }

    @Override
    public String toString() {
        return "Holder{" +
                "col=" + col +
                ", mark=" + mark +
                '}';
    }

    @Override
    public int compareTo(Holder o) {
        return 0;
    }
}
