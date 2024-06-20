package org.choongang.global.validators;

public interface Validator<T> {
    void check(T form);
}
// Validator를 할 때 다형성을 가지고 작업할 예정
