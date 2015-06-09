(ns clojurl.handler
  (:use [clojurl.short :only (shorten lengthen urls)])
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(def form
  "<html>
  <head>
  </head>
  <body>
  <form method='get' action='shorten'>
  <input type='text' name='url' />
  <button type='submit'>Shorten!</button>
  </form>
  </body>
  </html>"
  )

(defn redirect [url]
  {:status 302 :headers {"Location" url}})

(defroutes app-routes
  (GET "/" [] form)

  ; TODO use POST instead of GET
  (GET "/shorten" [url]
       (str "http://localhost:3000/" (shorten url)))

  (GET "/list" [] (str @urls))

  ; TODO handle non-existence of url
  (GET "/:short-link" [short-link]
       (redirect (lengthen short-link)))

  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
