(ns alfred.app.ghost_interactions.authentication
  (:require 
   ["@tryghost/admin-api" :as GhostAPI]
   ["process" :as process]))


(def ghost-key
  (.. process -env -GHOST_KEY))


(def ghost-url
  (.. process -env -GHOST_URL))

(defn make-api
  [url version key]
  (GhostAPI. #js {:url url 
                  :version version 
                  :key key}))


(comment
  (+ 1 3)
  
  ghost-key

  ghost-url 

  (make-api ghost-url "v3" ghost-key)

  (.. process -env -GHOST_KEY)

  (.. process -env -GHOST_URL)
  
  (+ 3 3))