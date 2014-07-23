package ru.sazonovkirill.algorithms.multiplication.domain;

public class MultiplicationResult<T> {
    private final T result;
    private final T overflow;

    public MultiplicationResult(T result, T overflow) {
        this.result = result;
        this.overflow = overflow;
    }

    public T getResult() {
        return result;
    }

    public T getOverflow() {
        return overflow;
    }

    @Override
    public String toString() {
        return "result=" + result +
               ", overflow=" + overflow;
    }
}
