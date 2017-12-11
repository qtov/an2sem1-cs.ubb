package sample;

import java.util.Comparator;

public class Comp implements Comparator<Articol> {
    @Override
    public int compare(Articol a, Articol b) {
        int c = a.getDomeniu().compareTo(b.getDomeniu());
        if (c == 0) {
            c = a.getTitlu().compareTo(b.getTitlu());
        }
        return c;
    }
}
