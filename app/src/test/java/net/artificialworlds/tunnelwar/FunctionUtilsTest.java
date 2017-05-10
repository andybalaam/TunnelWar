package net.artificialworlds.tunnelwar;

import org.junit.Test;

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
}
