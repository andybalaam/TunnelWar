package net.artificialworlds.tunnelwar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class FunctionUtils
{
    interface Function<T, R>
    {
        R apply(T t);
    }

    static <T, R> Iterable<R> map(final Function<T, R> fun, final Iterable<T> input)
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

    static <T> Iterable<T> take(final int howMany, final Iterable<T> input)
    {
        return new Iterable<T>()
        {
            @Override
            public Iterator<T> iterator()
            {
                class Holder
                {
                    int n = 0;
                }

                final Iterator<T> inputIterator = input.iterator();
                final Holder n = new Holder();

                return new Iterator<T>()
                {
                    @Override
                    public boolean hasNext()
                    {
                        return inputIterator.hasNext() && n.n < howMany;
                    }

                    @Override
                    public T next()
                    {
                        ++n.n;
                        if (n.n <= howMany)
                        {
                            return inputIterator.next();
                        }
                        else
                        {
                            throw new NoSuchElementException();
                        }
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

    static <T> Iterable<T> flatten(final Iterable<Iterable<T>> input)
    {
        class MaybeT
        {
            final T t;
            final boolean exists;

            MaybeT(T t)
            {
                this.t = t;
                exists = true;
            }

            private MaybeT()
            {
                this.t = null;
                exists = false;
            }
        }

        final MaybeT NONE = new MaybeT();

        return new Iterable<T>()
        {
            @Override
            public Iterator<T> iterator()
            {
                return new Iterator<T>()
                {
                    Iterator<Iterable<T>> outer = input.iterator();
                    Iterator<T> inner = null;
                    MaybeT nextT = findNext();

                    private MaybeT findNext()
                    {
                        try
                        {
                            return doFindNext();
                        }
                        catch (NoSuchElementException e)
                        {
                            return NONE;
                        }
                    }

                    private MaybeT doFindNext()
                    {
                        while (inner == null || !inner.hasNext())
                        {
                            if (!outer.hasNext())
                            {
                                return NONE;
                            }

                            inner = outer.next().iterator();
                        }

                        return new MaybeT(inner.next());
                    }

                    @Override
                    public boolean hasNext()
                    {
                        return nextT.exists;
                    }

                    @Override
                    public T next()
                    {
                        MaybeT ret = nextT;
                        if (!ret.exists)
                        {
                            throw new NoSuchElementException();
                        }
                        nextT = findNext();
                        return ret.t;
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

    static List<Object> list()
    {
        return Collections.emptyList();
    }

    static <T> List<T> list(Iterable<T> inputs)
    {
        ArrayList<T> ret = new ArrayList<>();
        for (T t : inputs)
        {
            ret.add(t);
        }
        return ret;
    }

    @SafeVarargs
    static <T> List<T> list(T... inputs)
    {
        return Arrays.asList(inputs);
    }

}
