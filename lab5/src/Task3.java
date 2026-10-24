import java.io.*;

class CipherWriter extends FilterWriter {
    private char key;

    public CipherWriter(Writer out, char key) {
        super(out);
        this.key = key;
    }

    @Override
    public void write(int c) throws IOException {
        super.write(c + key);
    }
}

class CipherReader extends FilterReader {
    private char key;

    public CipherReader(Reader in, char key) {
        super(in);
        this.key = key;
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        if (c == -1) {
            return -1;
        }
        return c - key;
    }
}

public class Task3 {
    public static void encrypt(String input, String output, char key) throws IOException {
        try (Reader reader = new FileReader(input);
             Writer writer = new CipherWriter(new FileWriter(output), key)) {
            int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
            }
        }
    }

    public static void decrypt(String input, String output, char key) throws IOException {
        try (Reader reader = new CipherReader(new FileReader(input), key);
             Writer writer = new FileWriter(output)) {
            int c;
            while ((c = reader.read()) != -1) {
                writer.write(c);
            }
        }
    }

    public static void main(String[] args) {
        try {
            encrypt("input.txt", "encrypted.txt", 'A');
            decrypt("encrypted.txt", "decrypted.txt", 'A');
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}