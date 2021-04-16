
- [Concept](#concept)
  - [Daemon](#daemon)
  - [Flynn's Taxonomy](#flynns-taxonomy)
  - [Process vs Thread](#process-vs-thread)
  - [Concurrency vs Parallelism](#concurrency-vs-parallelism)
  - [Mutex](#mutex)
  - [Liveness](#liveness)
- [Reference](#reference)


This repo is basically for Concurrency learning in Java.

## Concept

Phases of life cycle of a thread:
- new
- runnable
- blocked
- terminated
- WAITING (JAVA)
- TIMED_WAITING (JAVA)


### Daemon

Daemon thread is also considered as background thread. A daemon thread is detached from the main thread, which means it does not prevent the process from terminating.

### Flynn's Taxonomy

Instruction Streams & Data Streams:
- SISD: Single Instruction, Single Data
- SIMD: Single Instruction, Multiple Data
- MISD: Multiple Instruction, Single Data (not practical)
- MIMD: Multiple Instruction, Multiple Data
    - SPMD: Single Program, Multiple Data
    - MPMD: Multiple Program, Multiple Data

### Process vs Thread

| Process | Thread |
| --- | --- |
| Includes code, data and state information | Independent path of execution |
| Independent instance of a running program | Subset of a process |
| Separate address space | Operating system schedules threads for execution |

### Concurrency vs Parallelism

Concurrency is the ability of a program to be broken into parts that can run independently of each other. It's not actually simultaneous, but it worked like simultaneously for the reason that memory runs faster than disk.

| Concurrency | Parallelism |
| --- | --- |
| Program Structure | Simultaneous Execution |
| DEALING with multiple things at once | DOING multiple things at once |
| Use case: I/O dependent tasks, like graphical user interfaces | Use case: Computationally intensive tasks, like matrix multiplication |

### Mutex

Mutex (Mutual Exclusion), which is instituted for the purpose of preventing race conditions, is a property of concurrency control where lock is needed to come into play.

Lock: 
- Mechanism to implement mutual exclusion
- Only one thread or process can possess at a time
- Limit access to critical section
- Synchronized: Easier to implement and prevents many pitfalls of locks
- Locks: Provide more flexibility to implement certain algorithms

Deadlock: All processes and threads are unable to continue executing.

Reentrant Mutex:
- Can be locked multiple times by the same thread
- Must be unlocked as many times as it was locked
- Synonymous terms: 
    - Reentrant Lock
    - Recursive Mutex
    - Recursive Lock

### Liveness

Deadlock: Each member is waiting for another memeber to take action.

Technique to prevent Deadlock:
- __Lock Ordeing__: ensure locks are always taken in the same order by any thread
- __Lock Timeout__: 
  - put a timeout on lock attempts
  - if a thread fail to acquire all locks within the time limit:
    - back up and free all Locks
    - wait for a random amount of time then try again

Abandoned lock: If one thread or process acquires a lock, and then terminate because of some unexpected reason, it may not automatically release the lock before it disappears, which leaves other threads stuck waiting for a lock that would never be released.

Starvation: A process or thread is perpetually denied the resources it needs.

Livelock: Multiple threads or processes are actively responding to each other to resolve conflict, but that prevents them from making progress.




## Reference

- Parallel and Concurrent Programming with Java 1: https://www.linkedin.com/learning/parallel-and-concurrent-programming-with-java-1/
- A Clarified String Formatting Cheatsheet: https://dzone.com/articles/how-to-format-a-string-clarified
