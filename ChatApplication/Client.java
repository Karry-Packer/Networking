import java.net.*;
import java.io.*;

public class Client
 {
    
    Socket socket;
    BufferedReader br;
    PrintWriter out;

   public Client()
    {
        try{
            System.out.println("Request to server");
           socket = new Socket("localhost",7777);

           System.out.println("Connection done.");

           br = new BufferedReader(new InputStreamReader((socket.
             getInputStream())));
       out = new PrintWriter(socket.getOutputStream());

       startReading();
       startWriting();

        }catch(Exception e){

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
                    System.out.println("Server terminated the chat");
            
                    socket.close();
                    break;
                }
                System.out.println("Server: "+msg+ "\n");
               
            
            }
              }catch(Exception e){
                e. printStackTrace();
              }
            };
                  new Thread(r1).start();
            }

            public void startWriting(){ 
              
                //   thread-data user
                Runnable r2 = () ->{
                  
                    try{
                      while(true){
                        
                        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                        String conent = br1.readLine();
                       
                        out.println(conent);
                        out.flush();
                      }
                   }catch(Exception e){
                    e. printStackTrace();
                  }
                };
            
                new Thread(r2).start();
                }
    
    public static void main(String[] args) {
        System.out.println("Connecting.........");

        new Client();

    }
}