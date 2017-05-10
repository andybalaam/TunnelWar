package net.artificialworlds.tunnelwar;

import org.junit.Test;

import java.util.Collections;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import static net.artificialworlds.tunnelwar.FunctionUtils.*;

public class FunctionUtilsTest
{
    @Test
    public void Mapping_an_empty_list_gives_an_empty_list() throws Exception
    {
        Function<Object, Object> toStrings = new Function<Object, Object>()
        {
            @Override
            public Object apply(Object t)
            {
                return t.toString();
            }
        };

        assertThat(list(map(toStrings, list())), equalTo(list()));
    }

    @Test
    public void Mapping_a_list_applies_the_function_over_the_list() throws Exception
    {
        Function<Double, Integer> percent = new Function<Double, Integer>()
        {
            @Override
            public Integer apply(Double t)
            {
                return new Double(t * 100).intValue();
            }
        };

        assertThat(list(map(percent, list(0.2, 0.1))), equalTo(list(20, 10)));
    }

    @Test
    public void Taking_none_gives_the_empty_list() throws Exception
    {
        assertThat(list(take(0, list(0.2, 0.1))), equalTo(Collections.<Double>emptyList()));
    }

    @Test
    public void Taking_some_stops_before_the_end() throws Exception
    {
        assertThat(list(take(2, list(3, 4, 5, 6))), equalTo(list(3, 4)));
    }

    @Test
    public void Taking_some_from_an_infinite_iterator_terminates() throws Exception
    {
        Iterable<Integer> allNumbers = new Iterable<Integer>()
        {
            @Override
            public Iterator<Integer> iterator()
            {
                return new Iterator<Integer>()
                {
                    int n = 0;

                    @Override
                    public boolean hasNext()
                    {
                        return true;
                    }

                    @Override
                    public Integer next()
                    {
                        return n++;
                    }

                    @Override
                    public void remove()
                    {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };

        assertThat(list(take(5, allNumbers)), equalTo(list(0, 1, 2, 3, 4)));
    }
}
