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

Total: 10 encodings

### Width Dependent Cells
* $reduce_and
* $reduce_or
* $reduce_xor
* $lt 
* $le
* $eq 
* $ne 
* $ge 
* $gt 
* $add 
* $sub 
* $mul 
* $div 
* $mod 
* $memrd 
* $memwr
* $shl
* $shr
* $sshl
* $sshr

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
* $reduce_or
* $reduce_xor
* $eq
    * $eq, $ne
* $lgt
    * $lt, $le, $ge, $gt
* $add
    * $add, $sub
* $mul 
* $div 
* $mod 
* $memrd
* $memwr

Total Encodings: 47

### In summary: 
* $mux 
* $not
* $and
* $or
* $xor
* $sh:[4, 8, 16, 32, 64]
* $reduce_and:[4, 8, 16, 32, 64]
* $reduce_or:[4, 8, 16, 32, 64]
* $reduce_xor:[4, 8, 16, 32, 64]
* $eq:[8, 16, 32, 64]
* $lgt:[8, 16, 32, 64]
* $add:[8, 16, 32, 64]
* $mul:[8, 16, 32, 64]
* $div:[8, 16, 32, 64]
* $mod:[8, 16, 32, 64]
* $memrd:[32, 64]
* $memwr:[32, 64]
