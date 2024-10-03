# Additional clean files
cmake_minimum_required(VERSION 3.16)

if("${CONFIG}" STREQUAL "" OR "${CONFIG}" STREQUAL "Debug")
  file(REMOVE_RECURSE
  "TetrisGui\\CMakeFiles\\TetrisGui_autogen.dir\\AutogenUsed.txt"
  "TetrisGui\\CMakeFiles\\TetrisGui_autogen.dir\\ParseCache.txt"
  "TetrisGui\\TetrisGui_autogen"
  )
endif()
