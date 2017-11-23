package filter;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterAndSorter<E> {
    private Comparator<E> comp;
    private Predicate<E> pred;
    private List<E> list;

    public FilterAndSorter(List<E> list, Predicate<E> pred, Comparator<E> comp) {
        this.list = list;
        this.pred = pred;
        this.comp = comp;
    }

    public List<E> doFilter() {
        return list.stream()
                .filter(pred)
                .sorted(comp)
                .collect(Collectors.toList());
    }
}
