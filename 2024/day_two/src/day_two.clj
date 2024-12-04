(ns day-two
  (:require [clojure.string :as str])
  (:gen-class))

(defn descending? [report]
  (= report (reverse (sort report))))

(defn ascending? [report]
  (= report (sort report)))

(defn differences [report]
  (map - (rest report) report))

(defn gradually? [report]
  (let [problems (->> (differences report)
                      (filter #(or (< % 1) (> % 3)))
                      (count))]
    (= 0 problems)))

(defn safe? [report]
  (and (or (descending? report) (ascending? report))
       (gradually? (sort report))))

(defn remove-level [report n]
  (concat (take n report) (drop (inc n) report)))

(defn dampened-safe? [report new-report n]
  (or (safe? new-report)
      (and (< n (count report))
           (recur report (remove-level report n) (inc n)))))

(defn line->report [line]
  (->> (str/split line #"\s+")
       (map Integer/parseInt)))

(defn read-file [filename]
  (->> (slurp (str (System/getProperty "user.dir") "/" filename))
       (str/split-lines)
       (map line->report)))

(defn safe-reports [filename]
  (->> (read-file filename)
       (filter safe?)
       count))

(defn dampened-safe-reports [filename]
  (->> (read-file filename)
       (filter #(dampened-safe? % % 0))
       count))

(defn -main [& args]
  (println "Hello world!"))

