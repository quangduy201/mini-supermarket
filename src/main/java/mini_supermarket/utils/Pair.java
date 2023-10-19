package mini_supermarket.utils;

import java.io.Serializable;

public class Pair<F, S> implements Serializable {
    private F first;
    private S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Pair<?, ?> pair)) return false;

        if (getFirst() != null ? !getFirst().equals(pair.getFirst()) : pair.getFirst() != null) return false;
        return getSecond() != null ? getSecond().equals(pair.getSecond()) : pair.getSecond() == null;
    }

    @Override
    public int hashCode() {
        int result = getFirst() != null ? getFirst().hashCode() : 0;
        result = 31 * result + (getSecond() != null ? getSecond().hashCode() : 0);
        return result;
    }
}
