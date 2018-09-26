class Upper{
    def Upper(string: String*):Seq[String] = {
        string.map((s:String) => s.toUpperCase())
    }
}

val p = new Upper
println(p.Upper("Hello","Wolrd"))