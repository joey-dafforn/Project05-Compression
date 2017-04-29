
public class MoveToFront {
	// apply move-to-front encoding, reading from standard input and writing to standard output
	public static void encode()
	{
        char[] myChars = new char[256];
        for (int i = 0; i < 256; i++) {
            myChars[i] = (char) i;
        }
        while (!BinaryStdIn.isEmpty()) {
            char ch = BinaryStdIn.readChar();
            char tempIn;
            char count = 0;
            char tempOut = myChars[0];
            while (ch != myChars[count]) {
                tempIn = myChars[count];
                myChars[count] = tempOut;
                tempOut = tempIn;
                count++;
            }
            myChars[count] = tempOut;
            BinaryStdOut.write(count);
            myChars[0] = ch;
        }
        BinaryStdOut.close();
	}

	// apply move-to-front decoding, reading from standard input and writing to standard output
	public static void decode()
	{
        char[] myChars = new char[256];
        for (int i = 0; i < 256; i++) {
            myChars[i] = (char) i;
        }
        while (!BinaryStdIn.isEmpty()) {
            char count = BinaryStdIn.readChar();
            BinaryStdOut.write(myChars[count]);
            char index = myChars[count];
            while (count > 0) {
                myChars[count] = myChars[--count];
            }
            myChars[0] = index;
        }
        BinaryStdOut.close();
	}

	// if args[0] is '-', apply move-to-front encoding
	// if args[0] is '+', apply move-to-front decoding
	public static void main(String[] args)
	{
		if (args[0].equals("-")) {
			encode();
		}
		else if (args[0].equals("+")) {
			decode();
		}
		else {
			System.out.println("Invalid arguments, need a '+' or '-'");
		}
	}
}