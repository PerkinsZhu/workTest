package com.zpj.actor;

import akka.actor.*;

/**
 * Created by PerkinsZhu on 2017/9/21 18:05.
 */


class Swapper extends AbstractLoggingActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder().matchEquals("Hello", s -> {
            log().info("Hi");
            getContext().become(receiveBuilder().
                    matchEquals("Hellos", x -> {
                        log().info("Ho");
                        getContext().unbecome(); // resets the latest 'become' (just for fun)
                    }).build(), false); // push on top instead of replace
        }).build();
    }
}

public class SwapperApp {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("SwapperSystem");
        ActorRef swapper = system.actorOf(Props.create(Swapper.class), "swapper");
        swapper.tell("Hello", ActorRef.noSender()); // logs Hi
        swapper.tell("Hello", ActorRef.noSender()); // logs Ho
        swapper.tell("Hello", ActorRef.noSender()); // logs Hi
        swapper.tell("Hello", ActorRef.noSender()); // logs Ho
        swapper.tell("Hello", ActorRef.noSender()); // logs Hi
        swapper.tell("Hello", ActorRef.noSender()); // logs Ho
        system.terminate();
    }
}