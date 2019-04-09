(ns slownie.core-test
  (:require [midje.sweet :refer :all]
            [slownie.core :refer [in-words money]]))

(tabular
  (fact (in-words ?x) => ?exp)
  ?x ?exp
  0 "zero"
  1 "jeden"
  6 "sześć"
  10 "dziesięć"
  11 "jedenaście"
  18 "osiemnaście"
  20 "dwadzieścia"
  22 "dwadzieścia dwa"
  99 "dziewięćdziesiąt dziewięć"
  100 "sto"
  173 "sto siedemdziesiąt trzy"
  4275 "cztery tysiące dwieście siedemdziesiąt pięć"
  4734275 "cztery miliony siedemset trzydzieści cztery tysiące dwieście siedemdziesiąt pięć"
  84894734275 "osiemdziesiąt cztery miliardy osiemset dziewięćdziesiąt cztery miliony siedemset trzydzieści cztery tysiące dwieście siedemdziesiąt pięć"
  80000000004 "osiemdziesiąt miliardów cztery"
  3000000000000 "trzy biliony"
  1002 "jeden tysiąc dwa"
  1012 "jeden tysiąc dwanaście"
  1112 "jeden tysiąc sto dwanaście"
  1100 "jeden tysiąc sto"
  101000 "sto jeden tysięcy"
  1000000 "jeden milion"
  14000000 "czternaście milionów"
  24000000 "dwadzieścia cztery miliony"
  1001001000 "jeden miliard jeden milion jeden tysiąc"
  2002002000 "dwa miliardy dwa miliony dwa tysiące"
  5005005000 "pięć miliardów pięć milionów pięć tysięcy"
  (Math/pow 10 60) "jeden decylion"
  (Math/pow 10 62) "sto decylionów"
  (- 1 (bigint (Math/pow 10 63))) "minus dziewięćset dziewięćdziesiąt dziewięć decylionów dziewięćset dziewięćdziesiąt dziewięć noniliardów dziewięćset dziewięćdziesiąt dziewięć nonilionów dziewięćset dziewięćdziesiąt dziewięć oktyliardów dziewięćset dziewięćdziesiąt dziewięć oktylionów dziewięćset dziewięćdziesiąt dziewięć septyliardów dziewięćset dziewięćdziesiąt dziewięć septylionów dziewięćset dziewięćdziesiąt dziewięć sekstyliardów dziewięćset dziewięćdziesiąt dziewięć sekstylionów dziewięćset dziewięćdziesiąt dziewięć kwintyliardów dziewięćset dziewięćdziesiąt dziewięć kwintylionów dziewięćset dziewięćdziesiąt dziewięć kwadryliardów dziewięćset dziewięćdziesiąt dziewięć kwadrylionów dziewięćset dziewięćdziesiąt dziewięć tryliardów dziewięćset dziewięćdziesiąt dziewięć trylionów dziewięćset dziewięćdziesiąt dziewięć biliardów dziewięćset dziewięćdziesiąt dziewięć bilionów dziewięćset dziewięćdziesiąt dziewięć miliardów dziewięćset dziewięćdziesiąt dziewięć milionów dziewięćset dziewięćdziesiąt dziewięć tysięcy dziewięćset dziewięćdziesiąt dziewięć")

(facts
  (in-words (Math/pow 10 63)) => (throws AssertionError)
  (in-words (- (Math/pow 10 63))) => (throws AssertionError))

(tabular
  (fact (money ?x) => ?exp)
  ?x ?exp
  0 "zero złotych zero groszy"
  1.02 "jeden złoty dwa grosze"
  2.05 "dwa złote pięć groszy"
  5.22 "pięć złotych dwadzieścia dwa grosze"
  101.01 "sto jeden złotych jeden grosz"
  212 "dwieście dwanaście złotych zero groszy"
  -22.03 "minus dwadzieścia dwa złote trzy grosze"
  (Math/pow 10 60) "jeden decylion złotych zero groszy"
  (- 0.01M (bigdec (Math/pow 10 63))) "minus dziewięćset dziewięćdziesiąt dziewięć decylionów dziewięćset dziewięćdziesiąt dziewięć noniliardów dziewięćset dziewięćdziesiąt dziewięć nonilionów dziewięćset dziewięćdziesiąt dziewięć oktyliardów dziewięćset dziewięćdziesiąt dziewięć oktylionów dziewięćset dziewięćdziesiąt dziewięć septyliardów dziewięćset dziewięćdziesiąt dziewięć septylionów dziewięćset dziewięćdziesiąt dziewięć sekstyliardów dziewięćset dziewięćdziesiąt dziewięć sekstylionów dziewięćset dziewięćdziesiąt dziewięć kwintyliardów dziewięćset dziewięćdziesiąt dziewięć kwintylionów dziewięćset dziewięćdziesiąt dziewięć kwadryliardów dziewięćset dziewięćdziesiąt dziewięć kwadrylionów dziewięćset dziewięćdziesiąt dziewięć tryliardów dziewięćset dziewięćdziesiąt dziewięć trylionów dziewięćset dziewięćdziesiąt dziewięć biliardów dziewięćset dziewięćdziesiąt dziewięć bilionów dziewięćset dziewięćdziesiąt dziewięć miliardów dziewięćset dziewięćdziesiąt dziewięć milionów dziewięćset dziewięćdziesiąt dziewięć tysięcy dziewięćset dziewięćdziesiąt dziewięć złotych dziewięćdziesiąt dziewięć groszy")

(facts
  (money 1.02 :currency "FOO") => "jeden FOO 2/100"
  (money 1.234 :currency "FOO" :decimal-places 3) => "jeden FOO 234/1000")
