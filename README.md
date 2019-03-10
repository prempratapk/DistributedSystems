# DistributedSystems
Step 1: Writing a Content Provider
Your first task is to write a content provider. This provider should be used to store all messages, but the abstraction it provides should be a general key-value table. Before you start, please read the following to understand the basics of a content provider: http://developer.android.com/guide/topics/providers/content-providers.html

Typically, a content provider supports basic SQL statements. However, you do not need to do it for this course. You will use a content provider as a table storage that stores (key, value) pairs.

The following are the requirements for your provider:
You should not set any permission to access your provider. This is very important since if you set a permission to access your content provider, then our testing program cannot test your app. The current template takes care of this; so as long as you do not change the template, you will be fine.
Your provider’s URI should be “content://edu.buffalo.cse.cse486586.groupmessenger1.provider”, which means that any app should be able to access your provider using that URI. To simplify your implementation, your provider does not need to match/support any other URI pattern. This is already declared in the project template’s AndroidManifest.xml.
Your provider should have two columns.
The first column should be named as “key” (an all lowercase string without the quotation marks). This column is used to store all keys.
The second column should be named as “value” (an all lowercase string without the quotation marks). This column is used to store all values.
All keys and values that your provider stores should use the string data type.
Your provider should only implement insert() and query(). All other operations are not necessary.
Since the column names are “key” and “value”, any app should be able to insert a <key, value> pair as in the following example:

ContentValues keyValueToInsert = new ContentValues();

// inserting <”key-to-insert”, “value-to-insert”>
keyValueToInsert.put(“key”, “key-to-insert”);
keyValueToInsert.put(“value”, “value-to-insert”);

Uri newUri = getContentResolver().insert(
    providerUri,    // assume we already created a Uri object with our provider URI
    keyValueToInsert
);

If there’s a new value inserted using an existing key, you need to keep only the most recent value. You should not preserve the history of values under the same key.
Similarly, any app should be able to read a <key, value> pair from your provider with query(). Since your provider is a simple <key, value> table, we are not going to follow the Android convention; your provider should be able to answer queries as in the following example:

Cursor resultCursor = getContentResolver().query(
    providerUri,    // assume we already created a Uri object with our provider URI
    null,                // no need to support the projection parameter
    “key-to-read”,    // we provide the key directly as the selection parameter
    null,                // no need to support the selectionArgs parameter
    null                 // no need to support the sortOrder parameter
);

Thus, your query() implementation should read the selection parameter and use it as the key to retrieve from your table. In turn, the Cursor returned by your query() implementation should include only one row with two columns using your provider’s column names, i.e., “key” and “value”. You probably want to use android.database.MatrixCursor instead of implementing your own Cursor.
Your provider should store the <key, value> pairs using one of the data storage options. The details of possible data storage options are in http://developer.android.com/guide/topics/data/data-storage.html. You can choose any option; however, the easiest way to do this is probably use the internal storage with the key as the file name and the value stored in the file.
After implementing your provider, you can verify whether or not you are meeting the requirements by clicking “PTest” provided in the template. You can take a look at OnPTestClickListener.java to see what tests it does.
If your provider does not pass PTest, there will be no point for this portion of the assignment.
Step 2: Implementing Multicast
The final step is implementing multicast, i.e., sending messages to multiple AVDs. The requirements are the following.
Your app should multicast every user-entered message to all app instances (including the one that is sending the message). In the rest of the description, “multicast” always means sending a message to all app instances.
Your app should be able to send/receive multiple times.
Your app should be able to handle concurrent messages.
As with PA1, we have fixed the ports & sockets.
Your app should open one server socket that listens on 10000.
You need to use run_avd.py and set_redir.py to set up the testing environment.
python run_avd.py 5
python set_redir.py 10000
The grading will use 5 AVDs. The redirection ports are 11108, 11112, 11116, 11120, and 11124.
You should just hard-code the above 5 ports and use them to set up connections.
Please use the code snippet provided in PA1 on how to determine your local AVD.
emulator-5554: “5554”
emulator-5556: “5556”
emulator-5558: “5558”
emulator-5560: “5560”
emulator-5562: “5562”
Your app needs to assign a sequence number to each message it receives. The sequence number should start from 0 and increase by 1 for each message.
Each message and its sequence number should be stored as a <key, value> pair in your content provider. The key should be the sequence number for the message (as a string); the value should be the actual message (again, as a string).
All app instances should store every message and its sequence number individually.
For your debugging purposes, you can display all the messages on the screen. However, there is no grading component for this.
Please read the notes at the end of this document. You might run into certain problems, and the notes might give you some ideas about a couple of potential problems
