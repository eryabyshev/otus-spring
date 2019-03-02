package ru.evgeny.otus_spring3.answer;

public abstract class Answer<T> {
    private String name;
    private T point;


    public Answer(String name, T point) {
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
