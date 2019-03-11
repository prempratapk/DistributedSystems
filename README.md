CSE 486/586 Distributed Systems {#cse-486586-distributed-systems .western}
-------------------------------

Programming Assignment 2, Part A {#programming-assignment-2-part-a .western}
--------------------------------

Group Messenger with a Local Persistent Key-Value Table {#group-messenger-with-a-local-persistent-key-value-table .western}
-------------------------------------------------------

\
\
\

### Introduction {#introduction .western}

[The teaching staff hopes you had fun working on PA1! If you got
frustrated, we feel for you and believe us, we were there too. While it
is expected to be frustrated in the beginning, we promise you, it will
get better and you will enjoy more and more as you do it. You might even
start enjoying reading the Android documentation because it \*is\*
actually the single best place to get great information about Android.
We do hope, though, that you now understand a bit more about what it
means to write networked apps on Android.]{lang="en-US"}

\
\
\

[Now back to the assignment: this assignment builds on the previous
simple messenger and points to the next assignment. You will design a
group messenger that can send message to multiple AVDs and store them in
a permanent key-value storage. ]{lang="en-US"}[Once again, please follow
everything exactly. Otherwise, it might result in getting no point for
this assignment.]{lang="en-US"}

\
\
\

[The rest of the description can be long. Please don’t “tl;dr”! Please
read to the end first and get the overall picture. Then please revisit
as you go!]{lang="en-US"}

### Step 0: Importing the project template {#step-0-importing-the-project-template .western}

[Unlike the previous assignment, we will have strict requirements for
the UI as well as a few other components. In order to provide you more
help in meeting these requirements, we have a project template you can
import to Android Studio.]{lang="en-US"}

1.  2.  

<!-- -->

a.  

<!-- -->

3.  4.  5.  

### Step 1: Writing a Content Provider {#step-1-writing-a-content-provider .western}

[Your first task is to write a content provider. This provider should be
used to store all messages, but the abstraction it provides should be a
general key-value table. Before you start, please read the following to
understand the basics of a content provider:
]{lang="en-US"}[[http://developer.android.com/guide/topics/providers/content-providers.html]{lang="en-US"}](http://developer.android.com/guide/topics/providers/content-providers.html)

\
\
\

[Typically, a content provider supports basic SQL statements. However,
you do not need to do it for this course. You will use a content
provider as a table storage that stores (key, value)
pairs.]{lang="en-US"}

\
\
\

[The following are the requirements for your provider:]{lang="en-US"}

6.  7.  8.  

<!-- -->

a.  b.  c.  

<!-- -->

9.  10. 

\
\
\

++
|  |
++

\
\
\

11. 12. 

\
\
\

  ------------------------------------------------------------------------------
  Cursor resultCursor = getContentResolver().query(
  
  providerUri, // assume we already created a Uri object with our provider URI
  
  null, // no need to support the *projection* parameter
  
  “key-to-read”, // we provide the key directly as the *selection* parameter
  
  null, // no need to support the *selectionArgs* parameter
  
  null // no need to support the *sortOrder* parameter
  
  );
  ------------------------------------------------------------------------------

\
\
\

[Thus, your query() implementation should read the
]{lang="en-US"}[*selection*]{lang="en-US"}[ parameter and use it as the
key to retrieve from your table. In turn, the Cursor returned by your
query() implementation should include only one row with two columns
using your provider’s column names, i.e., “key” and “value”. You
probably want to use android.database.MatrixCursor instead of
implementing your own Cursor.]{lang="en-US"}

13. 14. 15. 

### Step 2: Implementing Multicast {#step-2-implementing-multicast .western}

[The final step is implementing multicast, i.e., sending messages to
multiple AVDs.]{lang="en-US"}[ ****** ]{lang="en-US"}[The requirements
are the following.]{lang="en-US"}

16. 17. 18. 19. 

<!-- -->

a.  b.  

<!-- -->

i.  ii. 

<!-- -->

a.  b.  c.  

<!-- -->

i.  ii. iii. iv. v.  

<!-- -->

20. 21. 22. 23. 24. 

#### Testing {#testing .western}

\
\
\

[We have testing programs to help you see how your code does with our
grading criteria. If you find any rough edge with the testing programs,
please report it on Piazza so the teaching staff can fix it. The
instructions are the following:]{lang="en-US"}

25. 

<!-- -->

a.  b.  c.  

<!-- -->

26. 27. 28. 29. 30. 

### Submission {#submission .western}

[We use the CSE submit script. You need to use either
“]{lang="en-US"}[submit\_cse486” or “submit\_cse586”, depending on your
registration status.]{lang="en-US"}[ If you haven’t used it, the
instructions on how to use it is here:
]{lang="en-US"}[[https://wiki.cse.buffalo.edu/services/content/submit-script]{lang="en-US"}](https://wiki.cse.buffalo.edu/services/content/submit-script)

\
\
\

[You need to submit one file described below. ]{lang="en-US"}[Once
again, you must follow everything below exactly. Otherwise, you will get
no point on this assignment.]{lang="en-US"}

-   

m.  n.  o.  

### Deadline: 2/22/2017 (Friday) 11:59:59am {#deadline-2222017-friday-115959am .western}

[This is one hour before class. The deadline is firm; if your timestamp
is 12pm, it is a late submission.]{lang="en-US"}

### Grading {#grading .western}

[This assignment is 5% of your final grade. The breakdown for this
assignment is:]{lang="en-US"}

-   -   

#### Notes {#notes .western}

-   -   

\
\

