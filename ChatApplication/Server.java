import java.net.*;
import java.io.*;

public class Server{
    
  ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    public Server()
    {
        try{
       server = new ServerSocket(7777);
       System.out.println("Server is ready to accept connection");
       System.out.println("Waiting.....");
       socket = server.accept();

       br = new BufferedReader(new InputStreamReader((socket.
       getInputStream())));
       out = new PrintWriter(socket.getOutputStream());

      startReading();
      startWriting();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
  
    public void startReading(){
    //  thread-read 
    Runnable r1= ()->{
     System.out.println("Reader started...");
   try{

     while(true){
            
        String msg = br.readLine();

        if(msg.equals("exit"))
        {
            System.out.println("Client terminated the chat");
           
            socket.close();
            break;
        }
    
        System.out.println("Client: " + msg + "\n");
        
       }

    }catch(Exception e){
        System.out.println("Error in server Reading");
    }
    };
          new Thread(r1).start();
    }
   
    
    public void startWriting(){
    //   thread-data user

    Runnable r2 = () ->{
        System.out.println("Writer started...");
            
        try{
       while(true){
           
            BufferedReader br1= new BufferedReader(new InputStreamReader(System.in));
            
            String content = br1.readLine();
            
            out.println(content);
            out.flush();     
       }
    
}catch(Exception e){
    System.out.println("Error in server ");
  }
    };

    new Thread(r2).start();
    }
    public static void main(String[] args) {
        System.out.println("Waiting for Client.......");

        new Server();
    }
}