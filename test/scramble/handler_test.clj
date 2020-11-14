(ns scramble.handler-test
  (:require [clojure.test :refer [deftest testing is]]
            [ring.mock.request :as mock]
            [scramble.logic :as logic]
            [scramble.handler :refer [app]]))

(deftest test-app
  (let [args (atom nil)]
    (with-redefs
     [logic/scramble?
      (fn [s1 s2]
        (reset! args [s1 s2])
        true)]
      (testing "scramble route"
        (let [response (app (mock/request :get "/scramble?str1=asd&str2=asdf"))]
          (is (= (first @args) "asd"))
          (is (= (last @args) "asdf"))
          (is (= (:status response) 200))))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
