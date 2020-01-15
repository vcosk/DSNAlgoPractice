package in.vikk.dailycoding;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//Given an array of time intervals (start, end) for classroom lectures (possibly overlapping), find the minimum number of rooms required.
//
//For example, given [(30, 75), (0, 50), (60, 150)], you should return 2.

public interface MinResourceReq<T extends Comparable<T>> {

    static <T extends Comparable<T>> MinResourceReq<T> getInstance() {
        return new MinResourceReq<T>() {

            private List<Duration<T>> durations = new ArrayList<>();

            private int counter = 0;

            @Override
            public void addDurations(T start, T end) {
                durations.add(new Duration<>(start, end));
                counter = 0;
            }

            @Override
            public int findMinResourceReq() {
                if (counter == 0 && !durations.isEmpty()) {
                    durations.sort(Duration.startComparator);
                    counter = 1;
                    Duration<T> previousClass = durations.get(0);
                    for (int index = 1; index < durations.size(); index++) {
                        Duration<T> current = durations.get(index);
                        if (previousClass.end.compareTo(current.start) > 0) {
                            counter += 1;
                        }
                        previousClass = current;
                    }
                }
                return counter;
            }
        };
    }

    void addDurations(T start, T end);

    int findMinResourceReq();

}

class Duration<T extends Comparable<T>> {

    T start;

    T end;

    Duration(T start, T end) {
        this.start = start;
        this.end = end;
    }

    static Comparator<Duration> startComparator = (first, second) ->  first.start.compareTo(second.start);

    static Comparator<Duration> endComparator = (first, second) ->  first.end.compareTo(second.end);
}

class Runner {
    public static void main(String[] args) {
        MinResourceReq<Integer> classes = MinResourceReq.getInstance();
        classes.addDurations(30, 75);
        classes.addDurations(0, 50);
        classes.addDurations(60, 150);
        System.out.println(classes.findMinResourceReq());

        MinResourceReq<LocalTime> departure = MinResourceReq.getInstance();
        departure.addDurations(LocalTime.of(9, 0), LocalTime.of(9, 10));
        departure.addDurations(LocalTime.of(9, 40), LocalTime.of(12, 0));
        departure.addDurations(LocalTime.of(9, 50), LocalTime.of(11, 20));
        departure.addDurations(LocalTime.of(11, 0), LocalTime.of(11, 30));
        departure.addDurations(LocalTime.of(15, 0), LocalTime.of(19, 0));
        departure.addDurations(LocalTime.of(18, 0), LocalTime.of(20, 0));
        System.out.println(departure.findMinResourceReq());
    }
}
