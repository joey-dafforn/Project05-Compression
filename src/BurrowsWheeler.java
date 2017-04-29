/* Created by jdafforn on 4/27/17*/
public class BurrowsWheeler {
    // apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
    public static void encode()
    {
        String s = BinaryStdIn.readString();
        CircularSuffixArray myCsa = new CircularSuffixArray(s);
        int first = 0;
        while (first < myCsa.length() && myCsa.index(first) != 0) {
            first++;
        }
        BinaryStdOut.write(first);
        int i = 0;
        while (i < myCsa.length()) {
            BinaryStdOut.write(s.charAt((myCsa.index(i) + s.length() - 1) % s.length()));
            i++;
        }
        BinaryStdOut.close();
    }
    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode() {
        int first = BinaryStdIn.readInt();
        String t = BinaryStdIn.readString();
        int n = t.length();
        int[] count = new int[256 + 1];
        int[] next = new int[n];
        int i = 0;
        while (i < n) {
            count[t.charAt(i) + 1]++;
            i++;
        }
        i = 1;
        while (i < 256 + 1) {
            count[i] += count[i - 1];
            i++;
        }
        i = 0;
        while (i < n) {
            next[count[t.charAt(i)]++] = i;
            i++;
        }
        i = next[first];
        int c = 0;
        while (c < n){
            BinaryStdOut.write(t.charAt(i));
            i = next[i];
            c++;
        }
        BinaryStdOut.close();
    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args)
    {
        switch (args[0]) {
            case "-":
                encode();
                break;
            case "+":
                decode();
                break;
            default:
                System.out.println("Invalid arguments, needs to be a '-' or '+'");
                break;
        }
    }
}