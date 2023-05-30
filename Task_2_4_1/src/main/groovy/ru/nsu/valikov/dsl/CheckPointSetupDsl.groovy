package ru.nsu.valikov.dsl

import ru.nsu.valikov.models.CheckPoint

import static groovy.lang.Closure.DELEGATE_ONLY

class CheckPointSetupDsl {
    public static final Map<String, CheckPoint> checkpointMap = new HashMap<>();

    static void checkpoints(@DelegatesTo(value = CheckPointsDsl, strategy = DELEGATE_ONLY) Closure closure) {
        var checkPointsDsl = new CheckPointsDsl()
        closure.delegate = checkPointsDsl
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }

    static class CheckPointsDsl {

        static void checkpoint(String date, @DelegatesTo(value = CheckPoint, strategy = DELEGATE_ONLY) Closure closure) {
            var checkpoint = new CheckPoint(date)
            closure.delegate = checkpoint
            closure.resolveStrategy = DELEGATE_ONLY
            closure.call()
            checkpointMap.put(checkpoint.name, checkpoint)
            println checkpointMap
        }
    }
}
