(ns slownie.core
  (:require [clojure.string :as str]))

(def blocks
  (into
    [{:from 0, :base 1, :shift 0
      :ordinals ["zero"
                 "jeden"
                 "dwa"
                 "trzy"
                 "cztery"
                 "pięć"
                 "sześć"
                 "siedem"
                 "osiem"
                 "dziewięć"
                 "dziesięć"
                 "jedenaście"
                 "dwanaście"
                 "trzynaście"
                 "czternaście"
                 "piętnaście"
                 "szesnaście"
                 "siedemnaście"
                 "osiemnaście"
                 "dziewiętnaście"]}
     {:from 20, :base 10, :shift 2
      :ordinals ["dwadzieścia"
                 "trzydzieści"
                 "czterdzieści"
                 "pięćdziesiąt"
                 "sześćdziesiąt"
                 "siedemdziesiąt"
                 "osiemdziesiąt"
                 "dziewięćdziesiąt"]}
     {:from 100, :shift 1
      :ordinals ["sto"
                 "dwieście"
                 "trzysta"
                 "czterysta"
                 "pięćset"
                 "sześćset"
                 "siedemset"
                 "osiemset"
                 "dziewięćset"]}
     {:from 1000, :flexes ["tysiąc" "tysiące" "tysięcy"]}]
    (first
      (reduce
        (fn [[acc prev] s]
          (let [n (* prev 1000)]
            [(conj acc {:from n, :flexes [s (str s "y") (str s "ów")]}) n]))
        [[] (bigint 1000)]
        ["milion"
         "miliard"
         "bilion"
         "biliard"
         "trylion"
         "tryliard"
         "kwadrylion"
         "kwadryliard"
         "kwintylion"
         "kwintyliard"
         "sekstylion"
         "sekstyliard"
         "septylion"
         "septyliard"
         "oktylion"
         "oktyliard"
         "nonilion"
         "noniliard"
         "decylion"]))))

(def limit (* (-> blocks last :from) 1000))
(def minus "minus")

(defn flex
  [x [s1 s2-4 s5+]]
  (cond
    (= 1 x) s1
    (= 1 (-> x (quot 10) (rem 10))) s5+
    (< 1 (rem x 10) 5) s2-4
    :else s5+))

(defn abs [x] (if (neg? x) (- x) x))

(defn in-words
  [x]
  {:pre [(< (abs x) limit)]}
  (str/join " "
    (first
      (reduce
        (fn [[acc x] {:keys [from base shift ordinals flexes] :or {base from}}]
          (if (and (<= from x)
                (not (and (seq acc) (zero? x))))
            (let [q (quot (bigint x) base)]
              [(if ordinals
                 (conj acc (nth ordinals (- q shift)))
                 (conj acc (in-words q) (flex q flexes)))
               (rem (bigint x) base)])
            [acc x]))
        [(if (neg? x) [minus] []) (abs x)]
        (reverse blocks)))))

(def whole-currency ["złoty" "złote" "złotych"])
(def frac-currency ["grosz" "grosze" "groszy"])

(defn money
  [x & {:keys [currency decimal-places] :or {decimal-places 2}}]
  (let [whole (bigint x)
        base (Math/pow 10 decimal-places)
        frac (abs (Math/round (* base (- x whole))))]
    (if (string? currency)
      (format "%s %s %d/%d" (in-words whole) currency frac (long base))
      (str/join " " [(in-words whole)
                     (flex (abs whole) whole-currency)
                     (in-words frac)
                     (flex frac frac-currency)]))))
