java-hashMap-X
==============

Simple implementation of a volatile in-memory key-value store backed by Java's HashMap with support for transactions.

LIMITATIONS
1. Single-threaded process
2. Only supports 1 data type (Strings)
3. No recovery
4. Currently only supports commands through a command line interface.

TO USE
Compile and run Main. Supported commands are:

1. SET KEY VALUE - adds/updates the key with the value and returns the previous value (if the key existed) or null if the key didn't exist yey.
2. UNSET KEY - removes the key from the db. Returns the previous value if the key existed or null if the key didn't exist.
3. GET KEY - retrieves the current value of the key or null if the key doesn't exist. 
4. BEGIN - starts a transaction block (can be nested)
5. ROLLBACK - undo-s all commands in the current transaction block.
6. COMMIT - commits all transaction blocks
7. END - exits the session

FUTURE WORK/FEATURES
1. Modify structure so that it can be easily imported to use in applications instead of just CLI
2. Use concurrent data structures to allow for multi-threaded accesses to the data store.
3. Model transactions as threads
4. Add / allow more data types (use Generics)


MAYBE?
1. Persistence (leaning towards a Redis clone instead of memcached)
2. Recovery

