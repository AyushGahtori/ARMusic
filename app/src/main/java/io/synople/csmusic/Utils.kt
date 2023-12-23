package io.synople.csmusic

fun stringToRes(str: String): Int {
    when (str) {
        "c3i" -> return R.raw.c3i
        "c3q" -> return R.raw.c3q
        "c3h" -> return R.raw.c3h
        "c3w" -> return R.raw.c3w
        "c4i" -> return R.raw.c4i
        "c4q" -> return R.raw.c4i
        "c4h" -> return R.raw.c4h
        "c4w" -> return R.raw.c4w
        "c5i" -> return R.raw.c5i
        "c5q" -> return R.raw.c5q
        "c5h" -> return R.raw.c5h
        "c5w" -> return R.raw.c5w
        "c6i" -> return R.raw.c6i
        "c6q" -> return R.raw.c6q
        "c6h" -> return R.raw.c6h
        "c6w" -> return R.raw.c6w

        "d3i" -> return R.raw.d3i
        "d3q" -> return R.raw.d3q
        "d3h" -> return R.raw.d3h
        "d3w" -> return R.raw.d3w
        "d4i" -> return R.raw.d4i
        "d4q" -> return R.raw.d4i
        "d4h" -> return R.raw.d4h
        "d4w" -> return R.raw.d4w
        "d5i" -> return R.raw.d5i
        "d5q" -> return R.raw.d5q
        "d5h" -> return R.raw.d5h
        "d5w" -> return R.raw.d5w
        "d6i" -> return R.raw.d6i
        "d6q" -> return R.raw.d6q
        "d6h" -> return R.raw.d6h
        "d6w" -> return R.raw.d6w

        "e3i" -> return R.raw.e3i
        "e3q" -> return R.raw.e3q
        "e3h" -> return R.raw.e3h
        "e3w" -> return R.raw.e3w
        "e4i" -> return R.raw.e4i
        "e4q" -> return R.raw.e4i
        "e4h" -> return R.raw.e4h
        "e4w" -> return R.raw.e4w
        "e5i" -> return R.raw.e5i
        "e5q" -> return R.raw.e5q
        "e5h" -> return R.raw.e5h
        "e5w" -> return R.raw.e5w

        "f3i" -> return R.raw.f3i
        "f3q" -> return R.raw.f3q
        "f3h" -> return R.raw.f3h
        "f3w" -> return R.raw.f3w
        "f4i" -> return R.raw.f4i
        "f4q" -> return R.raw.f4i
        "f4h" -> return R.raw.f4h
        "f4w" -> return R.raw.f4w
        "f5i" -> return R.raw.f5i
        "f5q" -> return R.raw.f5q
        "f5h" -> return R.raw.f5h
        "f5w" -> return R.raw.f5w

        "g3i" -> return R.raw.g3i
        "g3q" -> return R.raw.g3q
        "g3h" -> return R.raw.g3h
        "g3w" -> return R.raw.g3w
        "g4i" -> return R.raw.g4i
        "g4q" -> return R.raw.g4i
        "g4h" -> return R.raw.g4h
        "g4w" -> return R.raw.g4w
        "g5i" -> return R.raw.g5i
        "g5q" -> return R.raw.g5q
        "g5h" -> return R.raw.g5h
        "g5w" -> return R.raw.g5w
        "g6i" -> return R.raw.g6i
        "g6q" -> return R.raw.g6q
        "g6h" -> return R.raw.g6h
        "g6w" -> return R.raw.g6w

        "a3i" -> return R.raw.a3i
        "a3q" -> return R.raw.a3q
        "a3h" -> return R.raw.a3h
        "a3w" -> return R.raw.a3w
        "a4i" -> return R.raw.a4i
        "a4q" -> return R.raw.a4i
        "a4h" -> return R.raw.a4h
        "a4w" -> return R.raw.a4w
        "a5i" -> return R.raw.a5i
        "a5q" -> return R.raw.a5q
        "a5h" -> return R.raw.a5h
        "a5w" -> return R.raw.a5w

        "b3i" -> return R.raw.b3i
        "b3q" -> return R.raw.b3q
        "b3h" -> return R.raw.b3h
        "b3w" -> return R.raw.b3w
        "b4i" -> return R.raw.b4i
        "b4q" -> return R.raw.b4i
        "b4h" -> return R.raw.b4h
        "b4w" -> return R.raw.b4w
        "b5i" -> return R.raw.b5i
        "b5q" -> return R.raw.b5q
        "b5h" -> return R.raw.b5h
        "b5w" -> return R.raw.b5w

        "c#3i" -> return R.raw.cs3i
        "c#3q" -> return R.raw.cs3q
        "c#3h" -> return R.raw.cs3h
        "c#3w" -> return R.raw.cs3w
        "c#4i" -> return R.raw.cs4i
        "c#4q" -> return R.raw.cs4i
        "c#4h" -> return R.raw.cs4h
        "c#4w" -> return R.raw.cs4w
        "c#5i" -> return R.raw.cs5i
        "c#5q" -> return R.raw.cs5q
        "c#5h" -> return R.raw.cs5h
        "c#5w" -> return R.raw.cs5w

        "f#3i" -> return R.raw.fs3i
        "f#3q" -> return R.raw.fs3q
        "f#3h" -> return R.raw.fs3h
        "f#3w" -> return R.raw.fs3w
        "f#4i" -> return R.raw.fs4i
        "f#4q" -> return R.raw.fs4i
        "f#4h" -> return R.raw.fs4h
        "f#4w" -> return R.raw.fs4w
        "f#5i" -> return R.raw.fs5i
        "f#5q" -> return R.raw.fs5q
        "f#5h" -> return R.raw.fs5h
        "f#5w" -> return R.raw.fs5w

        "rw" -> return R.raw.rw
        "rh" -> return R.raw.rh
        "rq" -> return R.raw.rq
        "ri" -> return R.raw.ri

        else -> { // Note the block
            throw Exception("No file found: $str")
        }
    }
}
