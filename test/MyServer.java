package test;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyServer {
    int port;
    boolean stop;
    ClientHandler ch;

    public MyServer(int port, ClientHandler ch){
        this.port=port;
        this.ch=ch;
    }
    public void start(){
        stop=false;
        new Thread(()->startServer()).start();
    }
    private void startServer(){
        ServerSocket server=null;
        Socket client=null;
        try {
            server=new ServerSocket(port);
            server.setSoTimeout(1000);
            while(!stop){
                try{
                    client=server.accept();
                    ch.handleClient(client.getInputStream(), client.getOutputStream());
                    ch.close();
                    client.close();}
                catch (SocketTimeoutException e){}
            }
        } catch (IOException e) {
            //throw new RuntimeException(e);
            e.printStackTrace();
        }
        finally {
            ch.close();
            try {
                server.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                client.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void close(){
        stop();
    }
	public void stop(){
        stop=true;
    }
}

// package test;

// import java.io.IOException;
// import java.net.ServerSocket;
// import java.net.Socket;

// public class MyServer {
//     int port;
//     ClientHandler ch;
//     private volatile boolean running; // כדי לאפשר עצירה בטוחה של השרת


// 	public MyServer(int port, ClientHandler ch)
//     {
//         this.port=port;
//         this.ch=ch;
//         this.running = false;
//     }

//     public void start() {
//         running = true;
//         new Thread(() -> {
//             // ServerSocket הוא המחלקה שמנהלת את הצד של השרת בתקשורת רשת מבוססת TCP
//             // . היא מאזינה לחיבורים נכנסים ויוצרת Socket חדש עבור כל חיבור כזה, שמאפשר תקשורת ישירה עם הלקוח.
//             try (ServerSocket serverSocket = new ServerSocket(port)) { // פתיחת השרת והמתנה לחיבור לקוחות
//                 while (running) {
//                     try {
//                         Socket clientSocket = serverSocket.accept(); // השרת ממתין לחיבור של לקוח
//                         ch.handleClient(clientSocket.getInputStream(), clientSocket.getOutputStream()); // טיפול בלקוח
//                         clientSocket.close(); // סגירת ה-Socket של הלקוח לאחר הטיפול בו
//                     } catch (IOException e) {
//                         if (running) {
//                             e.printStackTrace(); // טיפול בשגיאה רק אם השרת עדיין רץ
//                         }
//                     }

//                     // המתנה של שנייה לפני ניסיון החיבור הבא
//                     try {
//                         Thread.sleep(1000); // המתנה של שנייה
//                     } catch (InterruptedException e) {
//                         Thread.currentThread().interrupt(); // השבתת הת'רד במקרה של הפרעה
//                     }
//                 }
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }).start(); // הפעלת השרת ברקע
//     }
//     public void close() {
//         running = false; // עצירת השרת
//         ch.close(); // סגירת ה- streams של ה- ClientHandler
//     }
// }
