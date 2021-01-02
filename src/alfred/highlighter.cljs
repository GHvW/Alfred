(ns highlighter)

(def hljs (js/require "highlight.js"))

(defn element
  [tag attributes children]
  (str "<" tag " " attributes ">" children "</" tag ">"))

(defn void-element
  [tag attributes]
  (str "<" tag " " attributes " " "/>"))


(def pre (partial element "pre"))

(def code (partial element "code"))

(defn highlighted
  [code-text]
  (pre (code (.highlight hljs code-text))))