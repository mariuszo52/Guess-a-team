package pl.mowebcreations.guesstheteam.data


enum class Position(val widthPercent: Int, val heightPercent: Int) {
    SN(50, 15),
    LN(35,20),
    PN(65,20),
    SPO(50, 35),
    LSP(35,50),
    PSP(65,50),
    LS(20, 30),
    PS(80, 30),
    SP(50, 50),
    SPD(50, 65),
    LDP(35,65),
    PDP(65,65),
    SO(50, 80),
    LSO(35, 80),
    PSO(65,80),
    LO(20, 70),
    PO(80,70),
    BR(50, 93)

}