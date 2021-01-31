(ns alfred.app.highlight.code-highlighter
  (:require ["highlight.js" :as hljs]))


(defn element
  ([tag] 
   (element tag, nil, nil))
  ([tag children]
   (element tag nil children))
  ([tag attributes children]
   (str "<" tag (if (nil? attributes) 
                  "" 
                  (str " " attributes)) ">" 
        children 
        "</" tag ">")))


(defn void-element
  [tag attributes]
  (str "<" tag " " attributes " />"))


(def pre (partial element "pre"))


(def code (partial element "code"))


(defn highlight-code-block
  "takes the name of a programming language and 
  a text block of code from that programming language 
  and applies highlight.js to the block"
  [language block]
  (pre (code (->> block
                  (hljs/highlight language)
                  (.-value)))))

(defn adder [x y] (+ x y))

(comment
  (+ 2 2)

  (apply str ["hello" " " "world" "!"])

  (apply adder [10 20])

  (pre (code (+ 2 2)))

  (->> "(+ 2 2)"
       (highlight-code-block "clojure")))
