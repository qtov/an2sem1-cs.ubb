package sample;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Filter<E> {
    private List<E> list;
    private Predicate<E> pred;
    private Comparator<E> comp;

    public Filter(List<E> list, Predicate<E> pred, Comparator<E> comp) {
        this.list = list;
        this.pred = pred;
        this.comp = comp;
    }

    public List<E> doFilter() {
        return this.list.stream().
                filter(pred).
                sorted(comp).
                collect(Collectors.toList());
    }
}
