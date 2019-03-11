CSE 486/586 Distributed Systems


Group Messenger with a Local Persistent Key-Value Table 

### Step 1: Writing a Content Provider {#step-1-writing-a-content-provider .western}

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
  
  );
  ------------------------------------------------------------------------------



[Thus, your query() implementation should read the
]{lang="en-US"}[*selection*]{lang="en-US"}[ parameter and use it as the
key to retrieve from your table. In turn, the Cursor returned by your
query() implementation should include only one row with two columns
using your provider’s column names, i.e., “key” and “value”. You
probably want to use android.database.MatrixCursor instead of
implementing your own Cursor.]{lang="en-US"}

### Step 2: Implementing Multicast {#step-2-implementing-multicast .western}

[The final step is implementing multicast, i.e., sending messages to
multiple AVDs.]{lang="en-US"}[ ****** ]{lang="en-US"}[The requirements
are the following.]{lang="en-US"}



#### Testing {#testing .western}


[We have testing programs to help you see how your code does with our
grading criteria. If you find any rough edge with the testing programs,
please report it on Piazza so the teaching staff can fix it. The
instructions are the following:]{lang="en-US"}
 

### Submission {#submission .western}

[We use the CSE submit script. You need to use either
“]{lang="en-US"}[submit\_cse486” or “submit\_cse586”, depending on your
registration status.]{lang="en-US"}[ If you haven’t used it, the
instructions on how to use it is here:
]{lang="en-US"}[[https://wiki.cse.buffalo.edu/services/content/submit-script]{lang="en-US"}](https://wiki.cse.buffalo.edu/services/content/submit-script)


[You need to submit one file described below. ]{lang="en-US"}[Once
again, you must follow everything below exactly. Otherwise, you will get
no point on this assignment.]{lang="en-US"}



### Deadline: 2/22/2017 (Friday) 11:59:59am {#deadline-2222017-friday-115959am .western}

[This is one hour before class. The deadline is firm; if your timestamp
is 12pm, it is a late submission.]{lang="en-US"}

### Grading {#grading .western}

[This assignment is 5% of your final grade. The breakdown for this
assignment is:]{lang="en-US"}


#### Notes {#notes .western}

