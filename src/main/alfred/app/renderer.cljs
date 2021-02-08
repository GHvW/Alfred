(ns alfred.app.renderer
  (:require
  ;; https://github.com/electron/electron/issues/7300 (issue with electron require)
  ;;  ["electron" :refer [ipcRenderer]] ;; this is whwat is killing me, need a workaround
   [cljs.core.match :refer-macros [match]]
   [cljs.core.async :refer [go chan <! put!]]))

;; (def electron (.require js/window "electron"))

;; (def ipcRenderer (partial ()))

(defn renderer-in
  "test"
  [store-chan]
  (-> js/window
      (.-ipcRenderer)
      (.on "renderer-in"
           (fn [_ response]
             (println "at least made it here")
             (put! store-chan response)))))

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