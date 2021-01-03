// See README.md for license details.

package randmod

import chisel3._
import chisel3.util._
import chisel3.iotesters._


class RandomPath(sequence: Array[(String, Int, Int)]) extends Module {
  val max_width: Int = RandModConfig.max_bus_width
  val layer_height: Int = sequence.size
  val io = IO(new Bundle {
    val in          = Input(Bits(max_width.W))
    val level_in_b  = Input(Vec(sequence.size, Bits(max_width.W)))
    val level_in_s  = Input(Vec(sequence.size, Bits(1.W)))
    val out         = Output(Bits(max_width.W))
  })

  val level_out = Wire(Vec(sequence.size, Bits(max_width.W)))

  for(i <- 0 until sequence.size){
    // Prepare Inputs
    val cell_type = sequence(i)._1
    val in_width = sequence(i)._2
    val out_width = sequence(i)._3
    println(s"${cell_type} ${in_width} ${out_width}")

    // Define Cell Ports
    val cell_in_a = Wire(Bits(in_width.W)) // a is from last layer
    val cell_in_b = Wire(Bits(in_width.W))
    val cell_in_s = Wire(Bits(1.W))
    val cell_out = Wire(Bits(out_width.W))
    val last_layer_out = Wire(Bits(max_width.W))

    // Prepare Cell Inputs
    if(i == 0){
      // first layer
      last_layer_out := io.in
    } else{
      last_layer_out := level_out(i - 1)
    }
    cell_in_a := last_layer_out(in_width - 1, 0)
    cell_in_b := io.level_in_b(i)(in_width - 1, 0)
    cell_in_s := io.level_in_s(i)

    // Apply Cell Functionality
    if(cell_type == "add"){
      cell_out := cell_in_a + cell_in_b
    } else if(cell_type == "mul")
    {
      cell_out := cell_in_a * cell_in_b
    }
    
    // Write Back Results to Level out
    level_out(i) := Cat(last_layer_out(max_width - 1, out_width), cell_out)
  }

  io.out := level_out(layer_height - 1)
}

class RandomPathPeekPokeTester(c: RandomPath) extends PeekPokeTester(c)  {
    println("Hello World!")
}

object RandomPathTest extends App{
  // (cell_type, input_width, output_width)
  val sequence = Array(("add", 8, 8), ("mul", 16, 32), ("add", 32, 32))
    chisel3.iotesters.Driver.execute(Array("--backend-name", "verilator"), () => new RandomPath(sequence)) 
    { c =>
      new RandomPathPeekPokeTester(c)
    }
}
