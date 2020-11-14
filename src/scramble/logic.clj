(ns scramble.logic)

(defn scramble? [str1 str2]
  (let [str1map (frequencies str1)
        str2map (frequencies str2)]
    (if (empty? (apply dissoc str2map (keys str1map)))
      (reduce-kv
       (fn [acc key value]
         (and acc (<= (- value (get str1map key)) 0))) true str2map)
      false)))