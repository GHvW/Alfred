(ns alfred.app.main
  (:require
   ["electron" :refer [app BrowserWindow ipcMain]]
   [cljs.core.async :refer [go chan <! put! >!]]
   [cljs.core.match :refer-macros [match]]
   [alfred.app.octokit.octokit-integration :refer [get-page]]))


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


;; (defn github-handler
;;   []
;;   (let [in-chan (chan 1)]
;;     (go
;;       (loop []
;;         (let [[data reply-chan] (<! in-chan)]
;;           (>! reply-chan (<! (get-page data)))
;;           (recur))))))


;; (defn main-in
;;   [channel-map]
;;   (.on ipcMain "main-in"
;;        (fn [event [request payload]]
;;          (let [reply-chan (chan 1)]
;;            (match [request payload]
;;              [:github-page data] (put! (channel-map :github) {:data data
;;                                                               :reply-chan reply-chan}))
;;            (go
;;              (-> event
;;                  (.-sender)
;;                  (.send "renderer-in" (<! reply-chan))))))))


(defn main
  []
  (.on app "window-all-closed" maybe-quit)
  (.on app "activiate" check-window)
  (.on app "ready" init)
  (println "main started"))
  ;; (main-in {:github-page (github-handler)}))