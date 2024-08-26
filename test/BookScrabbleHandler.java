package test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class BookScrabbleHandler implements ClientHandler {

    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(inFromClient));
    PrintWriter writer = new PrintWriter(outToClient, true);

    try {
        // קריאת המחרוזת מהלקוח
        String request = reader.readLine();
        if (request == null || request.isEmpty()) {
            writer.println("false");
            return;
        }

        // פיצול המחרוזת לשמות ספרים ולשאילתה
        String[] parts = request.split(",", 2); //מחלק ל2 חלקים, השמות של הקבצים ומה לעשות בכל קובץ
        if (parts.length < 2) {
            writer.println("false");
            return;
        }

        String command = parts[0];
        String rest = parts[1];

        // פיצול החלק הנותר לשמות ספרים ושאילתה
        String[] booksAndQuery = rest.split(",");
        if (booksAndQuery.length < 2) {
            writer.println("false");
            return;
        }

        // שמות הספרים
        String[] bookNames = new String[booksAndQuery.length - 1];
        System.arraycopy(booksAndQuery, 0, bookNames, 0, booksAndQuery.length - 1);

        // השאילתה
        String query = booksAndQuery[booksAndQuery.length - 1];

        boolean result;

        // ביצוע השאילתה או האתגר בהתאם לפקודה
        switch (command) {
            case "Q":
                result = DictionaryManager.get().query(combineArrays(bookNames, query));
                break;
            case "C":
                result = DictionaryManager.get().challenge(combineArrays(bookNames, query));
                break;
            default:
                result = false;
                break;
        }

        // שליחת התשובה ללקוח
        writer.println(result ? "true" : "false");

    } catch (IOException e) {
        writer.println("false");
    } finally {
        // סיום השיחה
        close();
    }
}

// פונקציה לשילוב מערך של ספרים עם השאילתה
private String[] combineArrays(String[] bookNames, String query) {
    String[] combined = new String[bookNames.length + 1];
    System.arraycopy(bookNames, 0, combined, 0, bookNames.length);
    combined[combined.length - 1] = query;
    return combined;
}

    @Override
    public void close() {
        // סגירת כל המשאבים הפתוחים (למשל, סגירת Streams) אם יש צורך
    }
}

