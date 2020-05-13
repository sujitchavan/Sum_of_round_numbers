import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 *
 * @author Sujit Chavan
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        OutputWriter out = new OutputWriter(outputStream);
        TaskA solver = new TaskA();
        solver.solve(1, in, out);
        out.close();
    }

    static class TaskA {
        public void solve(int testNumber, InputReader in, OutputWriter out) {
            int total_test = in.nextInt();
            int count = 0, flag = 0, num = 0, ncount = 0;
            for (int i = 0; i < total_test; i++) {
                flag = 0;
                num = in.nextInt();
                int dnum = num;
                while (dnum != 0) {
                    int ans = dnum % 10;
                    dnum = dnum / 10;
                    if (ans != 0)
                        ncount++;
                }
                System.out.println(ncount);
                ncount = 0;
                while (num != 0) {
                    flag++;
                    int ans = num % 10;
                    num = num / 10;
                    if (ans == 0) {
                        count++;
                        continue;
                    }
                    if (count == 0 && flag == 1)
                        System.out.print(ans + " ");
                    else if (count == 0 && flag != 1) {
                        for (int k = 0; k < flag - 1; k++) {
                            ans = ans * 10;
                        }
                        System.out.print(ans + " ");
                    } else {
                        for (int j = 0; j < flag - 1; j++) {
                            ans = ans * 10;
                        }
                        System.out.print((ans + " "));
                        count = 0;
                    }
                }
                System.out.println();
            }
        }

    }

    static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void close() {
            writer.close();
        }

    }

    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private InputReader.SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1) {
                throw new InputMismatchException();
            }
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0) {
                    return -1;
                }
            }
            return buf[curChar++];
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c)) {
                c = read();
            }
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9') {
                    throw new InputMismatchException();
                }
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            if (filter != null) {
                return filter.isSpaceChar(c);
            }
            return isWhitespace(c);
        }

        public static boolean isWhitespace(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);

        }

    }
}

