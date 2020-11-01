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
