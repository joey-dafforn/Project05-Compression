/**
 * Created by jdafforn on 4/27/17.
 */
public class CircularSuffixArray {
    private static final int CUTOFF = 15;
    public int[] index;
    public int n;

    public CircularSuffixArray(String s) {
        n = s.length();
        index = new int[n];
        int i = 0;
        while (i < s.length()) {
            index[i] = i;
            i++;
        }
        sort(s, 0, n - 1, 0);
    }

    public void exchange(int i, int j) {
        int tmp = index[i];
        index[i] = index[j];
        index[j] = tmp;
    }

    public int length() {
        return n;
    }

    public void sort(String s, int lo, int hi, int offset) {
        if (hi - lo <= CUTOFF) {
            insertion(s, lo, hi, offset);
            return;
        }
        int lt = lo, gt = hi, piv = charAt(s, index[lo], offset), eq = lo + 1;
        while (eq <= gt) {
            int t = charAt(s, index[eq], offset);
            if (t < piv)
                exchange(lt++, eq++);
            else if (t > piv)
                exchange(eq, gt--);
            else
                eq++;
        }
        sort(s, lo, lt - 1, offset);
        if (piv >= 0)
            sort(s, lt, gt, offset + 1);
        sort(s, gt + 1, hi, offset);
    }

    public int index(int i) {
        return index[i];
    }

    public boolean less(String s, int i, int j, int offset) {
        int oi = index[i], oj = index[j];
        while (offset < n) {
            int ival = charAt(s, oi, offset), jval = charAt(s, oj, offset);
            if (ival < jval)
                return true;
            else if (ival > jval)
                return false;
            offset++;
        }
        return false;
    }

    public void insertion(String s, int lo, int hi, int offset) {
        int i = lo;
        while (i <= hi) {
            int j = i;
            while (j > lo && less(s, j, j - 1, offset)) {
                exchange(j, j - 1);
                j--;
            }
            i++;
        }
    }

    public char charAt(String s, int suffix, int offset) {
        return s.charAt((suffix + offset) % n);
    }
}
