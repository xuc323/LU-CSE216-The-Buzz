import 'package:flutter_test/flutter_test.dart';
import 'package:startup_namer/counter.dart';

void main() {
  group("Counter", () {
    test("value should start at 0", () {
      expect(Counter().value, 0);
    });

    test("value should be increment", () {
      final counter = Counter();

      counter.increment();

      expect(counter.value, 1);
    });

    test("value should be decremented", () {
      final counter = Counter();

      counter.decrement();

      expect(counter.value, -1);
    });
  });
}