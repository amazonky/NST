package com.example.NST.converter;

public interface DTOEntityConverter<T, E> {
    T toDto(E e);
    E toEntity(T t);
}
