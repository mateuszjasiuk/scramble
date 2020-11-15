(ns scramble.core
  (:require [reagent.dom :as r.dom]
            [reagent.core :as r]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]])
  (:require-macros [cljs.core.async.macros :refer [go]]))

(def connection "http://localhost:3003")

(def state {:str1 (r/atom "foo") :str2 (r/atom "bar") :scramble? (r/atom false)})

(defn get-scramble-conn [str1 str2]
  (str connection "/scramble?str1=" str1 "&str2=" str2))

(defn get-scramble []
  (go (let [response (<! (http/get (get-scramble-conn @(state :str1) @(state :str2))))
            body (:body response)
            scramble? (aget (js/JSON.parse body) "scramble?")]
        (reset! (state :scramble?) scramble?))))

(defn str-input [value]
  [:input {:type "text"
           :value @value
           :on-change #(reset! value (-> % .-target .-value))}])
  
(defn app []
    (fn []
      [:div
       [:p "Scramble?: " (str @(state :scramble?))]
       [:p "Str1: " [str-input (state :str1 )]]
       [:p "Str2: " [str-input (state :str2 )]]
       [:input {:type "button" :value "Click me!"
                :on-click get-scramble}]]))


(defn mount []
  (r.dom/render [app] (js/document.getElementById "root")))


(defn ^:after-load re-render []
  (mount))


(defonce start-up (do (mount) true))
