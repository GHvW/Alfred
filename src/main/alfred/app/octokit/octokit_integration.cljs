(ns alfred.app.octokit.octokit-integration
  (:require
   ["@octokit/rest" :refer [Octokit]]
   [cljs.core.async :refer [go chan >! <!]]
   [cljs.core.async.interop :refer-macros [<p!]]))


(defn get-page ;; :- promise page
  "github api content output is base64" ;; will need to use atob function to convert
  [request-map] ;; :- { option user option repo option path }
  (let [out-chan (chan 1)]
    (go
      (>! out-chan
          (<p! (-> (Octokit.)
                   (.-repos)
                   (.getContent (clj->js request-map))))))
    out-chan))

(comment

  (try
    (let [p (get-page {:owner "GHvW"
                       :repo "clay"})]
      (go
        (println (<! p))))
    (catch js/Error err (println (str "got an error: " err))))

  (+ 1 4))
