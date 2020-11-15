(ns scramble.handler
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.json :as ring-json]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.util.response :as rr]
            [scramble.logic :refer [scramble?]]))

(defroutes app-routes
  (GET "/" []
    (rr/content-type (rr/resource-response "index-static.html" {:root "public"}) "text/html"))
  (GET "/scramble" [str1 str2]
    (rr/response
     {:scramble? (scramble? str1 str2)}))
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      (wrap-defaults site-defaults)
      (ring-json/wrap-json-response)
      (wrap-cors
       :access-control-allow-origin [#".*"]
       :access-control-allow-methods [:get]
       :access-control-allow-credentials "true")))