package com.mygaienko.reactive.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.core.publisher.UnicastProcessor;
import reactor.core.scheduler.Schedulers;

import static reactor.core.publisher.FluxSink.OverflowStrategy.BUFFER;

@Slf4j
class CarServiceTest {

    @Test
    void testJust() {
        Flux.just(1, 2, 3)
                .subscribe(value -> log.info("Received value: {}", value));
    }

    @Test
    void testJust_switchIfEmpty() {
        Flux.empty()
                .switchIfEmpty(Mono.just(-1))
                .subscribe(value -> log.info("Received value: {}", value));
    }

    @Test
    void testJust_flatMap() {
        Flux.just(1, 2, 3)
                .flatMap(value -> Flux.just(value, value * 2))
                .filter(value ->  value % 2 == 0)
                .distinct()
                .subscribe(value -> log.info("Received value: {}", value));
    }

    @Test
    void testJustWithTake() {
        Flux.just(1, 2, 3)
                .take(1)
                .subscribe(value -> log.info("Received value: {}", value));
    }


    // publishOn vs subscribeOn
    @Test
    void testJustWithTake_subscribeOnParallelScheduler() {
        Flux.just(1, 2, 3, 4, 5, 6)
                .subscribeOn(Schedulers.newParallel("subscribe-parallel-scheduler", 4))
                .take(3)
                .subscribe(value -> log.info("Received value: {}", value));
    }

    @Test
    void testJustWithTake_publishOnParallelScheduler() {
        Flux.just(1, 2, 3, 4, 5, 6)
                .log()
                .publishOn(Schedulers.newParallel("publish-parallel-scheduler", 4))
                .take(3)
                .subscribe(value -> log.info("Received value: {}", value));
    }

    @Test
    void testJustWithTake_subscribeOnParallelScheduler_publishOnParallelScheduler() {
        Flux.just(1, 2, 3, 4, 5, 6)
//                .log()
                .subscribeOn(Schedulers.newParallel("subscribe-parallel-scheduler", 4))
                .log()
                .take(3)
                .publishOn(Schedulers.newParallel("publish-parallel-scheduler", 4))
                .subscribe(value -> log.info("Received value: {}", value));
    }

    @Test
    void testJustWithTake_subscribeOnParallelScheduler_publishOnParallelScheduler_publishOnParallelScheduler() {
        Flux.just(1, 2, 3, 4, 5, 6)
//                .log()
                .subscribeOn(Schedulers.newParallel("subscribe-parallel-scheduler", 4))
                .doOnNext(value -> log.info("Received value on 1st doOnNext: {}", value))
                .publishOn(Schedulers.newParallel("publish-parallel-scheduler", 4))
                .doOnNext(value -> log.info("Received value on 2nd doOnNext: {}", value))
                .publishOn(Schedulers.newParallel("2nd-publish-parallel-scheduler", 4))
                .subscribe(value -> log.info("Finally received value: {}", value));
    }

    // ERROR HANDLING
    @Test
    void testJustWith_onErrorReturn() {
        Flux.just(1, 2, 3, 4, 5, 6)
                .map(value -> value < 5 ? value : value/0)
                .onErrorReturn(-1)
                .subscribe(value -> log.info("Received value: {}", value));
    }

    @Test
    void testJustWith_onErrorResume() {
        Flux.just(1, 2, 3, 4, 5, 6)
                .map(value -> value < 5 ? value : value/0)
                .onErrorResume(error -> Mono.just(-1))
                .subscribe(value -> log.info("Received value: {}", value));
    }

    @Test
    void testJustWith_onErrorMap() {
        Flux.just(1, 2, 3, 4, 5, 6)
                .map(value -> value < 5 ? value : value/0)
                .onErrorMap(error -> new RuntimeException("Wrapped exception", error))
                .subscribe(value -> log.info("Received value: {}", value));
    }

    @Test
    void testJustWith_doOnError() {
        Flux.just(1, 2, 3, 4, 5, 6)
                .map(value -> value < 5 ? value : value/0)
                .doOnError(error -> log.error("Got exception", error))
                .subscribe(value -> log.info("Received value: {}", value));
    }

    // Processor
    @Test
    void testUnicastProcessor() {
        UnicastProcessor<Integer> processor = UnicastProcessor.create();

        FluxSink<Integer> sink = processor.sink(BUFFER);
        sink.next(1);
        sink.next(2);

        processor.subscribe(value -> log.info("Received value: {}", value));
    }

}