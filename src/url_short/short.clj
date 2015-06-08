(ns url-short.short)

(def urls
  (atom {}))

(defn store [short-url url]
  (swap! urls, assoc short-url url))

(def alphabet
  "_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789")

(defn rand-char []
  (nth alphabet
    (rand-int (count alphabet))))

(defn rand-str [length]
  (apply str (repeatedly length rand-char)))

(defn shorten [url]
  "Takes a url, saves it and returns the shortened value"
  (let [short-url (rand-str 7)]
    (store short-url url)
    short-url))

(defn lengthen [short-url]
  "Takes a shortened url and returns the original url"
  (@urls short-url))
