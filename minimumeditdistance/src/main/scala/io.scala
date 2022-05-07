package io

import scala.io.Source.fromFile
import java.io.File
import java.io.FileWriter
import java.io.BufferedWriter

package object input {
    val inputWords: String => (String, String) = filename => {
        val l = fromFile(filename).getLines().toList
        (l(0), l(1))
    }
}

package object output {
    val outputNumber: (String, Int) => Unit = (filename, n) => {
        val file = new File(filename)
        val bw = new BufferedWriter( new FileWriter(file) )
        bw.write(n.toString)
        bw.close()
    }
}