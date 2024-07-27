package foo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public final class CompoundOrdering<T> implements Comparator<T> {

    final Comparator<? super T>[] comparators;

    @SuppressWarnings("unchecked")
    public static final <T> Comparator<T> of(List<? extends Comparator<? super T>> comparators) {
        if (comparators.size() == 1) {
            return (Comparator<T>) comparators.get(0);
        }
        return new CompoundOrdering<>(comparators);
    }

    @SuppressWarnings("unchecked")
    private CompoundOrdering(List<? extends Comparator<? super T>> comparators) {
        this.comparators = comparators.toArray(Comparator[]::new);
    }

    @Override
    public int compare(T left, T right) {
        for (int i = 0; i < comparators.length; i++) {
            int result = comparators[i].compare(left, right);
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof CompoundOrdering<?> other) {
            return Arrays.equals(this.comparators, other.comparators);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(comparators);
    }

    @Override
    public String toString() {
        return "Ordering.compound(" + Arrays.toString(comparators) + ")";
    }
}
