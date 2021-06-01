package info.jab.reactive.ch0.euler;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Problem 20: Factorial digit sum
 * n! means n (n 1) ... 3 2 1
 *
 * For example, 10! = 10   9   ...   3   2   1 = 3628800,
 *
 * and the sum of the digits in the number 10! is
 * 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
 *
 * Find the sum of the digits in the number 100!
 *
 */
public class EulerProblem20 {

    public Mono<Long> ReactorSolution(Long limit) {
        Mono<Long> response = Flux.range(1, limit.intValue())//create a range from 1 to limit
                .sort(Comparator.reverseOrder()) //reverse order stream
                .map(BigDecimal::new) //convert int to BigDecimal value
                .reduce((x,y) -> x.multiply(y))//multiply reversed stream values
                .map(result -> this.getSum(result)); //map value and apply getSum function

        return response;
    }

    /**
     * Given a BigDecimal, sum his digits and return Long value
     */
    private Long getSum(BigDecimal num) {
       return Long.valueOf(num.toString().chars().map(Character::getNumericValue).sum());
    }

}
