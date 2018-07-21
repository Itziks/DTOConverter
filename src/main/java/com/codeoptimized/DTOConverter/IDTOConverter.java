package com.codeoptimized.DTOConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Converts <T> object s to <D> objects.
 * @author itziks
 *
 * @param <T> the input type.
 * @param <D> the output type.
 */
public interface IDTOConverter<T, D> extends Function<T, D> {

    /**
     * converts a <T> object to a <D> object.
     * @param input the <T> object.
     * @return <D> object.
     */
    default D convert(final T input) {
        D output = null;
        if (input != null) {
            output = this.apply(input);
        }
        return output;
    }

    /**
     * converts a List<T> to a List<D>.
     * @param input the List<T>.
     * @return List<D>.
     */
    default List<D> convert(final List<T> input) {
        List<D> output = new ArrayList<D>();
        if (input != null) {
            output = input.stream().map(this::apply).collect(Collectors.toList());
        }
        return output;
    }

    /**
     * converts a Iterable<T> to a List<D>.
     * @param input the Iterable<T>.
     * @return List<D>.
     */
    default List<D> convert(final Iterable<T> input) {
        List<T> inputList = null;
        List<D> output = null;

        if (input != null) {
            inputList = new ArrayList<T>();
            input.forEach(inputList::add);
            output = inputList.stream().map(this::apply).collect(Collectors.toList());
        }
        return output;
    }

    /**
     * converts a Page<T> to a Page<D>.
     * @param input the Page<T>.
     * @return Page<D>.
     */
    default Page<D> convert(final Page<T> input) {
        List<T> inputList = input.getContent();
        List<D> output = new ArrayList<D>();
        if (inputList != null) {
            output = inputList.stream().map(this::apply).collect(Collectors.toList());
        }
        return new PageImpl<>(output, input.getPageable(), input.getTotalElements());
    }
}