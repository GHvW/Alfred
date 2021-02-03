(ns alfred.app.nodjs-wrappers.readline
  (:require 
   ["readline" :as readline]
   ["fs" :as fs]
   [cljs.core.async :refer [go chan <! put!]]))


(defn read-line
  [path]
  (let [out-chan (chan)
        line-reader (.createInterface 
                     readline 
                     #js {:input (.createReadStream fs path)})]
    (.on line-reader "line" (fn [line] (put! out-chan line)))
    out-chan))


(comment

  ; read-line
  (let [line-chan (read-line "C:/Users/ghvw/Desktop/artshortlist.txt")]
    (go
      (loop []
        (println (<! line-chan))
        (recur))))

  (+ 2 2))