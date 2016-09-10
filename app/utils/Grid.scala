package utils

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

case class Coordinate(val x: Int, val y: Int)

class Cell {


}

class Grid(size: Int, radius: Int = 0, thickness: Int = 1) {
  var grid = ArrayBuffer.fill(size*size)(0)
  val rand = Random
  private val circleLeft  = ArrayBuffer.tabulate(size){ i => size/2 + i*(size-1) } toVector
  private val circleRight = ArrayBuffer.tabulate(size){ i => size/2 + i*(size+1) } toVector
  val circle = (circleLeft ++ circleRight).toSet.toVector

  setSeed(size/2 + size/2 * size)
  
  def printGrid = println(toString)
  
  override def toString = 
    grid.view.zipWithIndex.map { e => if((e._2+1)%size==0) e._1.toString + "\n" else e._1.toString } mkString(" ")
    
  def createWalkMonitorSeed = {
    val newSeed = getRandom(circle)
    moveToCenter(newSeed)  
  }
  
  private def getRandom(arr: Vector[Int]) = arr(rand.nextInt(arr.size))
  
  private def insideCircle(idx: Int): Boolean = {
    val l = circleLeft.toIterator
    val r = circleRight.toIterator
    r.zip(l).find {
      case (x1, x2) =>
        idx < x1 && idx > x2
    }
    .isDefined
  }
  
  private def getNearby(idx: Int) = Vector(idx+1, idx-1, idx - size, idx + size)
  
  private def getNewIdx(idx: Int) = {
    val possiblePositions = getNearby(idx) filter {insideCircle(_)}
    getRandom(possiblePositions)
  }
  
  private def isTouchingSeed(idx:Int) = {
    (getNearby(idx).toSet intersect (grid.zipWithIndex.filter(_._1==1).map(_._2)).toSet).size > 0
  }
  
  private def setSeed(idx:Int) =
    grid(idx) = 1

  private def moveToCenter(idx: Int) {
    val newIdx = getNewIdx(idx)
    if(isTouchingSeed(newIdx)) setSeed(newIdx)  
    else moveToCenter(newIdx)    
  }
  
}