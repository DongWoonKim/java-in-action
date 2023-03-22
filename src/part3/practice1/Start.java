package part3.practice1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Start {

    Trader raoul = new Trader("Raoul", "Cambridge");
    Trader mario = new Trader("Mario", "Milan");
    Trader alan = new Trader("Alan", "Cambridge");
    Trader brian = new Trader("Brian", "Cambridge");

    List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    public void exam1() {
        transactions.stream()
                .filter( transaction -> transaction.getYear() == 2011 )
                .sorted( Comparator.comparing(Transaction::getValue) )
                .forEach( System.out::println );
    }

    public void exam2() {
        transactions.stream()
                .map( transaction -> transaction.getTrader().getCity() )
                .distinct()
                .forEach(System.out::println);
    }

    public void exam3() {
        transactions.stream()
                .map( Transaction::getTrader )
                .filter( trader -> trader.getCity().equals("Cambridge") )
                .sorted( Comparator.comparing(Trader::getName) )
                .forEach( p-> System.out.println(p.getName() + " : " + p.getCity()) );
    }

    public void exam4() {
        String resStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);

        System.out.println( resStr );
    }

    public void exam5() {
        boolean isCity = transactions.stream()
                .anyMatch( transaction -> "Milan".equals(transaction.getTrader().getCity()) );

        System.out.println(isCity);
    }

    public void exam6() {
        transactions.stream()
                .filter( transaction -> "Cambridge".equals(transaction.getTrader().getCity()) )
                .map( Transaction::getValue )
                .forEach(System.out::println);
    }

    public void exam7() {
        transactions.stream()
                .map(Transaction::getValue)
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .forEach(System.out::println);

        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .ifPresent(System.out::println);
    }

    public void exam8() {
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min)
                .ifPresent(System.out::println);
    }

    public static void main(String[] args) {

        Start start = new Start();
        start.exam8();

    }

}
