// // package fileSharing;

// // import java.io.*;
// // import java.net.*;

// // public class FileSharingServer {
// //     public static void main(String[] args) throws IOException {
       
// //     	ServerSocket soc = new ServerSocket(12345);
// //     	System.out.println("Server started...");
// //     	Socket sock = soc.accept();
// //     	System.out.println("Client connected...");
    	
// //     	DataInputStream dis = new DataInputStream(sock.getInputStream());
// //     	String fileName = dis.readUTF();
// //     	long fileSize = dis.readLong();
    	
// //     	FileOutputStream fos = new FileOutputStream(fileName);
// //     	byte[] buffer = new byte[4096];
// //     	int read;
// //     	long remaining = fileSize;
    	
// //     	while((read = dis.read(buffer,0,Math.min(buffer.length, (int) remaining))) > 0) {
// //     		fos.write(buffer,0,read);
// //     		remaining -= read;
// //     	}
    	
// //     	fos.close();
// //     	System.out.println("File received: " + fileName);
    	
// //     }
// // }


// // part 2 -----------------------

// import java.io.*;
// import java.net.*;

// public class FileSharingServer {
//     public static void main(String[] args) throws IOException {
       
//         ServerSocket soc = new ServerSocket(12345);
//         System.out.println("Server started...");
//         Socket sock = soc.accept();
//         System.out.println("Client connected...");
        
//         DataInputStream dis = new DataInputStream(sock.getInputStream());
//         String fileName = dis.readUTF();
//         long fileSize = dis.readLong();
        
//         FileOutputStream fos = new FileOutputStream(fileName);
//         byte[] buffer = new byte[4096];
//         int read;
//         long remaining = fileSize;
        
//         while ((read = dis.read(buffer, 0, Math.min(buffer.length, (int) remaining))) > 0) {
//             fos.write(buffer, 0, read);
//             remaining -= read;
//         }
        
//         fos.close();
//         System.out.println("File received: " + fileName);
        
//         // Server's exit loop
//         BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//         while (true) {
//             System.out.println("Enter 'exit' to stop the server: ");
//             String input = reader.readLine();
//             if (input.equalsIgnoreCase("exit")) {
//                 System.out.println("Server is exiting...");
//                 break; // Exit the loop and close the server
//             }
//         }
        
//         soc.close();
//         sock.close();
//         dis.close();
//     }
// }

import java.io.*;
import java.net.*;

public class FileSharingServer {
    private static ServerSocket dis;

	public static void main(String[] args) throws IOException {
       
        ServerSocket soc = new ServerSocket(12345);
        System.out.println("Server started...");
        Socket sock = soc.accept();
        System.out.println("Client connected...");
        
        try {
            DataInputStream dis = new DataInputStream(sock.getInputStream());
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("Enter 'exit' to stop the server, or 'receive' to receive files: ");
                String input = reader.readLine();
                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Server is exiting...");
                    break; // Exit the loop and close the server
                } else if (input.equalsIgnoreCase("receive")) {
                    receiveFile(dis);
                }
            }
        } finally {
            soc.close();
            sock.close();
            dis.close();
        }
    }
    
    private static void receiveFile(DataInputStream dis) throws IOException {
        String fileName = dis.readUTF();
        long fileSize = dis.readLong();
        
        FileOutputStream fos = new FileOutputStream(fileName);
        byte[] buffer = new byte[4096];
        int read;
        long remaining = fileSize;
        
        while ((read = dis.read(buffer, 0, (int) Math.min(buffer.length, remaining))) > 0) {
            fos.write(buffer, 0, read);
            remaining -= read;
        }
        
        fos.close();
        System.out.println("File received: " + fileName);
    }
}