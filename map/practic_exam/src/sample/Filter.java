package sample;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Filter<E> {
    private List<E> list;
    private Predicate<E> predicate;
    private Comparator<E> comparator;

    public Filter(List<E> list, Predicate<E> predicate, Comparator<E> comparator) {
        this.list = list;
        this.predicate = predicate;
        this.comparator = comparator;
    }

    public List<E> doFilter() {
        return this.list.stream().
                filter(predicate).
                sorted(comparator).
                collect(Collectors.toList());
    }
}
