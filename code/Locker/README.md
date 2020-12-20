
In Java, there are various locks for different purposes:
- Reentrant Lock:
    - there maybe times when a program needs to lock a mutex multiple times before unlocking it. 
    - for instance, when the mutex exists in a recursive function.
- ReadWrite Lock:
    - shared read: multiple threads can read at the same time
    - exclusive write: only one thread can write at a time, no other thread can read it either
    - a suitable case for such lock is when the number of reading threads is much more than the number of writing threads

To make the program not to be blocked, apply `tryLock()` instead:
- it's a non-blocking version of the `lock()` method
- it's non-blocking lock/acquire method for mutex
- if the mutex is available, lock it and return TRUE
- if the mutex is not available, immediately return FALSE 
- it enables a thread to execute alternate operations if the lock it needs to acquire is already taken

For __TryLockDemo__, it's easy to see the difference between the normal way to and the way applied `tryLock` 
by comparison.
