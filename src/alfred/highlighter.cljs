(ns alfred.highlighter)

(def hljs (js/require "highlight.js"))


(defn element
  [tag attributes children]
  (str "<" tag " " attributes ">" children "</" tag ">"))


(defn void-element
  [tag attributes]
  (str "<" tag " " attributes " " "/>"))


(def pre (partial element "pre"))


(def code (partial element "code"))


(def div (partial element "div"))


(defn highlighted
  [code-text]
  (div
    nil
    (.highlightAuto hljs nil (pre nil (code code-text)))))