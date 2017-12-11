package sample;

import java.util.Comparator;

public class TComparator implements Comparator<Task> {
    @Override
    public int compare(Task a, Task b) {
        int c;

        c = a.getUser().compareTo(b.getUser());

        if (c == 0) {
            c = a.getStatus().compareTo(b.getStatus());
        }

        return -c;
    }
}
