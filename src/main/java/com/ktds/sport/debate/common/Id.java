package com.ktds.sport.debate.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

public class Id<R,V> {
    private final Class<R> reference;

    private final V value;

    private Id(Class<R> reference, V value) {
        this.reference = reference;
        this.value = value;
    }

    public static <R, V> Id<R, V> of(Class<R> reference, V value) {
        checkArgument(reference != null, "reference must be provided.");
        checkArgument(value != null, "value must be provided.");

        return new Id<>(reference, value);
    }

    public V value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id<?, ?> id = (Id<?, ?>) o;
        return Objects.equals(reference, id.reference) &&
                Objects.equals(value, id.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, value);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("reference", reference.getSimpleName())
                .append("value", value)
                .toString();
    }
}
