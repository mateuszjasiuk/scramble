(ns scramble.logic-test
  (:require [clojure.test :refer [deftest testing is]]
            [scramble.logic :refer [scramble?]]))

(deftest test-scramble?
  (testing "test-scramble"
    (testing "should return true when boths strings are empty"
      (let [str1 "" str2 ""]
        (is (true? (scramble? str1 str2)))))
    (testing "should return true when str1 is empty"
      (let [str1 "asd" str2 ""]
        (is (true? (scramble? str1 str2)))))
    (testing "should return false when str2 is empty"
      (let [str1 "" str2 "asd"]
        (is (false? (scramble? str1 str2)))))
    (testing "should return true when strings are equal"
      (let [str1 "asd" str2 "asd"]
        (is (true? (scramble? str1 str2)))))
    (testing "should return true when str2 has extra characters"
      (let [str1 "asdzx" str2 "asd"]
        (is (true? (scramble? str1 str2)))))
    (testing "should return false when str1 is missing certain characters"
      (let [str1 "asdzx" str2 "asdf"]
        (is (false? (scramble? str1 str2)))))
    (testing "should return false when str1 is has not enough characters to use"
      (let [str1 "aabb" str2 "aaa"]
        (is (false? (scramble? str1 str2)))))
    (testing "should return true for challenge example 1"
      (let [str1 "rekqodlw" str2 "world"]
        (is (true? (scramble? str1 str2)))))
    (testing "should return true for challenge example 2"
      (let [str1 "cedewaraaossoqqyt" str2 "codewars"]
        (is (true? (scramble? str1 str2)))))
    (testing "should return false for challenge example 3"
      (let [str1 "katas" str2 "steak"]
        (is (false? (scramble? str1 str2)))))))

