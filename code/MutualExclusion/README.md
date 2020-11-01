
The __Demo__ is the main class to run the examples.

- __ThreadInDataRace__: Displays how the data race happens. The counter value could be outdated when read since other instance could update it anytime, which leads to the final incorrect value.
- __ThreadWithLock__: The lock version, which utilize a reentrant lock to make sure the counter can only be accessed by a thread at a time.
- __ThreadWithAtomicVariable__: Use an atomic class to make the variable thread-safe, which means only a thread can access it at a time.
- __ThreadInSynchronizedMethod__: Apply `synchronized` to function level, in which case only a thread can invoke the function to increment the counter at a time.
- __ThreadInSynchronizedStatement__: Apply `synchronized` to object level, in which case the class object can be accessed by only a thread at a time.

