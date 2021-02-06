(ns alfred.app.main
  (:require 
   ["electron" :refer [app BrowserWindow ipcMain]]
   [cljs.core.async :refer [go chan <! put!]]
   [cljs.core.match :refer-macros [match]]))


; (defn ipcHandler
;   []
;   (let [ipc-chan (chan)]))


(def main-window (atom nil))


(defn init
  []
  (let [window (BrowserWindow.
                #js {:width 1400
                     :height 900
                     :webPreferences {:nodeIntegration true}})]
    (.loadURL window (str "file://" js/__dirname "/public/index.html"))
    (.on window "closed" (fn [] (reset! main-window nil)))
    (reset! main-window window)))


(defn maybe-quit 
  []
  (when (not= js/process.platform "darwin")
    (.quit app)))


(defn check-window
  []
  (when-not @main-window (init)))


(defn main-in
  [channels]
  (let [out-chan (chan)]
    (.on "main-in" 
         (fn [event [request payload]] 
           (match [request payload]
             [:github-page data] (put! (channels :github) [data (chan)]))))))

(defn main
  []
  (.on app "window-all-closed" maybe-quit)
  (.on app "activiate" check-window)
  (.on app "ready" init)
  )