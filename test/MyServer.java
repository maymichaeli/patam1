package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    int port;
    ClientHandler ch;
    private volatile boolean running; // כדי לאפשר עצירה בטוחה של השרת


	public MyServer(int port, ClientHandler ch)
    {
        this.port=port;
        this.ch=ch;
        this.running = false;
    }

    public void start() {
        running = true;
        new Thread(() -> {
            // ServerSocket הוא המחלקה שמנהלת את הצד של השרת בתקשורת רשת מבוססת TCP
            // . היא מאזינה לחיבורים נכנסים ויוצרת Socket חדש עבור כל חיבור כזה, שמאפשר תקשורת ישירה עם הלקוח.
            try (ServerSocket serverSocket = new ServerSocket(port)) { // פתיחת השרת והמתנה לחיבור לקוחות
                while (running) {
                    try {
                        Socket clientSocket = serverSocket.accept(); // השרת ממתין לחיבור של לקוח
                        ch.handleClient(clientSocket.getInputStream(), clientSocket.getOutputStream()); // טיפול בלקוח
                        clientSocket.close(); // סגירת ה-Socket של הלקוח לאחר הטיפול בו
                    } catch (IOException e) {
                        if (running) {
                            e.printStackTrace(); // טיפול בשגיאה רק אם השרת עדיין רץ
                        }
                    }

                    // המתנה של שנייה לפני ניסיון החיבור הבא
                    try {
                        Thread.sleep(1000); // המתנה של שנייה
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // השבתת הת'רד במקרה של הפרעה
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start(); // הפעלת השרת ברקע
    }
    public void close() {
        running = false; // עצירת השרת
        ch.close(); // סגירת ה- streams של ה- ClientHandler
    }
}
