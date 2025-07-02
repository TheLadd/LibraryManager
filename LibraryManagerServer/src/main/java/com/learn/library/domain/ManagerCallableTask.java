package com.learn.library.domain;

import org.aspectj.weaver.ast.Call;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ManagerCallableTask implements Callable<Object> {
    private final Supplier<?> task;

    private ManagerCallableTask(Supplier<?> task) {
        this.task = task;
    }

    @Override
    public Object call() throws Exception {
        return task.get();
    }
}
