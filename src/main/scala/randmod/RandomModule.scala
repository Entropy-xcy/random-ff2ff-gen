// See README.md for license details.

package randmod

import chisel3._
import chisel3.util._
import chisel3.iotesters._


class RandomModule(sequence: Array[(String, Int, Int)]) extends Module {
  val max_width: Int = RandModConfig.max_bus_width
  val layer_height: Int = sequence.size
  val io = IO(new Bundle {
    val in          = Input(Bits(max_width.W))
    val level_in_b  = Input(Vec(sequence.size, Bits(max_width.W)))
    val level_in_s  = Input(Vec(sequence.size, Bits(1.W)))
    val out         = Output(Bits(max_width.W))
  })

  val random_path = Module(new RandomPath(sequence))
  val in_reg = Reg(Bits(max_width.W))
  val out_reg = Reg(Bits(max_width.W))

  random_path.io.level_in_b := io.level_in_b
  random_path.io.level_in_s := io.level_in_s

  in_reg := io.in
  random_path.io.in := in_reg
  out_reg := random_path.io.out
  io.out := out_reg
}

class RandomModulePeekPokeTester(c: RandomModule) extends PeekPokeTester(c)  {
    println("Hello World!")
}

object RandomModuleTest extends App{
  // (cell_type, input_width, output_width)
  val sequence = Array(("add", 8, 8), ("mul", 16, 32), ("add", 32, 32))
    chisel3.iotesters.Driver.execute(Array("--backend-name", "verilator"), () => new RandomModule(sequence)) 
    { c =>
      new RandomModulePeekPokeTester(c)
    }
}
