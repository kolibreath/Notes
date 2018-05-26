# the RxJava note of Mine

## Observer and Observable

````
Observable.create(subscriber ->{
    subscriber.onNext("A");
    subscriber.onNext("B");

    subscriber.onError();
})
````

creaete() and subcribe() :the create method will block subcribe()

## Subject and Subscription

## Scheduler
``RxJava lets the user take the full control of the concurrency!``
Scheduler.immediate() vs Scheduler.trampoline()
Scheduler,immediate() will not queue inside tasks, the tasks will perform more like  sequentially; while Scheduler.trampoline will queue inside the inner tasks, the outer tasks will perform and complete before the inner tasks start 

The worker is more or less like a thread existing in the thread pool, and the Scheduler is the thread pool.  The tasks on the same worker will perform concurrently, the tasks on different Workers from the same Scheduler will perform concurrently, too. 

````
Scheduler scheduler = Scheduler.immediate();
Scheduler.Worker worker = Scheduler.createWorker();

worker->{}
````

The Scheduler assign the thread which will be working on, and inside the worker.schedule, the task will perform when the Scheduler points.

### Observable and Subscribe()
The subscribe() invokes the onSubcribed() method of Observable, and the create() blocks the subcribe() method

### subcriberOn() and observeOn()
- the subcribeOn() closest to the Observable wins
- the whole chain of trasformation of RxJava will be on the thread assigned by the subscribeOn() before subscribe(). 



