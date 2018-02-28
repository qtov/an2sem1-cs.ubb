import java.util.Comparator;

public class Sorted implements Comparable<Sorted>, Comparator<Sorted>
{
    private int num;
    private String text;

    public Sorted(int n, String t) {
        this.num = n;
        text = t;
    }

    public int compareTo(Sorted s) {
        return text.compareTo(s.text);
    }

    public int compare(Sorted s1, Sorted s2) {
        return s1.num - s2.num;
    }

    public String toString() {
        return "" + num;
    }
}
