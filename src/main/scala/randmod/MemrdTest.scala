// See README.md for license details.

package randmod

import chisel3._
import chisel3.util._
import chisel3.iotesters._


class Memrd extends Module {
  val max_width: Int = RandModConfig.max_bus_width
  val io = IO(new Bundle {
	val rd_addr     = Input(Bits(max_width.W))
	val wr_addr     = Input(Bits(max_width.W))
	val wr_data		= Input(Bits(max_width.W))
	val rd_data     = Output(Bits(max_width.W))
  })
	
	val width:Int = 32
	val enable = Input(Bool())
	val write = Input(Bool())
	val addr  = Input(UInt(10.W))
	val dataIn = Input(UInt(width.W))
	val dataOut = Output(UInt(width.W))


	val mem = SyncReadMem(2048, Bits(max_width.W))
	val rdPort = mem(io.rd_addr)
	val wrPort = mem(io.wr_addr)
	
	io.rd_data := rdPort
	wrPort := io.wr_data

	/*
	dataOut := DontCare
	when(enable) {
		val rdwrPort = mem(addr)
	when (write) { rdwrPort := dataIn }
	.otherwise { dataOut := rdwrPort }
	}
	*/

}

class MemrdPeekPokeTester(c: Memrd) extends PeekPokeTester(c)  {
	println("Hello World!")
}

object MemrdTest extends App{
  // (cell_type, input_width, output_width)
  val sequence = Array(("add", 8, 8), ("mul", 16, 32), ("add", 32, 32))
	chisel3.iotesters.Driver.execute(Array("--backend-name", "verilator"), () => new Memrd) 
	{ c =>
	  new MemrdPeekPokeTester(c)
	}
}
