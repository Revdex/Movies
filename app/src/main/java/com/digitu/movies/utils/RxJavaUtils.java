package com.digitu.movies.utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxJavaUtils {

    public static void sampleObserverSubscribe() {
        CompositeDisposable disposables = new CompositeDisposable();
        final Observable<Integer> observable = Observable.fromArray(1, 2, 3);
        final DisposableObserver<Integer> observer = new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer value) {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
        disposables.add(observer);
        disposables.dispose();
        observable.subscribeOn(Schedulers.io())
                .filter(i -> i > 0)
                .map(i -> i * i)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static void sampleObserverSubscribeWith() {
        CompositeDisposable disposables = new CompositeDisposable();
        disposables.add(Observable.fromArray(1, 2, 3)
                .subscribeOn(Schedulers.io())
                .filter(i -> i > 0)
                .map(i -> i * i)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer value) {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
        disposables.dispose();
    }

    public static void sampleObserverCreate() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
                              @Override
                              public void subscribe(ObservableEmitter<Integer> observableEmitter) {
                                  try {
                                      if (!observableEmitter.isDisposed()) {
                                          for (int i = 1; i < 5; i++) {
                                              observableEmitter.onNext(i);
                                          }
                                          observableEmitter.onComplete();
                                      }
                                  } catch (Exception error) {
                                      observableEmitter.onError(error);
                                  }
                              }
                          }
        );
    }

    public static void executeAfterDelay(long time, Consumer<Long> consumer) {
        if (time > 0 && consumer != null) {
            Observable.timer(time, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(consumer);
        }
    }

    public static void executePeriodic(long time, Consumer<Long> consumer) {
        if (time > 0 && consumer != null) {
            Observable.interval(time, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(consumer);
        }
    }

    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void loadProducts() {
      /*  getProductsObservable()
                .compose(applySchedulers())
                .subscribe(this::dispatchOnSuccess, this::dispatchOnFailure);*/
    }
}
