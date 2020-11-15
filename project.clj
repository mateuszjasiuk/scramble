(defproject scramble "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.2"]
                 [ring/ring-defaults "0.3.2"]
                 [ring/ring-json "0.3.1"]
                 [ring-cors "0.1.13"]
                 [org.clojure/clojurescript "1.10.764"]
                 [com.bhauman/figwheel-main "0.2.12"]
                 [com.bhauman/rebel-readline-cljs "0.1.4"]
                 [reagent "1.0.0-alpha2"]
                 [cljs-http "0.1.46"]
                 [org.eclipse.jetty/jetty-server "9.4.19.v20190610"]
                 [org.eclipse.jetty/jetty-util "9.4.19.v20190610"]
                 [org.eclipse.jetty.websocket/websocket-servlet "9.4.19.v20190610"]
                 [org.eclipse.jetty.websocket/websocket-server "9.4.19.v20190610"]]
  :plugins [[lein-ring "0.12.5"]
            [lein-cljsbuild "1.1.8"]]
  :resource-paths ["target" "resources"]
  :aliases {"fig" ["trampoline" "run" "-m" "figwheel.main"]
            "build-dev:client" ["trampoline" "run" "-m" "figwheel.main" "-b" "dev" "-r"]
            "build-dev:service" ["ring" "server-headless" "3003"]}
  :ring {:handler scramble.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.2"]]}}
  :source-paths ["src-cljs" "src"]
  :cljsbuild {:builds [{:source-paths ["src-cljs"]
                        :compiler {:output-to "resources/public/js/main.js"
                                   :output-dir "resources/public/js/out"
                                   :main "scramble.core"
                                   :asset-path "js/out"
                                   :optimizations :whitespace
                                   :pretty-print true}}]})