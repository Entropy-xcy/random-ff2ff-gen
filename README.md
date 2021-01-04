# Random FF2FF Path Generator

This project is built to generate random Flip-Flop to Flip-Flop paths which is defined by some random path synthesised by yosys to yosys Intermediate Representation (IR).

## Supported Yosys IR Cell
Detailed Specifications about IR Cells can be found in `Yosys Basic Cells Table.pdf`
### Width Independent Cells
* $mux 
* $not
* $and
* $or
* $xor
* $logic_not
* $shl
* $shr
* $sshl
* $sshr

Total: 10 encodings

### Width Dependent Cells
* $reduce_and
    * 4, 8, 16, 32, 64
* $reduce_or
    * 4, 8, 16, 32, 64
* $reduce_xor
    * 4, 8, 16, 32, 64
* $lt 
    * 8, 16, 32, 64
* $le
    * 8, 16, 32, 64
* $eq 
    * 8, 16, 32, 64
* $ne 
    * 8, 16, 32, 64
* $ge 
    * 8, 16, 32, 64
* $gt 
    * 8, 16, 32, 64
* $add 
    * 8, 16, 32, 64
* $sub 
    * 8, 16, 32, 64
* $mul 
    * 8, 16, 32, 64
* $div 
    * 8, 16, 32, 64
* $mod 
    * 8, 16, 32, 64
* $memrd 
    * 32, 64
* $memwr
    * 32, 64

Total: 63

## Encodings
### Grouping
* $mux 
* $not
    * $not, $logical_not
* $and
* $or
* $xor
* $sh
    * $shl, $shr, $sshl, $sshr
* $reduce_and
    * 4, 8, 16, 32, 64
* $reduce_or
    * 4, 8, 16, 32, 64
* $reduce_xor
    * 4, 8, 16, 32, 64
* $eq
    * $eq, $ne
        * 8, 16, 32, 64
* $lgt
    * $lt, $le, $ge, $gt
        * 8, 16, 32, 64
* $add
    * $add, $sub
        * 8, 16, 32, 64
* $mul 
    * 8, 16, 32, 64
* $div 
    * 8, 16, 32, 64
* $mod 
    * 8, 16, 32, 64
* $mem
    * $memrd, $memwr
        * 32, 64

Total Encodings: 47

### In summary: 
* $mux 
* $not
* $and
* $or
* $xor
* $sh
* $reduce_and:[4, 8, 16, 32, 64]
* $reduce_or:[4, 8, 16, 32, 64]
* $reduce_xor:[4, 8, 16, 32, 64]
* $eq:[8, 16, 32, 64]
* $lgt:[8, 16, 32, 64]
* $add:[8, 16, 32, 64]
* $mul:[8, 16, 32, 64]
* $div:[8, 16, 32, 64]
* $mod:[8, 16, 32, 64]
* $mem:[32, 64]