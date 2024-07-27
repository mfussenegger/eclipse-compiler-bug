package foo;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

public class CompoundOrderingTest {

    @Test
    public void test_ok() throws Exception {
        var orderings = Arrays.asList(
            OrderingByPosition.arrayOrdering(1, false, false),
            OrderingByPosition.arrayOrdering(0, false, false)
        );
        Comparator<Object[]> ordering = CompoundOrdering.of(orderings);
    }

    // @Test
    // public void test_compile_error() throws Exception {
    //     Comparator<Object[]> ordering = CompoundOrdering.of(Arrays.asList(
    //         OrderingByPosition.arrayOrdering(1, false, false),
    //         OrderingByPosition.arrayOrdering(0, false, false)
    //     ));
    // }
}
