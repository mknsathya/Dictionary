Approach

The first step was to get a list of words with synonyms, antonyms and meaning.

However finding such file proved difficult. Finally I was able to get such a file which was close to what I was looking for from

http://www.gutenberg.org/files/51155/51155-0.txt

This file could be reasonably parsed. It had KEY,SYN and ANT words to differentiate.

Since our intention was to run the dictionary as a stand alone application, I had chosen Derby as a simple light weight DB for my application. There were other databases like hsql etc but to keep it simple, I had chosen Derby.

The next task was to create a database in Derby and populate with the words , synonyms and antonyms. I wrote a simple file program, which read the downloaded text file and inserted the records into the derby database. 

Derby has been used like an embedded database rather than a client server application.

The main reason for this is simple - if required the same can be used for mobile applications 

Coming to the Java application, it has three main purpose.

1. Connect to the dictionaryDB
2. Run the application in interactive mode, reading each word from the standard input. 
3., As long as the user does not enter "STOP" the application would continue accepting the words
4. Fetch the synonyms and antonyms for the user input word. If the word was not found display a message that word was not found
5. When the user enters stop, shut down the database gracefully

Limitations

1. The number of words in the dictionary is limited. If we can find a better dictionary with all the entries we can have more entries
2. The usage of a word which was asked in the assignment was not done (as it was not available)
3. Currently only one application can be run at a time. This is due to the reason that we are not using derby in client / server mode

Improvements and suggestions that can be done

1. Auto suggest (as a nice to have suggested by Vasanth)
2. Currently reads/ writes only from console. Can be enhanced and refactored to write to for example file
3. Can be enhanced as a restful service that can be consumed