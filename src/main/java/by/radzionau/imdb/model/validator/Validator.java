package by.radzionau.imdb.model.validator;

public interface Validator {
    default boolean isNull(Object value) {
        return value == null;
    }

    default boolean isEmpty(String value) {
        return value.isEmpty();
    }
}
