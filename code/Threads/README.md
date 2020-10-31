
In Java, there are two primary ways to run additional threads: 
- Extending Thread class
- Implementing Runnable interface

Either way has its advantages:

| Extends `Thread` | Implements `Runnable` |
| --- | --- |
| Cannot extend additional classes | Can implement other interfaces and extend another class |
| Each instance is a separate object | Multiple threads can share a single Runnable object |

Check __ThreadDemo__ and __RunnableDemo__ to see the difference.


When it comes to Daemon Thread in Java:
- The main thread is a normal non-daemon thread
- Threads are created as non-daemon by default
- A new thread will inherit daemon status from its parent
- Use `setDaemon()` method to change status before starting thread
- When the JVM halts, any remaining daemon threads are abandoned

For __DaemonThreadDemo__, the program wouldn't finish if `cleaner.setDaemon(true);` is not presented.
