// See README.md for license details.

package randmod

import chisel3._
import chisel3.util._
import chisel3.iotesters._

class RandomModule extends Module {
  val io = IO(new Bundle {
    val in        = Input(UInt(16.W))
    val out       = Output(UInt(16.W))
  })

  io.out := io.in
}

class RandomModulePeekPokeTester(c: RandomModule) extends PeekPokeTester(c)  {
    println("Hello World!")
}

object RandomModuleTest extends App{
    chisel3.iotesters.Driver.execute(Array("--backend-name", "verilator"), () => new RandomModule) { c =>
      new RandomModulePeekPokeTester(c)
    }
}
