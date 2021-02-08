(ns alfred.app.renderer
  (:require
   ["electron" :refer [ipcRenderer]]
   [cljs.core.match :refer-macros [match]]
   [cljs.core.async :refer [go chan <! put!]]))


;; (defn renderer-in
;;   [store-chan]
;;   (.on ipcRenderer "renderer-in" 
;;        (fn [_ response]
;;          (println "at least made it here")
;;          (put! store-chan response))))

;; (defn store-router
;;   []
;;   (let [in-chan (chan)]
;;     (go
;;       (loop []
;;         (match [(<! in-chan)]
;;           [[:github-page page]] (println page))))
;;     in-chan))


(defn hey [] "hi")


(defn main
  []
  (println "hey")
  (js/console.log "renderer initialzed"))
  ;; (set! (-> js/document
  ;;           (.querySelector "#container")
  ;;           (.-innerHTML)) "<p>Hello World</p>")
  ;; (renderer-in (store-router)))


(comment
  (+ 2 2)
  (hey)
  (main))