; inspired from : https://gist.github.com/athos/5858819
(ns SendMoreMoneyPermutations
    (:require [clojure.math.combinatorics :refer [combinations permutations]])
    (:gen-class))

(defn solve [n cond]
  (for [ds (combinations (range 10) n)
        ds' (permutations ds)
        :when (cond ds')]
    ds'))

(defn sum-digits [ds]
  (reduce #(+ (* 10 %1) %2) ds))

(defn send-more-money [[s e n d m o r y]]
  (and (not= s 0) (not= m 0)
       (= (+ (sum-digits [s e n d]) (sum-digits [m o r e]))
          (sum-digits [m o n e y]))))

(defn -main [& args]
   (println (solve 8 send-more-money)))
