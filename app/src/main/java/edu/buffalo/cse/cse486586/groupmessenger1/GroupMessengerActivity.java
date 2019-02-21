package edu.buffalo.cse.cse486586.groupmessenger1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * GroupMessengerActivity is the main Activity for the assignment.
 * 
 * @author stevko
 *
 */
public class GroupMessengerActivity extends Activity {
    static final String TAG = GroupMessengerActivity.class.getSimpleName();
    static final String[] ports=new String[]{"11108","11112","11116","11120","11124"};
    static final int SERVER_PORT = 10000;
    static int counter=0;
    private static final String KEY_FIELD = "key";
    private static final String VALUE_FIELD = "value";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_messenger);

        /*
         * TODO: Use the TextView to display your messages. Though there is no grading component
         * on how you display the messages, if you implement it, it'll make your debugging easier.
         */
        TextView tv = (TextView) findViewById(R.id.textView1);
        tv.setMovementMethod(new ScrollingMovementMethod());
        
        /*
         * Registers OnPTestClickListener for "button1" in the layout, which is the "PTest" button.
         * OnPTestClickListener demonstrates how to access a ContentProvider.
         */
        findViewById(R.id.button1).setOnClickListener(
                new OnPTestClickListener(tv, getContentResolver()));
        
        /*
         * TODO: You need to register and implement an OnClickListener for the "Send" button.
         * In your implementation you need to get the message from the input box (EditText)
         * and send it to other AVDs.
         */
        TelephonyManager tel = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        String portStr = tel.getLine1Number().substring(tel.getLine1Number().length() - 4);
        final String myPort = String.valueOf((Integer.parseInt(portStr) * 2));
        try{
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            new ServerTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, serverSocket);
        }catch(IOException e){
            Log.e(TAG, "Can't create a ServerSocket");
            return;
        }
        //Code from PA1
        final EditText editText = (EditText) findViewById(R.id.editText1);
        findViewById(R.id.button4).setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        String msg = editText.getText().toString() + "\n";
                        editText.setText("");
                        new ClientTask().executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, msg, myPort);
                    }
                });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_group_messenger, menu);
        return true;
    }

     //Code from PA1
    private class ClientTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... msgs) {
            try {
               for(int i=0;i< 5;i++){
                   Socket socket = new Socket(InetAddress.getByAddress(new byte[]{10, 0, 2, 2}),
                           Integer.parseInt(ports[i]));

                   String msgToSend = msgs[0];
                   OutputStreamWriter opw = new OutputStreamWriter(socket.getOutputStream());
                   BufferedWriter bw = new BufferedWriter(opw);
                   bw.write(msgToSend);
                   bw.flush();

                   InputStreamReader ipr = new InputStreamReader(socket.getInputStream());
                   BufferedReader br = new BufferedReader(ipr);
                   String str = br.readLine();
                   ipr.close();
                   if (str.equals("Published")) {
                       Log.e(TAG, "ClientTask Received"+ports[i]);
                       socket.close();
                   }
               }
                /* TODO: Fill in your client code that sends out a message.
                 */
                //  socket.close();
            } catch (UnknownHostException e) {
                Log.e(TAG, "ClientTask UnknownHostException");
            } catch (IOException e) {
                Log.e(TAG, "ClientTask socket IOException");
                e.printStackTrace();
            }

            return null;
        }
    }

    private class ServerTask extends AsyncTask<ServerSocket, String, Void> {

        @Override
        protected Void doInBackground(ServerSocket... sockets) {
            ServerSocket serverSocket = sockets[0];
            try {

                while (true) {
                    Socket s = serverSocket.accept();
                    InputStreamReader ipr = new InputStreamReader(s.getInputStream());
                    BufferedReader br = new BufferedReader(ipr);
                    String str=br.readLine();

                    OutputStreamWriter opw=new OutputStreamWriter(s.getOutputStream());
                    BufferedWriter bw=new BufferedWriter(opw);
                    bw.write("Published");
                    bw.flush();
                    opw.close();

                    if(str!=null)
                        publishProgress(str);
                }

            } catch (IOException e) {
                Log.e(TAG, "ServerTask socket accept error");
            }
            /*
             * TODO: Fill in your server code that receives messages and passes them
             * to onProgressUpdate().
             */
            return null;
        }

        protected void onProgressUpdate(String...strings) {
             try {
                String strReceived = strings[0].trim();
                 TextView remoteTextView = (TextView) findViewById(R.id.textView1);
                 remoteTextView.append(strReceived + "\t\n");
                 ContentValues cv= new ContentValues();
                     cv.put(KEY_FIELD, String.valueOf(counter++));
                     cv.put(VALUE_FIELD,strReceived);

                 Uri.Builder uriBuilder = new Uri.Builder();
                 uriBuilder.scheme("content");
                 uriBuilder.authority("edu.buffalo.cse.cse486586.groupmessenger1.provider");
                 getContentResolver().insert(uriBuilder.build(), cv);
            } catch (Exception e) {
                Log.e(TAG, "File write failed");
            }

            return;
        }
    }
}
