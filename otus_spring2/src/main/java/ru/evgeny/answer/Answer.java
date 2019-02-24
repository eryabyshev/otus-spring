package ru.evgeny.answer;

public abstract class Answer<T> {
    String name;
    T point;


    public Answer(String name, T  point) {
        this.name = name;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public T getPoint() {
        return point;
    }







}
