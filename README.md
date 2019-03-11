CSE 486/586 Distributed Systems


Group Messenger with a Local Persistent Key-Value Table 

### Step 1: Writing a Content Provider

[Your first task is to write a content provider. This provider should be
used to store all messages, but the abstraction it provides should be a
general key-value table. Before you start, please read the following to
understand the basics of a content provider:
]{lang="en-US"}[[http://developer.android.com/guide/topics/providers/content-providers.html]{lang="en-US"}](http://developer.android.com/guide/topics/providers/content-providers.html)



[Typically, a content provider supports basic SQL statements. However,
you do not need to do it for this course. You will use a content
provider as a table storage that stores (key, value)
pairs.]{lang="en-US"}


  ------------------------------------------------------------------------------
  Cursor resultCursor = getContentResolver().query(
  
  providerUri, // assume we already created a Uri object with our provider URI
  
  null, // no need to support the *projection* parameter
  
  “key-to-read”, // we provide the key directly as the *selection* parameter
  
  null, // no need to support the *selectionArgs* parameter
  
  null // no need to support the *sortOrder* parameter
  
 
  ------------------------------------------------------------------------------



[Thus, your query() implementation should read the
]{lang="en-US"}[*selection*]{lang="en-US"}[ parameter and use it as the
key to retrieve from your table. In turn, the Cursor returned by your
query() implementation should include only one row with two columns
using your provider’s column names, i.e., “key” and “value”. You
probably want to use android.database.MatrixCursor instead of
implementing your own Cursor.]{lang="en-US"}

### Step 2: Implementing Multicast

[The final step is implementing multicast, i.e., sending messages to
multiple AVDs.]

#### Notes {#notes .western}

