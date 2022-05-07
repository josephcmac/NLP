import io.input._
import io.output._
import helpers.helpers._

object Main extends App {

  outputNumber("output.txt", 
  (inputWords 
    andThen 
      dist)("input.txt")  
  )
}