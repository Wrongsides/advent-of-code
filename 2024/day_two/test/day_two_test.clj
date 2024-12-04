(ns day-two-test
  (:require [clojure.test :refer :all]
            [day-two :refer :all]))

(deftest descending?-test
  (testing "returns true when all levels are descending"
    (is (= true (descending? [3 2 1]))))
  (testing "returns false when all levels are not descending"
    (is (= false (descending? [1 2 3])))))

(deftest ascending?-test
  (testing "returns true when all levels are ascending"
    (is (= true (ascending? [1 2 3]))))
  (testing "returns false when all levels are not ascending"
    (is (= false (ascending? [3 2 1])))))

(deftest differences-test
  (testing "returns the differences between each value in a collection"
    (is (= [1 3] (differences [1 2 5])))))

(deftest safe?-test
  (testing "returns true for safe report 7 6 4 2 1"
    (is (= true (safe? [7 6 4 2 1]))))
  (testing "returns false for unsafe report 1 2 7 8 9"
    (is (= false (safe? [1 2 7 8 9]))))
  (testing "returns false for unsafe report 9 7 6 2 1"
    (is (= false (safe? [9 7 6 2 1]))))
  (testing "returns false for unsafe report 1 3 2 4 5"
    (is (= false (safe? [1 3 2 4 5]))))
  (testing "returns false for unsafe report 8 6 4 4 1"
    (is (= false (safe? [8 6 4 4 1]))))
  (testing "returns true for safe report 1 3 6 7 9"
    (is (= true (safe? [1 3 6 7 9])))))

(deftest remove-level-test
  (testing "removes nth level from a report"
    (is (= [1 2 4 5] (remove-level [1 2 3 4 5] 2)))))

(deftest dampened-safe?-test
  (testing "returns true for safe report 7 6 4 2 1"
    (is (= true (dampened-safe? [7 6 4 2 1] [7 6 4 2 1] 0))))
  (testing "returns false for unsafe report 1 2 7 8 9"
    (is (= false (dampened-safe? [1 2 7 8 9] [1 2 7 8 9] 0))))
  (testing "returns false for unsafe report 9 7 6 2 1"
    (is (= false (dampened-safe? [9 7 6 2 1] [9 7 6 2 1] 0))))
  (testing "returns true for safe report 1 3 2 4 5"
    (is (= true (dampened-safe? [1 3 2 4 5] [1 3 2 4 5] 0))))
  (testing "returns true for safe report 8 6 4 4 1"
    (is (= true (dampened-safe? [8 6 4 4 1] [8 6 4 4 1] 0))))
  (testing "returns true for safe report 1 3 6 7 9"
    (is (= true (dampened-safe? [1 3 6 7 9] [1 3 6 7 9] 0)))))

(deftest safe-reports-test
  (testing "returns the number of safe reports from file"
    (is (= 371 (safe-reports "src/input")))))

(deftest dampened-safe-reports-test
  (testing "returns the number of dampened safe reports from file"
    (is (= 426 (dampened-safe-reports "src/input")))))
