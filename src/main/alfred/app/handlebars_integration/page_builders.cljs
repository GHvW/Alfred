(ns alfred.app.handlebars-integration.page-builders
  (:require
   ["handlebars" :as handlebars]))


(defn generate-html
  [template]
  (let [make-html (.compile handlebars template)]
    (fn [data]
      (make-html (clj->js data)))))

(comment

  ((generate-html "<h1>{{title}}</h1>") #js {:title "Hello World!"})

  (+ 2 4))
