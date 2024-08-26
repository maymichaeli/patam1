package test;

import java.util.HashMap;
import java.util.Map;

public class DictionaryManager {
    private static DictionaryManager dm = null;
    private final Map<String, Dictionary> map = new HashMap<>();

    // בנאי פרטי כדי למנוע יצירה של מופע נוסף מחוץ למחלקה
    private DictionaryManager() {}

    // מתודה סטטית להחזרת המופע הסינגלטוני של DictionaryManager
    public static DictionaryManager get() {
        if (dm == null) {
            dm = new DictionaryManager();
        }
        return dm;
    }

    // מתודה לטיפול בשאילתה
    public boolean query(String... args) {
        boolean found = false;
        String word = args[args.length - 1];

        for (int i = 0; i < args.length - 1; i++) {
            String bookName = args[i];
            Dictionary dictionary = map.computeIfAbsent(bookName, Dictionary::new);
            if (dictionary.query(word)) {
                found = true;
            }
        }
        return found;
    }

    // מתודה לטיפול באתגר - ביצוע חיפוש בפועל בכל הספרים
    public boolean challenge(String... args) {
        boolean found = false;
        String word = args[args.length - 1];

        for (int i = 0; i < args.length - 1; i++) {
            String bookName = args[i];
            Dictionary dictionary = map.computeIfAbsent(bookName, Dictionary::new);
            if (dictionary.challenge(word)) {
                found = true;
            }
        }
        return found;
    }

    // מתודה להחזרת גודל המפה (מספר הרשומות במילון)
    public int getSize() {
        return map.size();
    }
}

