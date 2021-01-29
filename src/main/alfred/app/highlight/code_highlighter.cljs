(ns alfred.app.highlight.code-highlighter
  (:require ["highlight.js" :as hljs]))


(defn element
  ([tag] 
   (element tag, nil, nil))
  ([tag children]
   (element tag nil children))
  ([tag attributes children]
   (str "<" tag (if (nil? attributes) "", " ") attributes ">" children "<" tag "/>")))


(defn void-element
  [tag attributes]
  (str "<" tag " " attributes " />"))


(def pre (partial element "pre"))


(def code (partial element "code"))


(defn highlight-code-block
  [language block]
  (.-value 
   (hljs/highlight language (pre (code block)))))

(defn add5 [it] (+ 5 it))

(comment
  (+ 2 2)

  (pre (code (+ 2 2)))
  
  (->> "(+ 2 2)"
       (highlight-code-block "clojure")))
