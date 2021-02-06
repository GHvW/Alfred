(ns alfred.app.renderer
  (:require
   [alfred.app.octokit.octokit-integration :refer [get-page]]))


(defn main []
  (js/console.log "renderer initialzed"))

(defn hey [] "hi")

(comment
  (+ 2 2)
  (hey))