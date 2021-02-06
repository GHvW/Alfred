(ns alfred.app.octokit.octokit-integration
  (:require
   ["@octokit/rest" :refer [Octokit]]
   [cljs.core.async :refer [go]]
   [cljs.core.async.interop :refer-macros [<p!]]))


(defn get-page
  [owner repo path]
    (-> (Octokit.)
        (.-repos)
        (.getContent #js {:owner owner
                          :repo repo
                          :path path})))

(comment
  (go
    (let [it (<p! (-> (Octokit.)
                      (.-repos)
                      (.getContent #js {:owner "GHvW"
                                        :repo "clay"
                                        :path "blob/7847146495d558b9f0c8cd95cd56a895d12a7bb6/src/shape_readers/bounds_box.rs"})))]
      it))

  (-> (Octokit.)
      (.-repos)
      (.getContent #js {:owner "GHvW"
                        :repo "clay"
                        :path "blob/7847146495d558b9f0c8cd95cd56a895d12a7bb6/src/shape_readers/bounds_box.rs"})
      (.then (fn [it] (println (str "it is a " it))))
      (.catch (fn [_] (println "hi, it broke"))))


  (let [promise (get-page
                 "GHvW"
                 "clay"
                 "blob/7847146495d558b9f0c8cd95cd56a895d12a7bb6/src/shape_readers/bounds_box.rs")]
    (go
      (println (<p! promise))))


  (get-page
   "GHvW"
   "clay"
   "blob/7847146495d558b9f0c8cd95cd56a895d12a7bb6/src/shape_readers/bounds_box.rs")

  (+ 1 4))
