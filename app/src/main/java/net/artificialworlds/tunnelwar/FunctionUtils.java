package net.artificialworlds.tunnelwar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FunctionUtils
{
    public static interface Function<T, R>
    {
        R apply(T t);
    }

    public static <T, R> Iterable<R> map(final Function<T, R> fun, final Iterable<T> input)
    {
        return new Iterable<R>()
        {
            @Override
            public Iterator<R> iterator()
            {
                final Iterator<T> inputIterator = input.iterator();

                return new Iterator<R>()
                {
                    @Override
                    public boolean hasNext()
                    {
                        return inputIterator.hasNext();
                    }

                    @Override
                    public R next()
                    {
                        return fun.apply(inputIterator.next());
                    }

                    @Override
                    public void remove()
                    {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };

    }


    public static List<Object> list()
    {
        return Collections.emptyList();
    }

    public static <T> List<T> list(Iterable<T> inputs)
    {
        ArrayList<T> ret = new ArrayList<>();
        for (T t : inputs)
        {
            ret.add(t);
        }
        return ret;
    }

    public static <T> List<T> list(T... inputs)
    {
        return Arrays.asList(inputs);
    }

}
