; inspired from : https://stackoverflow.com/questions/55315374/using-apply-in-core-logic-clojure-clp-cryptoarithmetic
(ns SendMoreMoneyConstraints
    (:require [clojure.core.logic :refer [run*]] [clojure.core.logic.fd :as fd])
    (:gen-class))

(defn find-solutions []
  (run* [s e n d m o r y]
        (fd/in s e n d m o r y (fd/interval 0 9))
        (fd/!= s 0)
        (fd/!= m 0)
        (fd/distinct [s e n d m o r y])
        (fd/eq (= (+ (* 1000 s) (* 100 e) (* 10 n) d
                     (* 1000 m) (* 100 o) (* 10 r) e)
                  (+ (* 10000 m) (* 1000 o) (* 100 n) (* 10 e) y)))))

(defn -main [& args]
   (println (find-solutions)))
