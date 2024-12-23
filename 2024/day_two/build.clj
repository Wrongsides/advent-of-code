(ns build
  (:require [clojure.tools.build.api :as b]))

(def class-dir "build/classes")

;; delay to defer side effects (artifact downloads)
(def basis (delay (b/create-basis {:project "deps.edn"})))

(defn clean [_]
  (b/delete {:path "build"}))

(defn uber [_]
  (clean nil)
  (b/copy-dir {:src-dirs ["src" "resources"]
               :target-dir class-dir})
  (b/compile-clj {:basis @basis
                  :ns-compile '[day-two]
                  :class-dir class-dir})
  (b/uber {:class-dir class-dir
           :uber-file "build/app.jar"
           :basis @basis
           :main 'day-two}))

