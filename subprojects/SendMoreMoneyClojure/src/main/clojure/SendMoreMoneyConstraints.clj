;
; SPDX-License-Identifier: Apache-2.0
;
; Licensed under the Apache License, Version 2.0 (the "License");
; you may not use this file except in compliance with the License.
; You may obtain a copy of the License at
;
;     https://www.apache.org/licenses/LICENSE-2.0
;
; Unless required by applicable law or agreed to in writing, software
; distributed under the License is distributed on an "AS IS" BASIS,
; WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
; See the License for the specific language governing permissions and
; limitations under the License.
;

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
