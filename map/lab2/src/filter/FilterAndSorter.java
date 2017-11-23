package filter;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterAndSorter {
    public static <E> List<E> filterAndSorter(List<E> list, Predicate<E> cond, Comparator<E> comp) {
        return list.stream()
                .filter(cond)
                .sorted(comp)
                .collect(Collectors.toList());
    }
}
