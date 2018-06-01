# The RxJava Notes of Mine(RxJava 1)

## Closures

``Function``
 <br>------> ``Action``
<br>------> ``Action1``
     <br>   ----->``Observable.onSubscribe<T>``

### Action and Function Family:
``ActionX``: x indicates the number of params 
ActionX<T...>

``FuncX``: x indicates the number of Ts 
FuncX<T..,R>

``Action1``: the closure only for execute method in side it the only. -> ``Observable.OnSubscribe<T>``

``Observer``  -> ``Subscriber``:


``Func1<? super T,? extends Observable<? extends R>>`
the super class T, and whatever extends R
:return R.

````
Observable.create(Observale.OnSubscribe->{

})
````

This method is considered unsafe and deprecated after the version 1.2.7

## Subscription and Subcriber and Observer

Subsriber implements the Observer and Subscription both

``public abstract class Subscriber<T> implements Observer<T>, Subscription``



``subcribe()`` observable.subcribe(subcriber)
``subcription`` Subscription subscription = Observable.subscribe(subscriber);
                subscription.unsubscribe();
 
> when the same Observable is subcribded many times, the code inside onSubsribed() will perform many times.

use can avoid the  senerio using cached() operator.


## Observable

### Cold! and Hot!

cold observables

> The logic inside the cold Observable will remain uninvoked only when someone subscribes to it.

hot observables
> The hot observables push events downstream even if no one listens to them.


The hot observables are  just like radio station, all the subscribers will hear the same song at the moment, the songs will be playing even though the subscribers unsubscribe.


## onError() onComplete() onNext()

## Scheduler

### subcribeOn() and observeOn()

> The subcribeOn() method is as a matter of fact, position-indefferent, but the observeOn() method is poisition-sensitive!

> It's recommened that the subcribeOn() should be placed close to the Producer.


subscribeOn(): The onSubcribed() of Observable is invoked by subscribe(), only when subcribed, the code in the create() method will be performed. 

observeOn(): the code below that uses the thread(Schedulers ) supplied in the observeOn().


````
Observable
.just("something1","something2","something3""something4")
    .flatMap(prod ->{
       return createObservable.subscribeOn(SchedulerA); 
    })
    .subscribe();
````
coding like this will use the thread pool in a more effective way.





### subscribe() and Observable

- the Observable.create() will block subcribe()
- the closest subcribe() to the Observable wins
- the whole chain of RxJava will run on the same thread by default which is assigned by the subcribeOn()

### BackPressure

What is BackPressure:

> BackPressure is kind of mechanism that allow the termial Subcriber and intermidiate operator to request a certain number of events from the producer.  

There are two ways to solve the backpressure problem

- sampling or buffering events from the stream using built-in operators like sample(),throttleFirst(),throttleLast(), buffer()

````
Observable
    .range(1,7)
    .buffer(3)
    .subscribe(List<Integer> list - >{

    });
````
the buffer operator takes the upstream event into a list, when size of the list reachs 3, the later events will be pushed downstream.

- subcribers propagate their demand and request to the observables 


## Talk about backPressure
This is the senerio: in a restaurant, dished being washed faster than waiter can handle. A Dish class was created to describe the situation.

Think about the following code:
````
Observable.range(1,1000)
    .map(Dish::new)
    .subscribe(x->{})
````

Will the dishes piled up and cause the OutOfMemory Exception? In fact, even though an observeOn() operator was added, the Exception will not be thrown out!

RxJava helps the user implement backPressure in the background.

### To implement BackPressure

The Subcriber normally receives the events from Observable passive, but the pattern can be changed to Reactive Pull
````
Observable observable=Observable.range(1,100000); class MySubscriber extends Subscriber<T> { 
    @Override 
    public void onStart() { 
        request(1); 
    } 
    @Override 
    public void onNext(T n) {  
         request(1);
     } 
}

````

## Error Handing & Memory Leaks Hanndling