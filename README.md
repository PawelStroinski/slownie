# słownie

> **słownie** /swɔvɲɛ/ is Polish for **in words**

Convert numbers or money into words in the 🇵🇱 Polish language. A data-driven approach.

[![Clojars Project](https://img.shields.io/clojars/v/slownie.svg)](https://clojars.org/slownie)

## Credits

The algorithm is essentially the same as in [this Gary Fredericks's work](https://github.com/gfredericks/forty-two/blob/cd3b2ae25ad400788c8c128e2dffdb2d55f132d6/src/com/gfredericks/forty_two.clj) as it was written after reading the aforementioned.

## Usage

```clojure
(require '[slownie.core :refer [in-words money]])
;=> nil

(in-words (* Math/PI 100))
;=> "trzysta czternaście"

(money Math/PI)
;=> "trzy złote czternaście groszy"

;; The lowest integer supported

(in-words (- 1 (bigint (Math/pow 10 63))))
;=> "minus dziewięćset dziewięćdziesiąt dziewięć decylionów dziewięćset dziewięćdziesiąt dziewięć noniliardów dziewięćset dziewięćdziesiąt dziewięć nonilionów dziewięćset dziewięćdziesiąt dziewięć oktyliardów dziewięćset dziewięćdziesiąt dziewięć oktylionów dziewięćset dziewięćdziesiąt dziewięć septyliardów dziewięćset dziewięćdziesiąt dziewięć septylionów dziewięćset dziewięćdziesiąt dziewięć sekstyliardów dziewięćset dziewięćdziesiąt dziewięć sekstylionów dziewięćset dziewięćdziesiąt dziewięć kwintyliardów dziewięćset dziewięćdziesiąt dziewięć kwintylionów dziewięćset dziewięćdziesiąt dziewięć kwadryliardów dziewięćset dziewięćdziesiąt dziewięć kwadrylionów dziewięćset dziewięćdziesiąt dziewięć tryliardów dziewięćset dziewięćdziesiąt dziewięć trylionów dziewięćset dziewięćdziesiąt dziewięć biliardów dziewięćset dziewięćdziesiąt dziewięć bilionów dziewięćset dziewięćdziesiąt dziewięć miliardów dziewięćset dziewięćdziesiąt dziewięć milionów dziewięćset dziewięćdziesiąt dziewięć tysięcy dziewięćset dziewięćdziesiąt dziewięć"

(money (- 0.01M (bigdec (Math/pow 10 63))))
;=> "minus dziewięćset dziewięćdziesiąt dziewięć decylionów dziewięćset dziewięćdziesiąt dziewięć noniliardów dziewięćset dziewięćdziesiąt dziewięć nonilionów dziewięćset dziewięćdziesiąt dziewięć oktyliardów dziewięćset dziewięćdziesiąt dziewięć oktylionów dziewięćset dziewięćdziesiąt dziewięć septyliardów dziewięćset dziewięćdziesiąt dziewięć septylionów dziewięćset dziewięćdziesiąt dziewięć sekstyliardów dziewięćset dziewięćdziesiąt dziewięć sekstylionów dziewięćset dziewięćdziesiąt dziewięć kwintyliardów dziewięćset dziewięćdziesiąt dziewięć kwintylionów dziewięćset dziewięćdziesiąt dziewięć kwadryliardów dziewięćset dziewięćdziesiąt dziewięć kwadrylionów dziewięćset dziewięćdziesiąt dziewięć tryliardów dziewięćset dziewięćdziesiąt dziewięć trylionów dziewięćset dziewięćdziesiąt dziewięć biliardów dziewięćset dziewięćdziesiąt dziewięć bilionów dziewięćset dziewięćdziesiąt dziewięć miliardów dziewięćset dziewięćdziesiąt dziewięć milionów dziewięćset dziewięćdziesiąt dziewięć tysięcy dziewięćset dziewięćdziesiąt dziewięć złotych dziewięćdziesiąt dziewięć groszy"

;; Options accepted by `money`

(money 1 :currency "CLF" :decimal-places 4)
;=> "jeden CLF 0/10000"
```

## License

Copyright © 2019 Paweł Stroiński

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
